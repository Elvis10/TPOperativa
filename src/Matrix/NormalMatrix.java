package Matrix;
import java.util.List;
import Matrix.ComparisonMatrix;
import Matrix.Matrix_O;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Guillermo
 */
public class NormalMatrix extends Matrix_O 
{

    public NormalMatrix(List<String> items,ComparisonMatrix matrixC) 
    {
        super(items);
        this.normalize(matrixC);
    }
    
    /*
    Genera la matriz normalizada, a partir de la matriz de comparacion pareada
    */
    private void normalize(ComparisonMatrix matrixC)
    {
        
         for(String row : this.items)
         {
             Map<String,Double> pos = new LinkedHashMap<>();
             for(String colum: this.items)
             {  
                 pos.put(colum,matrixC.get(row, colum)/this.totalAmountColum(matrixC.getColum(colum)));  
             }
             this.matrix.replace(row, pos);
             

         }
    }
    /*
    Retorna la suma total de los valores de una columna
    */
    private double totalAmountColum(List<Double> colum)
    {
        double total = 0;
        
        for(Double value: colum)
        {
            total += value;
        }
        
        return total;
    }
    
    /*
    Retorna la suma total de los valores de una fila
    */
    
    private double totalAmountRow(Map<String,Double> row)
    {
        double total = 0;
        
        for(Double value: row.values())
        {
            total += value;
        }
        
        return total;
    }
    
    
    /*
    Obtengo el autoVector Principal
    */
    public List<Double> getPrincipalVector()
    {
        List<Double> vector = new ArrayList<>();
        
        int total_row = this.getSize();
        
        for(String row: this.items)
        {
            vector.add( (this.totalAmountRow(this.matrix.get(row)))/ total_row);
        }
        
        return vector;    
    }
    
    
}
