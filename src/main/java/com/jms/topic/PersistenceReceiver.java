package com.jms.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Dong_Liu
 * date：2017/10/17
 */
public class PersistenceReceiver {
    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = "tcp://127.0.0.1:61616";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接
        Session session;//会话 接受或者发送消息的线程
        Topic topic;//消息的目的地
        //实例化连接工厂(连接到ActiveMQ服务器)
        connectionFactory = new ActiveMQConnectionFactory(PersistenceReceiver.USERNAME, PersistenceReceiver.PASSWORD, PersistenceReceiver.BROKEURL);
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            connection.setClientID("winner_0715");
            //创建session
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //生产者将消息发送到MyTopic，所以消费者要到MyTopic去取
            topic = session.createTopic("MyTopic");
            //创建消息消费者
            TopicSubscriber consumer = session.createDurableSubscriber(topic, "t1");

            //启动连接
            connection.start();

            Message message = consumer.receive();
            while (message != null) {
                TextMessage txtMsg = (TextMessage) message;
                System.out.println("收到消 息：" + txtMsg.getText());
                //没这句有错
                message = consumer.receive(1000L);
            }
            session.commit();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
