package amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import thread.thread;
import thread.threadPool;

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
                    amqp("[RESPONSE]" + receivedMessage); // AMQP 테스트

                    //single_thread(1, 0, 1000); // 싱글 스레드 테스트
                    //multi_thread(1, 0, 1000); // 멀티 스레드 테스트
                    //thread_pool(1, 0, 1000); // 스레드 풀 테스트

                }
            }
        } else {
            System.out.println("No Message");
        }
    }


    public void amqp(String receivedMessage){

        System.out.println(receivedMessage);

    }

    public void single_thread(int num, int startCnt, int endCnt){

        Thread thread = new Thread(new thread(num, startCnt, endCnt));
        thread.start();

    }

    public void multi_thread(int num, int startCnt, int endCnt){

        Thread thread1 = new Thread(new thread(num, startCnt, (int) (endCnt*0.2)));
        thread1.start();
        Thread thread2 = new Thread(new thread(++num, (int) (endCnt*0.2), (int) (endCnt*0.4)));
        thread2.start();
        Thread thread3 = new Thread(new thread(++num, (int) (endCnt*0.4), (int) (endCnt*0.6)));
        thread3.start();
        Thread thread4 = new Thread(new thread(++num, (int) (endCnt*0.6), (int) (endCnt*0.8)));
        thread4.start();
        Thread thread5 = new Thread(new thread(++num, (int) (endCnt*0.8), endCnt));
        thread5.start();

    }

    public void thread_pool(int num, int startCnt, int endCnt){

        threadPool threadPool = new threadPool(5, 10, 3000, 100);
        Thread thread = new Thread(new thread((int) Thread.currentThread().getId(), 0, 1000));
        threadPool.execute(thread);

    }

}
