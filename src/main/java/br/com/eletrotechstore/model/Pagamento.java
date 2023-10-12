package br.com.eletrotechstore.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Data
public class Pagamento {

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


// todo - application properties