package br.com.ufba.votacao.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class TelaEnquete  extends JFrame {

	private JFrame frame;
	private static JFrame frameTable;
	private static int indEnq;
	private static String dados[];
	private Scanner scanner;
	private File arqPass = null;
	FileWriter fileWriter;
	BufferedWriter bw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEnquete window = new TelaEnquete(indEnq, frameTable);
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
	public TelaEnquete(int indEnq, JFrame frameTable) {
		this.indEnq = indEnq;		
		this.frameTable = frameTable;
		
		try {
			scanner = new Scanner(new File("Enquete"+indEnq));

			String lineFromFile = scanner.nextLine();

			dados = lineFromFile.split(":");	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(dados[1]);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 0, 384, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JCheckBox op1Check = new JCheckBox(dados[3]);
		op1Check.setBounds(44, 72, 285, 23);
		frame.getContentPane().add(op1Check);
		
		JCheckBox op2Check = new JCheckBox(dados[5]);
		op2Check.setBounds(44, 110, 285, 23);
		frame.getContentPane().add(op2Check);
		

		JCheckBox op3Check = new JCheckBox(dados[7]);
		op3Check.setBounds(44, 154, 285, 23);
		

		JCheckBox op4Check = new JCheckBox(dados[9]);
		op4Check.setBounds(44, 198, 285, 23);
		

		JCheckBox op5Check = new JCheckBox(dados[11]);
		op5Check.setBounds(44, 237, 285, 23);
		
		int numOP = Integer.parseInt(dados[2]);
		if(numOP >= 3) {
			frame.getContentPane().add(op3Check);
		}
		
		if(numOP >= 4) {
			frame.getContentPane().add(op4Check);
		}
		
		if(numOP >= 5) {
			frame.getContentPane().add(op5Check);
		}
		
		JButton btnVotar = new JButton("Votar");
		btnVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(op1Check.isSelected()) {
					int x = Integer.parseInt(dados[4])+1;
					dados[4] = Integer.toString(x);
				}
				
				if(op2Check.isSelected()) {
					int x = Integer.parseInt(dados[6])+1;
					dados[6] = Integer.toString(x);
				}
				
				if(op3Check.isSelected() && numOP >= 3) {
					int x = Integer.parseInt(dados[8])+1;
					dados[8] = Integer.toString(x);
				}
				
				if(op4Check.isSelected() && numOP >= 4) {
					int x = Integer.parseInt(dados[10])+1;
					dados[10] = Integer.toString(x);
				}
				
				if(op5Check.isSelected() && numOP >= 5) {
					int x = Integer.parseInt(dados[12])+1;
					dados[12] = Integer.toString(x);
				}
			
				String x = new String("");
				x = dados[0];
				for(int i = 1; i < 13; i++) 
					x += ':' + dados[i];

				File fold=new File("Enquete"+indEnq);
				fold.delete();
				File fnew=new File("Enquete"+indEnq);
				//System.out.println(x);

				try {
				    FileWriter f2 = new FileWriter(fnew, false);
				    f2.write(x);
				    f2.close();
				} catch (IOException e1) {
				    e1.printStackTrace();
				}      
				frame.setVisible(false);
				frameTable.setVisible(false);
				
				JTableRow obj = new JTableRow();
				
			}
		});
		btnVotar.setBounds(137, 306, 89, 23);
		frame.getContentPane().add(btnVotar);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
