package br.com.ufba.votacao.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

public class Historico {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historico window = new Historico();
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
	public Historico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(600,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(48, 156, 437, 174);
		panel.add(panel_1);
		
		JLabel label = new JLabel("Enquete:");
		label.setBounds(10, 11, 78, 23);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Nome da Enquete");
		label_1.setBounds(223, 15, 122, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Opcao marcada: ");
		label_2.setBounds(10, 54, 108, 33);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Nome da opcao");
		label_3.setBounds(223, 46, 88, 14);
		panel_1.add(label_3);
		
		JLabel lblInformaesDoUsurio = new JLabel("Informa\u00E7\u00F5es do Usu\u00E1rio");
		lblInformaesDoUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformaesDoUsurio.setBounds(189, 11, 188, 27);
		panel.add(lblInformaesDoUsurio);
		
		JLabel label_5 = new JLabel("Usuario:");
		label_5.setBounds(48, 64, 87, 14);
		panel.add(label_5);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				//JTableRow obj = new JTableRow();
			}
		});
		btnVoltar.setBounds(525, 11, 65, 50);
		panel.add(btnVoltar);
		
		textField = new JTextField();
		textField.setBounds(95, 61, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(48, 130, 87, 27);
		panel.add(panel_2);
		
		JLabel label_7 = new JLabel("Historico");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(label_7);
	}
}
