/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
/**
 *
 * @author Guillermo
 */
public class Matrix_O
{
    
    protected Map<String, Map<String,Double> > matrix = new LinkedHashMap<>(); // representacion de la matriz, accedida por clases hijas
    protected List<String> items;
    
    public Matrix_O(List<String> items) //recibe una lista de los items que componen la matriz, al ser simetrica, van a tener las mismas filas que columnas
    {
        this.items = items;
        
        for(String row: items)
        {
            Map<String,Double> pos = new LinkedHashMap<>();
            for(String colum: items)
            {
               
               if(colum == row) 
               {
                   pos.put(colum,1.0); // si son iguales, poseen la misma influencia
               }
               else
               {
                   pos.put(colum,0.0); //inicializo los valores en 0(cero)
               }
               
            }
            
            this.matrix.put(row, pos);
        }
    }
    
    public void add(String row,String colum,Double value)
    {
        Map<String,Double> ref = this.matrix.get(row);
        
        ref.replace(colum, value);
        
        this.matrix.replace(row, ref);
    }
    
    public double get(String row,String colum)
    {
        return this.matrix.get(row).get(colum);
    }
 
    public List<String> getItems(){
        List<String> aux = this.items;
        return aux;
    }
    
    public int getSize()
    {
        return this.items.size();
    }
    
    /*
    Metodo para impresion de la matriz, luego se borra!! culichupao
    */
    public void print()
    {
        
        for(String row: this.items)
        {
            for(String colum: this.items)
            {
               System.out.print(this.get(row, colum)+ "  ");
            
            }
            System.out.println("");
            
        }
    }
}
