package amqp.fanout;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 日志消费者
 *
 * @author Administrator
 */
public class DiskConsumer {

    private final static String EXCHANGE_NAME = "logs";

    private Logger logger = LoggerFactory.getLogger(DiskConsumer.class);
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public DiskConsumer() {
        super();
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public boolean close(){
        try {
            channel.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void consume() throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout",false);
        // 获取一个临时队列
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,EXCHANGE_NAME,"");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body, "utf-8");
                logger.debug("[s] 我是来打印日志的：",s);
            }
        };

        //这里自动确认为true，接收到消息后该消息就销毁了
        channel.basicConsume(queue,true,consumer);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DiskConsumer diskConsumer = new DiskConsumer();
        diskConsumer.consume();
    }

}
