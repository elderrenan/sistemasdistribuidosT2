package servidor;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Singleton;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

@Singleton
@Path("/funcoes")
public class Servidor {

	static ArrayList<Cliente> clientes = new ArrayList<>(); //Lista de clientes
    static ArrayList<InteresseCarona> interesseMotorista = new ArrayList<>(); //Lista de interesse em motoristas
    static ArrayList<InteressePassageiro> interessePassageiro = new ArrayList<>(); //Lista de interesse em passageiros
    static ArrayList<SseBroadcaster> broadcasters = new ArrayList<>(); //Lista broadcasters
    String result;
    int id;
    
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
					.entity("O nome do cliente � obrigatorio").build());
		}
		
		//Adiciona cliente na lista de clientes
		clientes.add(new Cliente(clientes.size(), nome, telefone));

        //Mostra todos os clientes cadastrados no console
		System.out.println("\nClientes cadastrados:");
        clientes.forEach(c -> {
            System.out.println("	Id: " + c.getId() + ". Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        });
        System.out.println();

	}	
	

	//Retorna a lista de corona cadastradas que se enquadram nos par�metros do passageiro
	@GET
	@Path("/busca/{origem}/{destino}/{data}")
	@Produces(MediaType.TEXT_HTML)
	public String listaCaronas(@PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data) {
		
		result = "<ul>";
		
		System.out.println("\nCaronas retornadas:");
        interessePassageiro.forEach(c -> {
            //Verifica��o de nulo para evitar null pointer exception dados que id �nico � o �ndice na lista
            if(c != null){
                if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                	System.out.println("	Carona: " + result);
                	result += "<li><b>Nome:</b> " + c.getNome() + ". <b>Telefone:</b> " + c.getTelefone() + ".</li>";
                }
            }
        });
        System.out.println();
  
        result += "</ul>";
        
		return result;
		
	}	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cadastroInteresse/{nome}/{telefone}/{origem}/{destino}/{data}")	
	public String interesseEmMotorista(@PathParam("nome") String nome, @PathParam("telefone") String telefone, @PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data)  {
	
        interesseMotorista.add(new InteresseCarona(nome, telefone, origem, destino, data));

        System.out.println("\nInteresses em motorista ap�s cadastro:");
        interesseMotorista.forEach(c -> {
        	if(c != null){
        		System.out.println("	Nome: " + c.getNome() + ". Origem: " + c.getOrigem() + ".");
        	}
        });
        System.out.println();
        
        interessePassageiro.forEach(c -> {
            //Verifica��o de nulo para evitar null pointer exception dados que id �nico � o �ndice na lista          
            if(c != null){
                if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                	
                    clientes.forEach(d -> {
                    	if(d.getNome().equals(c.getNome()) && d.getTelefone().equals(c.getTelefone())){
                    		id = d.getId();
                    	}
                    });
                	
                    broadcastMessage("Novo passageiro! Passageiro: " + nome + ". Telefone: " + telefone + ".", nome, telefone, id);
                }
            }
            
        });

        //C�digo �nico do intesse � a posi��o do elemento na matriz, que n�o muda
        return String.valueOf(interesseMotorista.size()-1);
    }	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cadastroCarona/{nome}/{telefone}/{origem}/{destino}/{data}/{pass}")		
	public String interesseEmPassageiro(@PathParam("nome") String nome, @PathParam("telefone") String telefone, @PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data, @PathParam("pass") String pass)  {
		
        interessePassageiro.add(new InteressePassageiro(nome, telefone, origem, destino, data, pass));

        
        interesseMotorista.forEach(c -> {
            //Verifica��o de nulo para evitar null pointer exception dados que id �nico � o �ndice na lista
            if(c != null){
                if(c.getOrigem().equals(origem) && c.getDestino().equals(destino) && c.getData().equals(data)){
                	
                    clientes.forEach(d -> {
                    	if(d.getNome().equals(c.getNome()) && d.getTelefone().equals(c.getTelefone())){
                    		id = d.getId();
                    	}
                    });
                	
                    broadcastMessage("Nova carona! Motorista: " + nome + ". Telefone: " + telefone + ".", nome, telefone, id);
                }
            }
        });        

        System.out.println("\nInteresses em passageiro ap�s cadastro: ");
        interessePassageiro.forEach(c -> {
        	if(c != null){
        		System.out.println("	Nome: " + c.getNome() + ". Origem: " + c.getOrigem() + ".");
        	}
        });
        System.out.println();
        
        //C�digo �nico do intesse � a posi��o do elemento na matriz, que n�o muda     
        return String.valueOf(interessePassageiro.size()-1);
                
    }	

	@DELETE
    @Path("/cancelaInteresse/")
    @Consumes(MediaType.APPLICATION_JSON)  
    public Response cancInteresseEmMotorista(@QueryParam("id") String id) {
	
		System.out.println("\nInteresses antes de cancelamento:");
		interesseMotorista.forEach(c -> {
			if(c != null){
				System.out.println("	Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
			}
        });    	
        System.out.println();			
		
        //Cancela o interesse em carona tornando nulos os dados do motorista
        interesseMotorista.set(Integer.parseInt(id), null);
     
        System.out.println("Interesses ap�s cancelamento: ");
        interesseMotorista.forEach(c -> {
        	if(c != null){
        		System.out.println("	Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        	}
        });
        System.out.println();
        
        return Response.ok().build();
    }    
 
    @DELETE
    @Path("/cancelaCarona/")
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response cancInteresseEmPassageiro(@QueryParam("id") String id) {
    	
    	System.out.println("\nMotoristas antes de cancelamento:");
    	interessePassageiro.forEach(c -> {
    		if(c != null){
    			System.out.println("	Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
    		}
        });    	
        System.out.println();	
    	
        //Cancela o interesse em passeiro tornando nulos os dados da carona
        interessePassageiro.set(Integer.parseInt(id), null);

        System.out.println("Motoristas ap�s de cancelamento::");
        interessePassageiro.forEach(c -> {
        	if(c != null){
        		System.out.println("Nome: " + c.getNome() + ". Telefone: " + c.getTelefone() + ".");
        	}
        });
        System.out.println();
        
        return Response.ok().build();
    } 	

    @POST
    @Path("/notificaCliente/{nome}/{telefone}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)    
    public String broadcastMessage(String message, @PathParam("nome") String nome, @PathParam("telefone") String telefone, @PathParam("id") int indice) {
        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        message = message + "\n\n";
        OutboundEvent event = eventBuilder.name("message")
            .mediaType(MediaType.TEXT_PLAIN_TYPE)
            .data(String.class, message)
            .build();

        broadcasters.get(indice).broadcast(event);

        return "A mensagem '" + message + "' foi enviada.";
    }

    @GET
    @Path("/notificaCliente/{nome}/{telefone}")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput listenToBroadcast() {
    	SseBroadcaster broadcaster = new SseBroadcaster();
        final EventOutput eventOutput = new EventOutput();
    	broadcaster.add(eventOutput);
        broadcasters.add(broadcaster);
        System.out.println("Registrado! Chave: " +  broadcaster);
        return eventOutput;
    }    
    
}
