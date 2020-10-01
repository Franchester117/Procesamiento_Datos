package edu.cecar.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class HiloImporteAnualMensual extends Thread{
    private final String ruta_archivo;
    private final String SEPARADOR_COMA=",";
    private final String SEPARADOR_GUION="-";     
    private Map<String,BigDecimal> auxliar=new HashMap<>();
    int acumulador=0; 
            
    public HiloImporteAnualMensual(String ruta_archivo) {
        this.ruta_archivo = ruta_archivo;
    }        

    public Map<String, BigDecimal> getAuxliar() {
        return auxliar;
    }   
    
   
    @Override
    public void run(){         
        BufferedReader bfr = null;
        String lineas = null;
        try {
            bfr = new BufferedReader(new FileReader(ruta_archivo));            
            while((lineas = bfr.readLine())!=null){                                 
                if(acumulador>0){                                   
                    String[] fila = lineas.split(SEPARADOR_COMA);    
                    String[] aux_ano_mes = fila[2].split(SEPARADOR_GUION);
                    String ano_mes = aux_ano_mes[0].concat(SEPARADOR_GUION).concat(aux_ano_mes[1]);
                    if(auxliar.get(ano_mes)==null){                        
                        BigDecimal b  = new BigDecimal(fila[3]);                        
                        auxliar.put(ano_mes, b);                         
                    }else{                                               
                       BigDecimal actual = auxliar.get(ano_mes).add(new BigDecimal(fila[3]));
                       auxliar.put(ano_mes, actual);                                                 
                    }
                }
                acumulador++;                  
            }                                               
        } catch (IOException e) {
            System.out.println("Error al leer los archivos csv: " + e.getMessage());
        }finally{
            if(bfr!=null){
                try {
                    bfr.close(); 
                } catch (IOException e) {
                    System.out.println("Error al cerrar el flujo: " + e.getMessage());
                }
            }
        }
    }
}
