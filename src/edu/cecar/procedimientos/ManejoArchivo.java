package edu.cecar.procedimientos;

import edu.cecar.modelo.HiloImporteAnoTrimestre;
import edu.cecar.modelo.HiloImporteAnual;
import edu.cecar.modelo.HiloImporteAnualMensual;
import edu.cecar.modelo.HiloImporteTotal;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ManejoArchivo {        
    private static final String PATH_RECURSOS = System.getProperty("user.dir").concat("/resources/"); //Obtiene la dirección del proyecto y de los archivos csv    
    private static final File  carpeta = new File(PATH_RECURSOS); //Se crea un directorio con la ruta     
    private static final File[] lista_archivos = carpeta.listFiles(); //Se listan los archivos del directorio
    private static HiloImporteTotal hilo_imp_total;
    private static HiloImporteAnual hilo_imp_anual;     
    private static HiloImporteAnualMensual hilo_imp_anual_mensual; 
    private static HiloImporteAnoTrimestre hilo_imp_anual_trimestre;
    private static ArrayList<HiloImporteTotal> arr_imp_total = new ArrayList<>(); //Array del importe total
    private static ArrayList<HiloImporteAnual> arr_imp_anual = new ArrayList<>(); //Array del importe anual      
    private static ArrayList<HiloImporteAnualMensual> arr_imp_anual_mensual = new ArrayList<>();  //Array del importe anual y mensual
    private static ArrayList<HiloImporteAnoTrimestre> arr_imp_anual_trim = new ArrayList<>();  //Array del importe anual y trimestre    
    private static Map<Integer, BigDecimal> importe_anual = new TreeMap<>();//Resultado del importe anual.
    private static Map<String, BigDecimal> importe_anual_mensual = new TreeMap<>();//Resultado del importe anual y mensual.
    private static Map<String, BigDecimal> importe_anual_trimestral = new TreeMap<>();//Resultado del importe anual y trimestral.
    private static BigDecimal importe_Total;//Resultado del importe total.
    private static int numero_nucleos; //Cantidad de núcleos.
    
    /**
     * Calculos de los importes.
     * @return 
    **/
    @SuppressWarnings("empty-statement")
    public static BigDecimal obtener_importe_total(){
        importe_Total = BigDecimal.ZERO;
        int acumulador = 0;
        numero_nucleos = Runtime.getRuntime().availableProcessors();                        
        if(lista_archivos == null || lista_archivos.length == 0){
            System.out.println("El directorio no contiene los archivos necesarios.");            
        }else{            
            for (int i=0; i<lista_archivos.length; i++) {
                hilo_imp_total = new HiloImporteTotal(lista_archivos[i].getPath());
                arr_imp_total.add(hilo_imp_total);
            }
            for(int i=0; i<arr_imp_total.size(); ++i){
                try {
                    if(acumulador < numero_nucleos) arr_imp_total.get(i).start();                                    
                    else arr_imp_total.get(i).start();
                    acumulador++; 
                    while(arr_imp_total.get(i).isAlive());
                    importe_Total = importe_Total.add(arr_imp_total.get(i).getImporte_total());
                } catch (Exception e) { 
                    System.out.println("Error: "+e.getMessage());
                    break;
                } 
            }                                  
        }                
        return importe_Total;
    }
    
    /**
     * Calcular importe por año.
    **/
    @SuppressWarnings("empty-statement")
    public static void obtener_importe_ano(){       
        int acumulador = 0;        
        numero_nucleos = Runtime.getRuntime().availableProcessors();                
        if(lista_archivos == null || lista_archivos.length == 0){
            System.out.println("El directorio no contiene los archivos necesarios.");            
        }else{            
            for (int i=0; i<lista_archivos.length; i++) {
                hilo_imp_anual = new HiloImporteAnual(lista_archivos[i].getPath());
                arr_imp_anual.add(hilo_imp_anual);
            }
            for (HiloImporteAnual lectura : arr_imp_anual) {
                try {
                    if(acumulador < numero_nucleos) lectura.start();                                    
                    else lectura.start();                
                    acumulador++; 
                    while(lectura.isAlive());
                    lectura.getAuxliar().keySet().forEach((i) -> {
                        if(importe_anual.containsKey(i)){
                            BigDecimal imp_aux = importe_anual.get(i).add(lectura.getAuxliar().get(i)); importe_anual.put(i, imp_aux);
                        }else{
                            BigDecimal imp_aux = lectura.getAuxliar().get(i); importe_anual.put(i, imp_aux);
                        }
                    });                                        
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                    break;
                }                
            }                                                         
            importe_anual.entrySet().forEach((entry) -> {
                System.out.println("Año: "+entry.getKey()+" = "+entry.getValue());
            });            
        }
    }
    
    /**
     * Calcular importe por año y mes.
    **/
    @SuppressWarnings("empty-statement")
    public static void obtener_importe_ano_mes(){
        int acumulador = 0;        
        numero_nucleos = Runtime.getRuntime().availableProcessors();                
        if(lista_archivos == null || lista_archivos.length == 0){
            System.out.println("El directorio no contiene los archivos necesarios.");            
        }else{            
            for (int i=0; i<lista_archivos.length; i++) {
                hilo_imp_anual_mensual = new HiloImporteAnualMensual(lista_archivos[i].getPath());
                arr_imp_anual_mensual.add(hilo_imp_anual_mensual);
            }
            for (HiloImporteAnualMensual lectura : arr_imp_anual_mensual) {
                try {
                    if(acumulador < numero_nucleos) lectura.start();                                    
                    else lectura.start();                
                    acumulador++; 
                    while(lectura.isAlive());
                    lectura.getAuxliar().keySet().forEach((i) -> {
                        if(importe_anual_mensual.containsKey(i)){
                            BigDecimal imp_aux = importe_anual_mensual.get(i).add(lectura.getAuxliar().get(i)); importe_anual_mensual.put(i, imp_aux);
                        }else{
                            BigDecimal imp_aux = lectura.getAuxliar().get(i); importe_anual_mensual.put(i, imp_aux);
                        }
                    });                                        
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                    break;
                }                
            }                                                         
            importe_anual_mensual.entrySet().forEach((entry) -> {
                System.out.println("Año-Mes: "+entry.getKey()+" = $"+entry.getValue());
            });            
        }
    }
    
    /**
     * Calcular importe por año y mes.
    **/
    @SuppressWarnings("empty-statement")
    public static void obtener_importe_ano_trim(){
        int acumulador = 0;        
        numero_nucleos = Runtime.getRuntime().availableProcessors();                
        if(lista_archivos == null || lista_archivos.length == 0){
            System.out.println("El directorio no contiene los archivos necesarios.");            
        }else{            
            for (int i=0; i<lista_archivos.length; i++) {
                hilo_imp_anual_trimestre = new HiloImporteAnoTrimestre(lista_archivos[i].getPath());
                arr_imp_anual_trim.add(hilo_imp_anual_trimestre);
            }
            for (HiloImporteAnoTrimestre lectura : arr_imp_anual_trim) {
                try {
                    if(acumulador < numero_nucleos) lectura.start();                                    
                    else lectura.start();                
                    acumulador++; 
                    while(lectura.isAlive());
                    lectura.getAuxliar().keySet().forEach((i) -> {
                        if(importe_anual_trimestral.containsKey(i)){
                            BigDecimal imp_aux = importe_anual_trimestral.get(i).add(lectura.getAuxliar().get(i)); importe_anual_trimestral.put(i, imp_aux);
                        }else{
                            BigDecimal imp_aux = lectura.getAuxliar().get(i); importe_anual_trimestral.put(i, imp_aux);
                        }
                    });                                        
                } catch (Exception e) {
                    System.out.println("Error: "+e.getMessage());
                    break;
                }                
            }                                                         
            importe_anual_trimestral.entrySet().forEach((entry) -> {
                System.out.println("Año-Trim: "+entry.getKey()+" = "+entry.getValue());
            });            
        }
    }
            
    /**
    * Buscar importe por año.
     * @param busq     
    */
    public static void buscar_importe_ano(int busq){
        Map<Integer, BigDecimal> result = importe_anual.entrySet() 
          .stream() 
          .filter(map -> map.getKey()==busq) 
          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));        
        if(result.isEmpty()) System.out.println("Resultado no encontrado");  
        else System.out.println("Importe " + result);
    }      
    
    /**
    * Buscar importe por año y mes.
     * @param busq     
    */
    public static void buscar_importe_ano_mes(String busq){
        Map<String, BigDecimal> result = importe_anual_mensual.entrySet() 
          .stream() 
          .filter(map -> map.getKey().equals(busq))
          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));         
        if(result.isEmpty()) System.out.println("Resultado no encontrado");  
        else System.out.println("Importe " + result);
    }
    
    /**
    * Buscar importe por año y trimestre.
     * @param busq     
    */
    public static void buscar_importe_ano_trimestre(String busq){
        Map<String, BigDecimal> result = importe_anual_trimestral.entrySet() 
          .stream() 
          .filter(map -> map.getKey().equals(busq))
          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));         
        if(result.isEmpty()) System.out.println("Resultado no encontrado");  
        else System.out.println("Importe " + result);
    }
}    
