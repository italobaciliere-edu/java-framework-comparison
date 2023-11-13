package br.com.eletrotechstore.configuration;

import br.com.eletrotechstore.dto.PagamentoDTO;
import br.com.eletrotechstore.model.Pagamento;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

  PagamentoDTO pagamentosToPagamentoDto(Pagamento pagamento);

  Pagamento pagamentoDtoToPagamento(PagamentoDTO pagamentoDTO);

}
