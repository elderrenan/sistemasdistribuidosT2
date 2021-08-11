/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest.Resources;

import Entidades.HospedagemDesejo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Eldrey
 */
@Path("/teste")
public class teste {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HospedagemDesejo sayHello() {
        //VendaHospedagem VH = new VendaHospedagem();
        HospedagemDesejo r = new HospedagemDesejo("Curitiba", "Paraná", "01/01/2014", "01/01/2014", 1);
//        Reserva r = new Reserva("0000", 1, 11, 1111, 1, 1, "Teste");
        return r;
    }
}
