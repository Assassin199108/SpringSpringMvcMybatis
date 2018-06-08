package spring.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * @author Administrator
 */
public class RabbitMqMessage {

    /**
     * 1、ConnectionFactory包装了所有物理连接信息，然后传递给RabbitAdmin创建了RabbitMQ支持协议的连接（AMQP 0-9-1）
     * 2、声明队列和交换中心后，通过BindingBuilder把队列的绑定关系声明到admin上；
     * 3、创建一个消息处理类，用一个适配器（MessageListenerAdapter）包装它，并注册到监听容器中，启动监听；
     * 4、最后通过连接信息创建一个Rabbit模板，调用发送方法。
     *
     * 这里引申出一个问题，为什么要用监听容器（SimpleMessageListenerContainer），
     * 我们点开它的outline，如下，可以看到，它是对监听这个动作的抽象，一个容器可以有多个Consumer，并且可以控制如超时时间等配置
     * 可以看出，比起直接使用RabbitMQ客户端，以上代码已经简化了一部分，最明显的部分就是，不需要手动去关Channel、Connection了
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //获取一个连接工厂，用户默认是guest/guest（只能使用部署在本机的RabbitMQ）
        //是Spring实现的对com.rabbitmq.client.Connection的包装
        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");

        //对AMQP 0-9-1的实现
        RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);
        //声明一个队列
        Queue queue = new Queue("spring");
        rabbitAdmin.declareQueue(queue);
        //声明一个exchange
        TopicExchange topicExchange = new TopicExchange("spring-exchange");
        rabbitAdmin.declareExchange(topicExchange);
        //绑定队列到exchange，加上routingKey foo.*
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with("foo.*"));

        //监听容器
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        //监听者对象
        Object object = new Object(){
          public void handlerMessage(String foo){
              System.out.println(foo);
          }
        };
        //通过这个适配器代理listener
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(object);
        //把适配器（listener）设置给Container
        container.setMessageListener(listenerAdapter);
        //设置该容器监听的队列名，可以传多个，public void setQueueNames(String... queueName) {
        container.setQueueNames("spring");
        //开始监听
        container.start();

        //发送模版，设置上连接工厂
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        //发送消息
        rabbitTemplate.convertAndSend("spring-exchange","foo.bar","hello-world");

        Thread.sleep(1000);
        container.stop();
    }

}
