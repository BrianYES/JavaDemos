package com.brian.test;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:applicationContext-activemq.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringActiveMQTest {

    @Resource(name = "test-queue")
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "listenerContainer")
    private DefaultMessageListenerContainer listenerContainer;

    @Test
    public void testJmsTemplateQueue() {
        jmsTemplate.send(queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("hello spring activemq queue...");
                return textMessage;
            }
        });
    }

    @Test
    public void testQueueConsumer() throws IOException {
        listenerContainer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
    }

}
