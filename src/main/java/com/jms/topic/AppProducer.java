package com.jms.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author dong_liu
 * @Date 2017/10/17 11:23
 * 生产者
 */
@Slf4j
public class AppProducer {
    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String topicName = "topic-test";

    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //创建Connection
        Connection connection = connectionFactory.createConnection();
        //启动链接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标
        Destination destination = session.createTopic(topicName);
        //创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 10; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("test" + i);
            //
            producer.send(textMessage);
            System.out.println("发送的消息：" + textMessage.getText());
        }
        connection.close();
    }
}
