package br.com.eletrotechstore.controller;

import br.com.eletrotechstore.dto.PagamentoDTO;
import br.com.eletrotechstore.model.Pagamento;
import br.com.eletrotechstore.service.PagamentoService;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;


@Path("/pagamentos")
public class PagamentoController {

  @Inject
  private PagamentoService pagamentoService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<PagamentoDTO> listar(
          @QueryParam("page") @DefaultValue("0") int page,
          @QueryParam("pageSize") @DefaultValue("10") int pageSize,
          @QueryParam("sort") @DefaultValue("nome") String sort,
          @QueryParam("order") @DefaultValue("asc") String order) {
    return pagamentoService.obterTodos(page, pageSize, sort, order);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response detalhar(@PathParam("id") @Valid Long id){
    return Response.ok(pagamentoService.obterPorId(id)).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response cadastrar(@Valid PagamentoDTO pagamentoDTO, @Context UriInfo uriInfo) {
    PagamentoDTO pagamentoCadastrado = pagamentoService.criarPagamento(pagamentoDTO);

    URI endereco = uriInfo.getAbsolutePathBuilder()
        .path(String.valueOf(pagamentoCadastrado.getId()))
        .build();

    return Response.created(endereco).entity(pagamentoCadastrado).build();
  }

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response atualizar(@PathParam("id") @NotNull Long id, @Valid PagamentoDTO pagamentoDTO) {
    PagamentoDTO pagamento = pagamentoService.atualizarPagamento(id, pagamentoDTO);
    if (pagamento == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(pagamento).build();
  }

  @DELETE
  @Path("/{id}")
  public Response remover(@PathParam("id") @NotNull Long id) {
    pagamentoService.excluirPagamento(id);
    return Response.noContent().build();
  }

}
