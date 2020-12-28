package com.marcodes;

public class Contato {

	/* propriedades da classe seguindo o padrao JavaBean, que consiste
	 * no uso de Propriedades no modo privado e a utilizacao de getters 
	 * e setters para acessa-la em outras classes
	 */
	private String id;
	
	private String nome;
	
	private String telefone;

	public Contato() {}
	
	public Contato(String id, String nome, String telefone) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	
	/* este boolean serve para distinguir se o cadastro e novo 
	 * ou se estamos editando um ja existente.
	 * Precisamos usar o id para definir por ele ser um registro unico
	 */
	public boolean isNovo() {
		return id == null;
	}
	
	//Getters and Setters ordened	
	
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


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
