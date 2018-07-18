package br.com.ufba.votacao.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JSplitPane;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class TelaCadastro {

	private JFrame frame;
	private JTextField tfNomeUser;
	private JTextField tfSenha;
	private JTextField tfRepetirSenha;
	private JTextField tfDataNascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro window = new TelaCadastro();
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
	public TelaCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setEnabled(false);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 30, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -30, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, -30, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setForeground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, lblCadastro, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblCadastro, 174, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblCadastro, -186, SpringLayout.EAST, panel);
		lblCadastro.setFont(new Font("Bookman Old Style", Font.BOLD, 22));
		panel.add(lblCadastro);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de Usu\u00E1rio");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNomeDeUsurio, 10, SpringLayout.SOUTH, lblCadastro);
		sl_panel.putConstraint(SpringLayout.WEST, lblNomeDeUsurio, 50, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNomeDeUsurio, -50, SpringLayout.EAST, panel);
		lblNomeDeUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeDeUsurio.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblNomeDeUsurio.setForeground(Color.WHITE);
		panel.add(lblNomeDeUsurio);
		
		tfNomeUser = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tfNomeUser, 5, SpringLayout.SOUTH, lblNomeDeUsurio);
		tfNomeUser.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.WEST, tfNomeUser, 150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, tfNomeUser, -285, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, tfNomeUser, -150, SpringLayout.EAST, panel);
		panel.add(tfNomeUser);
		tfNomeUser.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSenha, 10, SpringLayout.SOUTH, tfNomeUser);
		sl_panel.putConstraint(SpringLayout.WEST, lblSenha, -150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblSenha, 150, SpringLayout.EAST, panel);
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		panel.add(lblSenha);
		
		tfSenha = new JTextField();
		tfSenha.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.NORTH, tfSenha, 5, SpringLayout.SOUTH, lblSenha);
		sl_panel.putConstraint(SpringLayout.WEST, tfSenha, 150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, tfSenha, -150, SpringLayout.EAST, panel);
		panel.add(tfSenha);
		tfSenha.setColumns(10);
		
		JLabel lblRepetirSenha = new JLabel("Repetir Senha");
		sl_panel.putConstraint(SpringLayout.NORTH, lblRepetirSenha, 10, SpringLayout.SOUTH, tfSenha);
		sl_panel.putConstraint(SpringLayout.WEST, lblRepetirSenha, 150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblRepetirSenha, -150, SpringLayout.EAST, panel);
		lblRepetirSenha.setForeground(Color.WHITE);
		lblRepetirSenha.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblRepetirSenha.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblRepetirSenha);
		
		tfRepetirSenha = new JTextField();
		tfRepetirSenha.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.NORTH, tfRepetirSenha, 5, SpringLayout.SOUTH, lblRepetirSenha);
		sl_panel.putConstraint(SpringLayout.WEST, tfRepetirSenha, 150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, tfRepetirSenha, -150, SpringLayout.EAST, panel);
		panel.add(tfRepetirSenha);
		tfRepetirSenha.setColumns(10);
		
		JLabel lblDataNascimento = new JLabel("Data de Nascimento DD/MM/AAAA");
		sl_panel.putConstraint(SpringLayout.NORTH, lblDataNascimento, 10, SpringLayout.SOUTH, tfRepetirSenha);
		sl_panel.putConstraint(SpringLayout.WEST, lblDataNascimento, 50, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblDataNascimento, -50, SpringLayout.EAST, panel);
		lblDataNascimento.setForeground(Color.WHITE);
		lblDataNascimento.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDataNascimento);
		
		tfDataNascimento = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tfDataNascimento, 5, SpringLayout.SOUTH, lblDataNascimento);
		sl_panel.putConstraint(SpringLayout.WEST, tfDataNascimento, 150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, tfDataNascimento, -150, SpringLayout.EAST, panel);
		tfDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(tfDataNascimento);
		tfDataNascimento.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSexo, 10, SpringLayout.SOUTH, tfDataNascimento);
		sl_panel.putConstraint(SpringLayout.WEST, lblSexo, 150, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblSexo, -150, SpringLayout.EAST, panel);
		lblSexo.setForeground(Color.WHITE);
		lblSexo.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblSexo, -115, SpringLayout.SOUTH, panel);
		panel.add(lblSexo);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		sl_panel.putConstraint(SpringLayout.NORTH, rdbtnMasculino, 5, SpringLayout.SOUTH, lblSexo);
		sl_panel.putConstraint(SpringLayout.WEST, rdbtnMasculino, 0, SpringLayout.WEST, tfNomeUser);
		panel.add(rdbtnMasculino);
		
		JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		sl_panel.putConstraint(SpringLayout.SOUTH, rdbtnFeminino, 0, SpringLayout.SOUTH, rdbtnMasculino);
		sl_panel.putConstraint(SpringLayout.EAST, rdbtnFeminino, 0, SpringLayout.EAST, tfNomeUser);
		panel.add(rdbtnFeminino);
		
		JButton btnEfetuarCadastro = new JButton("Efetuar Cadastro");
		sl_panel.putConstraint(SpringLayout.WEST, btnEfetuarCadastro, 120, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnEfetuarCadastro, -120, SpringLayout.EAST, panel);
		btnEfetuarCadastro.setBackground(new Color(0, 0, 0));
		btnEfetuarCadastro.setForeground(Color.BLACK);
		btnEfetuarCadastro.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
		sl_panel.putConstraint(SpringLayout.NORTH, btnEfetuarCadastro, 20, SpringLayout.SOUTH, rdbtnMasculino);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnEfetuarCadastro, -20, SpringLayout.SOUTH, panel);
		panel.add(btnEfetuarCadastro);
		frame.setForeground(Color.WHITE);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 537, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
