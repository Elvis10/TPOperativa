/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Matrix.Matrix_O;
import Matrix.ComparisonMatrix;
import Matrix.NormalMatrix;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Algorithms.AHP;
/**
 *
 * @author Guillermo
 */
public class Main {
    
   public static void main(String args[]) {
       
       
    Map<String,ComparisonMatrix> ENTRADA = new LinkedHashMap<>();
    
     List<String> items = new ArrayList<>();
     
     items.add("Costo");
     items.add("Confiabilidad");
     items.add("Plazo Entrega");
     
     Matrix_O matrizC = new ComparisonMatrix(items);
     matrizC.add("Costo","Costo",1.0);
     matrizC.add("Costo","Confiabilidad", 7.0);
     matrizC.add("Costo","Plazo Entrega",9.0);
     
     matrizC.add("Confiabilidad","Costo",0.1428);
     matrizC.add("Confiabilidad","Confiabilidad", 1.0);
     matrizC.add("Confiabilidad","Plazo Entrega",3.0);
     
     matrizC.add("Plazo Entrega","Costo",0.1111);    
     matrizC.add("Plazo Entrega","Confiabilidad",0.3333);
     matrizC.add("Plazo Entrega","Plazo Entrega",1.0);
     
     
     System.out.println("Matriz comparacion: ");
     matrizC.print();
     
     ENTRADA.put("CRITERIOS", (ComparisonMatrix) matrizC);
     
     // CREACION DE ALTERNATIVAS
     List<String> ALTERNATIVAS = new ArrayList<>();
     
     ALTERNATIVAS.add("X");
     ALTERNATIVAS.add("Y");
     ALTERNATIVAS.add("Z");
     
     
     /*
      Matriz COSTO
     */
     Matrix_O matrizCosto = new ComparisonMatrix(ALTERNATIVAS);
     matrizCosto.add("X","X",1.0);
     matrizCosto.add("X","Y",0.3333);
     matrizCosto.add("X","Z",6.0);
     
     matrizCosto.add("Y","X",3.0);
     matrizCosto.add("Y","Y", 1.0);
     matrizCosto.add("Y","Z",8.0);
     
     matrizCosto.add("Z","X",0.1666);
     matrizCosto.add("Z","Y", 0.125);
     matrizCosto.add("Z","Z",1.0);
     
     
     ENTRADA.put("Costo", (ComparisonMatrix) matrizCosto);
     
     System.out.println("Matriz comparacion COSTO: ");
     matrizCosto.print();
     /*
      Matriz CONFIABILIDAD
     */
     Matrix_O matrizConfiabilidad = new ComparisonMatrix(ALTERNATIVAS);
     matrizConfiabilidad.add("X","X",1.0);
     matrizConfiabilidad.add("X","Y",6.0);
     matrizConfiabilidad.add("X","Z",2.0);
     
     matrizConfiabilidad.add("Y","X",0.1666);
     matrizConfiabilidad.add("Y","Y", 1.0);
     matrizConfiabilidad.add("Y","Z",0.3333);
     
     matrizConfiabilidad.add("Z","X",0.5);
     matrizConfiabilidad.add("Z","Y", 3.0);
     matrizConfiabilidad.add("Z","Z",1.0);
     
     
     ENTRADA.put("Confiabilidad", (ComparisonMatrix) matrizConfiabilidad);
     
     System.out.println("Matriz comparacion CONFIABILIDAD: ");
     matrizConfiabilidad.print();
     
     
      /*
      Matriz PLAZO ENTREGA
     */
     Matrix_O matrizPlazo = new ComparisonMatrix(ALTERNATIVAS);
     matrizPlazo.add("X","X",1.0);
     matrizPlazo.add("X","Y",8.0);
     matrizPlazo.add("X","Z",1.0);
     
     matrizPlazo.add("Y","X",0.125);
     matrizPlazo.add("Y","Y", 1.0);
     matrizPlazo.add("Y","Z",0.125);
     
     matrizPlazo.add("Z","X",1.0);
     matrizPlazo.add("Z","Y", 8.0);
     matrizPlazo.add("Z","Z",1.0);
     
     
     ENTRADA.put("Plazo Entrega", (ComparisonMatrix) matrizPlazo);
     
     System.out.println("Matriz comparacion PLAZO ENTREGA: ");
     matrizPlazo.print();
   
     
     /*
     COMIENZO DEL ALGORITMO
     */
     
     AHP ALGORITMO = new AHP(items,ALTERNATIVAS,ENTRADA);
     
     ALGORITMO.startmethod();
     
     System.out.println("Vector de prioridad: ");
     ALGORITMO.printPriority();
     
    }
    
}
