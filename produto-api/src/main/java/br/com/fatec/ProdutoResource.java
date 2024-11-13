package br.com.fatec;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

//import com.arjuna.ats.internal.arjuna.objectstore.jdbc.drivers.postgres_driver;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
//import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

@Operation(
    summary = "Criar produto",
    description = "Cadastrar novo produto"
)
@APIResponse(
    responseCode = "201",
    description = "produto cadastrado com sucesso"
)
@APIResponse(
    responseCode = "500",
    description = "Erro interno no servidor"
)
    @Transactional
    @POST
    @Path("/produto")
    public Response criarProduto( @Valid ProdutoDto produtoDto){
       Produto produto = new Produto(produtoDto.getNome(), produtoDto.getDescricao(),produtoDto.getPreco());
       produto.persist();

       return Response.status(Response.Status.CREATED).entity(produto).build();
    }
    @GET
    @Path("/produtos")
    public Response listarProduto(){
        return Response.status(Response.Status.OK).entity(Produto.listAll()).build();
    }

    @GET
    @Path("/produto/{id}")

    public Response buscarProduto(@PathParam("id") long id){
        Produto produto =  Produto.findById(id);

        if(produto == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Nao encontrado").build();
        }
        return Response.status(Response.Status.OK).entity(produto).build();
    }

    @Transactional
    @DELETE
    @Path("/produto/{id}")
    public Response deletarPrdouto(@PathParam("id") long id){
        Produto produto =  Produto.findById(id);

        if(produto == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Nao encontrado").build();
        }
        Produto.deleteById(id);

        return Response.status(Response.Status.OK).build();
    }
@Transactional
@PUT
@Path("/produto/{id}")
    public Response atualizarProduto(@PathParam("id") long id, ProdutoDto produtoDto){
        Produto produto =  Produto.findById(id);

        if(produto == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Nao encontrado").build();
        }

        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());

        Produto.persist(produto);

        return Response.status(Response.Status.OK).entity(" produto atualizado").build();
    }
}
