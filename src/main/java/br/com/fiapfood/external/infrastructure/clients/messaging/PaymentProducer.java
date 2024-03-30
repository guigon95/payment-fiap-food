package br.com.fiapfood.external.infrastructure.clients.messaging;

import br.com.fiapfood.adapters.dto.messaging.PaymentMessageDto;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentProducer {

	@Value("${aws.paymentsQueueName}")
	private String queueName;

	private final AmazonSQS amazonSQSClient;

	private final ObjectMapper objectMapper;

	public void publishMessage(PaymentMessageDto message) {

		try {
			String messageAsString = objectMapper.writeValueAsString(message);

			log.info("Publish message " + messageAsString);

			GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);
			amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), messageAsString);
		}
		catch (Exception e) {
			log.error("Queue Exception Message: {}", e.getMessage());
		}

	}

}
