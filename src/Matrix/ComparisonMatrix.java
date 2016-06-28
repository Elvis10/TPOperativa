/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Guillermo
 */
public class ComparisonMatrix  extends Matrix_O
{

    public ComparisonMatrix(List<String> items) 
    {
        super(items);
    }
    
    public List<Double> getColum(String colum)
    {
        List<Double> valuesColum = new ArrayList<>();
        for(String row: this.matrix.keySet()){
            Map<String,Double> colums = this.matrix.get(row);
            valuesColum.add(colums.get(colum));
        }
        return valuesColum;
    }
    
}
