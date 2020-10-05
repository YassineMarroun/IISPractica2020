/**
 * @ (#) Infectados.java
 *
 * Clase Infectados.
 * Crea un objeto Calculos para realizar la llamada a los metodos que desarrollan el algoritmo de simulacion.
 * Crea un objeto DatosSalida y realiza la llamada a los metodos que imprimen el resultado.
 *
 * @author Yassine Marroun
 * @version 1.00 2020/05/09
 */
package com.VirusSimulator.YassineMN;

public class Infectados {

	//El metodo infectados crea una instancia de Calculos y llama a cada uno de sus metodos pasandole el objeto DatosEntrada recibido por parametro,
	//a continuacion, crea una instancia de DatosSalida y llama a los metodos que implementan la visualizacion pasandole el objeto DatosEntrada y los resultados del objeto Calculos.
	public void infectados(DatosEntrada dE) {
			
		Calculos c = new Calculos();
		c.iniciarMapas(dE);
		c.calcularInfectadosDia(dE);
		c.calcularPorcentaje(dE);
		c.calcularTodos(dE);
		c.calcularPorcentajeTodos(dE);
			
		DatosSalida dS = new DatosSalida();
		dS.imprimirResultado(dE, c.getInfectadosTotal(), c.getInfectadosPorcentaje(),
			c.getInfectadosTodos(), c.getPorcentajeTodos());
		dS.imprimircsv(dE, c.getInfectadosTotal(), c.getInfectadosPorcentaje(),
			c.getInfectadosTodos(), c.getPorcentajeTodos());
	}
}