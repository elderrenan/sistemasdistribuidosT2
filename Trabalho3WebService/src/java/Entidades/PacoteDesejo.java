/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eldrey
 */
public class PacoteDesejo {

    private String dataIda;
    private String dataRetorno;
    private int quantPessoas;
    private Calendar dataIdaC;
    private Calendar dataRetornoC;
    private String cidadeOrigem;
    private String cidadeDestino;
    private String estadoOrigem;
    private String estadoDestino;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PacoteDesejo() {
    }

    public PacoteDesejo(String dataIda, String dataRetorno, int quantPessoas, String cidadeOrigem, String cidadeDestino, String estadoOrigem, String estadoDestino) {
        this.dataIda = dataIda;
        this.dataRetorno = dataRetorno;
        this.quantPessoas = quantPessoas;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.estadoOrigem = estadoOrigem;
        this.estadoDestino = estadoDestino;
    }

    public String getDataIda() {
        return dataIda;
    }

    public void setDataIda(String dataIda) {
        this.dataIda = dataIda;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public int getQuantPessoas() {
        return quantPessoas;
    }

    public void setQuantPessoas(int quantPessoas) {
        this.quantPessoas = quantPessoas;
    }

    public Calendar getDataIdaC() {
        return dataIdaC;
    }

    public void setDataIdaC(Calendar dataIdaC) {
        this.dataIdaC = dataIdaC;
    }

    public Calendar getDataRetornoC() {
        return dataRetornoC;
    }

    public void setDataRetornoC(Calendar dataRetornoC) {
        this.dataRetornoC = dataRetornoC;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    /**
     * Este método converte as Strings dataIda e dataRetorno em Calendar. As Strings devem estar no formato dd/MM/yyyy. Ele foi
     * criado, pois no XML não é passado um Calendar mas uma String.
     *
     */
    public void converteDataCalendar() {
        this.dataIdaC = Calendar.getInstance();
        this.dataRetornoC = Calendar.getInstance();
        try {
            this.dataIdaC.setTime(sdf.parse(dataIda));
            this.dataRetornoC.setTime(sdf.parse(dataRetorno));
        } catch (ParseException ex) {
            System.out.println("ERRO AO CONVERTER A DATA PARA CALENDAR");
            Logger.getLogger(PassagemDesejo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
