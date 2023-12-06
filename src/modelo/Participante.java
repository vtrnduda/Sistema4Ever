package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Participante {
		private String cpf;
		private String nascimento;
		private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
		
		
		public Participante(String cpf, String nascimento) {
			this.cpf = cpf;
			this.nascimento = nascimento;
		}
		
		public String getCpf() {
			return this.cpf;
		}
		
		public String getNascimento(){
			return this.nascimento;
		}

		public ArrayList<Ingresso> getIngressos() {
			return ingressos;
		}

		public void adicionar(Ingresso ingresso) {
			ingressos.add(ingresso);
		}

		public void remover(Ingresso ingresso){
			ingressos.remove(ingresso);
		}

		public int calcularIdade(){
        	LocalDate data_nasc = LocalDate.parse(this.nascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        	LocalDate data_atual = LocalDate.now();
        	return Math.toIntExact(ChronoUnit.YEARS.between(data_nasc, data_atual));
    	}
		
		
}
