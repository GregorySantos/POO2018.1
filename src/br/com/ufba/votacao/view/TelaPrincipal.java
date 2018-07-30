package br.com.ufba.votacao.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.ufba.votacao.models.Enquetes;
import br.com.ufba.votacao.models.Usuario;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaPrincipal {
	
	 public JFrame frame = new JFrame();
     JTable table = new JTable(); 
     private int qtdEnq;
     private ArrayList<String> expiratedSurveys;
     
     public TelaPrincipal() {
    	 initialize();
     }
     
    public static void main(String[] args){    
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    }	
    	
   private void initialize() {
	   frame.getContentPane().setBackground(UIManager.getColor("activeCaption"));
	   frame.setVisible(true);
	   frame.setSize(800,800);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
	   
        // Vetor de objetos com os identificadores de coluna
        Object[] coluna = {"ID", "Nome da Enquete","Votos","Data Limite"};
        
        //Cria uma table model e seta os identificadores de coluna desse model 
        DefaultTableModel model = new DefaultTableModel() {
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };       
        model.setColumnIdentifiers(coluna);
        
        //seta o model na table
        table.setModel(model);
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        /*JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(241, 94, 108, 44);
        btnDelete.setBounds(372, 94, 108, 44);*/
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 100, 772, 458);
        frame.getContentPane().setLayout(null);
       
        // colocando as rows na JTable
        ArrayList<Enquetes> validos = new ArrayList<Enquetes>();
	    ArrayList<Enquetes> expirados = new ArrayList<Enquetes>();
	    expiratedSurveys = new ArrayList<String>();
	    
	    // cria um array de objetos pra setar os conteudo das linhas
        Object[] linha = new Object[4];
        
        try {
			
        	qtdEnq = 0;
        	
    		LocalDate localDate = LocalDate.now();
    	           	
			while (true) {
				
				Scanner scanner = new Scanner(new File("Enquete" + qtdEnq));
				
				String lineFromFile = scanner.nextLine();
				Enquetes enquete = new Enquetes();

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
				
				enquete.dateTime = LocalDate.parse(enquete.dtf, enquete.formatter);
				
				if(!localDate.isAfter(enquete.dateTime)) {
					//System.out.println(enq.titulo);
					validos.add(enquete);					
				} else {
					expirados.add(enquete);
					expiratedSurveys.add(enquete.getId());
				}
				qtdEnq++;
				scanner.close();
			}	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		}
        
        Collections.sort(validos);
        
        //coloca as enquetes em aberto na tabela
        for(int i = 0; i < validos.size(); i++) {			
			Enquetes enquete = validos.get(i);

			int qtdVotos = Integer.parseInt(enquete.qtdOp1) + Integer.parseInt(enquete.qtdOp2) 
							+ Integer.parseInt(enquete.qtdOp3) + Integer.parseInt(enquete.qtdOp4) 
							+ Integer.parseInt(enquete.qtdOp5);
			
			String titulo = enquete.getTitulo();		
			
			Scanner input = null;
			try {
				input = new Scanner(new File("User" + TelaInicial.userID));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			String userData = input.nextLine();
			
	        Usuario user = new Usuario(userData);
			
	        linha[0] = enquete.getId();
	        if(user.getJaVotados().contains(enquete.getId())) {
	        	linha[1] = "(Votado) " + titulo;
	        } else {
	        	linha[1] = titulo;
	        }
			linha[2] = qtdVotos;
			linha[3] = enquete.dtf;
								
			model.addRow(linha);
		
		}
		
        //coloca as enquetes expiradas na tabela
		for(int i = 0; i < expirados.size(); i++) {
			Enquetes enquete = expirados.get(i);

			int qtdVotos = Integer.parseInt(enquete.qtdOp1) + Integer.parseInt(enquete.qtdOp2) 
							+ Integer.parseInt(enquete.qtdOp3) + Integer.parseInt(enquete.qtdOp4) 
							+ Integer.parseInt(enquete.qtdOp5);
			
			String titulo = enquete.getTitulo();	
			
			Scanner input = null;
			try {
				input = new Scanner(new File("User" + TelaInicial.userID));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			String userData = input.nextLine();
			
	        Usuario user = new Usuario(userData);
			
	        linha[0] = enquete.getId();
	        if(user.getJaVotados().contains(enquete.getId())) {
	        	linha[1] = "(Votado) " + titulo;
	        } else {
	        	linha[1] = titulo;
	        }
			linha[2] = qtdVotos;
			linha[3] = "Expirado";
								
			model.addRow(linha);
		
		}
            
		// Definindo tamanho das colunas
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(125);
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        
        // centralizando coluna do meio
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
     
        
        frame.getContentPane().add(pane);
        //frame.getContentPane().add(btnDelete);
       
        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(192, 192, 192)));
        panel.setBackground(new Color(0, 102, 0));
        panel.setBounds(367, -11, 415, 112);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        //Botao Logout
        JButton btnNewButton_1 = new JButton("Logout");
        btnNewButton_1.setForeground(new Color(0, 0, 0));
        btnNewButton_1.setBounds(240, 36, 157, 51);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		TelaInicial inicio = new TelaInicial();
        	}
        });
        
        //Botao Historico
        JButton btnNewButton = new JButton("Informa\u00E7\u00F5es do Usu\u00E1rio");
        btnNewButton.setBounds(30, 36, 177, 51);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Historico hist = new Historico(frame);
        	}
        });
       
        //Botao Enquete
        JButton btnAdicionarEnquete = new JButton("Criar Nova Enquete");
        btnAdicionarEnquete.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdicionarEnquete.setBounds(129, 23, 208, 51);
        frame.getContentPane().add(btnAdicionarEnquete);
        btnAdicionarEnquete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				AdicionarEnquete obj2 = new AdicionarEnquete(qtdEnq, frame);
        	}
        });
        
        //Botao Atualizar
        JButton btnNewButton_2 = new JButton("Atualizar");
        btnNewButton_2.setBounds(10, 24, 91, 51);
        frame.getContentPane().add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		frame.setVisible(false);
        		TelaPrincipal ob3 = new TelaPrincipal();
        	}
        });
       
       
        
        
        
        // button remove row
        /*btnDelete.addActionListener(new ActionListener(){

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
        });*/
        
 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
        	
        	if (e.getClickCount() == 2) {  
        		String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
    			Scanner input = null;
    			try {
    				input = new Scanner(new File("User" + TelaInicial.userID));
    			} catch (FileNotFoundException e1) {
    				e1.printStackTrace();
    			}
    	        
    			String userData = input.nextLine();
    			
    	        Usuario user = new Usuario(userData);
        		
    	        if(user.getJaVotados().contains(value) || expiratedSurveys.contains(value)) {
    	        	ResultadosEnquete obj = new ResultadosEnquete(Integer.parseInt(value), frame);
    	        } else {
    	        	TelaEnquete enq = new TelaEnquete((Integer.parseInt(value)), frame);
    	        }
            }            
        }
        });   
    }
}