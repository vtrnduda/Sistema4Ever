package appswing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;



public class TelaEventos {

	private JFrame frame;
	private JTable eventoTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEventos window = new TelaEventos();
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
	public TelaEventos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		eventoTable = new JTable();
		eventoTable.setBounds(6, 6, 284, 260);
		

		
		
		frame.getContentPane().add(eventoTable);
		
				
		
		JButton btnCriarEvento = new JButton("Criar evento");
		btnCriarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCriarEvento.setBounds(302, 6, 142, 29);
		frame.getContentPane().add(btnCriarEvento);
		
		JButton btnExcluirEvento = new JButton("Excluir evento");
		btnExcluirEvento.setBounds(302, 47, 142, 29);
		frame.getContentPane().add(btnExcluirEvento);
		
		JButton btnListarIngressos = new JButton("Listar ingressos");
		btnListarIngressos.setBounds(302, 88, 142, 29);
		frame.getContentPane().add(btnListarIngressos);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVoltar.setBackground(UIManager.getColor("Button.background"));
		btnVoltar.setForeground(UIManager.getColor("Button.select"));
		btnVoltar.setBounds(302, 237, 142, 29);
		frame.getContentPane().add(btnVoltar);
	}
}
