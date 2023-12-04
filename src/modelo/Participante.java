package modelo;

import java.util.ArrayList;

public class Participante {
		private String cpf;
		private String nascimento;
		private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
		
		
		public Participante(String cpf, String nascimento) {
			this.cpf = cpf;
			this.nascimento = nascimento;
		}
		
		public String getCPF (String cpf) {
			return this.cpf;
		}

		public void adicionar(Ingresso ingresso) {
			// TODO Auto-generated method stub
			
		}
		
		
}
