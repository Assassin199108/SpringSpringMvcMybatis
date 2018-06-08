package amqp.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 关键词发送类
 *
 * @author Administrator
 */
public class KeywordSender {

    private final static String EXCHANGE_NAME = "keyword_logs";

    private Logger logger = LoggerFactory.getLogger(KeywordConsumer.class);
    private Channel channel;

    public static void main(String[] args) throws IOException, InterruptedException {
        KeywordSender keywordSender = new KeywordSender();
        while (true){
            keywordSender.send("消息推送","a.b");
            Thread.sleep(1000);
        }
    }

    public KeywordSender() {
        super();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void send(String message,String routingKey) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",false);

        channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes());
        logger.debug("{} 发送消息：{}",routingKey,message);
    }

}
