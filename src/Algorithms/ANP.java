/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Jama.Matrix;
import Matrix.ComparisonMatrix;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Matrix.Matrix_O;
import static java.lang.Math.abs;


/**
 *
 * @author Guillermo
 */
public class ANP extends AHP
{
    /*
    Entrada
    */
    private Map<String,List<String> > clustersNames = new LinkedHashMap<>(); 
    private List<String> alternatives = new ArrayList<>();
    private List<Double> principalVectorClustersAlter;
    
    /*
    SuperMatriz Original
    */
    private Map<String,Map<String,Matrix_O> > superMatrixOriginal = new LinkedHashMap<>(); // Esta estructura incluye como cluster, tambien a las alternativas.
    private int size;
    private Matrix mat;

    
    
    
    private static double convergenceIndex = 0.00000001; 
    
    public ANP(List<String> c, List<String> a, Map<String, ComparisonMatrix> matrixComparison,Map<String,Map<String,Matrix_O> > superMO)
    {
        super(c, a, matrixComparison);
        this.superMatrixOriginal = superMO;
        
    }
    
    
    
    
    public void startmethod()
    {
       /*
        Solicitar influencias y pesos. // ME LOS DA LA VISTA!!!!!
        */ 
        
        /*
         Obtener supermatriz Estocastica o Ponderada
        */
        int nro_colum = 0;
        for(String Ccolum: this.superMatrixOriginal.keySet())
        {
            
            for(String Crow: this.superMatrixOriginal.keySet())
            {
                for(String e_colum : this.clustersNames.get(Ccolum))
                {
                    if(is_weigh(Ccolum,e_colum))
                    {
                        weigh(Ccolum,e_colum,this.principalVectorClustersAlter.get(nro_colum));
                    }
                }
                
            }
            nro_colum++;
        }
        
        
        /*
        Obtener supermatrix Limite : setear vector de prioridades entre alternativas, y entre elementos de cada cluster.
        */
        Matrix superMatrixLimit = this.generateMatrix_JAMA();
       
        int row = 0;
        for(String c_row: this.superMatrixOriginal.keySet())
        {
                Matrix_O aux = this.superMatrixOriginal.get(c_row).get(c_row);
                for(String e_name: aux.getItems() )
                {
                    this.vectorPrincipal.put(e_name,superMatrixLimit.get(row,1));
                    row++;
                }
        }
        
        this.printPriority();
        
    }
    
    public Matrix generateMatrix_JAMA()
    {
        this.size = 0;
        for(String c_colum: this.superMatrixOriginal.keySet())
        {
           Matrix_O aux = this.superMatrixOriginal.get(c_colum).get(c_colum);
           size += aux.getSize();
        }
        /*
        Retorna la matriz resultado
        */
        this.mat = new Matrix(size,size);
        
        int colum = 0;
        int row = 0;
        for(String c_row: this.superMatrixOriginal.keySet())
        {
            for(String c_colum: this.superMatrixOriginal.keySet())
            {
                Matrix_O aux = this.superMatrixOriginal.get(c_row).get(c_colum); // obtengo la correspondiente matriz del cluster Ci/j
                for(String e_row: aux.getItems()) // recorro esa matriz fila a fila, con respecto a la columna en cuestion
                {
                    
                    for(String e_col:aux.getItems())
                    {
                     mat.set(row, colum,aux.get(e_row, c_colum));
                     colum++;   
                    }
                    row++;
                } 
                row =0;
            }
            colum = 0;
                       
        }
        
        return mat;
        
    }
    public Matrix generateSuperMatrixLimit_JAMA()
    {
        Matrix mat_ant = new Matrix(size,size,1);
        
        Matrix mat_act = this.mat.times(this.mat);
        while(this.converges(mat_act, mat_ant))
        {
           mat_ant = mat_act;
           mat_act.times(mat_act);
        }
        
        mat_act.print(null, size);
        return mat_act;
    }
    
    /*
    Retorna el valor de verdad de la convergencia del algoritmo
    */
    public Boolean converges(Matrix act,Matrix ant) 
    {
        
        for(int j =0 ;j < act.getRowDimension();j++)
        {
            for(int i=0; i < act.getColumnDimension();i++)
            {
                if(abs(act.get(i, j) - ant.get(i, j)) > this.convergenceIndex)
                {
                    return false;
                }
            }
        }
        
        return true;
    }

    
    /*
    Retorna verdadero si debo ponderar una columna determinada de la SuperMatriz original
    */
    private boolean is_weigh(String c_colum,String e_colum) {
        int number = 0;
    
    
        for(String c_row: this.superMatrixOriginal.keySet())
        {
            
            Matrix_O aux = this.superMatrixOriginal.get(c_row).get(c_colum); // obtengo la correspondiente matriz del cluster Ci/j
            for(String e_row: aux.getItems()) // recorro esa matriz fila a fila, con respecto a la columna en cuestion
            {
                 if(aux.get(e_row, e_colum) != 0)
                 {
                     number++;
                     break;
                 }                
            }
             
            if(number > 1)
            {
                return true;
            }
                 
        }
        
        return false;
    }

    /*
    Pondera una determinada columna de la SuperMatriz Original
    */
    private void weigh(String Ccolum,String e_colum,Double p_weigh)
    {
        for(String c_row: this.superMatrixOriginal.keySet())
        {
            
            Matrix_O aux = this.superMatrixOriginal.get(c_row).get(Ccolum); // obtengo la correspondiente matriz del cluster Ci/j
            for(String e_row: aux.getItems()) // recorro esa matriz fila a fila, con respecto a la columna en cuestion
            {
                 aux.add(e_row, e_colum, p_weigh*aux.get(e_row, e_colum));
            }            
        }
    }

    
}