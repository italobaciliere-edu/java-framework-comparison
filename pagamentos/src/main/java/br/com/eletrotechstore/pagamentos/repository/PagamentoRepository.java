package br.com.eletrotechstore.pagamentos.repository;

import br.com.eletrotechstore.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
