package servidor;

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

	static ArrayList<Cliente> clientes = new ArrayList<>(); //Lista de clientes
    static ArrayList<InteresseCarona> interesseMotorista = new ArrayList<>(); //Lista de interesse em motoristas
    static ArrayList<InteressePassageiro> interessePassageiro = new ArrayList<>(); //Lista de interesse em passageiros
    String result;

	public Servidor() {		
			
	}

	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastro/{nome}/{telefone}")
	public void cadastrarUsuario(@PathParam("nome") String nome, @PathParam("telefone") String telefone) {
		
		if (nome == null || nome.trim().equals("")) {
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST)
					.entity("O nome do cliente é obrigatorio").build());
		}
		
		//Adiciona cliente na lista de clientes
		clientes.add(new Cliente(clientes.size(), nome, telefone));

        //Mostra todos os clientes cadastrados no console
        clientes.forEach(c -> {
            System.out.println("Id: " + c.getId() + ". Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        });

	}	
	

	//Retorna a lista de corona cadastradas que se enquadram nos parâmetros do passageiro
	@GET
	@Path("/busca/{origem}/{destino}/{data}")
	@Produces(MediaType.TEXT_HTML)
	public String listaCaronas(@PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data) {
		
		result = "<ul>";
		
        interessePassageiro.forEach(c -> {
            //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
            if(c != null){
                if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                	System.out.println("a" + result);
                	result += "<li><b>Nome:</b> " + c.getNome() + ". <b>Telefone:</b> " + c.getTelefone() + ".</li>";
                }
            }
        });
  
        result += "</ul>";
        
        System.out.println(result);
        
		return result;
		
	}	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cadastroInteresse/{nome}/{telefone}/{origem}/{destino}/{data}")	
	public String interesseEmMotorista(@PathParam("nome") String nome, @PathParam("telefone") String telefone, @PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data)  {
	
        interesseMotorista.add(new InteresseCarona(nome, telefone, origem, destino, data));

        interesseMotorista.forEach(c -> {
            System.out.println("Nome: " + c.getNome() + ". Origem: " + c.getOrigem() + ".");
        });	
        
        interessePassageiro.forEach(c -> {
            //Verificação de nulo para evitar null pointer exception dados que id único é o índice na lista
            if(c != null){
               // try {
                    if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                        System.out.println("Interesse M: " + interesseMotorista.size());

                        //Chamada bidirecional chamando método do cliente para notificar o motorista
                        //c.getInterfaceCli().notificarMotorista("Novo passageiro! Passageiro: " + nome + " Telefone: " + telefone);

                    } //else {
                        //"Notifica" motoristas que não se enquadraram nos dados da carona com vazio. Melhorar.
                        //c.getInterfaceCli().notificarMotorista("");
                    //}
                //} catch (Exception ex) {
                //        System.out.println("NotificarPassageiro: " + ex.getMessage());
                //}
            }
        });

        System.out.println("Passageiros: " + interesseMotorista.size());

        //Código único do intesse é a posição do elemento na matriz, que não muda
        return String.valueOf(interesseMotorista.size()-1);
    }	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cadastroCarona/{nome}/{telefone}/{origem}/{destino}/{data}/{pass}")		
	public String interesseEmPassageiro(@PathParam("nome") String nome, @PathParam("telefone") String telefone, @PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data, @PathParam("pass") String pass)  {
		
        interessePassageiro.add(new InteressePassageiro(nome, telefone, origem, destino, data, pass));

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

        interessePassageiro.forEach(c -> {
            System.out.println("Nome: " + c.getNome() + ". Origem: " + c.getOrigem() + ".");
        });	        
        
        //Código único do intesse é a posição do elemento na matriz, que não muda     
        return String.valueOf(interessePassageiro.size()-1);
                
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
    @Path("/cancelaInteresse/")
    @Consumes(MediaType.APPLICATION_JSON)  
    public Response cancInteresseEmMotorista(@QueryParam("id") String id) {
	
		System.out.println("Interesses antes:");
		interesseMotorista.forEach(c -> {
            System.out.println("Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        });    	
        System.out.println();			
		
        //Cancela o interesse em carona tornando nulos os dados do motorista
        interesseMotorista.set(Integer.parseInt(id), null);
     
        System.out.println("Interesses depois:");
        interesseMotorista.forEach(c -> {
        	if(c != null){
        		System.out.println("Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        	}
        });        
        
        return Response.ok().build();
    }    
 
    @DELETE
    @Path("/cancelaCarona/")
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response cancInteresseEmPassageiro(@QueryParam("id") String id) {
    	
    	System.out.println("Caronas antes:");
    	interessePassageiro.forEach(c -> {
            System.out.println("Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        });    	
        System.out.println();	
    	
        //Cancela o interesse em passeiro tornando nulos os dados da carona
        interessePassageiro.set(Integer.parseInt(id), null);

        System.out.println("Caronas depois:");
        interessePassageiro.forEach(c -> {
        	if(c != null){
        		System.out.println("Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        	}
        }); 
        
        return Response.ok().build();
    } 	

}
