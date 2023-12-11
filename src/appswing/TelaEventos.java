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
import regras_negocio.Fachada;
import javax.swing.JFormattedTextField;


public class TelaEventos {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button_1;
	private JLabel label_4;
	private JButton button;
	private JButton button_2;
	private JButton button_3;
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

		button_1 = new JButton("Apagar Evento");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						//confirma��o
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma exclus�o "+nome, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarEvento(idEvento);
							label.setText("exclus�o realizada");
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
		button_1.setBounds(389, 275, 160, 23);
		frame.getContentPane().add(button_1);

		label_4 = new JLabel("selecione uma linha");
		label_4.setBounds(26, 181, 315, 14);
		frame.getContentPane().add(label_4);

		button = new JButton("Criar Evento");
		button.addActionListener(new ActionListener() {
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(26, 343, 202, 23);
		frame.getContentPane().add(button);

		button_2 = new JButton("Voltar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						//Fachada.cancelarMatriculaAlunoTurma(nome);
						label.setText("cancelou matricula " + nome);
						listagem();
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}

			}
		});
		button_2.setToolTipText("");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(389, 343, 160, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Exibir Ingresso");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
						String id = JOptionPane.showInputDialog("id da turma");
						//Fachada.matricularAluno(nome, Integer.parseInt(id));
						label.setText("matriculou " + id);
						listagem();
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_3.setToolTipText("");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(389, 309, 160, 23);
		frame.getContentPane().add(button_3);
		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(195, 194, 332, 14);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Data:");
		label_1.setBounds(26, 216, 46, 14);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("Descrição:");
		label_2.setBounds(149, 216, 63, 14);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("Capacidade:");
		label_3.setBounds(26, 275, 69, 14);
		frame.getContentPane().add(label_3);
		
		label_5 = new JLabel("Preço:");
		label_5.setBounds(147, 275, 46, 14);
		frame.getContentPane().add(label_5);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setToolTipText("dd/mm/yy");
		formattedTextField.setBounds(26, 241, 79, 20);
		frame.getContentPane().add(formattedTextField);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setToolTipText("dd/mm/yy");
		formattedTextField_1.setBounds(149, 241, 79, 20);
		frame.getContentPane().add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setToolTipText("dd/mm/yy");
		formattedTextField_2.setBounds(26, 300, 79, 20);
		frame.getContentPane().add(formattedTextField_2);
		
		formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setToolTipText("dd/mm/yy");
		formattedTextField_3.setBounds(149, 300, 79, 20);
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
			System.out.println(lista);
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
