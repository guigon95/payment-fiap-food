package br.com.fiapfood.external.infrastructure.repository.jpa;

import br.com.fiapfood.external.infrastructure.entity.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long> {

	Optional<CheckoutEntity> findByOrderId(Long id);

}
