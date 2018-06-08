package amqp;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author Administrator
 */
public class Consumer {

    private final static String QUEUE_NAME = "tiger";

    public static void main(String[] args) throws IOException, TimeoutException {
        Runnable zhangsan = () -> {
                try {
                    Consumer consumer = new Consumer();
                    consumer.consume("zhangsan");
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
        };

        Runnable lisi = () -> {
            Consumer consumer1 = new Consumer();
            try {
                consumer1.consume("lisi");
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(zhangsan);
        thread.start();

        Thread thread1 = new Thread(lisi);
        thread1.start();
    }

    public void consume(String consumer) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setHost("localhost");
        //建立代理服务器到连接
        Connection connection = connectionFactory.newConnection();
        //获得信道
        Channel channel = connection.createChannel();
        //声明交换机
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);
        //// 获取一个临时队列
        String queue = channel.queueDeclare().getQueue();
        String routingKey = "hola";

        //在消息确认之前，不接收其他消息
        channel.basicQos(1);

        //绑定队列，通过键 hola 将队列和交换机绑定起来
        channel.queueBind(queue,exchangeName,QUEUE_NAME);
        while (true){
            //消费消息
            //关闭自动确认
            boolean autoAck = false;
            channel.basicConsume(queue,autoAck,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey1 = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键:"+routingKey1);
                    System.out.println("消费的内容类型:"+contentType);

                    //得到该次消息的交付标签
                    long deliveryTag = envelope.getDeliveryTag();

                    //确认消息
                    //  @para1:这个是RabbitMQ用来区分消息的 https://www.rabbitmq.com/amqp-0-9-1-reference.html#basic.deliver.delivery-tag
                    //  @para2:为true的话，确认所有消息，为false只确认当前消息
                    //RabbitMQ只有在收到消费者确认后，才会从内存中删除消息，
                    // 如果消费者忘了确认（更多情况是因为代码问题没有执行到确认的代码），将会导致内存泄漏
                    channel.basicAck(deliveryTag,false);

                    //消息体
                    String s = new String(body, "UTF-8");
                    System.out.println(consumer+"消费者"+"消费的消息体内容："+s);
                }
            });
        }
    }


}
