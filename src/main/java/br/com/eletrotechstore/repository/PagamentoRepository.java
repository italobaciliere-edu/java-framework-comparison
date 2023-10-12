package br.com.eletrotechstore.repository;

import br.com.eletrotechstore.model.Pagamento;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.data.jpa.repository.JpaRepository;

@ApplicationScoped
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
