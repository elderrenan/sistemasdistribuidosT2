/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest.Resources;

import CDB.CDBHospedagem;
import Entidades.Hospedagem;
import Entidades.HospedagemDesejo;
import Entidades.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Classe que disponibiliza a consulta e compra de hospedagens segundo os requisitos: Consulta e compra de hospedagem (fornecendo
 * destino (nome da cidade ou do hotel), data da entrada e data da saída, número de quartos, número e idade das pessoas e dados do
 * cartão juntamente com a opção de parcelamento (se for efetuar a compra));
 *
 * @author Eldrey
 */
@Path("/vendaHospedagem")
public class VendaHospedagem {

    private CDBHospedagem cdbh = new CDBHospedagem();
    private List<Hospedagem> hospedagens = cdbh.getLista();

    /**
     * Este método é responsável pro retornar uma lista de hospedagens validas para uma dado estado, cidade, pedríodo e quantidade
     * de quartos a serem usados.
     *
     * @param hospSolicitada
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hospedagem> listaHospedagem(HospedagemDesejo hospSolicitada) {
        if (hospSolicitada.getEstado() == null) {
            System.out.println("[ERRO] ESSE PUTO ESTÁ NULL");
        }
        System.out.println("[?] ESSE PUTO ESTA NULL " + hospSolicitada.getEstado());
        hospSolicitada.convertPeriodoCalendar();
        List<Hospedagem> returnHosp = new ArrayList<>();

        for (Hospedagem hosp : hospedagens) {
//            System.out.println("1 ->" + hospSolicitada.getEstado() + "/" + hospSolicitada.getCidade());
//            System.out.println("1 <-" + hosp.getEstado() + "/" + hosp.getCidade());
            if (hosp.getEstado().equals(hospSolicitada.getEstado()) && hosp.getCidade().equals(hospSolicitada.getCidade())) {
//                System.out.println("2");
                if (hosp.validaPeríodo(hospSolicitada.getPeriodoInicioC(), hospSolicitada.getPeriodoFimC())) {
//                    System.out.println("3" + hospSolicitada.getNumQuartoDesejo());
//                    System.out.println("3" + hosp.getNumQuartoDisponivel());
                    if (hosp.getNumQuartoDisponivel() >= hospSolicitada.getNumQuartoDesejo()) {
//                        System.out.println("4");
                        returnHosp.add(hosp);
                    }
                }
            } else {
                System.out.println("[ATENÇÃO] ESTADO/CIDADE DIFERENTE DO SOLICITADO: " + hospSolicitada.getEstado() + " / "
                        + hospSolicitada.getCidade());
            }
        }

        return returnHosp;
    }

    /**
     * Este método é responsável por fazer a reserva de uma hospedagem, ele recebe um objeto Reserva, busca a hospedagem no List
     * pelo código, valida novamente que a quantidade de quartos solicitado é menor igual a quantidade cadastrada e faz o
     * cadastro. O cadastro é simplesmente a redução do número de quartos disponíveis.
     *
     * @param reserva
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("reserva")
    public boolean reservaHospedagem(Reserva reserva) {
        for (Hospedagem hosp : hospedagens) {

            System.out.println("[ATENÇÃO] COD HOSPEDAGEM: " + reserva.getCodigo());
            if (reserva.getCodigo() == hosp.getCodigo()) {
                if (reserva.getNumReservas() <= hosp.getNumQuartoDisponivel()) {
                    hosp.setNumQuartoDisponivel(reserva.getNumReservas());
                    cdbh.efetuaReserva(hosp, reserva.getNumReservas());
                    System.out.println("[SUCESSO] RESERVA HOSPEDAGEM");
                    System.out.println("[ATENÇÃO] NUM DE QUARTOS DISPONÍVEIS: " + hosp.getNumQuartoDisponivel());
                    return true;
                } else {
                    System.out.println("[ERRO] NUMERO DE QUARTOS DISPONÍVEIS É MENOR DO QUE O SOLICITADO");
                    return false;
                }
            }
        }
        System.out.println("[ERRO] NÃO FOI ENCONTRADO A HOSPEDAGEM " + reserva.getCodigo());

        return false;
    }

    public Hospedagem getHospedagens(int cod) {
        hospedagens = cdbh.getLista();
        for (Hospedagem hospedagen : hospedagens) {
            if (hospedagen.getCodigo() == cod) {
                return hospedagen;
            }
        }
        return null;
    }

}
