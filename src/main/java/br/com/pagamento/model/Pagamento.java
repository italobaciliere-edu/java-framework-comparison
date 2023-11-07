package br.com.pagamento.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Data
@Serdeable
public class Pagamento implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Positive
  private BigDecimal valor;

  @NotBlank
  @Size(max=100)
  private String nome;

  @NotBlank
  @Size(max=19)
  private String numero;

  @NotBlank
  @Size(max=7)
  private String expiracao;

  @NotBlank
  @Size(min=3, max=3)
  private String codigo;

  @Enumerated(EnumType.STRING)
  private Status status;

  private Long pedidoId;

  private Long formaDePagamentoId;
}
