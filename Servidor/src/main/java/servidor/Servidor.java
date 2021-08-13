package servidor;

import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/funcoes")
public class Servidor {

    ArrayList<Cliente> clientes = new ArrayList<>(); //Lista de clientes
    ArrayList<InteresseCarona> interesseMotorista = new ArrayList<>(); //Lista de interesse em motoristas
    ArrayList<InteressePassageiro> interessePassageiro = new ArrayList<>(); //Lista de interesse em passageiros

	public Servidor() {


			InteressePassageiro interesse = new InteressePassageiro("Elder", "(41) 998629640", "Curitiba", "Paranagua", "13-08-2021", 4);			
			interessePassageiro.add(interesse);
			interesse = new InteressePassageiro("Nataly", "(41) 998643444", "Paranagua", "Curitiba", "14-08-2021", 3);			
			interessePassageiro.add(interesse);

	}

	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastro/{nome}/{telefone}")
	public Cliente cadastrarUsuario(@PathParam("nome") String nome, @PathParam("telefone") String telefone) {
		
		if (nome == null || nome.trim().equals("")) {
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST)
					.entity("O nome do cliente é obrigatorio").build());
		}
				
		Cliente cliente = new Cliente(clientes.size() +1, nome, telefone);
		
        //Adiciona cliente na lista de clientes
        clientes.add(cliente);
        
        //Mostra todos os clientes cadastrados no console
        clientes.forEach(c -> {
            System.out.println("Id: " + c.getId() + ". Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        });		
		
		return cliente;
	}	
	

	//Retorna a lista de corona cadastradas que se enquadram nos parâmetros do passageiro
	@GET
	@Path("/busca/{origem}/{destino}/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InteressePassageiro> listaCaronas(@PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data) {
		
		List<InteressePassageiro> result = new ArrayList<InteressePassageiro>();
		
        interessePassageiro.forEach(c -> {
            //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
            if(c != null){

                if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                    //listaCaronas.add("\nMotorista: " + c.getNome() + ". Telefone: " + c.getTelefone());
                	result.add(c);
                }
            }
        });
        //System.out.println(result);
		return result;
		
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{contactId}")
	public Cliente getContact(@PathParam("contactId") int id) {

		Cliente result = null;

		for (Cliente cliente : clientes) {
			if (cliente.getId() == id) {
				result = cliente;
				break;
			}
		}

		if (result == null) {
			throw new WebApplicationException(404);
		}

		return result;
	}

	@PUT
	@Path("/{contactId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("contactId") int id, Cliente cliente) {
		clientes.set(id - 1, cliente);
		cliente.setId(clientes.indexOf(cliente) + 1);
		return Response.ok().build();
	}

	@DELETE
	public Response delete(@QueryParam("contactId") int id) {

		if (id > clientes.size()) {
			throw new WebApplicationException(404);
		}

		clientes.remove(id - 1);
		return Response.ok().build();
	}

    /*   
    public int interesseEmMotorista(String nome, String telefone, String origem, String destino, String data)  {

        interesseMotorista.add(new InteresseCarona(nome, telefone, origem, destino, data));

        interessePassageiro.forEach(c -> {
            //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
            if(c != null){
                try {
                    if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                        System.out.println("Interesse M: " + (interesseMotorista.size() - 1));

                        //Chamada bidirecional chamando método do cliente para notificar o motorista
                        //c.getInterfaceCli().notificarMotorista("Novo passageiro! Passageiro: " + nome + " Telefone: " + telefone);

                    } else {
                        //"Notifica" motoristas que não se enquadraram nos dados da carona com vazio. Melhorar.
                        //c.getInterfaceCli().notificarMotorista("");
                    }
                } catch (Exception ex) {
                        System.out.println("NotificarPassageiro: " + ex.getMessage());
                }
            }
        });

        System.out.println();

        //Código único do intesse é a posição do elemento na matriz, que não muda
        return interesseMotorista.size() - 1;
                
    }    

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)  
    public void cancInteresseEmMotorista(@PathParam("id") int id) {
        //Cancela o interesse em carona tornando nulos os dados do motorista
        interesseMotorista.set(id, null);  
    }    
    
    public int interesseEmPassageiro(String nome, String telefone, String origem, String destino, String data, int passageiros) {

        interessePassageiro.add(new InteressePassageiro(nome, telefone, origem, destino, data, passageiros));

        interesseMotorista.forEach(c -> {
            //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
            if(c != null){
                try {
                    if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){

                        System.out.println("\ninteresse P: " + (interessePassageiro.size() - 1));

                        //Chamada bidirecional chamando método do cliente para notificar o passageiro
                        //c.getInterfaceCli().notificarPassageiro("Nova carona! Motorista: " + nome + " Telefone: " + telefone);

                    } else {
                        //"Notifica" passageiros que não se enquadraram nos dados da carona com vazio. Melhorar.           
                        //c.getInterfaceCli().notificarPassageiro("");
                    }
                } catch (Exception ex) {
                    System.out.println("NotificarPassageiro: " + ex.getMessage());
                }
            }
        });

        System.out.println();

        //Código único do intesse é a posição do elemento na matriz, que não muda
        return interessePassageiro.size() - 1;
                
    }
 
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)    
    public void cancInteresseEmPassageiro(@PathParam("id") int id) {
        //Cancela o interesse em passeiro tornando nulos os dados da carona
        interessePassageiro.set(id, null);
    } 	
	*/
}
