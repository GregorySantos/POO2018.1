package br.com.ufba.votacao.telas;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AdicionarEnquete {

	private JFrame frame;
	private JTextField titulo;
	private static File arqEnq;
	private static int qtdEnq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarEnquete window = new AdicionarEnquete(qtdEnq);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdicionarEnquete(int qtdEnq) {
		this.qtdEnq = qtdEnq;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Add Enquete");
		label.setBounds(336, 144, 120, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Titulo");
		label_1.setBounds(193, 189, 46, 14);
		panel.add(label_1);
		
		titulo = new JTextField();
		titulo.setColumns(10);
		titulo.setBounds(455, 186, 178, 20);
		panel.add(titulo);
		
		Choice choice = new Choice();
		choice.setBounds(490, 234, 143, 20);
		choice.addItem("2");
		choice.addItem("3");
		choice.addItem("4");
		choice.addItem("5");
		panel.add(choice);
		
		Label label_2 = new Label("Numero de opcoes");
		label_2.setBounds(193, 234, 105, 22);
		panel.add(label_2);
		
		Label label_3 = new Label("Op 1");
		label_3.setBounds(193, 296, 62, 22);
		panel.add(label_3);
		
		Label label_4 = new Label("Op 2");
		label_4.setBounds(193, 333, 62, 22);
		panel.add(label_4);
		
		Label label_5 = new Label("Op 3");
		label_5.setBounds(193, 361, 62, 22);
		panel.add(label_5);
		
		Label label_6 = new Label("Op 4");
		label_6.setBounds(193, 397, 62, 22);
		panel.add(label_6);
		
		TextField op1Text = new TextField();
		op1Text.setBounds(455, 296, 178, 22);
		panel.add(op1Text);
		
		TextField op2Text = new TextField();
		op2Text.setBounds(455, 333, 178, 22);
		panel.add(op2Text);
		
		TextField op3Text = new TextField();
		op3Text.setBounds(455, 361, 178, 22);
		panel.add(op3Text);
		
		TextField op4Text = new TextField();
		op4Text.setBounds(455, 397, 178, 22);
		panel.add(op4Text);
		
		JCheckBox checkBox = new JCheckBox("Permitir Marcacao Multipla");
		checkBox.setBounds(193, 499, 291, 23);
		panel.add(checkBox);
		
		TextField op5Text = new TextField();
		op5Text.setBounds(455, 437, 178, 22);
		panel.add(op5Text);
		
		Label label_7 = new Label("Op 5");
		label_7.setBounds(193, 437, 62, 22);
		panel.add(label_7);
		
		JButton btnAddEnq = new JButton("Adicionar");
        
        // button add row
        btnAddEnq.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	String toWrite = Integer.toString(qtdEnq);
            	toWrite = toWrite + ':' + titulo.getText() + ':' + choice.getSelectedItem() + ':' + op1Text.getText() + ':' + '0' + ':' + op2Text.getText() + ':' + '0' + ':' + op3Text.getText() + ':' + '0' + ':' + op4Text.getText() + ':' + '0' + ':' + op5Text.getText() + ':' + '0';           	
            	
            	try {
            		String aux = "Enquete"+qtdEnq;
            		
        			arqEnq = new File(aux);
        			if (!arqEnq.exists()) {
        				System.out.println(aux);
        				arqEnq.createNewFile();
        			}

        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}

        		FileWriter fileWriter;
        		try {
        			fileWriter = new FileWriter(arqEnq.getName(), true);
        			BufferedWriter bw = new BufferedWriter(fileWriter);
        			bw.write(toWrite + "\n");
        			bw.close();
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
            	
            	
            	
            	
                //row[0] = textId.getText();
                //row[1] = textFname.getText();
                //row[2] = textLname.getText();
                
                // add row to the model
                //model.addRow(row);
                
                frame.setVisible(false);
				JTableRow obj = new JTableRow();
                
                
            }
            
        });
        
        
		btnAddEnq.setBounds(336, 529, 89, 23);
		panel.add(btnAddEnq);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				JTableRow obj = new JTableRow();
			}
		});
		btnVoltar.setBounds(166, 140, 89, 23);
		panel.add(btnVoltar);
		
		
	}
}
