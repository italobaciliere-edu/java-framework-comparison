package br.com.pagamento.service;

import br.com.pagamento.dto.PagamentoDTO;
import br.com.pagamento.model.Pagamento;
import br.com.pagamento.model.Status;
import br.com.pagamento.repository.PagamentoRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

@Singleton
public class PagamentoService {

  @Inject
  private PagamentoRepository pagamentoRepository;

  @Inject
  private ModelMapper modelMapper;

  public PagamentoService(ModelMapper modelMapper){
    this.modelMapper = modelMapper;
  }

  public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
    return pagamentoRepository
        .findAll(paginacao)
        .map(p -> modelMapper.map(p, PagamentoDTO.class));
  }

  public PagamentoDTO obterPorId(Long id){
    Pagamento pagamento = pagamentoRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public PagamentoDTO criarPagamento(PagamentoDTO pagamentoDto){
    Pagamento pagamento = modelMapper.map(pagamentoDto, Pagamento.class);
    pagamento.setStatus(Status.CRIADO);
    pagamentoRepository.save(pagamento);
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto){
    Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
    pagamento.setId(id);
    pagamento = pagamentoRepository.save(pagamento);
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public void excluirPagamento(Long id){
    pagamentoRepository.deleteById(id);
  }
}
