/**
 * @ (#) Calculos.java
 *
 * Clase Calculos.
 * Recibe los datos introducidos por el usuario, realiza los cálculos
 * y prepara los resultados para que estén disponibes para la clase datosSalida.
 *
 * @author Yassine Marroun
 * @version 1.00 2020/05/09
 */
package com.VirusSimulator.YassineMN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Calculos {
	
	//Mapas que almacenan los resultados de los calculos, donde la clave es el nombre de la poblacion
	//y como valor tiene una lista que almacena el numero de infectados para cada dia.
	protected HashMap<String, ArrayList<Integer>> infectadosDia = new HashMap<String, ArrayList<Integer>>();
	protected HashMap<String, ArrayList<Integer>> infectadosViajeros = new HashMap<String, ArrayList<Integer>>();
	protected HashMap<String, ArrayList<Integer>> infectadosTotal = new HashMap<String, ArrayList<Integer>>();
	protected HashMap<String, ArrayList<Float>> infectadosPorcentaje = new HashMap<String, ArrayList<Float>>();
	//Los datos totales se almacenan en listas.
	protected ArrayList<Integer> infectadosTodos = new ArrayList<Integer>();
	protected ArrayList<Float> porcentajeTodos = new ArrayList<Float>();
	protected Integer poblacionTotal = 0; //Atributo donde se guarda la poblacion total.
	
	public Calculos () {
		
	}
	
	public Calculos(HashMap<String, ArrayList<Integer>> infectadosTotal, HashMap<String, ArrayList<Float>> infectadosPorcentaje,
			ArrayList<Integer> infectadosTodos, ArrayList<Float> porcentajeTodos) {

		this.infectadosTotal = infectadosTotal;
		this.infectadosPorcentaje = infectadosPorcentaje;
		this.infectadosTodos = infectadosTodos;
		this.porcentajeTodos = porcentajeTodos;
	}

	public HashMap<String, ArrayList<Integer>> getInfectadosTotal() {
		return infectadosTotal;
	}

	public void setInfectadosTotal(HashMap<String, ArrayList<Integer>> infectadosTotal) {
		this.infectadosTotal = infectadosTotal;
	}

	public HashMap<String, ArrayList<Float>> getInfectadosPorcentaje() {
		return infectadosPorcentaje;
	}

	public void setInfectadosPorcentaje(HashMap<String, ArrayList<Float>> infectadosPorcentaje) {
		this.infectadosPorcentaje = infectadosPorcentaje;
	}
	
	public ArrayList<Integer> getInfectadosTodos() {
		return infectadosTodos;
	}

	public void setInfectadosTodos(ArrayList<Integer> infectadosTodos) {
		this.infectadosTodos = infectadosTodos;
	}
	
	public ArrayList<Float> getPorcentajeTodos() {
		return porcentajeTodos;
	}

	public void setPorcentajeTodos(ArrayList<Float> porcentajeTodos) {
		this.porcentajeTodos = porcentajeTodos;
	}
	
	//Recorre el mapa de poblaciones introducidas por el usuario incluyendo cada una de ellas con una nueva lista
	//donde iran almacenando el numero de infectados para cada dia.
	//Se inicia con un primer infectado en la primera poblacion.
	public void iniciarMapas(DatosEntrada datos) {
		
		boolean uno = true;
		
		for (String poblacion : datos.poblaciones.keySet()) {
			if (uno) {
				infectadosDia.put(poblacion, new ArrayList<Integer>(Arrays.asList(1)));
				infectadosTotal.put(poblacion, new ArrayList<Integer>(Arrays.asList(1)));
				infectadosViajeros.put(poblacion, new ArrayList<Integer>(Arrays.asList(1)));
				uno = false;
			} else {
				infectadosDia.put(poblacion, new ArrayList<Integer>(Arrays.asList(0)));
				infectadosTotal.put(poblacion, new ArrayList<Integer>(Arrays.asList(0)));
				infectadosViajeros.put(poblacion,new ArrayList<Integer>(Arrays.asList(0)));
			}
		}
	}
		
	//Desarrolla el primer calculo, Nd+1 = Nd * (1+E*p). Almacena los resultados en su mapa,
	//le pasa los datos necesarios a los metodos que realizan los siguientes calculos del algoritmo.
	public void calcularInfectadosDia(DatosEntrada datos) {
		
		Integer Nd = 0;
		Integer numeroDias = datos.getNumeroDias();
		ArrayList<Integer> infectDia = new ArrayList<Integer>();
		ArrayList<Integer> infectadosDiaTotal = new ArrayList<Integer>();
		
		for (int i = 1; i < numeroDias; i++) {
			for (String poblacion : datos.poblaciones.keySet()) {
				infectDia = infectadosDia.get(poblacion);
				infectadosDiaTotal = infectadosTotal.get(poblacion);
				Nd = infectadosDiaTotal.get(i-1) * (1 + datos.getE() * datos.getP());
				infectDia.add(Nd);
				infectadosDia.put(poblacion, infectDia);		
			}
			calcularInfectadosViajeros(datos, infectadosDia, i);
			calcularInfectadosTotal(datos, infectadosDia, infectadosViajeros, i);
		}
	}

	
	//Desarrolla el segundo calculo, Nv = E*p*V*Nd,origen/Porigen.
	private void calcularInfectadosViajeros(DatosEntrada datos, HashMap<String, ArrayList<Integer>> infectadosDia, Integer i) {
		
		Integer Nv = 0;
		double NvTemp = 0;
		double division = 0f;
		ArrayList<Integer> infectViajeros = new ArrayList<Integer>();
		
		for (String poblacion : infectadosViajeros.keySet()) {
			Nv = 0;
			infectViajeros = infectadosViajeros.get(poblacion);
			for (String poblacionOrigen : infectadosViajeros.keySet()) {
				if (!poblacion.equals(poblacionOrigen)) {
					division = (double)infectadosDia.get(poblacionOrigen).get(i)/(double)datos.getPoblaciones().get(poblacionOrigen);
					NvTemp = Nv + datos.getE() * datos.getP() * datos.getV() * division;
					Nv = (int)NvTemp;
				}
			}			
			infectViajeros.add(Nv);
			infectadosViajeros.put(poblacion, infectViajeros);
		}	
	}
	
	//Recibe los mapas que almacenan los resultados de las dos primeras operaciones, suma ambos,
	//numero de viajeros infectados localmente y numero de viajeros infectados por los viajeros.
	//Almacena el resultado en infectadosTotal, mapa con el numero final de infectados en cada poblacion por cada dia.
	private void calcularInfectadosTotal(DatosEntrada datos, HashMap<String, ArrayList<Integer>> infectadosDia, HashMap<String, ArrayList<Integer>> infectadosViajeros, Integer i) {
		
		Integer n = 0;
		ArrayList<Integer> infectTotal = new ArrayList<Integer>();
		
		for (String poblacion : infectadosTotal.keySet()) {
			infectTotal = infectadosTotal.get(poblacion);
			n = infectadosDia.get(poblacion).get(i) + infectadosViajeros.get(poblacion).get(i);
			if(n < datos.poblaciones.get(poblacion)) {
				infectTotal.add(n);
				infectadosTotal.put(poblacion, infectTotal);
			} else {
				n = datos.poblaciones.get(poblacion);
				infectTotal.add(n);
			}
			infectadosTotal.put(poblacion, infectTotal);
		}
	}
	
	//Recorre el mapa de infectadosTotal, calcula el porcentaje infectados
	//y guarda el resultado en un mapa creado para el porcentaje de infectados en cada poblacion por cada dia.
	public void calcularPorcentaje(DatosEntrada datos) {
		
		for (String poblacion : infectadosTotal.keySet()) {
			ArrayList<Float> porcentajes = new ArrayList<Float>();
			for(int i = 0; i < datos.getNumeroDias(); i++){
				Float habitantes = (float)datos.poblaciones.get(poblacion);
				Float porcentajeInfectadosDia = (float)(infectadosTotal.get(poblacion).get(i) / habitantes) * 100;
				porcentajes.add(porcentajeInfectadosDia);
			}
			infectadosPorcentaje.put(poblacion, porcentajes);
		}
	}
	
	//Almacena en una lista el numero total de infectados
	//obtenido de sumar el numero de infectados de todas las poblaciones.
	public void calcularTodos(DatosEntrada datos) {
		
		Integer infectadosTotalDia;
		
		for(int i = 0; i < datos.getNumeroDias(); i++) {
			infectadosTotalDia = 0;
			for(String poblacion : infectadosTotal.keySet()) {
				infectadosTotalDia = infectadosTotalDia + infectadosTotal.get(poblacion).get(i);
			}
			infectadosTodos.add(infectadosTotalDia);
		}
	}
	
	//Almacena en una lista el porcentaje de infectados de la poblacion total
	//obtenido de realizar el calculo sobre la lista infectadosTotal.
	public void calcularPorcentajeTodos(DatosEntrada datos) {
		
		Float habitantesTotal = (float)calcularPoblacionTotal(datos);
		for(int i = 0; i < datos.getNumeroDias(); i++) {
			Float porcentajeTotalDia = (float)(infectadosTodos.get(i) / habitantesTotal) * 100;
			porcentajeTodos.add(porcentajeTotalDia);
		}
	}
	
	//Recorre el mapa de poblaciones para obtener la poblacion total.
	private Integer calcularPoblacionTotal(DatosEntrada datos) {
		
		for (String poblacion : datos.poblaciones.keySet()) {
			poblacionTotal = poblacionTotal + datos.poblaciones.get(poblacion);
		}
		return poblacionTotal;
	}
}