package edu.cecar.modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class HiloImporteAnual extends Thread{
    private final String ruta_archivo;
    private final String SEPARADOR_COMA=",";
    private final String SEPARADOR_GUION="-";     
    private Map<Integer,BigDecimal> auxliar=new HashMap<>();
    int acumulador=0;        
        
    public HiloImporteAnual(String ruta_archivo) {
        this.ruta_archivo = ruta_archivo;
    }        

    public Map<Integer, BigDecimal> getAuxliar() {
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
                    String[] ano = fila[2].split(SEPARADOR_GUION);
                    int aux_ano = Integer.parseInt(ano[0]);                    
                    if(auxliar.get(aux_ano)==null){                        
                        BigDecimal b  = new BigDecimal(fila[3]);                        
                        auxliar.put(aux_ano, b);                         
                    }else{                                               
                       BigDecimal actual = auxliar.get(aux_ano).add(new BigDecimal(fila[3]));
                       auxliar.put(aux_ano, actual);                                                 
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
