package br.com.fiapfood.external.infrastructure.clients.gateway_card;

import br.com.fiapfood.adapters.dto.messaging.PaymentGatewayDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExternalGateway {

	@Value("${external.gateway}")
	private String url;

	public void send(PaymentGatewayDto message) {
		log.info("Sending message to external gateway {} {}", message, url);
	}

}
