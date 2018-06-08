package amqp.topic;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 关键词routingKey消费者
 *
 * @author Administrator
 */
public class KeywordConsumer {

    private final static String EXCHANGE_NAME = "keyword_logs";

    private Logger logger = LoggerFactory.getLogger(KeywordConsumer.class);
    private Channel channel;


    public static void main(String[] args) throws IOException {
        KeywordConsumer keywordConsumer = new KeywordConsumer();
        keywordConsumer.consume("a.#");
    }

    public KeywordConsumer() {
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

    public void consume(String routingKey) throws IOException {
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,EXCHANGE_NAME,routingKey);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                logger.debug("{} 打印日志：{}",EXCHANGE_NAME,s);
            }
        };

        channel.basicConsume(queue,true,consumer);
    }

}
