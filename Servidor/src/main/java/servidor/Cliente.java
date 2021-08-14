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
 
    public Cliente() {

    }  
    
    public Cliente(int id, String nome, String telefone) {
    	this.id = id;
        this.nome = nome;
        this.telefone = telefone;
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

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Cliente) {
			Cliente c = (Cliente) obj;
			result = c.getId() == this.getId();
		}

		return result;
	}    
  
	@Override
	public int hashCode() {

		return getId() ^ 7;
	}	
	
	@Override
	public String toString() {
		StringBuilder cliente = new StringBuilder();
		cliente.append("{ ");
		cliente.append(getId());
		cliente.append(", ");
		cliente.append(getNome());
		cliente.append(", ");
		cliente.append(getTelefone());
		cliente.append(" }");

		return cliente.toString();
	}    
    
}