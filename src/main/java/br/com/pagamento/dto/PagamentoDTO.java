package br.com.pagamento.dto;

import br.com.pagamento.model.Status;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Serdeable.Serializable
@Serdeable.Deserializable
@Introspected
public class PagamentoDTO {

  private Long id;
  private BigDecimal valor;
  private String nome;
  private String numero;
  private String expiracao;
  private String codigo;
  private Status status;
  private Long pedidoId;
  private Long formaDePagamentoId;
}
