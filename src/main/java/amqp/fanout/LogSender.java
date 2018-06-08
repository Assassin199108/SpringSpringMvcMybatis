package amqp.fanout;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogSender {

    private final static String exchangeName = "logs";

    private Logger log = LoggerFactory.getLogger(LogSender.class);
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public LogSender() {
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

    /**
     * 发送消息
     *
     * @param message 信息
     */
    public void sendMessage(String message) throws IOException {
        channel.exchangeDeclare(exchangeName,"fanout",false);
        //fanout类型的exchange，忽略routingKey，所以第二个参数为空
        channel.basicPublish(exchangeName,"",null,message.getBytes());
        log.debug(" message send:{}",message);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LogSender logSender = new LogSender();
        while (true){
            logSender.sendMessage("测试");
            Thread.sleep(1000);
        }
    }
}
