package br.com.pagamento.repository;

import br.com.pagamento.model.Pagamento;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {}
