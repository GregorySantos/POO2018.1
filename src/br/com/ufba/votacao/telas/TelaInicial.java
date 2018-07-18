package br.com.ufba.votacao.telas;
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

public class TelaInicial {
	
	private static String inputData;
	private static File arqPass;

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private Scanner scanner;
	private static boolean achei;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {//sfasfafs
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
		frame.getContentPane().setEnabled(true);
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel_inicial = new JPanel();
		panel_inicial.setBounds(114, 22, 561, 557);
		frame.getContentPane().add(panel_inicial);
		panel_inicial.setLayout(null);
		
		JLabel lblVotao = new JLabel("Vota\u00E7\u00E3o");
		lblVotao.setBounds(210, 0, 90, 29);
		panel_inicial.add(lblVotao);
		lblVotao.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblVotao.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel aux = new JPanel();
		aux.setBounds(114, 40, 279, 140);
		panel_inicial.add(aux);
		aux.setLayout(null);
		
		JPanel ASD = new JPanel();
		ASD.setBackground(Color.LIGHT_GRAY);
		ASD.setBounds(10, 11, 259, 64);
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
		
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String user  = username.getText();
				String pass = password.getText();
				achei = false;
				
				if (user.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Campo usuario em branco");
				}
				else if (pass.equals("")){
					JOptionPane.showMessageDialog(panel_inicial,"Campo senha em branco");
				}
				else {
					try {
						scanner = new Scanner(new File("pass"));

						while (scanner.hasNextLine()) {

							String lineFromFile = scanner.nextLine();

							String userAndPass[] = lineFromFile.split(":");

							String user1 = userAndPass[0];
							String pass1 = userAndPass[1];

							if (user.equals(user1) && pass1.equals(new String(pass))) {
								achei = true;
							}
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					if (achei) {
						 JOptionPane.showMessageDialog(frame, "Login efetuado com sucesso!");
						 frame.setVisible(false);
						 JTableRow obj = new JTableRow();
					} else {
						JOptionPane.showMessageDialog(panel_inicial,"Usuario ou Senha Incorretos");
					}
					
							
				}
			}
		});
		
		btnCadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				TelaCadastro obj = new TelaCadastro();
			}
		});
		
	}
}
