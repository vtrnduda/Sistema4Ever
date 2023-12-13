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
import javax.swing.ListSelectionModel;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import modelo.Ingresso;
import regras_negocio.Fachada;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;


public class TelaIngressos {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnApagarIngresso;
	private JLabel label_4;
	private JButton btnCriarIngresso;
	private JButton btnVoltar;
	private JLabel label;
	private JLabel lblIdDoEvento;
	private JLabel lblTelefone;
	private JLabel lblCpf;
	private JFormattedTextField idField;
	private JFormattedTextField telefoneField;
	private JFormattedTextField cpfField;


	public TelaIngressos() {
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
		frame.setTitle("Ingressos");
		frame.setBounds(100, 100, 575, 430);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 11, 510, 172);
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

		// APAGAR Ingresso
		btnApagarIngresso = new JButton("Apagar Ingresso");
		btnApagarIngresso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						
						String codIngresso =  (String) table.getValueAt( table.getSelectedRow(), 0);
						// Confirmação
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão "+ codIngresso, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarIngresso(codIngresso);
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
		btnApagarIngresso.setBounds(376, 264, 160, 23);
		frame.getContentPane().add(btnApagarIngresso);

		label_4 = new JLabel("selecione uma linha");
		label_4.setBounds(26, 181, 315, 14);
		frame.getContentPane().add(label_4);


		
		// CRIAR Ingresso 
		btnCriarIngresso = new JButton("Criar Ingresso");
		btnCriarIngresso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String idTexto = idField.getText();
					String cpf = cpfField.getText();
					String telefone = telefoneField.getText();
					
					
					if (!idTexto.isEmpty() && !cpf.isEmpty() && !telefone.isEmpty()) {
					        label.setText("");
							int id = Integer.parseInt(idTexto);
					        
					        Fachada.criarIngresso(id, cpf, telefone);
					        listagem();
					        
					} else {
						label.setText("Todos os campos devem ser preenchidos!");
					}
				
					
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarIngresso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarIngresso.setBounds(26, 352, 303, 29);
		frame.getContentPane().add(btnCriarIngresso);

		// VOLTAR
		btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(new Color(255, 0, 0));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});
		btnVoltar.setToolTipText("");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVoltar.setBounds(376, 358, 160, 23);
		frame.getContentPane().add(btnVoltar);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setForeground(Color.RED);
		label.setBounds(36, 204, 491, 15);
		frame.getContentPane().add(label);
		
		lblIdDoEvento = new JLabel("Id do Evento:");
		lblIdDoEvento.setBounds(26, 243, 90, 14);
		frame.getContentPane().add(lblIdDoEvento);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(26, 296, 90, 14);
		frame.getContentPane().add(lblTelefone);
		
		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(128, 243, 79, 14);
		frame.getContentPane().add(lblCpf);
		
		idField = new JFormattedTextField();
		idField.setBounds(26, 264, 90, 24);
		frame.getContentPane().add(idField);
		
		telefoneField = new JFormattedTextField();

		telefoneField.setBounds(26, 317, 303, 24);
		frame.getContentPane().add(telefoneField);
		
		cpfField = new JFormattedTextField();
		cpfField.setBounds(128, 264, 201, 24);
		frame.getContentPane().add(cpfField);



	}

	public void listagem () {
		try{
			List<Ingresso> lista = Fachada.listarIngressos();

			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Cód.");
			model.addColumn("Telefone");
			model.addColumn("Preço do ingresso");
			model.addColumn("Preço do evento");
			model.addColumn("Data do evento");
			model.addColumn("Idade");
			
			
			for(Ingresso i : lista)	
				model.addRow(new Object[]{i.getCodigo(), i.getTelefone(), i.calcularPreco(), i.getEvento().getPreco(), i.getEvento().getData(),i.getParticipante().calcularIdade()});
		
			table.setModel(model);
			label_4.setText("resultados: "+lista.size()+ " linhas  - selecione uma linha");
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(5).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
