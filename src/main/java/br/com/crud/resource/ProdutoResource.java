package br.com.crud.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.crud.domain.model.Produto;
import br.com.crud.dto.CadastroProdutoDTO;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

      @GET
      public List<Produto> listar() {
            return Produto.listAll();
      }
      
      @GET
      @Path("{id}")
      public Produto buscar(@PathParam(value = "id") Long id) {
            Optional<Produto> produtoOptional = Produto.findByIdOptional(id);

            if (produtoOptional.isPresent())
                  return produtoOptional.get();

            throw new NotFoundException();
      }

      @POST
      @Transactional
      public Response salvar(CadastroProdutoDTO produtoDTO) {
            var produto = new Produto(produtoDTO.getNome(), produtoDTO.getValor());
            produto.persist();

            return Response.ok(produto).status(Status.CREATED).build();
      }

      @PUT
      @Path("{id}")
      @Transactional
      public Produto editar(@PathParam(value = "id") Long id, CadastroProdutoDTO produtoDTO) {
            Optional<Produto> produtoOptional = Produto.findByIdOptional(id);

            if (!produtoOptional.isPresent())
                  throw new NotFoundException();

            var produto = produtoOptional.get();
            produto.setNome(produtoDTO.getNome());
            produto.setValor(produtoDTO.getValor());

            produto.persist();

            return produto;
      }

      @DELETE
      @Path("{id}")
      @Transactional
      public void deletar(@PathParam(value = "id") Long id) {
            Optional<Produto> produtoOptional = Produto.findByIdOptional(id);

            produtoOptional.ifPresentOrElse(Produto::delete, () -> { throw new NotFoundException(); });
      }

}