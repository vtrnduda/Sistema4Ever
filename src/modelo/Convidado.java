package modelo;

public class Convidado extends Participante{
	String empresa;
	
	public Convidado (String cpf, String nascimento, String empresa) {
		super(cpf, nascimento);
		this.empresa = empresa;
	}
	
	
	public String getEmpresa() {
		return this.empresa;
	}

}
