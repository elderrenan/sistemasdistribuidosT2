/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author elder
 */

public class Cliente {
    
    private String nome;
    private String telefone;
    private int id; 
   
    public Cliente(int id, String nome, String telefone) {
    	this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }
    
    public Cliente() {

    }    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
    	this.nome = nome;
    }    
    
    public void setTelefone(String telefone) {
    	this.telefone = telefone;
    }
    
    public String getTelefone() {
    	return telefone;
    }       
    
}