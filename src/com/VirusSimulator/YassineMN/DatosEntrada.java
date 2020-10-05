/**
 * @ (#) DatosEntrada.java
 *
 * Clase DatosEntrada.
 * Cuando un usuario introduce los datos necesarios para una simulación, se crea un objeto datosEntrada.
 *
 * @author Yassine Marroun
 * @version 1.00 2020/05/09
 */
package com.VirusSimulator.YassineMN;
import java.util.HashMap;
import java.util.Scanner;

public class DatosEntrada {

	public static final Scanner sc = new Scanner(System.in); //Variable para obtener la entrada de datos.
	//Estructura de datos que almacena el nombre de la comunidad como clave y su poblacion como valor.
	protected HashMap<String, Integer> poblaciones = new HashMap<String, Integer>();
	protected Integer porcentajeViajeros;	//Porcentaje de habitantes de cada comunidad que viaja diariamente a cada una de las otras comunidades, igual para todas las comunidades para simplificar.
	protected Integer E;			//Numero de contactos que en promedio tenga cada infectado con personas no infectadas.
	protected Integer p;			//Probabilidad de infectarse con un contacto.
	protected Integer V;			//Numero de viajeros diarios de una comunidad a otra.
	protected Integer numeroDias;	//Numero de dias a simular.
	protected String rutaSalida;	//Directorio donde se va a almacenar el archivo de texto y el archivo csv.

	
	public DatosEntrada() {
	}
	
	public DatosEntrada(HashMap<String, Integer> poblaciones, Integer porcentajeViajeros,
			Integer E, Integer p, Integer V, Integer numeroDias, String rutaSalida) {
		this.poblaciones = poblaciones;
		this.porcentajeViajeros = porcentajeViajeros;
		this.E = E;
		this.p = p;
		this.V = V;
		this.numeroDias = numeroDias;
		this.rutaSalida = rutaSalida;
	}

	public HashMap<String, Integer> getPoblaciones() {
		return poblaciones;
	}

	public void setPoblaciones(HashMap<String, Integer> poblaciones) {
		this.poblaciones = poblaciones;
	}
	
	public Integer getPorcentajeViajeros() {
		return porcentajeViajeros;
	}

	public void setPorcentajeViajeros(Integer porcentajeViajeros) {
		this.porcentajeViajeros = porcentajeViajeros;
	}

	public Integer getE() {
		return E;
	}

	public void setE(Integer e) {
		E = e;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getV() {
		return V;
	}

	public void setV(Integer v) {
		V = v;
	}

	public Integer getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(Integer numeroDias) {
		this.numeroDias = numeroDias;
	}
	
	public String getRutaSalida() {
		return rutaSalida;
	}

	public void setRutaSalida(String rutaSalida) {
		this.rutaSalida = rutaSalida;
	}
}	