package modelo;

import java.util.ArrayList;

public class Evento {
	private int id;
	private String data;
	private String descricao;
	private int capacidade;
	private double preco;
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
	
	
	public Evento (int id, String data, String descricao, int capacidade,  double preco) {
		 this.id = id;
		 this.data = data;
		 this.descricao = descricao;
		 this.capacidade = capacidade;
		 this.preco = preco;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getData() {
		return this.data;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public int getCapacidade(){
		return this.capacidade;
	}

	public double getPreco(){
		return this.preco;
	}

	public void adicionar(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public void remover(Ingresso ingresso){
			ingressos.remove(ingresso);
	}

	public boolean lotado(){
		return ingressos.size() >= capacidade;
	}

	public int quantidadeIngressos(){
		return ingressos.size();
	}

	public double totalArrecadado(){
		double total = 0;
		for (Ingresso i : ingressos){
			total += i.calcularPreco();
		}

		return total;
	}
	
	
	

}
