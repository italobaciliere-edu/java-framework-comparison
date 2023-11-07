package br.com.pagamento.controller;

import br.com.pagamento.dto.PagamentoDTO;
import br.com.pagamento.service.PagamentoService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.Collections;

@Controller("/pagamentos")
public class PagamentoController {

  @Inject
  private PagamentoService pagamentoService;

  @Get("api-status/{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String isRunnig(String name) {
    return "API is running " + name;
  }

  @Get
  public Page<PagamentoDTO> listar(Pageable paginacao,
                                   @QueryValue(defaultValue = "0") int page,
                                   @QueryValue(defaultValue = "10") int size,
                                   @QueryValue(defaultValue = "nome") String sort) {
    paginacao = Pageable.from(page, size).order(sort);
    return pagamentoService.obterTodos(paginacao);
  }


  @Get("/{id}")
  public HttpResponse<PagamentoDTO> detalhar(@PathVariable @Valid Long id){
    return HttpResponse.ok(pagamentoService.obterPorId(id));
  }

  @Post
  public HttpResponse<PagamentoDTO> cadastrar(@Body @Valid PagamentoDTO pagamentoDTO, UriBuilder uriBuilder) {
    PagamentoDTO pagamentoCadastrado = pagamentoService.criarPagamento(pagamentoDTO);
    URI endereco = uriBuilder.path("/pagamentos/{id}")
        .expand(Collections.singletonMap("id", pagamentoCadastrado.getId()));
    return HttpResponse.created(endereco);
  }

  @Put("/{id}")
  public HttpResponse<PagamentoDTO> atualizar(@PathVariable @NotNull Long id,
                                                @Body @Valid PagamentoDTO pagamentoDTO){
    return HttpResponse.ok(
        pagamentoService.atualizarPagamento(id, pagamentoDTO));
  }

  @Delete("/{id}")
  public HttpResponse<PagamentoDTO> remover(@PathVariable @NotNull Long id){
    pagamentoService.excluirPagamento(id);
    return HttpResponse.noContent();
  }

}
