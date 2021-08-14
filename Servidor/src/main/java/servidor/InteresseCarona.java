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

public class InteresseCarona {
    
    private String nome;
    private String telefone;
    private String origem;
    private String destino;
    private String data;   
    
    public InteresseCarona(String nome, String telefone, String origem, String destino, String data) {
        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
    }
    
    public InteresseCarona() {

    }    

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getData() {
        return data;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public void setData(String data) {
		this.data = data;
	}
  
	@Override
	public String toString() {
		StringBuilder cliente = new StringBuilder();
		cliente.append("{");
		cliente.append(getNome());
		cliente.append(", ");
		cliente.append(getTelefone());
		cliente.append("}");

		return cliente.toString();
	}  	
    
}