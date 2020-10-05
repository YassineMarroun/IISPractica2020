/**
 * @ (#) GuiInfectados.java
 *
 * Clase GuiInfectados.
 * Clase con el metodo main que inicia el programa. Implementa la interfaz grafica del programa,
 * recoge los datos y con ello crea un objeto DatosEntrada.
 *
 * @author Yassine Marroun
 * @version 1.00 2020/05/09
 */
package com.VirusSimulator.YassineMN;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiInfectados {
	
	private static final Integer LARGOPANEL = 800;
	private static final Integer ALTOPANEL = 250;
	private static final Integer LONGALTO = 20;
	private static final Integer ALTOCAB = 20;
	private static final Integer LONGSTRING = 250;
	private static final Integer LONGNUMERO = 30;
		
    private static JTextField comunidades = null;
    private static JTextField habitantes = null;
    private static JTextField porcentajeViajeros = null;
    private static JTextField E = null;
    private static JTextField p = null;
    private static JTextField V = null;
    private static JTextField numeroDias = null;
    private static JTextField rutaSalida = null;
   
    private static JButton calcular = null;
	
    public static void main(String[] args) {
    	
    	try{
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	datosGui();
	            }
	        });
    	}catch(Exception e){
			String error = "Fallo global: " + e;
		    System.out.println(error);
    	}
    }
    
	private static void datosGui(){
	    	    
        JFrame frame = new JFrame("Simulador de Virus");
        frame.setMinimumSize(new Dimension(LARGOPANEL, ALTOPANEL));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
	    
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setMinimumSize(new Dimension(250, ALTOPANEL));
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setMinimumSize(new Dimension(250, ALTOPANEL));
	    
	    JLabel lComunidades = new JLabel("Comunidades - Nombre separado por ;");
	    Utils.tamanno(lComunidades, LONGSTRING, LONGALTO);    
	    JLabel lHabitantes = new JLabel("Habitantes - Numero separado por ;");
	    Utils.tamanno(lHabitantes, LONGSTRING, LONGALTO);
	    JLabel lPorcentajeViajeros = new JLabel("Porcentaje de Viajeros");
	    Utils.tamanno(lPorcentajeViajeros, LONGSTRING, LONGALTO);
	    JLabel lE = new JLabel("E");
	    Utils.tamanno(lE, LONGNUMERO, LONGALTO);
	    JLabel lp = new JLabel("p");
	    Utils.tamanno(lp, LONGNUMERO, LONGALTO);
	    JLabel lV = new JLabel("V");
	    Utils.tamanno(lV, LONGNUMERO, LONGALTO);
	    JLabel lNumeroDias = new JLabel("Numero de Dias");
	    Utils.tamanno(lNumeroDias, LONGSTRING, LONGALTO);
	    JLabel lRutaSalida = new JLabel("Directorio de Salida ");
	    Utils.tamanno(lRutaSalida, LONGSTRING, LONGALTO);
	    
	    comunidades = new JTextField();
        Utils.tamanno(comunidades, LONGSTRING, LONGALTO);
	    habitantes = new JTextField();
        Utils.tamanno(habitantes, LONGSTRING, LONGALTO);
	    porcentajeViajeros = new JTextField();
        Utils.tamanno(porcentajeViajeros, LONGNUMERO, LONGALTO);
	    E = new JTextField();
        Utils.tamanno(E, LONGNUMERO, LONGALTO);
	    p = new JTextField();
        Utils.tamanno(p, LONGNUMERO, LONGALTO);
	    V = new JTextField();
        Utils.tamanno(V, LONGNUMERO, LONGALTO);
	    numeroDias = new JTextField();
        Utils.tamanno(numeroDias, LONGNUMERO, LONGALTO);
	    rutaSalida = new JTextField();
        Utils.tamanno(rutaSalida, LONGSTRING, LONGALTO);
        
	    // accions
        JPanel botones = new JPanel();
        botones.setLayout(new GridBagLayout());
        botones.setMinimumSize(new Dimension(LARGOPANEL, ALTOCAB));
	    
	    calcular = new JButton("CALCULAR");
	    calcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcularInfectados();
			}
		});
	    
	    GridBagConstraints c0 = new GridBagConstraints();
	    Utils.comp(c0, 0, 1);
	    panel2.add(Utils.hueco(), c0);
	    
        GridBagConstraints c1 = new GridBagConstraints();
        Utils.comp(c1, 0, 1);
        panel1.add(lComunidades, c1);
        Utils.comp(c1, 0, 2);
        panel1.add(lHabitantes, c1);
        Utils.comp(c1, 0, 3);
        panel1.add(lPorcentajeViajeros, c1);
        Utils.comp(c1, 0, 4);
        panel1.add(lE, c1);
        Utils.comp(c1, 0, 5);
        panel1.add(lp, c1);
        Utils.comp(c1, 0, 6);
        panel1.add(lV, c1);
        Utils.comp(c1, 0, 7);
        panel1.add(lNumeroDias, c1);
        Utils.comp(c1, 0, 8);
        panel1.add(lRutaSalida, c1);
        
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        Utils.comp(c2, 0, 1);
        panel2.add(comunidades, c2);
        Utils.comp(c2, 0, 2);
        panel2.add(habitantes, c2);
        Utils.comp(c2, 0, 3);
        panel2.add(porcentajeViajeros, c2);
        Utils.comp(c2, 0, 4);
        panel2.add(E, c2);
        Utils.comp(c2, 0, 5);
        panel2.add(p, c2);
        Utils.comp(c2, 0, 6);
        panel2.add(V, c2);
        Utils.comp(c2, 0, 7);
        panel2.add(numeroDias, c2);
        Utils.comp(c2, 0, 8);
        panel2.add(rutaSalida, c2);

        GridBagConstraints b1 = new GridBagConstraints();
        b1.fill = GridBagConstraints.HORIZONTAL;
        Utils.comp(b1, 0, 0);
	    botones.add(calcular);
	    	    
        frame.add(panel1);
        frame.add(panel2);
        frame.add(botones);

        frame.pack();
        frame.setVisible(true);   

	}
	
	private static void calcularInfectados(){
		DatosEntrada dE = new DatosEntrada();
		try {
			
			HashMap<String, Integer> poblaciones = new HashMap<String, Integer>();
			String[] clave = comunidades.getText().split(";");
			String[] valor = habitantes.getText().split(";");
			for (int i = 0; i < clave.length; i++) {
				poblaciones.put(clave[i], Integer.parseInt(valor[i]));
			}
			dE.setPoblaciones(poblaciones);
			dE.setPorcentajeViajeros(Integer.parseInt(porcentajeViajeros.getText()));
			dE.setE(Integer.parseInt(E.getText()));
			dE.setP(Integer.parseInt(p.getText()));
			dE.setV(Integer.parseInt(V.getText()));
			dE.setNumeroDias(Integer.parseInt(numeroDias.getText()));
			dE.setRutaSalida(rutaSalida.getText());
		} catch (Exception e) {
			Utils.aviso("El dato debe ser numerico");
		}
		try {
			Infectados infectados= new Infectados();
			infectados.infectados(dE);
			
			Utils.aviso("Simulacion con exito!\nPuede introducir datos para una nueva simulacion");
		} catch (Exception e) {
			Utils.aviso("Fallo en ejecucion de sistema");
		}		
	}
}