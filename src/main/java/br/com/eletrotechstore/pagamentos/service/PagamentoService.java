package br.com.eletrotechstore.pagamentos.service;

import br.com.eletrotechstore.pagamentos.dto.PagamentoDTO;
import br.com.eletrotechstore.pagamentos.model.Pagamento;
import br.com.eletrotechstore.pagamentos.model.Status;
import br.com.eletrotechstore.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class PagamentoService {

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Autowired
  private ModelMapper modelMapper;

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
