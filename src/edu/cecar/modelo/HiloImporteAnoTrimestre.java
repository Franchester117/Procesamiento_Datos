package edu.cecar.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class HiloImporteAnoTrimestre extends Thread{
    private final String ruta_archivo;
    private final String SEPARADOR_COMA=",";
    private final String SEPARADOR_GUION="-";     
    private Map<String,BigDecimal> auxliar=new HashMap<>();
    int acumulador=0;     
    
    public HiloImporteAnoTrimestre(String ruta_archivo) {
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
                    String[] aux_ano_trim = fila[2].split(SEPARADOR_GUION); 
                    String ano=aux_ano_trim[0];
                    String trim=aux_ano_trim[1];                   
                    switch(trim){
                        case "01":
                            if(auxliar.get(ano.concat("-01"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-01"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-01")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-01"), actual);                                                 
                            }
                        break;
                        case "02":
                            if(auxliar.get(ano.concat("-01"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-01"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-01")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-01"), actual);                                                 
                            }
                        break;
                        case "03":
                            if(auxliar.get(ano.concat("-01"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-01"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-01")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-01"), actual);                                                 
                            }
                        break;
                        case "04":
                            if(auxliar.get(ano.concat("-02"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-02"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-02")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-02"), actual);                                                 
                            }
                        break;
                        case "05":
                            if(auxliar.get(ano.concat("-02"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-02"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-02")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-02"), actual);                                                 
                            }
                        break;
                        case "06":
                            if(auxliar.get(ano.concat("-02"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-02"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-02")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-02"), actual);                                                 
                            }
                        break;
                        case "07":
                            if(auxliar.get(ano.concat("-03"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-03"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-03")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-03"), actual);                                                 
                            }
                        break;
                        case "08":
                            if(auxliar.get(ano.concat("-03"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-03"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-03")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-03"), actual);                                                 
                            }
                        break;
                        case "09":
                            if(auxliar.get(ano.concat("-03"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-03"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-03")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-03"), actual);                                                 
                            }
                        break;
                        case "10":
                            if(auxliar.get(ano.concat("-04"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-04"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-04")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-04"), actual);                                                 
                            }
                        break;
                        case "11":
                            if(auxliar.get(ano.concat("-04"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-04"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-04")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-04"), actual);                                                 
                            }
                        break;
                        case "12":
                            if(auxliar.get(ano.concat("-04"))==null){                        
                                BigDecimal b  = new BigDecimal(fila[3]);                        
                                auxliar.put(ano.concat("-04"), b);                         
                            }else{                                               
                               BigDecimal actual = auxliar.get(ano.concat("-04")).add(new BigDecimal(fila[3]));
                               auxliar.put(ano.concat("-04"), actual);                                                 
                            }
                        break;
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
