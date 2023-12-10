package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

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

		if (capacidade < 2){
			throw new Exception("A capacidade do evento deve ser de no mínimo 2");
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

	public static void criarParticipante(String cpf, String nascimento) throws Exception{
		if(repositorio.localizarParticipante(cpf) != null){
			throw new Exception("Este CPF já foi cadastrado.");
		}

		if(nascimento.isEmpty() || nascimento == null){
			throw new Exception("Não é possível criar participante sem data de nascimento.");
		}

		Participante participante = new Participante(cpf, nascimento);
		repositorio.adicionar(participante);
		repositorio.salvarObjetos();
	}

	public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception{
		if(repositorio.localizarParticipante(cpf) != null){
			throw new Exception("Este CPF já foi cadastrado");
		}
		if(empresa.isEmpty() || empresa == null){
			throw new Exception("A empresa não pode estar vazia");
		}
		
			Convidado convidado = new Convidado(cpf, nascimento, empresa);

			repositorio.adicionar(convidado);
			repositorio.salvarObjetos();
		
	}

	public static void criarIngresso(int idEvento, String cpf, String telefone) throws Exception {
		
		String codigo = idEvento + "-" + cpf;
		Evento evento = repositorio.localizarEvento(idEvento);
		
		if (repositorio.localizarIngresso(codigo) != null){
			throw new Exception("Este ingresso já foi criado");
		}

		if (evento == null){
			throw new Exception("Este evento não existe!");
		}

		if(telefone.isEmpty() || telefone == null){
			throw new Exception("Telefone não pode ser nulo.");
		}

		if(evento.quantidadeIngressos() >= evento.getCapacidade()){
			throw new Exception("Este evento está lotado. Não é possível criar ingresso.");
		}

		Participante participante = repositorio.localizarParticipante(cpf);

		if (participante == null){
			throw new Exception("Este participante não existe!");
		}

		
		Ingresso ingresso = new Ingresso(codigo, telefone, evento, participante);
		evento.adicionar(ingresso);
		participante.adicionar(ingresso);

		repositorio.adicionar(ingresso);
		repositorio.salvarObjetos();

	}

	public static void apagarEvento(int idEvento) throws Exception {
		if(repositorio.localizarEvento(idEvento) == null){
			throw new Exception("Este evento não existe!");
		}
		Evento e = repositorio.localizarEvento(idEvento);
		if(e.quantidadeIngressos() > 0 ){
			throw new Exception("Este evento não pode ser apagado, pois ainda possui ingressos!");
		}

		repositorio.remover(e);
		repositorio.salvarObjetos();
	}


	public static void apagarParticipante(String cpf) throws Exception{
		boolean ingressoValido = false;

		if (repositorio.localizarParticipante(cpf) == null){
			throw new Exception("Este participante não existe!");
		}

		Participante participante = repositorio.localizarParticipante(cpf);
		ArrayList<Ingresso> listaIngressos = participante.getIngressos();

		for(Ingresso i : listaIngressos) {
			LocalDate data_evento = LocalDate.parse(i.getEvento().getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate data_atual = LocalDate.now();

			if(data_evento.isAfter(data_atual)){
				ingressoValido = true;
				break;
			}
		}

		if (ingressoValido) {
			throw new Exception("O participante não pode ser apagado, pois ainda possui ingresso válido.");
		} else {
			for (Ingresso i : listaIngressos){
				repositorio.remover(i);
			}
		}

		repositorio.remover(participante);
		repositorio.salvarObjetos();

	}

	public static void apagarIngresso(String codigo) throws Exception {
		if (repositorio.localizarIngresso(codigo)== null) {
			throw new Exception("Este ingresso não existe");
		}
		int idEvento = Integer.parseInt(codigo.split("-")[0]);
		String cpf = codigo.split("-")[1];

		Participante participante = repositorio.localizarParticipante(cpf);
		Ingresso ingresso = repositorio.localizarIngresso(codigo);
		Evento evento = repositorio.localizarEvento(idEvento);

		participante.remover(ingresso);
		evento.remover(ingresso);

		repositorio.remover(ingresso);
		repositorio.salvarObjetos();

	}

	//public static ArrayList<Evento> listarEventos() - retornar todos os eventos do repositório

	public static ArrayList<Evento> listarEventos(){
		return repositorio.getEventos();
	}

	public static ArrayList<Ingresso> listarIngressos(){
		return repositorio.getIngressos();
	}

	public static ArrayList<Participante> listarParticipantes(){
		return repositorio.getParticipantes();
	}






}
