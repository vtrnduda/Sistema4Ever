package modelo;

public class Ingresso {
	private String codigo;
	private String telefone;
	private Evento evento;
	private Participante participante;
	
	
	public Ingresso (String codigo, String telefone, Evento evento, Participante participante) {
		this.codigo = codigo;
		this.telefone = telefone;
		this.evento = evento;
		this.participante = participante;
	}
	public double calcularPreco() {
		return 0;
		//todo
	}
		
}
