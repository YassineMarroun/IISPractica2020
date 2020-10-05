/**
 * @ (#) Utils.java
 *
 * Clase Utils.
 * Implementa metodos para estructurar la interfaz grafica.
 *
 * @author Yassine Marroun
 * @version 1.00 2020/05/09
 */
package com.VirusSimulator.YassineMN;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Utils {
	
	private static final Integer HUECO = 5;
	private static final Integer HUECOV = 1;
	private static final Integer ALTOPAD = 1;
    
    public static void tamanno(Component j, Integer ancho, Integer alto) {
    	j.setMinimumSize(new Dimension(ancho, alto));
    	j.setPreferredSize(new Dimension(ancho, alto));
    }
    
    public static void tamanno(Component j, Integer ancho, Integer alto, Integer max) {
    	tamanno(j, ancho, alto);
    	j.setMaximumSize(new Dimension(max, alto));
    }
    
    public static void comp(GridBagConstraints c, Integer posX, Integer posY) {
    	c.weightx = 0.5;
    	c.weighty = 0.5;
    	c.anchor = GridBagConstraints.LINE_START;
    	c.gridx = posX;
    	c.gridy = posY;
    	c.ipady = ALTOPAD;
    	c.ipadx = ALTOPAD;
    }
    
    public static Component hueco() {
    	JLabel nada = new JLabel(" ");
    	nada.setMaximumSize(new Dimension(HUECO, 1));
    	return nada;
    }
    
    public static Component huecoV() {
    	JLabel nada = new JLabel(" ");
    	nada.setMaximumSize(new Dimension(1, HUECOV));
    	return nada;
    }
    
    //Metodo para mostrar el error o fallo en el sistema en una ventana emergente.
    public static void aviso(String aviso) {
    	JFrame frame = new JFrame("1");
    	frame.setAlwaysOnTop(true);
    	JOptionPane.showMessageDialog (frame, aviso);
    }
}