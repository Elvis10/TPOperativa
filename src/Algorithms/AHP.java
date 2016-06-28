/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;
import Matrix.ComparisonMatrix;
import Matrix.NormalMatrix;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import Algorithms.Consistency;
/**
 *
 * @author Guillermo
 */
public class AHP 
{
    
    private List<String> criteria = new ArrayList<>();
    protected List<String> alternatives = new ArrayList<>();
    
    protected Map<String,ComparisonMatrix> mComparison = new LinkedHashMap<>();
    protected Map<String,NormalMatrix> mNormalize = new LinkedHashMap<>();
    
    protected List<Double> principalVectorCriteria = new ArrayList<>();
    
    protected Map<String,List<Double> > principalVectorsCriterionAlternatives = new LinkedHashMap<>();
    
    
    protected Map<String,Double> vectorPrincipal = new LinkedHashMap<>();

    
    
    /** ANALIZADOR DE INCONSISTENCIAS**/
    private Consistency analizer;
    
    
    
    /*
    El primer elemento del Map es la matriz de comparacion pareada con respecto a los criterios (utilizo clave = "CRITERIOS"), los siguientes corresponden a cada una de las alternativas en relacion a un determiando criterio
    */
    public AHP(List<String> c,List<String> a,Map<String,ComparisonMatrix> matrixComparison)
    {
        this.mComparison = matrixComparison;
        this.criteria = c;
        this.alternatives = a;
        
        
        
        /*
        INICIALIZO EL ANALIZADOR
        */
        this.analizer = new Consistency();
    }
    
    public void startmethod()
    {
        
        /*
        ANALISIS DE LA CONSISTENCIA
        */
        
        Boolean consistency = this.analizer.analizar(this.mComparison.get("CRITERIOS"));
 
        for(int i =0;i < this.criteria.size();i++)
        {
           consistency = consistency && this.analizer.analizar(this.mComparison.get(this.criteria.get(i)));      
        }
        
        
        
        if(consistency){
            /*
            Genero el vector principal de criterios
            */
            NormalMatrix nC = new NormalMatrix(this.criteria,this.mComparison.get("CRITERIOS"));
            this.mNormalize.put("CRITERIOS",nC);
            this.principalVectorCriteria = (List<Double>) (nC.getPrincipalVector());


            /*
            Genero los vectores principales correspondientes a la relacion Alternativa/Criterio
            */
            for(String criterion: this.criteria)
            {
                NormalMatrix n = new NormalMatrix(this.alternatives,this.mComparison.get(criterion));
                this.mNormalize.put(criterion,n);
                this.principalVectorsCriterionAlternatives.put(criterion,n.getPrincipalVector());     
            }



            /*
            Genero el Vector Global
            */

            for(int i = 0; i < this.alternatives.size(); i++)
            {   
                double priority = 0;
                List<Double> values_alternative = this.getValuesCriterio(i);
                for(int j = 0; j < this.principalVectorCriteria.size();j++)
                {
                    priority += values_alternative.get(j)*this.principalVectorCriteria.get(j);
                }
                this.vectorPrincipal.put(this.alternatives.get(i), priority);
            }
       
        }
        else
        {
            System.out.println("LOS DATOS INGRESADOS NO SON CONSISTENTES");
        }
   
 }
    
    private List<Double> getValuesCriterio(int posAlternative)
    {
        List<Double> values = new ArrayList<>();
        
        for(String criterion: this.principalVectorsCriterionAlternatives.keySet())
        {
            values.add(this.principalVectorsCriterionAlternatives.get(criterion).get(posAlternative));
        }
        
        return values;
    }
    
    
    /*
    getters y seters de atributos privados, para cuando usemos el MODO SECUENCIAL
    */
    public List<String> getCriteria() {
        return criteria;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public Map<String, ComparisonMatrix> getmComparison() {
        return mComparison;
    }

    public Map<String, NormalMatrix> getmNormalize() {
        return mNormalize;
    }

    public List<Double> getPrincipalVectorCriteria() {
        return principalVectorCriteria;
    }

    public Map<String, List<Double>> getPrincipalVectorsCriterionAlternatives() {
        return principalVectorsCriterionAlternatives;
    }

    public Map<String, Double> getVectorPrincipal() {
        return vectorPrincipal;
    }
    
    
    /*
    Metodo para imprimir la lista de prioridad
    */
    
    public void printPriority()
    {
        for(String a: this.vectorPrincipal.keySet())
        {
            System.out.println(a+": "+this.vectorPrincipal.get(a));
        }
    }

    
}
