/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta classe representa uma reserva de hospedagem.
 *
 * @author Eldrey
 */
@XmlRootElement
public class Reserva {

    private String numCartao;
    private int numVezes;
    private int codSeg;
    private int cpf;
    private int numReservas;
    private int codigo;
    private String nomeCliente;

    public Reserva() {
    }

    public Reserva(String numCartao, int numVezes, int codSeg, int cpf, int numQuartosReserva, int codigoHospedagem, String nomeCliente) {
        this.numCartao = numCartao;
        this.numVezes = numVezes;
        this.codSeg = codSeg;
        this.cpf = cpf;
        this.numReservas = numQuartosReserva;
        this.codigo = codigoHospedagem;
        this.nomeCliente = nomeCliente;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumVezes() {
        return numVezes;
    }

    public void setNumVezes(int numVezes) {
        this.numVezes = numVezes;
    }

    public int getCodSeg() {
        return codSeg;
    }

    public void setCodSeg(int codSeg) {
        this.codSeg = codSeg;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public void setNumReservas(int numQuartosReserva) {
        this.numReservas = numQuartosReserva;
    }

}
