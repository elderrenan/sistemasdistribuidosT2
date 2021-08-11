/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest.Resources;

import CDB.CDBPassagem;
import Entidades.Passagem;
import Entidades.PassagemDesejo;
import Entidades.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Eldrey
 */
@Path("/vendaPassagem")
public class VendaPassagens {

    private CDBPassagem cdbp = new CDBPassagem();
    private List<Passagem> passagens = cdbp.getLista();

    /**
     * Este método é responsávem por retornar uma lista de passagens que se enquadre nas caracteristicas recebinas no objeto
     * passDesejo. Se a informação passDesejo.isIdaValta for TRUE, siguinifica que o cliente quer a passagem de ida e de volat,
     * logo o método vai inverter a origem com o destino e adicionar as opções a lista de retorno, caso seja FALSE irá apenas
     * buscar as passagens de ida.
     *
     * @param passDesejo
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passagem> listaPassagens(PassagemDesejo passDesejo) {

        //passagens = cdbp.getLista();
        passDesejo.converteDataCalendar();
        List<Passagem> returnPassagem = new ArrayList<>();

        for (Passagem passagem : passagens) {
//            System.out.println("ORIGEM - " + passagem.getEstadoOrigem() + " - " + passDesejo.getEstadoOrigem());
            if (passagem.getEstadoOrigem().equals(passDesejo.getEstadoOrigem()) && passagem.getCidadeOrigem().equals(passDesejo.getCidadeOrigem())) {
//                System.out.println("DESTINO -" + passagem.getEstadoDestino() + "- " + passDesejo.getEstadoDestino());
//                System.out.println("DESTINO -" + passagem.getCidadeDestino() + "- " + passDesejo.getCidadeDestino());
                if (passagem.getEstadoDestino().equals(passDesejo.getEstadoDestino()) && passagem.getCidadeDestino().equals(passDesejo.getCidadeDestino())) {
                    if (passagem.validaData(passDesejo.getDataViagemC())) {
                        returnPassagem.add(passagem);
                        System.out.println("[ ADD ]");
                    }
                } else {
                    System.out.println("[ATENÇÃO] DESTINO É DIFERENTE");
                }
            } else {
                System.out.println("[ATENÇÃO] ORIGEM É DIFERENTE: " + passDesejo.getEstadoOrigem() + "/" + passDesejo.getCidadeOrigem());
            }
            if (passDesejo.isIdaVolta()) {
                if (passagem.getEstadoOrigem().equals(passDesejo.getEstadoDestino()) && passagem.getCidadeOrigem().equals(passDesejo.getCidadeDestino())) {
                    if (passagem.getEstadoDestino().equals(passDesejo.getEstadoOrigem()) && passagem.getCidadeDestino().equals(passDesejo.getCidadeOrigem())) {
                        if (passagem.validaData(passDesejo.getDataViagemC())) {
                            returnPassagem.add(passagem);
                            System.out.println("[ ADD ]");
                        }
                    } else {
                        System.out.println("[ATENÇÃO] DESTINO É DIFERENTE");
                    }
                } else {
                    System.out.println("[ATENÇÃO] ORIGEM É DIFERENTE: " + passDesejo.getEstadoOrigem() + "/" + passDesejo.getCidadeOrigem());
                }
            }
        }
        return returnPassagem;
    }

    /**
     * Este método usa um objeto reserva para fazer a reserva de das passagens. O método só trata idas, logo se o cliente quiser a
     * ida + a volta irá ter qeu chamar o método duas vezes. Porem o cliente pode reservar N passagens de ida.
     *
     * @param reserva
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("reserva")
    public boolean reservaPassagem(Reserva reserva) {

        for (Passagem passagen : passagens) {
            if (passagen.getCodigo() == reserva.getCodigo()) {
                if (passagen.getQuantPassagemDisponivel() >= reserva.getNumReservas()) {
                    passagen.setQuantPassagemDisponivel(reserva.getNumReservas());
                    cdbp.efetuaReserva(passagen, reserva.getNumReservas());
                    System.out.println("[SUCESSO] RESERVA DE PASSAGEM REALIZADA");
                    System.out.println("[ATENÇÃO] NUMERO DE PASSAGENS DISPONÍVEIS: " + passagen.getQuantPassagemDisponivel());
                    return true;
                } else {
                    System.out.println("[ERRO] O NUMERO DE PASSAGENS RESERVADAS É MAIOR QUE O DISPONPIVEL");
                    return false;
                }
            }
        }
        System.out.println("[ERRO] CODIGO NÃO ENCONTRADO: " + reserva.getCodigo());
        return false;
    }

    public Passagem getPassagens(int cod) {
        passagens = cdbp.getLista();
        for (Passagem passagen : passagens) {
            if (passagen.getCodigo() == cod) {
                return passagen;
            }
        }
        return null;
    }

}
