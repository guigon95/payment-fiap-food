package br.com.fiapfood.external.infrastructure.repository.JPA;

import br.com.fiapfood.external.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByOrderId(Long id);
}
