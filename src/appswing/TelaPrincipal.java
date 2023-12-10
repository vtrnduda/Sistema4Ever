package appswing;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class TelaPrincipal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuEventos;
	private JMenu menuParticipantes;
	private JMenu menuIngressos;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Sistema4Ever");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(new Color(0, 0, 0));
		menuBar.setBounds(0, 0, 454, 27);
		frame.getContentPane().add(menuBar);
		
		menuEventos = new JMenu("Eventos");
		menuEventos.setForeground(new Color(255, 255, 255));
		menuEventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaEventos tela = new TelaEventos();
			}
		});
		menuBar.add(menuEventos);
		
		menuParticipantes = new JMenu("Participantes");
		menuParticipantes.setForeground(new Color(255, 255, 255));
		menuBar.add(menuParticipantes);
		
		menuIngressos = new JMenu("Ingressos");
		menuIngressos.setForeground(new Color(255, 255, 255));
		menuBar.add(menuIngressos);
		
		label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(-11, 23, 477, 271);
		ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/imgPrincipal.jpg"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));//		label.setIcon(fotos);
		label.setIcon(imagem);
		
		frame.getContentPane().add(label);
	}
}
