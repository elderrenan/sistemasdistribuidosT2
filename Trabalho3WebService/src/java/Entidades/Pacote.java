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
 * Esta classe representa um pacote com hospedagem, passagem de ida e passagem de volta.Um pacote é contituido por hospedagem,
 * passagens, valor, número de pessoas, data de partida e retorno, estado e cidade de origem e destino.
 *
 * @author Eldrey
 */
@XmlRootElement
public class Pacote {

    private int codigo;
    private int codHospedagen;
    private int codPassagemIda;
    private int codPassagemVolta;
    private double valorPacote;
    private int vagasDisponíveis;
    private String dataPartida;
    private String dataRetorno;
    private String estadoOrigem;
    private String cidadeOrigem;
    private String estadoDestio;
    private String cidadeDestino;
    private Calendar dataPartidaC;
    private Calendar dataRetornoC;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Pacote(int codigo, int codHospedagen, int codPassagemIda, int codPassagemVolta, double valorPacote, int vagasDisponíveis, String dataPartida, String dataRetorno, String estadoOrigem, String cidadeOrigem, String estadoDestio, String cidadeDestino) {
        this.codigo = codigo;
        this.codHospedagen = codHospedagen;
        this.codPassagemIda = codPassagemIda;
        this.codPassagemVolta = codPassagemVolta;
        this.valorPacote = valorPacote;
        this.vagasDisponíveis = vagasDisponíveis;
        this.dataPartida = dataPartida;
        this.dataRetorno = dataRetorno;
        this.estadoOrigem = estadoOrigem;
        this.cidadeOrigem = cidadeOrigem;
        this.estadoDestio = estadoDestio;
        this.cidadeDestino = cidadeDestino;
        this.dataPartidaC = Calendar.getInstance();
        this.dataRetornoC = Calendar.getInstance();
        try {
            this.dataPartidaC.setTime(sdf.parse(dataPartida));
            this.dataRetornoC.setTime(sdf.parse(dataRetorno));
        } catch (ParseException ex) {
            Logger.getLogger(Hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pacote() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodHospedagen() {
        return codHospedagen;
    }

    public void setCodHospedagen(int codHospedagen) {
        this.codHospedagen = codHospedagen;
    }

    public int getCodPassagemIda() {
        return codPassagemIda;
    }

    public void setCodPassagemIda(int codPassagemIda) {
        this.codPassagemIda = codPassagemIda;
    }

    public int getCodPassagemVolta() {
        return codPassagemVolta;
    }

    public void setCodPassagemVolta(int codPassagemVolta) {
        this.codPassagemVolta = codPassagemVolta;
    }

    public int getVagasDisponíveis() {
        return vagasDisponíveis;
    }

    public void setVagasDisponíveis(int vagasDisponíveis) {
        this.vagasDisponíveis = vagasDisponíveis;
    }

    public Calendar getDataRetornoC() {
        return dataRetornoC;
    }

    public void setDataRetornoC(Calendar dataRetornoC) {
        this.dataRetornoC = dataRetornoC;
    }

    public double getValorPacote() {
        return valorPacote;
    }

    public void setValorPacote(double valorPacote) {
        this.valorPacote = valorPacote;
    }

    public int getNumPessoas() {
        return vagasDisponíveis;
    }

    public void setNumPessoas(int numPessoas) {
        this.vagasDisponíveis = numPessoas;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
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

    public String getEstadoDestio() {
        return estadoDestio;
    }

    public void setEstadoDestio(String estadoDestio) {
        this.estadoDestio = estadoDestio;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public Calendar getDataPartidaC() {
        return dataPartidaC;
    }

    public void setDataPartidaC(Calendar dataPartidaC) {
        this.dataPartidaC = dataPartidaC;
    }

    public Calendar getDataPartidaF() {
        return dataRetornoC;
    }

    public void setDataPartidaF(Calendar dataPartidaF) {
        this.dataRetornoC = dataPartidaF;
    }

    /**
     * Método que valida o periodo desejado com o periodo cadastrado pela hospedagem. Para fazer a validação o método recebe duas
     * Strings, pIni e pFim, que representa respectivamente a data de inicio e de fim do período desejado pelo usuário. Essas
     * datas estão no formato "dd/mm/yyyy" A função retorna TRUE se o período desejado estiver contido no periodo cadastrada para
     * a hospedagem e FALSE caso contrário.
     *
     * @param pIni - String que representa a data de inicio do período desejado.
     * @param pFim - String que representa a data de fim do periodo desejado.
     * @return
     */
    public boolean validaPeríodo(Calendar pIni, Calendar pFim) {
        if (pIni.after(dataPartidaC) || pIni.equals(dataPartidaC)) {
            if (pFim.before(dataRetornoC) || pFim.equals(dataRetornoC)) {
                return true;
            } else {
                System.err.println("[ATENÇÃO] PERIODO FIM SOLICITADO É MAIOR QUE O CADASTRADO");
                return false;
            }
        } else {
            System.err.println("[ATENÇÃO] PERIODO INICIO SOLINITADO É MENOR QUE O CADASTRADO");
            return false;
        }
    }
}
