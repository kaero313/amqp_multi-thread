package amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component("myListen")
public class amqpListener implements MessageListener {

    /**
     *  amqp message listen
     * @param message
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onMessage(Message message) {
        String receivedMessage = "";
        try {
            receivedMessage = new String(message.getBody(), StandardCharsets.UTF_8);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!"".equals(receivedMessage) && receivedMessage != null) {
            String routeKey = message.getMessageProperties().getReceivedRoutingKey();

            if (routeKey != null && !"".equals(routeKey)) {
                if (routeKey.endsWith("receiver")) {
                    System.out.println(receivedMessage);
                }
            }
        } else {
            System.out.println("No Message");
        }
    }


}
