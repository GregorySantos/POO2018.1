package br.com.ufba.votacao.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.ufba.votacao.models.Enquetes;
import br.com.ufba.votacao.models.Usuario;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

/**Classe que implementa o historico
 * de votação dos usuarios.
* @author Gregory Santos
* @author Yndyra Pinheiro
* @author Mauro Meneses
* @author Yuri Oliveira
* @author Guilherme Costa
* @author Lucas
* @version 1.00
* @since Release 01 da aplicação
*/

public class Historico extends JFrame {

	private JFrame frame;
	private static JFrame frameTable;
	private JTable table;
	private Usuario user;
	private Enquetes enquete = new Enquetes();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historico window = new Historico(frameTable);
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
	public Historico(JFrame table) {
		frameTable = table;
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
		panel.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(48, 175, 437, 147);
		panel.add(panel_1);
		
		table = new JTable();
		table.setBounds(0, 0, 437, 147);
		panel_1.add(table);
		
		//Mesmo procedimento para criar table row da classe TelaPrincipal
		Object[] columns = {"ID", "Nome da Enquete","Votos","Data Limite"};
        DefaultTableModel model = new DefaultTableModel() {
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
       
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        
        Scanner input = null;
		try {
			input = new Scanner(new File("User" + TelaInicial.userID));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		String userData = input.nextLine();
		
        user = new Usuario(userData);
        
        Object[] row = new Object[4];
        
        for(int i = user.getJaVotados().size()-1, j = 0; i >= 0 && j < 5; j++, i--) {
        	    		
				Scanner scanner;
				try {
					scanner = new Scanner(new File("Enquete" + user.getJaVotados().get(i)));
				
					String lineFromFile = scanner.nextLine();
	
					String arr[] = lineFromFile.split(":");
					enquete.setId(arr[0]);
					enquete.setTitulo(arr[1]);
					enquete.numOpcoes = arr[2];
					enquete.op1 = arr[3];
					enquete.qtdOp1 = arr[4];
					enquete.op2 = arr[5];
					enquete.qtdOp2 = arr[6];
					enquete.op3 = arr[7];
					enquete.qtdOp3 = arr[8];
					enquete.op4 = arr[9];
					enquete.qtdOp4 = arr[10];
					enquete.op5 = arr[11];
					enquete.qtdOp5 = arr[12];
					enquete.dtf = arr[13];
					
	
					int qtdVotos = Integer.parseInt(enquete.qtdOp1) + Integer.parseInt(enquete.qtdOp2) 
									+ Integer.parseInt(enquete.qtdOp3) + Integer.parseInt(enquete.qtdOp4) 
									+ Integer.parseInt(enquete.qtdOp5);
						
					row[0] = enquete.getId();
					row[1] = enquete.getTitulo();
					row[2] = qtdVotos;
					row[3] = enquete.dtf;
										
					model.addRow(row);
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				}
        }
		input.close();
		
		JLabel lblInformaesDoUsurio = new JLabel("Informa\u00E7\u00F5es do Usu\u00E1rio");
		lblInformaesDoUsurio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInformaesDoUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformaesDoUsurio.setBounds(189, 11, 188, 27);
		panel.add(lblInformaesDoUsurio);
		
		TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		
		JLabel label_5 = new JLabel("Usuario:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_5.setBounds(48, 59, 121, 37);
		panel.add(label_5);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnVoltar.setBounds(493, 11, 97, 50);
		panel.add(btnVoltar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(48, 130, 87, 27);
		panel.add(panel_2);
		
		JLabel label_7 = new JLabel("Historico");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(label_7);
		
		JLabel lblNome = new JLabel(user.getNome());
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(132, 59, 274, 37);
		panel.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("Qtd de enquetes votadas:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(48, 92, 208, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(Integer.toString(user.getJaVotados().size()));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(266, 92, 152, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("                            Nome da Enquete                            Votos     Data de Expira\u00E7\u00E3o");
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(48, 156, 437, 19);
		panel.add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(48, 156, 437, 19);
		panel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(48, 59, 437, 263);
		panel.add(panel_4);
		
		table.addMouseListener(new MouseAdapter(){
		@Override
        public void mouseClicked (MouseEvent e) {
        	
        	if (e.getClickCount() == 2) {  
        		String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
        		frame.setVisible(false);
                ResultadosEnquete rEnq = new ResultadosEnquete(Integer.parseInt(value), frameTable);
            }            
        }
		
		});
		
	}
}
