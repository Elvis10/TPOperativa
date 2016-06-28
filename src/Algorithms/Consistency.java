package Algorithms;

import java.util.List;
import java.util.ArrayList;
import Matrix.ComparisonMatrix;
import Matrix.NormalMatrix;
import java.util.Map;
import java.util.HashMap;


public class Consistency {
	private static final double overallValue = 0.1;
	private boolean consistent;
	private List<Double> values; // Multiplicacion de la Matriz de Pares con vector
	private List<Double> vector; // Vector de la matriz normal
        private Map<Integer,Double> alternativaIA;
	
	public Consistency(){
                this.consistent = false;
                this.values = new ArrayList<>();
                this.vector = new ArrayList<>();
                this.alternativaIA = new HashMap<>();
                this.alternativaIA.put(3,0.58);
                
	}
	
        public void multiplication(ComparisonMatrix pares){
            double value;
            for(String criteria1: pares.getItems()) {
                value = 0.0;
                for (int i = 0; i < pares.getItems().size(); i++) {
                    double matrixValue = pares.get(criteria1, pares.getItems().get(i));
                    double finalValue = (matrixValue * this.vector.get(i));
                    value = value + finalValue;
                   
                }
                this.values.add(value);
            }
            

        }
        
	// Calcula la consistencia de una Matriz
	public boolean analizar(ComparisonMatrix pares){
            
                this.setConfig();
                
		NormalMatrix normal = new NormalMatrix(pares.getItems(),pares);
                this.loadVector(normal.getPrincipalVector());
                this.multiplication(pares);
                return (this.isConsistent(pares.getSize()));	
	}
	
        private void setConfig(){
                this.consistent = false;
                this.values = new ArrayList<>();
                this.vector = new ArrayList<>();
                this.alternativaIA = new HashMap<>();
                this.alternativaIA.put(3,0.58);
        }
        
	// Carga directamente los resultados de la multiplicacion de la matriz de pares con el vector
	public void loadValue(double v){
		this.values.add(v);
	}
	
	// Carga el vector que es el promedio de la Matriz normalizada
	public void loadVector(List<Double> v){
		this.vector = v;
	}
	
	// Retorna la Media para el Analisis de Consistencia
	public double half(){
		int cantidad = this.values.size();
		double suma = 0;
		for(int pos=0; pos<cantidad; pos++){
			double valor = (this.values.get(pos) / this.vector.get(pos));
			suma = suma + valor;
		}
		return (suma/cantidad);
	}
	
	// Calcular el indice de Consistencia
	public double calculateIndex(){
		int cantidad = this.values.size();
		double media = this.half();
		return((media-cantidad)/(cantidad-1));
	}
	
	// Calcula el cociente del Indice de Consistencia para saber si nuesta matriz es consistente
	public double consistencyReason(int x){
            return (this.calculateIndex()/this.alternativaIA.get(x));
	}
	
	// Comprueba consistencia
	public boolean isConsistent(int x){
		double razon = this.consistencyReason(x);
		if(razon <= overallValue){
			this.consistent = true;
			return true;
		}
                this.consistent = false;
		return false;
	}
}