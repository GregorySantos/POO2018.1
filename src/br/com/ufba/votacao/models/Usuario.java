package br.com.ufba.votacao.models;

import java.util.ArrayList;

/**Classe modelo para objetos do tipo Usuario, onde serão 
 * contidos os dados de um usuario.
* @author Gregory Santos
* @author Yndyra Pinheiro
* @author Mauro Meneses
* @author Yuri Oliveira
* @author Guilherme Costa
* @author Lucas
* @version 1.00
* @since Release 01 da aplicação
*/

public class Usuario {
	private String id, nome, senha;
	private int qtdEnquetesVotadas;
	private ArrayList<String> jaVotados;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getQtdEnquetesVotadas() {
		return qtdEnquetesVotadas;
	}

	public void setQtdEnquetesVotadas(int qtdEnquetesVotadas) {
		this.qtdEnquetesVotadas = qtdEnquetesVotadas;
	}

	public ArrayList<String> getJaVotados() {
		return jaVotados;
	}

	public Usuario(String data) {
		String aux[] = data.split(":");
		id = aux[0];
		nome = aux[1];
		senha = aux[2];
		jaVotados = new ArrayList<String>();
		qtdEnquetesVotadas = Integer.valueOf(aux[3]);
		
		for(int i = 4, j = 0; j < qtdEnquetesVotadas; i++,j++) {
			jaVotados.add(aux[i]);
		}
	}
}
