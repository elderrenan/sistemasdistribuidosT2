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
 * Essa classe representa uma passagem (apenas ida, uma vez que a volta seria uma ida com a origem e o destido invertidos). Uma
 * passagem aéria é composta pelo estado e cidade de origem e destino, data de partida, nome da compania e o valor. Esta classe
 * tambem detem a informação da quantidade de passagens disponíveis.
 *
 * @author Eldrey
 */
@XmlRootElement
public class Passagem {

    private String estadoOrigem;
    private String cidadeOrigem;
    private String estadoDestino;
    private String cidadeDestino;
    private String compania;
    private String dataInicio;
    private String dataFim;
    private int quantPassagemDisponivel;
    private int codigo;
    private double valor;
    private Calendar dataInicioC;
    private Calendar dataFimC;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Passagem() {
    }

    /**
     * Construtor da classe.
     *
     * @param codigo
     * @param estadoOrigem
     * @param cidadeOrigem
     * @param estadoDestino
     * @param cidadeDestino
     * @param compania
     * @param dataInicio
     * @param dataFim
     * @param data
     * @param quantPassagemDisponivel
     * @param valor
     */
    public Passagem(int codigo, String estadoOrigem, String cidadeOrigem, String estadoDestino, String cidadeDestino,
            String compania, String dataInicio, String dataFim, int quantPassagemDisponivel, double valor) {
        this.codigo = codigo;
        this.estadoOrigem = estadoOrigem;
        this.cidadeOrigem = cidadeOrigem;
        this.estadoDestino = estadoDestino;
        this.cidadeDestino = cidadeDestino;
        this.compania = compania;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantPassagemDisponivel = quantPassagemDisponivel;
        this.valor = valor;
        this.dataInicioC = Calendar.getInstance();
        this.dataFimC = Calendar.getInstance();
        try {
            this.dataInicioC.setTime(sdf.parse(dataInicio));
            this.dataFimC.setTime(sdf.parse(dataFim));
        } catch (ParseException ex) {
            Logger.getLogger(Hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getQuantPassagemDisponivel() {
        return quantPassagemDisponivel;
    }

    /**
     * Todas as vezes que uma passagem for vendida será subtraido da quantidade de passagens disponíveis, independente se a venda
     * foi feita apenas da passagem (ida, volta, ida e volta) ou em um pacote. É considerado que sempre o numero de passagens
     * vendidas é IGUAL ou MENOR que a quantidade de passagens disponíves com essas caracteristicas.
     *
     * @param numPassagens - Número de passágens vendidas.
     */
    public void setQuantPassagemDisponivel(int numPassagens) {
        quantPassagemDisponivel = quantPassagemDisponivel - numPassagens;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Este étodo valida se a data desejada da passagem está dentro do período cadastrado.
     *
     * @param dataViagemC - Calendar que representa a data pretendida de viagem.
     * @return
     */
    public boolean validaData(Calendar dataViagemC) {
        System.out.println("[ATENÇÃO] DATA VIAGEM CALENDAR: " + dataViagemC.toString());
        System.out.println("[ATENÇÃO] CADASTRADO DE: " + dataInicio);
        if (dataViagemC.after(dataInicioC) || dataViagemC.equals(dataInicioC)) {
            if (dataViagemC.before(dataFimC) || dataViagemC.equals(dataFimC)) {
                return true;
            } else {
                System.out.println("[ATEBNÇÃO] - DATA É SUPERIOS A DATA CADASTRADA");
                return false;
            }
        } else {
            System.out.println("[ATENÇÃO] - DATA É INFERIO A DATA CADASTRADA");
            return false;
        }

    }

}
