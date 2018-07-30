package br.com.ufba.votacao.view;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.SystemColor;

public class TelaInicial {
	
	private static String inputData;
	private static File arqPass;
	private static File arqUser;

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private Scanner scanner;
	private static boolean userFound;
	private int nextUserId;
	public static int userID;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
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
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 204, 204));
		frame.getContentPane().setEnabled(true);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel_inicial = new JPanel();
		panel_inicial.setBounds(127, 64, 533, 231);
		frame.getContentPane().add(panel_inicial);
		panel_inicial.setLayout(null);
		
		JLabel lblVotacao = new JLabel("INSIRA NOME CRIATIVO AQUI");
		lblVotacao.setBounds(50, 11, 395, 29);
		panel_inicial.add(lblVotacao);
		lblVotacao.setFont(new Font("Courier New", Font.BOLD, 23));
		lblVotacao.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel aux = new JPanel();
		aux.setBounds(114, 40, 279, 140);
		panel_inicial.add(aux);
		aux.setLayout(null);
		
		JPanel ASD = new JPanel();
		ASD.setBackground(Color.LIGHT_GRAY);
		ASD.setBounds(10, 11, 259, 78);
		aux.add(ASD);
		ASD.setLayout(null);
		
		username = new JTextField();
		username.setBackground(Color.WHITE);
		username.setBounds(69, 11, 149, 20);
		ASD.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("USU\u00C1RIO : ");
		lblNewLabel.setBounds(0, 14, 70, 14);
		ASD.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblSenha = new JLabel("SENHA :");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(0, 45, 70, 14);
		ASD.add(lblSenha);
		
		password = new JPasswordField();
		password.setBounds(69, 42, 149, 20);
		ASD.add(password);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(153, 100, 126, 29);
		aux.add(btnLogin);
		
		JButton btnCadastro = new JButton("CADASTRO");
		btnCadastro.setBounds(0, 100, 126, 29);
		aux.add(btnCadastro);
		
		nextUserId = 0;
		try {
			scanner = new Scanner(new File("pass"));
			
			while (scanner.hasNextLine()) {
				scanner.nextLine();
				nextUserId++;	
			}	
		} catch (FileNotFoundException e1) {
			File arqPass = new File("pass");
			try {
				arqPass.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		//LOGIN
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user  = username.getText();
				String pass = new String(password.getPassword());
				userFound = false;
				int id = 0;
				int counter = 0;
				
				if (user.equals("") || pass.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Por favor, preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						scanner = new Scanner(new File("pass"));

						while (scanner.hasNextLine()) {

							String lineFromFile = scanner.nextLine();

							String userAndPass[] = lineFromFile.split(":");

							String user1 = userAndPass[1];
							String pass1 = userAndPass[2];

							if (user.equals(user1) && pass1.equals(new String(pass))) {
								userFound = true;
								id = counter;
							}
							counter++;
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					if (userFound) {
						 JOptionPane.showMessageDialog(frame, "Login efetuado com sucesso!");
						 userID = id;
						 frame.setVisible(false);
						 TelaPrincipal telaprincipal = new TelaPrincipal();
					} else {
						JOptionPane.showMessageDialog(panel_inicial,"Usuario ou Senha Incorretos", "ERRO", JOptionPane.ERROR_MESSAGE );
					}			
				}
			}
		});
		
		//CADASTRO
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user  = username.getText();
				String pass = new String(password.getPassword());
				userFound = false;
				

				if (user.equals("") || pass.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Por favor, preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
				} else {
					inputData = user + ":" + pass;
					panel_inicial.setVisible(false);
					try {
						arqPass = new File("pass");
						if (!arqPass.exists()) {
							arqPass.createNewFile();
						}

					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					FileWriter fileWriter;
					try {
						
						try {
							scanner = new Scanner(new File("pass"));

							while (scanner.hasNextLine()) {
								String lineFromFile = scanner.nextLine();
								String userAndPass[] = lineFromFile.split(":");

								String user1 = userAndPass[1];
								String pass1 = userAndPass[2];
								if (user.equals(user1)) {
									
									userFound = true;
									break;
								}
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}

						if (!userFound) {
							//insere credencias do novo user no arquivo pass
							fileWriter = new FileWriter(arqPass.getName(), true);
							BufferedWriter bw = new BufferedWriter(fileWriter);
							bw.write(nextUserId + ":" + inputData + "\n");
							bw.close();
							
							//cria arquivo pro novo user e insere as informações dele
							String aux = "User"+nextUserId;
							try {			            		
			        			arqUser = new File(aux);
			        			if (!arqUser.exists()) {
			        				arqUser.createNewFile();
			        			}
			        		} catch (IOException e1) {
			        			e1.printStackTrace();
			        		}
							fileWriter = new FileWriter(aux, true);
							bw = new BufferedWriter(fileWriter);
							bw.write(nextUserId + ":" + inputData + ":0" + "\n");
							bw.close();
							
							JOptionPane.showMessageDialog(panel_inicial,"Cadastro Efetuado com Sucesso");
							frame.setVisible(false);
							userID = nextUserId;
							TelaPrincipal telaprincipal = new TelaPrincipal();
						} else {
							JOptionPane.showMessageDialog(panel_inicial,"Usuário já cadastrado", "ERRO", JOptionPane.ERROR_MESSAGE);
							frame.setVisible(false);
							TelaInicial inicio = new TelaInicial();
						}
	
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});	
	}
}
