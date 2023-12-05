package regras_negocio;

import java.util.Random;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();
	private static int idEvento = 1;
	private Fachada() {};

	public static void criarEvento(String data, String descricao, int capacidade, double preco){

		while(true){
			idEvento++;

			if (repositorio.localizarEvento(idEvento) == null) {
				break;
			}
		}
		
		Evento evento = new Evento(idEvento, data, descricao, capacidade, preco);

		repositorio.adicionar(evento);
		repositorio.salvarObjetos();

	}
	


}
