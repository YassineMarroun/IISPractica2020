/**
 * @ (#) DatosSalida.java
 *
 * Clase DatosSalida.
 * Se implementan las funciones para mostrar los datos al usuario,
 * una vez los cálculos ya se han realizado.
 *
 * @author Yassine Marroun
 * @version 1.00 2020/05/09
 */
package com.VirusSimulator.YassineMN;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DatosSalida {
	
	public DatosSalida() {
	}
	
	//Creamos una instancia de la fecha y hora actual para nombrar con ello cada archivo creado.
	DateFormat formato = new SimpleDateFormat("yyyy.MM.dd HH.mm");
	Calendar calendar = Calendar.getInstance();
	private String stringFecha = formato.format(calendar.getTime());
	
	//El metodo dosDecimales lo aplicamos sobre los datos de porcentaje que se vayan a imprimir
	//para que muestren unicamente dos decimales.
	DecimalFormat df = new DecimalFormat();
	private void dosDecimales() {
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
	}
	
	//Recorre cada uno de los mapas que almacenan los resultados de los calculos y los exporta a un archivo de texto.
	public void imprimirResultado(DatosEntrada datos, HashMap<String, ArrayList<Integer>> infectadosTotal,
			HashMap<String, ArrayList<Float>> infectadosPorcentaje, ArrayList<Integer> infectadosTodos,
			ArrayList<Float> porcentajeTodos) {
		
		try {
			dosDecimales();
    		FileWriter writer = new FileWriter(datos.getRutaSalida() + "\\SimulacionVirus_" + stringFecha + ".txt");
    		
    		
    		writer.write("---------------------------------\n");
    		writer.write("Numero de Infectados\n");
    		
    		for (String poblacion : infectadosTotal.keySet()) {
    			writer.write("Comunidad: " + poblacion + "\n");
    			for(int i = 0; i < datos.getNumeroDias(); i++){
    				writer.write("Dia: " + (i + 1) + " Infectados: " + infectadosTotal.get(poblacion).get(i) + "\n");
    			}
    		}	
    	
    		writer.write("---------------------------------\n");
    		writer.write("Porcentaje de Poblacion Infectada\n");
    		for (String poblacion : infectadosPorcentaje.keySet()) {
    			writer.write("Comunidad: " + poblacion + "\n");
    			for(int i = 0; i < datos.getNumeroDias(); i++) {
    				writer.write("Dia: " + (i + 1) + " Porcentaje: " + infectadosPorcentaje.get(poblacion).get(i) + "%\n");
    			}	
    		}
    		
    		writer.write("---------------------------------\n");
    		writer.write("Infectados de la Poblacion Total\n");
    		for (int i = 0; i < datos.getNumeroDias(); i++) {
    			writer.write("Dia: " + (i + 1) + " Infectados: " + infectadosTodos.get(i) + "\n");
    		}
    		
    		writer.write("---------------------------------\n");
    		writer.write("Porcentaje de Poblacion Total Infectada\n");
    		for (int i = 0; i < datos.getNumeroDias(); i++) {
    			writer.write("Dia: " + (i + 1) + " Porcentaje: " + df.format(porcentajeTodos.get(i)) + "%\n");
    		}
    		writer.write("---------------------------------\n");
    		writer.close();
    		
			
		} catch (IOException e) {
		    Utils.aviso ("Fallo: " + e);
		}	
	}
	
	//Recorre los mapas de resultados y los escribe en el nuevo archivo csv creado.
	public void imprimircsv(DatosEntrada datos, HashMap<String, ArrayList<Integer>> infectadosTotal,
			HashMap<String, ArrayList<Float>> infectadosPorcentaje, ArrayList<Integer> infectadosTodos,
			ArrayList<Float> porcentajeTodos) {
		
		try {
			dosDecimales();
			PrintWriter pw = new PrintWriter(new File(datos.getRutaSalida() + "\\SimulacionVirus_" + stringFecha + ".csv"));
			StringBuilder sb = new StringBuilder();
			
			sb.append("Numero de Infectados");
			sb.append(";");
			for(int i = 0; i < datos.getNumeroDias(); i++){
				sb.append("Dia: " + (i + 1));
				sb.append(";");
			}
			sb.append("\r\n");
			for (String poblacion : infectadosTotal.keySet()) {
				sb.append("Comunidad: " + poblacion);
				sb.append(";");
				for(int i = 0; i < datos.getNumeroDias(); i++){
					sb.append(infectadosTotal.get(poblacion).get(i));
					sb.append(";");
					}
					sb.append("\r\n");
			}
			sb.append("\r\n");
			
			sb.append("Porcentaje de Poblacion Infectada");
			sb.append("\r\n");
			for (String poblacion : infectadosPorcentaje.keySet()) {
				sb.append("Comunidad: " + poblacion);
				sb.append(";");
				for(int i = 0; i < datos.getNumeroDias(); i++){
					sb.append(df.format(infectadosPorcentaje.get(poblacion).get(i)) + "%");
					sb.append(";");
				}
				sb.append("\r\n");
			}
			sb.append("\r\n");
			
			sb.append("Infectados de la Poblacion Total");
			sb.append(";");
			for (int i = 0; i < datos.getNumeroDias(); i++) {
				sb.append(infectadosTodos.get(i));
				sb.append(";");
			}
			sb.append("\r\n");
			
			sb.append("Porcentaje de Poblacion Total Infectada");
			sb.append(";");
			for (int i = 0; i < datos.getNumeroDias(); i++) {
				sb.append((df.format(porcentajeTodos.get(i))) + "%");
				sb.append(";");
			}
			sb.append("\r\n");
			
			pw.write(sb.toString());
			pw.close();
			
		} catch (IOException e) {
		    Utils.aviso ("Fallo: " + e);
		}
	}
}