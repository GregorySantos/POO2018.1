package br.com.ufba.votacao.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.com.ufba.votacao.models.Enquetes;
import br.com.ufba.votacao.models.Usuario;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

/**Classe que implementa a votação
 * numa enquete já criada.
* @author Gregory Santos
* @author Yndyra Pinheiro
* @author Mauro Meneses
* @author Yuri Oliveira
* @author Guilherme Costa
* @author Lucas
* @version 1.00
* @since Release 01 da aplicação
*/
public class TelaEnquete  extends JFrame {

	private JFrame frame;
	private static JFrame frameTable;
	private static int enqueteId;
	private static String dados[];
	private Enquetes enquete = new Enquetes();
	private Scanner scanner;
	FileWriter fileWriter;
	BufferedWriter bw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEnquete window = new TelaEnquete(enqueteId, frameTable);
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
	public TelaEnquete(int enqId, JFrame fTable) {
		getContentPane().setLayout(null);
		
		Scanner input = null;
		try {
			input = new Scanner(new File("User" + TelaInicial.userID));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		String userData = input.nextLine();
		
        Usuario user = new Usuario(userData);
		
        if(!user.getJaVotados().contains(enqId)) {
		
			enqueteId = enqId;		
			frameTable = fTable;
			
			try {
				scanner = new Scanner(new File("Enquete"+enqId));
	
				String lineFromFile = scanner.nextLine();
	
				dados = lineFromFile.split(":");
				enquete.setId(dados[0]);
				enquete.setTitulo(dados[1]);
				enquete.numOpcoes = dados[2];
				enquete.op1 = dados[3];
				enquete.qtdOp1 = dados[4];
				enquete.op2 = dados[5];
				enquete.qtdOp2 = dados[6];
				enquete.op3 = dados[7];
				enquete.qtdOp3 = dados[8];
				enquete.op4 = dados[9];
				enquete.qtdOp4 = dados[10];
				enquete.op5 = dados[11];
				enquete.qtdOp5 = dados[12];
				enquete.dtf = dados[13];
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			initialize();
		
        } else {
        	ResultadosEnquete obj = new ResultadosEnquete(enqId,fTable);
        }	
        
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel(enquete.getTitulo());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(0, 0, 384, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton op1Check = new JRadioButton(enquete.op1);
		op1Check.setBounds(44, 72, 285, 23);
		frame.getContentPane().add(op1Check);
		
		JRadioButton op2Check = new JRadioButton(enquete.op2);
		op2Check.setBounds(44, 110, 285, 23);
		frame.getContentPane().add(op2Check);
		

		JRadioButton op3Check = new JRadioButton(enquete.op3);
		op3Check.setBounds(44, 154, 285, 23);
		

		JRadioButton op4Check = new JRadioButton(enquete.op4);
		op4Check.setBounds(44, 198, 285, 23);
		

		JRadioButton op5Check = new JRadioButton(enquete.op5);
		op5Check.setBounds(44, 237, 285, 23);
		
		int numOP = Integer.parseInt(enquete.numOpcoes);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(op1Check);
		grupo.add(op2Check);
		if(numOP >= 3) {
			frame.getContentPane().add(op3Check);
			grupo.add(op3Check);
		}
		
		if(numOP >= 4) {
			frame.getContentPane().add(op4Check);
			grupo.add(op4Check);
		}
		
		if(numOP >= 5) {
			frame.getContentPane().add(op5Check);
			grupo.add(op5Check);
		}
		
		
		
		JButton btnVotar = new JButton("Votar");
		btnVotar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(op1Check.isSelected()) {
					int x = Integer.parseInt(enquete.qtdOp1)+1;
					enquete.qtdOp1 = Integer.toString(x);
					//System.out.println(enq.qtdOp1);
				}
				
				if(op2Check.isSelected()) {
					int x = Integer.parseInt(enquete.qtdOp2)+1;
					enquete.qtdOp2 = Integer.toString(x);
				}
				
				if(op3Check.isSelected() && numOP >= 3) {
					int x = Integer.parseInt(enquete.qtdOp3)+1;
					enquete.qtdOp3 = Integer.toString(x);
				}
				
				if(op4Check.isSelected() && numOP >= 4) {
					int x = Integer.parseInt(enquete.qtdOp4)+1;
					enquete.qtdOp4 = Integer.toString(x);
				}
				
				if(op5Check.isSelected() && numOP >= 5) {
					int x = Integer.parseInt(enquete.qtdOp5)+1;
					enquete.qtdOp5 = Integer.toString(x);
				}
			
				String newData = new String("");
				newData = enquete.getId() + ":" + enquete.getTitulo() + ":" + enquete.numOpcoes + ":" 
						+ enquete.op1 + ":" + enquete.qtdOp1 + ":" + enquete.op2 + ":" 
						+ enquete.qtdOp2 + ":" + enquete.op3 + ":" + enquete.qtdOp3 + ":" 
						+ enquete.op4 + ":" + enquete.qtdOp4 + ":" + enquete.op5 + ":"
						+ enquete.qtdOp5 + ":" + enquete.dtf;

				File newArqEnq = new File("Enquete"+enqueteId);
				
				try {
				    FileWriter f1 = new FileWriter(newArqEnq, false);
				    f1.write(newData);
				    f1.close();
				} catch (IOException e1) {
				    e1.printStackTrace();
				}     
				String newUserData = "";
				
				try {
					scanner = new Scanner(new File("User" + TelaInicial.userID));
					newData = scanner.nextLine();
					scanner.close();
					Usuario userUpdate = new Usuario(newData);
					int votadas = userUpdate.getQtdEnquetesVotadas();
					votadas++;
					userUpdate.setQtdEnquetesVotadas(votadas);
					newUserData += userUpdate.getId() + ":" + userUpdate.getNome()+ ":"
								+ userUpdate.getSenha() + ":" + userUpdate.getQtdEnquetesVotadas() + ":";
					
					for(int i = 0; i < userUpdate.getJaVotados().size(); i++) {
						newUserData += userUpdate.getJaVotados().get(i) + ":";
					}
					newUserData += enqueteId;
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				File newArqUser = new File("User"+TelaInicial.userID);
				
				try {
				    FileWriter f2 = new FileWriter(newArqUser, false);
				    f2.write(newUserData);
				    f2.close();
				} catch (IOException e1) {
				    e1.printStackTrace();
				}  
				frame.setVisible(false);
				
				ResultadosEnquete ob = new ResultadosEnquete(enqueteId, frameTable);
				
			}
		});
		
		btnVotar.setBounds(137, 306, 89, 23);
		frame.getContentPane().add(btnVotar);
		frame.setVisible(true);
		
	}
}
