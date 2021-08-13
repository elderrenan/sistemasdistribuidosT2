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
public class InteressePassageiro {

    private String nome;
    private String telefone;
    private String origem;
    private String destino;
    private String data;
    private int passageiros;

    public InteressePassageiro() {

    }     
    
    public InteressePassageiro(String nome, String telefone, String origem, String destino, String data, int passageiros) {

        this.nome = nome;
        this.telefone = telefone;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.passageiros = passageiros;
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

    public int getPassageiros() {
        return passageiros;
    }  
    
}