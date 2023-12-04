package modelo;

import java.util.ArrayList;

public class Evento {
	private int id;
	private String data;
	private String descricao;
	private int capacidade;
	private double preco;
	private boolean lotado;
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


	public void adicionar(Ingresso ingresso) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
