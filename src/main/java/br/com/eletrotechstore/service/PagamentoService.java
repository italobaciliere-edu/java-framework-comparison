package br.com.eletrotechstore.service;

import br.com.eletrotechstore.dto.PagamentoDTO;
import br.com.eletrotechstore.model.Pagamento;
import br.com.eletrotechstore.model.Status;
import br.com.eletrotechstore.repository.PagamentoRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


@Transactional
@ApplicationScoped
public class PagamentoService {

  @Inject
  private PagamentoRepository pagamentoRepository;

  @Inject
  private ModelMapper modelMapper;

  public List<PagamentoDTO> obterTodos(int page, int pageSize, String sort, String order) {

    String jpql = sort + (order.equalsIgnoreCase("asc") ? " asc" : " desc");

    return pagamentoRepository.findAll(Sort.by(jpql))
            .page(Page.of(page, pageSize))
            .list()
            .stream()
            .map(pagamento -> modelMapper.map(pagamento, PagamentoDTO.class))
            .collect(Collectors.toList());
  }

  public List<Pagamento> listAllPagamentos() {
    return pagamentoRepository.listAll();
  }

  public PagamentoDTO obterPorId(Long id){
    Pagamento pagamento = pagamentoRepository.findById(id);
    if (pagamento == null) {
      throw new EntityNotFoundException();
    }
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public PagamentoDTO criarPagamento(PagamentoDTO pagamentoDto){
    Pagamento pagamento = modelMapper.map(pagamentoDto, Pagamento.class);
    pagamento.setStatus(Status.CRIADO);
    pagamentoRepository.persist(pagamento);
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto){
    Pagamento pagamento = pagamentoRepository.findById(id);
    System.out.println("Busca no banco: " + pagamento.toString());
    if (pagamento == null) {
      return null;}
    pagamento.setValor(dto.getValor());
    pagamento.setNome(dto.getNome());
    pagamento.setNumero(dto.getNumero());
    pagamento.setExpiracao(dto.getExpiracao());
    pagamento.setCodigo(dto.getCodigo());
    pagamento.setStatus(dto.getStatus());
    pagamento.setPedidoId(dto.getPedidoId());
    pagamento.setFormaDePagamentoId(dto.getFormaDePagamentoId());
    pagamentoRepository.persist(pagamento);
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public void excluirPagamento(Long id){
    pagamentoRepository.deleteById(id);
  }
}
