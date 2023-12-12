package appswing;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.UIManager;

import regras_negocio.Fachada;

public class TelaPrincipal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuEventos;
	private JMenu menuParticipantes;
	private JMenu menuIngressos;
	private JLabel label;
	private Timer timer;

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
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				//JOptionPane.showMessageDialog(frame, "at� breve !");
				timer.stop();
			}
			
		});
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
				TelaEventos telaEventos = new TelaEventos();
			}
		});
		
		menuBar.add(menuEventos);
		
		menuParticipantes = new JMenu("Participantes");
		menuParticipantes.setForeground(new Color(255, 255, 255));
		menuParticipantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaParticipantes telaParticipantes = new TelaParticipantes();
			}
		});
		
		menuBar.add(menuParticipantes);
		
		menuIngressos = new JMenu("Ingressos");
		menuIngressos.setForeground(new Color(255, 255, 255));
		menuIngressos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaIngressos telaIngressos = new TelaIngressos();
			}
		});
		menuBar.add(menuIngressos);
		
		label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(-11, 23, 477, 271);
		ImageIcon imagem = new ImageIcon(getClass().getResource("/arquivos/imgPrincipal.jpg"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));//		label.setIcon(fotos);
		label.setIcon(imagem);
		
		frame.getContentPane().add(label);
		
		
		frame.setVisible(true);

		//----------timer----------------
				timer = new Timer(0, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
						frame.setTitle("Sistema4Ever - "+ dt);
						
					}
				});
				timer.setRepeats(true);
				timer.setDelay(1000);	//1segundos
				timer.start();			//inicia o temporizador

	}
}
