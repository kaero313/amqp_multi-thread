package amqp;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class amqpProducer {

    public void sendMessage(String routingKey, String message) {

        CachingConnectionFactory cf = null;
        try {
            cf = new CachingConnectionFactory("localhost", Integer.parseInt("5672"));
            cf.setUsername("admin");
            cf.setPassword("admin");

            //메시지 전송
            RabbitTemplate template = new RabbitTemplate(cf);
            template.setExchange("receiver.exchange");
            //template.setQueue("receiver.queue");
            template.setEncoding("UTF-8");
            template.convertAndSend(routingKey, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cf != null) {
                try {
                    cf.destroy();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }
    }
}