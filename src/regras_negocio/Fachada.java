package regras_negocio;

import java.util.Random;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();
	private Fachada() {};

	public static void criarEvento(String data, String descricao, int capacidade, double preco){

		Random random = new Random();
		int id;

		while(true) {
			id = random.nextInt(100000);

			if (repositorio.localizarEvento(id) == null) {
				break;
			}
		} 
		
		Evento evento = new Evento(int id, String data, String descricao, int capacidade, double preco);

		repositorio.adicionar(evento);
		repositorio.salvarObjetos();

	}
	


}
