package edu.cecar.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class HiloImporteTotal extends Thread{
    private final String ruta_archivo;    
    private final String SEPARADOR_COMA=",";  
    private final String SEPARADOR_GUION="-";  
    private BigDecimal importe_total = BigDecimal.ZERO;
    private int acumulador=0;
    
    public HiloImporteTotal(String ruta_archivo) {
        this.ruta_archivo = ruta_archivo;
    }

    public  BigDecimal getImporte_total() {
        return importe_total;
    }

    public void setImporte_total(BigDecimal importe_total) {
        this.importe_total = importe_total;
    }

    @Override
    public void run(){       
        BufferedReader bfr = null;
        String lineas = null;
        BigDecimal importe=null;
        try {
            bfr = new BufferedReader(new FileReader(ruta_archivo));            
            while((lineas = bfr.readLine())!=null){
                String[] fila = lineas.split(SEPARADOR_COMA);    
                if(acumulador>0){
                    importe = new BigDecimal(fila[3]);
                    importe_total = importe_total.add(importe);                    
                }
                acumulador++;  
            }                                              
        } catch (IOException e) {
            System.out.println("Error al leer los archivos csv: " + e.getMessage());
        } finally{
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
