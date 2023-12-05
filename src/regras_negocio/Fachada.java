package regras_negocio;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import repositorio.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();
	private static int idEvento = 0;
	private Fachada() {};

	public static void criarEvento(String data, String descricao, int capacidade, double preco) throws Exception {

		if (preco < 0){
			throw new Exception("Não é possível criar um evento com preço negativo.");
		}

		if (data == null || data.isEmpty() || descricao == null || descricao.isEmpty()){
			throw new Exception("Não é possível criar evento sem data ou descrição.");
		}

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
