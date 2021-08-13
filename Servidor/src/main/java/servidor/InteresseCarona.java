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

    public InteresseCarona() {

    }    
    
    public InteresseCarona(String nome, String telefone, String origem, String destino, String data) {
        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
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
      
}