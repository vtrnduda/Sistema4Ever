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

import modelo.Convidado;

import modelo.Ingresso;
import modelo.Participante;
import regras_negocio.Fachada;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import javax.swing.SwingConstants;


public class TelaParticipantes {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnApagarParticipante;
	private JLabel label_4;
	private JButton btnCriarParticipante;
	private JButton btnVoltar;
	private JButton btnExibirIngressos;
	private JLabel label;
	private JLabel lblCpf;
	private JLabel lblEmpresa;
	private JLabel lblDataDeNascimento;
	private JFormattedTextField cpfField;
	private JFormattedTextField empresaField;
	private JFormattedTextField dataNascField;


	public TelaParticipantes() {
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
		frame.setTitle("Participantes");
		frame.setBounds(100, 100, 575, 430);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 11, 523, 172);
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

		// APAGAR PARTICIPANTE
		btnApagarParticipante = new JButton("Apagar Participante");
		btnApagarParticipante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApagarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						
						String cpf =  (String) table.getValueAt( table.getSelectedRow(), 0);
						// Confirmação
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão "+ cpf, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.apagarParticipante(cpf);
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
		btnApagarParticipante.setBounds(389, 251, 160, 23);
		frame.getContentPane().add(btnApagarParticipante);

		label_4 = new JLabel("selecione uma linha");
		label_4.setBounds(26, 181, 315, 14);
		frame.getContentPane().add(label_4);


		
		// CRIAR PARTICIPANTE 
		btnCriarParticipante = new JButton("Criar Participante/Convidado");
		btnCriarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String cpf = cpfField.getText();
					String dataNasc = dataNascField.getText();
					String empresa = empresaField.getText();
					
					
						
					if(cpf.matches("\\d+") && dataNasc.matches("\\d{2}/\\d{2}/\\d{4}")){
							label.setText("");
							if(empresa.isEmpty())
								Fachada.criarParticipante(cpf, dataNasc);
					        else
					        	Fachada.criarConvidado(cpf, dataNasc, empresa);
					        listagem();
					        
					} else {
						label.setText("CPF deve ser numérico e Data de Nascimento deve seguir o formato dd/mm/aaaa");
					}
				
					
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarParticipante.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarParticipante.setBounds(26, 346, 303, 29);
		frame.getContentPane().add(btnCriarParticipante);

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
		btnVoltar.setBounds(389, 349, 160, 23);
		frame.getContentPane().add(btnVoltar);

		// EXIBIR INGRESSOS
		btnExibirIngressos = new JButton("Exibir Ingressos");
		btnExibirIngressos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 0);
						
						List<Participante> participantes = Fachada.listarParticipantes();
						List<Ingresso> ingressos = null;
						for(Participante p : participantes) {
							if(p.getCpf().equals(cpf)) {
								ingressos = p.getIngressos();
							}
						}
						
						String titulo = "Ingressos do CPF: " + String.valueOf(cpf);
						String listagemIngressos = "";
						if(ingressos.size() > 0) 
							for(Ingresso i : ingressos) 
								listagemIngressos += i.getCodigo() + " - " + i.getTelefone() + "\n";
						 else 
							listagemIngressos = "Este participante não possui ingressos cadastrados";
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
		btnExibirIngressos.setBounds(389, 286, 160, 23);
		frame.getContentPane().add(btnExibirIngressos);
		
		label = new JLabel("");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
//		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setForeground(Color.RED);
		label.setBounds(26, 204, 501, 14);
		frame.getContentPane().add(label);
		
		lblCpf = new JLabel("CPF: ");
		lblCpf.setBounds(26, 230, 46, 14);
		frame.getContentPane().add(lblCpf);
		
		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(26, 285, 90, 14);
		frame.getContentPane().add(lblEmpresa);
		
		lblDataDeNascimento = new JLabel("Data de nascimento:");
		lblDataDeNascimento.setBounds(184, 230, 145, 14);
		frame.getContentPane().add(lblDataDeNascimento);
		
		cpfField = new JFormattedTextField();
		cpfField.setBounds(26, 251, 145, 23);
		frame.getContentPane().add(cpfField);
		
		empresaField = new JFormattedTextField();
		empresaField.setBounds(26, 311, 303, 23);
		frame.getContentPane().add(empresaField);
		
		dataNascField = new JFormattedTextField();
		dataNascField.setToolTipText("dd/mm/yyyy");
		dataNascField.setBounds(184, 251, 145, 23);
		frame.getContentPane().add(dataNascField);



	}

	public void listagem () {
		try{
			List<Participante> lista = Fachada.listarParticipantes();

			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("CPF");
			model.addColumn("Data de nascimento");
			model.addColumn("Idade");
			model.addColumn("Empresa");
			

			for(Participante p : lista)
				if(p instanceof Convidado)
					model.addRow(new Object[]{p.getCpf(), p.getNascimento(), p.calcularIdade(), ((Convidado) p).getEmpresa() });
				else
					model.addRow(new Object[]{p.getCpf(), p.getNascimento(), p.calcularIdade(), "#" });

			table.setModel(model);
			label_4.setText("resultados: "+lista.size()+ " linhas  - selecione uma linha");
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(2).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}

// TODO Criar functions p/ o label

