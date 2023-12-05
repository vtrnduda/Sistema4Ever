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

	public String getCodigo(){
		return this.codigo;
	}

	public String getTelefone(){
		return this.telefone;
	}
	
	public double calcularPreco() {
    	double preco = evento.getPreco();
    	int idade = participante.calcularIdade();

    	if (idade < 18) {
        	preco *= 0.9;
    	} else if (idade >= 60) {
        	preco *= 0.8;
    	}

    	if (participante instanceof Convidado) {
        	preco *= 0.5;
    	}
		
    	return preco;
	}

		
}
