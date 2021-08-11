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
 * Esta classe representa um desejo de hospedagem, é considerado um desejo de hospedagem quando o cliente deseja uma lista de
 * hospedagem em uma determinado estado/cidade, com uam quantidade de quartos e em um dado periodo.
 *
 * @author Eldrey
 */
@XmlRootElement
public class HospedagemDesejo {

    private String cidade;
    private String estado;
    private String periodoInicio;
    private String periodoFim;
    private Calendar periodoInicioC;
    private Calendar periodoFimC;
    private Integer numQuartoDesejo;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public HospedagemDesejo() {
    }

    public HospedagemDesejo(String cidade, String estado, String periodoInicio, String periodoFim, int numQuartoDesejo) {
        this.cidade = cidade;
        this.estado = estado;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.numQuartoDesejo = numQuartoDesejo;
    }

    public Calendar getPeriodoInicioC() {
        return periodoInicioC;
    }

    public Calendar getPeriodoFimC() {
        return periodoFimC;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public String getPeriodoFim() {
        return periodoFim;
    }

    public Integer getNumQuartoDesejo() {
        return numQuartoDesejo;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public void setPeriodoFim(String periodoFim) {
        this.periodoFim = periodoFim;
    }

    /**
     * Este método converte uma String periodo em Calendar. A String deve estar no formato dd/MM/yyyy. Ele foi criado, pois no XML
     * não é passado um Calendar mas uma String.
     *
     */
    public void convertPeriodoCalendar() {
        System.out.println("Fim:" + periodoFim);
        this.periodoFimC = Calendar.getInstance();
        try {
            this.periodoFimC.setTime(sdf.parse(periodoFim));
        } catch (ParseException ex) {
            System.out.println("ERRO AO CONVERTER O PERIODO FIM PARA CALENDAR");
            Logger.getLogger(Hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Inicio:" + periodoInicio);
        this.periodoInicioC = Calendar.getInstance();
        try {
            this.periodoInicioC.setTime(sdf.parse(periodoInicio));
        } catch (ParseException ex) {
            System.out.println("ERRO AO CONVERTER O PERIODO INICIO PARA CALENDAR");
            Logger.getLogger(Hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNumQuartoDesejo(Integer numQuartoDesejo) {
        this.numQuartoDesejo = numQuartoDesejo;
    }

}
