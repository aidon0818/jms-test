package com.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
    private static final String URL="tcp://127.0.0.1:61616";
    private static final String queueName="queue-test";
    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);
        //创建Connection
        Connection connection=connectionFactory.createConnection();
        //启动链接
        connection.start();
        //创建会话
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标
        Destination destination=session.createQueue(queueName);
        //创建一个消费者
        MessageConsumer consumer=session.createConsumer(destination);
        //创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println("接收到信息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // connection.close();
    }
}
