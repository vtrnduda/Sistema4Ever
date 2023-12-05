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

	public static void criarParticipante(String cpf, String nascimento){
		Participante participante = new Participante(cpf, nascimento);

		repositorio.adicionar(participante);
		repositorio.salvarObjetos();

	}

	public static void criarConvidado(String cpf, String nascimento, String empresa) {
		if (empresa != null){
			Convidado convidado = new Convidado(cpf, nascimento, empresa);

			repositorio.adicionar(convidado);
			repositorio.salvarObjetos();
		}
	}

	public static void criarIngresso(int idEvento, String cpf, String telefone) throws Exception {
		Evento evento = repositorio.localizarEvento(idEvento);

		if (evento == null){
			throw new Exception("Este evento não existe!");
		}

		Participante participante = repositorio.localizarParticipante(cpf);

		if (participante == null){
			throw new Exception("Este participante não existe!");
		}

		//Ingresso ingresso = new Ingresso(cpf, telefone, evento, participante);





	}


}
