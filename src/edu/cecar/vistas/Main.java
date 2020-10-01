package edu.cecar.vistas;

import edu.cecar.procedimientos.ManejoArchivo;
import java.util.Scanner;

/**
 *
 * @author Frank Atencio Loreth
 */
public class Main {

    public static void main(String[] args) {
        
        Scanner e = new Scanner(System.in);        
        Scanner e2 = new Scanner(System.in); 
        Scanner e3 = new Scanner(System.in); 
        Scanner e4 = new Scanner(System.in); 
        int opc = 0, opc1=0;    
        long t1,t2,tt;
        String busq=null;
        
        System.out.println("Numero de procesadores: "+Runtime.getRuntime().availableProcessors());
        do{
            System.out.println(
                "MENÚ DE OPCIONES\n"+
                "1. Importe de ventas totales.\n"+
                "2. Importe de ventas por año (YYYY).\n"+
                "3. Importe de ventas por año (YYYY) y mes (MM).\n"+
                "4. Importe de ventas por año (YYYY) y trimestres (N).\n"+
                "5. Búsqueda.\n"+                
                "6. Salir.\n"+
                "Opción: "
            );
            
            try {
                opc = e.nextInt();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }            
            switch(opc){               
                case 1:                     
                    t1=System.currentTimeMillis();
                    System.out.println("1. Importe de ventas totales: ");
                    System.out.println("Total: $" + ManejoArchivo.obtener_importe_total());
                    t2=System.currentTimeMillis();
                    tt=(t2-t1)/1000;
                    System.out.println("Tiempo: " + tt);
                break;
                case 2:
                    t1=System.currentTimeMillis();
                    System.out.println("2. Importe de ventas por año (YYYY): ");
                    ManejoArchivo.obtener_importe_ano();
                    t2=System.currentTimeMillis();
                    tt=(t2-t1)/1000;
                    System.out.println("Tiempo: " + tt);
                break;
                case 3:
                    t1=System.currentTimeMillis();
                    System.out.println("3. Importe de ventas por año (YYYY) y mes (MM): ");
                    ManejoArchivo.obtener_importe_ano_mes();
                    t2=System.currentTimeMillis();
                    tt=(t2-t1)/1000;
                    System.out.println("Tiempo: " + tt);
                break;
                case 4:
                    t1=System.currentTimeMillis();
                    System.out.println("4. Importe de ventas por año (YYYY) y trimestres (N): ");
                    ManejoArchivo.obtener_importe_ano_trim();
                    t2=System.currentTimeMillis();
                    tt=(t2-t1)/1000;
                    System.out.println("Tiempo: " + tt);
                break;
                case 5:                       
                    do{
                        System.out.println(
                           "MENU DE BUSQUEDA\n"+
                           "1. Buscar ventas por año.\n"+
                           "2. Buscar ventas por año y mes.\n"+
                           "3. Buscar ventas por año y trimestre.\n"+
                           "4. Salir de la búsqueda."
                        );
                        try {
                            opc1 = e2.nextInt();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        switch(opc1){
                            case 1:
                                System.out.println("Ingrese el año: ");                                
                                try {
                                    busq = e3.nextLine();                                    
                                    if(Integer.parseInt(busq)<0 || busq==null){
                                        System.out.println("Error de entrada");
                                        break;
                                    }else{
                                        ManejoArchivo.buscar_importe_ano(Integer.parseInt(busq));
                                    }                                    
                                } catch (NumberFormatException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            break;
                            case 2:
                                busq=null;
                                System.out.println("Ingrese el año y el mes (YYYY-MM): ");                                
                                try {
                                    busq=e4.nextLine();                                    
                                    if(busq.length()<0 || busq==null){
                                        System.out.println("Error de entrada");
                                        break;
                                    }else{
                                        ManejoArchivo.buscar_importe_ano_mes(busq);
                                    }                                    
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            break;
                            case 3:
                                busq=null;
                                System.out.println("Ingrese el año y el trimestre (YYYY-N): ");                                
                                try {
                                    busq=e4.nextLine();                                    
                                    if(busq.length()<0 || busq==null){
                                        System.out.println("Error de entrada");
                                        break;
                                    }else{
                                        ManejoArchivo.buscar_importe_ano_trimestre(busq);
                                    }                                    
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            break;
                        }
                    }while(opc1!=4);
                break;
                
                case 6:
                    System.out.println("Saliendo...");
                    System.exit(0);
                break;
                default:
                    System.out.println("Opción desconocida.");
                break;
            }                    
        }while(opc!=6);
    }    
}
