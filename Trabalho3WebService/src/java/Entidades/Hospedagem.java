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
 * A classe Hospedagem representa uma hospedagem com: Nome da hospedagem; Estado e Cidade onde a hospedagem se emcontra; Numero de
 * Quartos que estão disponiveis; Periono em que esses quartos estão disponíveis; Valor da diária para os quartos cadastrados.
 *
 * Uma mesma hospedagem pode ter vários quartos distintos cadastrados.
 *
 * @author Eldrey
 */
@XmlRootElement
public class Hospedagem {

    private String nome, cidade, estado;
    private int numQuartoDisponivel;
    private int codigo;
    private Calendar periodoInicio, periodoFim;
    private double valorDiaria;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Hospedagem() {
    }

    /**
     * Construtor da classe.
     *
     * @param codigo
     * @param nome
     * @param cidade
     * @param estado
     * @param numQuartoDisponivel
     * @param valorDiaria
     * @param periodoI
     * @param periodoF
     */
    public Hospedagem(int codigo, String nome, String cidade, String estado, int numQuartoDisponivel, double valorDiaria, String periodoI, String periodoF) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.numQuartoDisponivel = numQuartoDisponivel;
        this.valorDiaria = valorDiaria;
        this.periodoInicio = Calendar.getInstance();
        this.periodoFim = Calendar.getInstance();
        try {
            this.periodoInicio.setTime(sdf.parse(periodoI));
            this.periodoFim.setTime(sdf.parse(periodoF));
        } catch (ParseException ex) {
            Logger.getLogger(Hospedagem.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        //System.out.println("Valida periodo pIni:" + pIni.toString() + " pFim: " + pFim.toString() + 
        //      " com Ini:" + periodoInicio.toString() + " Fim: " + periodoFim);
        if (pIni.after(periodoInicio) || pIni.equals(periodoInicio)) {
            if (pFim.before(periodoFim) || pFim.equals(periodoFim)) {
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

    /**
     * Todas as vezes que um quarto for reservado, independente se for apenas o quarto ou um pacote fechado, será subtraido da
     * quantidade de quartos disponíveis a quantidade de quartos reservados. Sempre será considerado que a quantidade de quartos
     * reservados é IGUAL ou MENOR que a quantidade de quartos disponíveis.
     *
     * @param numQuartos - numero de quartos que serão reservados para esta hospedagem.
     */
    public void setNumQuartoDisponivel(int numQuartos) {
        this.numQuartoDisponivel = numQuartoDisponivel - numQuartos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Calendar getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(Calendar periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public Calendar getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(Calendar periodoFim) {
        this.periodoFim = periodoFim;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getNumQuartoDisponivel() {
        return numQuartoDisponivel;
    }

}
