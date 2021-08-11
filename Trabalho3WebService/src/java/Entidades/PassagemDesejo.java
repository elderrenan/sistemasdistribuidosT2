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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eldrey
 */
@XmlRootElement
public class PassagemDesejo {

    private String estadoOrigem;
    private String cidadeOrigem;
    private String estadoDestino;
    private String cidadeDestino;
    private String dataViagem;
    private int numPassagens;
    private boolean idaVolta;
    private Calendar dataViagemC;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PassagemDesejo() {
    }

    public PassagemDesejo(String estadoOrigem, String cidadeOrigem, String estadoDestino, String cidadeDestino, String dataViagem, 
            int numPassagens, boolean idaVolta) {
        this.estadoOrigem = estadoOrigem;
        this.cidadeOrigem = cidadeOrigem;
        this.estadoDestino = estadoDestino;
        this.cidadeDestino = cidadeDestino;
        this.dataViagem = dataViagem;
        this.numPassagens = numPassagens;
        this.idaVolta = idaVolta;
    }

    public Calendar getDataViagemC() {
        return dataViagemC;
    }

    public void setDataViagemC(Calendar dataViagemC) {
        this.dataViagemC = dataViagemC;
    }

    public String getEstadoOrigem() {
        return estadoOrigem;
    }

    public void setEstadoOrigem(String estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public String getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(String dataViagem) {
        this.dataViagem = dataViagem;
    }

    public int getNumPassagens() {
        return numPassagens;
    }

    public void setNumPassagens(int numPassagens) {
        this.numPassagens = numPassagens;
    }

    public boolean isIdaVolta() {
        return idaVolta;
    }

    public void setIdaVolta(boolean idaVolta) {
        this.idaVolta = idaVolta;
    }

    /**
     * Este método converte uma String dataViagem em Calendar. A String deve estar no formato dd/MM/yyyy. Ele foi criado, pois no
     * XML não é passado um Calendar mas uma String.
     *
     */
    public void converteDataCalendar() {
        System.out.println("data:" + dataViagem);
        this.dataViagemC = Calendar.getInstance();
        try {
            this.dataViagemC.setTime(sdf.parse(dataViagem));
        } catch (ParseException ex) {
            System.out.println("ERRO AO CONVERTER A DATA PARA CALENDAR");
            Logger.getLogger(PassagemDesejo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
