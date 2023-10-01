package br.com.eletrotechstore.pagamentos.controller;

import br.com.eletrotechstore.pagamentos.dto.PagamentoDTO;
import br.com.eletrotechstore.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

  @Autowired
  private PagamentoService pagamentoService;

  @GetMapping
  public Page<PagamentoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
    return pagamentoService.obterTodos(paginacao);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagamentoDTO> detalhar(@PathVariable @Valid Long id){
    return ResponseEntity.ok(pagamentoService.obterPorId(id));
  }

  public ResponseEntity<PagamentoDTO> cadastar(@RequestBody @Valid PagamentoDTO pagamentoDTO, UriComponentsBuilder uriComponentsBuilder){

    PagamentoDTO pagamentoCadastrado = pagamentoService.criarPagamento(pagamentoDTO);
    URI endereco = uriComponentsBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoCadastrado.getId()).toUri();
    return ResponseEntity.created(endereco).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PagamentoDTO> atualizar(@PathVariable @NotNull Long id,
                                                @RequestBody @Valid PagamentoDTO pagamentoDTO){
    return ResponseEntity.ok(
        pagamentoService.atualizarPagamento(id, pagamentoDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id){
     pagamentoService.excluirPagamento(id);
     return ResponseEntity.noContent().build();
  }

}

/* Boas práticas API Rest
* Faça uso de recursos corretamente
* Utilize sub-recursos
* Use aquele nome feio (HATEOAS)
* Filtro, ordenação, páginação e seleção de campos
* Versione sua API
* Utilize os status HTTP corretamente
* */
