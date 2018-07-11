package br.com.ufba.votacao.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JTableRow {
	
	 public JFrame frame = new JFrame();
     JTable table = new JTable(); 
     private int qtdEnq;
     
     public JTableRow() {
    	 initialize();
     }
     
     

    public static void main(String[] args){    
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTableRow window = new JTableRow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    }	
    	
   private void initialize() {
	   frame.setVisible(true);
	   frame.setSize(800,800);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
	   
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"ID", "Nome da Enquete","Votos","Categoria"};
        DefaultTableModel model = new DefaultTableModel() {
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
       
        model.setColumnIdentifiers(columns);
        
        // set the model to the table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(241, 94, 108, 44);
        btnDelete.setBounds(372, 94, 108, 44);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 258, 772, 500);
        
        frame.getContentPane().setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(41, 34, 177, 115);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        //BOTAO ADDENQUETE
        JButton btnAdicionarEnquete = new JButton("Adicionar Enquete");
        btnAdicionarEnquete.setBounds(10, 60, 157, 44);
        panel_1.add(btnAdicionarEnquete);
        btnAdicionarEnquete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// panel_1.setVisible(false);
        		// panel.setVisible(true);
        		frame.setVisible(false);
				AdicionarEnquete obj = new AdicionarEnquete(qtdEnq);
        	}
        });
        
        // colocando as rows na JTable
        
        // create an array of objects to set the row data
        Object[] row = new Object[4];
        try {
			
        	qtdEnq = 0;
        	
			while (true) {
				
				Scanner scanner = new Scanner(new File("Enquete" + qtdEnq));
				
				String lineFromFile = scanner.nextLine();

				String arr[] = lineFromFile.split(":");

				int qtdVotos = Integer.parseInt(arr[4]) + Integer.parseInt(arr[6]) + Integer.parseInt(arr[8]) + Integer.parseInt(arr[10]) + Integer.parseInt(arr[12]);
				
				String titulo = arr[1];		
				row[0] = arr[0];
				row[1] = titulo;
				row[2] = qtdVotos;
				row[3] = "";
								
				model.addRow(row);
				
				qtdEnq++;
			}	
			
			
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		}
             
        // Definindo tamanho das colunas
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        // centralizando coluna do meio
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
     
        
        frame.getContentPane().add(pane);
        frame.getContentPane().add(btnDelete);
        frame.getContentPane().add(btnUpdate);
        
        JButton btnNewButton = new JButton("Informa\u00E7\u00F5es do Usu\u00E1rio");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Historico hist = new Historico();
        	}
        });
        btnNewButton.setBounds(605, 11, 177, 51);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Logout");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		TelaInicial obj = new TelaInicial();
        	}
        });
        btnNewButton_1.setBounds(605, 70, 177, 44);
        frame.getContentPane().add(btnNewButton_1);
        
        
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
        	
        	if (e.getClickCount() == 2) {  
        		// i = the index of the selected row
                //int i = table.getSelectedRow();
        		String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                TelaEnquete enq = new TelaEnquete(Integer.parseInt(value), frame);
                //System.out.println("Linha " + table.getSelectedRow());
            }            
        }
        });
        
        
        
    	
        
    }
}