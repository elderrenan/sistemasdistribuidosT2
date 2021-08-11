/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest.Resources;

import CDB.CDBPacote;
import Entidades.Hospedagem;
import Entidades.Pacote;
import Entidades.PacoteDesejo;
import Entidades.Passagem;
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
@Path("/vendaPacote")
public class VendaPacote {

    private final CDBPacote cdbpac = new CDBPacote();
    private List<Pacote> pacotes = cdbpac.getLista();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pacote> listaPacotes(PacoteDesejo pacDesejo) {
        pacDesejo.converteDataCalendar();
        pacotes = cdbpac.getLista();
        List<Pacote> returnPac = new ArrayList<>();

        for (Pacote pac : pacotes) {
            if (pac.getEstadoOrigem().equals(pacDesejo.getEstadoOrigem()) && pac.getCidadeOrigem().equals(pacDesejo.getCidadeOrigem())) {
                if (pac.getEstadoDestio().equals(pacDesejo.getEstadoDestino()) && pac.getCidadeDestino().equals(pacDesejo.getCidadeDestino())) {
                    if (pac.validaPeríodo(pacDesejo.getDataIdaC(), pacDesejo.getDataRetornoC())) {
                        if (pac.getVagasDisponíveis() >= pacDesejo.getQuantPessoas()) {
                            returnPac.add(pac);
                        }
                    }
                }
            }

        }
        return returnPac;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("reserva")
    public boolean reservaPacote(Reserva reserva) {
        VendaHospedagem VH = new VendaHospedagem();
        VendaPassagens VP = new VendaPassagens();
        for (Pacote pacote : pacotes) {
            if (reserva.getCodigo() == pacote.getCodigo()) {
                if (reserva.getNumReservas() <= pacote.getVagasDisponíveis()) {
                    Hospedagem hosp = VH.getHospedagens(pacote.getCodHospedagen());
                    Passagem pasIda = VP.getPassagens(pacote.getCodPassagemIda());
                    Passagem pasVolta = VP.getPassagens(pacote.getCodPassagemVolta());

                    cdbpac.efetuaReserva(pacote, reserva.getNumReservas(), (hosp.getNumQuartoDisponivel() - reserva.getNumReservas()),
                            (pasIda.getQuantPassagemDisponivel() - reserva.getNumReservas()),
                            (pasVolta.getQuantPassagemDisponivel() - reserva.getNumReservas()));
                    return true;

                } else {
                    System.out.println("[ERRO] NUMERO DE RESERVAS PARA ESSE PACOTE É SUPERIOR AO NUMERO DE VAGAS DO MESMO");
                    return false;
                }
            }
        }
        System.out.println("[ERRO] NÃO FOI ENCONTRADO O PACODE COD: " + reserva.getCodigo());
        return false;
    }
}
