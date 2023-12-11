/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o orientada a objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package appswing;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Evento;
import modelo.Ingresso;
import regras_negocio.Fachada;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;


public class TelaEventos {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnApagarEvento;
	private JLabel label_4;
	private JButton btnCriarEvento;
	private JButton btnVoltar;
	private JButton btnExibirIngressos;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_5;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;
	private JFormattedTextField formattedTextField_2;
	private JFormattedTextField formattedTextField_3;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaReuniao window = new TelaReuniao();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaEventos() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		frame.setTitle("Eventos");
		frame.setBounds(100, 100, 575, 430);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 11, 501, 172);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() >= 0) 
					label.setText("selecionado="+ table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// APAGAR EVENTO
		btnApagarEvento = new JButton("Apagar Evento");
		btnApagarEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						
						int idEvento =  (int) table.getValueAt( table.getSelectedRow(), 0);
						// Confirmação
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão "+ idEvento, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarEvento(idEvento);
							label.setText("exclusão realizada");
							listagem();
						}
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		btnApagarEvento.setBounds(389, 243, 160, 23);
		frame.getContentPane().add(btnApagarEvento);

		label_4 = new JLabel("selecione uma linha");
		label_4.setBounds(26, 181, 315, 14);
		frame.getContentPane().add(label_4);

		// CRIAR EVENTO
		btnCriarEvento = new JButton("Criar Evento");
		btnCriarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//if(textField.getText().isEmpty() ||  textField_1.getText().isEmpty()) {
						//label.setText("campo vazio");
						//return;
					//}
					//String nome = textField.getText();
					//String media = textField_1.getText();
					//Fachada.criarAluno(nome, Double.parseDouble(media));
					//label.setText("aluno criado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarEvento.setBounds(26, 336, 303, 29);
		frame.getContentPane().add(btnCriarEvento);

		// VOLTAR
		btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(UIManager.getColor("Button.select"));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		btnVoltar.setToolTipText("");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(389, 343, 160, 23);
		frame.getContentPane().add(btnVoltar);

		// EXIBIR INGRESSOS
		btnExibirIngressos = new JButton("Exibir Ingressos");
		btnExibirIngressos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						int idEvento = (int) table.getValueAt( table.getSelectedRow(), 0);
						
						List<Evento> eventos = Fachada.listarEventos();
						List<Ingresso> ingressos = null;
						for(Evento evento : eventos) {
							if(evento.getId() == idEvento) {
								ingressos = evento.getIngressos();
							}
						}
						
						String titulo = "Ingressos do ID: " + String.valueOf(idEvento);
						String listagemIngressos = "";
						if(ingressos.size() > 0) 
							for(Ingresso i : ingressos) 
								listagemIngressos += i.getCodigo() + " - " + i.getTelefone() + "\n";
						 else 
							listagemIngressos = "Este evento não possui ingressos cadastrados";
						JOptionPane.showMessageDialog(null, listagemIngressos, titulo, JOptionPane.INFORMATION_MESSAGE);
						
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		btnExibirIngressos.setToolTipText("");
		btnExibirIngressos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExibirIngressos.setBounds(389, 278, 160, 23);
		frame.getContentPane().add(btnExibirIngressos);
		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(195, 194, 332, 14);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Data:");
		label_1.setBounds(26, 220, 46, 14);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("Descrição:");
		label_2.setBounds(26, 275, 90, 14);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("Capacidade:");
		label_3.setBounds(117, 220, 79, 14);
		frame.getContentPane().add(label_3);
		
		label_5 = new JLabel("Preço:");
		label_5.setBounds(248, 220, 46, 14);
		frame.getContentPane().add(label_5);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setToolTipText("dd/mm/yy");
		formattedTextField.setBounds(26, 241, 79, 23);
		frame.getContentPane().add(formattedTextField);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setToolTipText("dd/mm/yy");
		formattedTextField_1.setBounds(26, 301, 303, 23);
		frame.getContentPane().add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setToolTipText("dd/mm/yyyy");
		formattedTextField_2.setBounds(117, 240, 119, 23);
		frame.getContentPane().add(formattedTextField_2);
		
		formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setToolTipText("dd/mm/yy");
		formattedTextField_3.setBounds(248, 241, 79, 23);
		frame.getContentPane().add(formattedTextField_3);



	}

	public void listagem () {
		try{
			List<Evento> lista = Fachada.listarEventos();

			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("ID");
			model.addColumn("Data");
			model.addColumn("Capacidade");
			model.addColumn("Preço");
			model.addColumn("Qtd Ingressos");
			model.addColumn("Arrecadado");
			model.addColumn("Situação");
//			System.out.println(lista);
			for(Evento e : lista)
				if(e.lotado())
					model.addRow(new Object[]{  e.getId(), e.getData(), e.getCapacidade(), e.getPreco(), e.quantidadeIngressos(), e.totalArrecadado(), "Lotado!" });
				else
					model.addRow(new Object[]{  e.getId(), e.getData(), e.getCapacidade(), e.getPreco(), e.quantidadeIngressos(), e.totalArrecadado(), e.quantidadeIngressos()+ "/"+e.getCapacidade() });

			table.setModel(model);
			label_4.setText("resultados: "+lista.size()+ " linhas  - selecione uma linha");
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			//table.getColumnModel().getColumn(1).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
