package amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @author Administrator
 */
public class Producer {

    private final static String QUEUE_NAME = "tiger";

    public static void main(String[] args) throws IOException, TimeoutException {
        Producer producer = new Producer();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            producer.sendMessage(scanner.next());
        }
    }

    private void sendMessage(String message) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置RabbitMq的地址
        connectionFactory.setHost("localhost");
        //建立到代理服务器到连接
        Connection connection = connectionFactory.newConnection();
        //获得信道
        Channel channel = connection.createChannel();
        //声明一个队列
        //参数含义：
        //  @parameter1:队列名称
        //  @parameter2:是否持久化，为ture则在rabbit重启后生存
        //  @parameter3:是否是排他性队列（别人看不到），只对当前连接有效，当前连接断开后，队列删除（设置了持久化也删除）
        //  @parameter4:自动删除，在最后一个连接断开后删除队列
        //  @parameter5: 其他参数
        // 注意，RabbitMQ不允许对一个已经存在的队列用不同的参数重新声明，对于试图这么做的程序，会报错，所以，改动之前代码之前，要在控制台中把原来的队列删除
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //声明交换机
        //  @param1:交换机名称
        //  @param2:交换机 类型 目前共四种类型：direct、fanout、fanout、headers
        //  @param3:是否持久化
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

        String routingKey = "hola";
        //发布消息
        byte[] messageBodyBytes = message.getBytes();
        //发送一条消息
        //  @parameter1:    交换模式，会在后面讲 官方文档 https://www.rabbitmq.com/tutorials/tutorial-three-java.html
        //  @parameter2:    控制消息发送到哪个队列
        //  @parameter3:    routing headers etc 其他参数
        //  @parameter4:    消息，byte数组
        channel.basicPublish(exchangeName,QUEUE_NAME,null,messageBodyBytes);
    }

}
