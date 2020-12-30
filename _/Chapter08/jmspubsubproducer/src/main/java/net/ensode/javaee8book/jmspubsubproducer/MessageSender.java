package net.ensode.javaee8book.jmspubsubproducer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

@Named
@RequestScoped
public class MessageSender {

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/JavaEE8BookTopic")
    private Topic topic;
    private static final Logger LOG = Logger.getLogger(MessageSender.class.getName());

    public void produceMessages() {
        JMSContext jmsContext = connectionFactory.createContext();
        JMSProducer jmsProducer = jmsContext.createProducer();

        String msg1 = "Testing, 1, 2, 3. Can you hear me?";
        String msg2 = "Do you copy?";
        String msg3 = "Good bye!";

        LOG.log(Level.INFO, "Sending the following message: {0}", msg1);
        jmsProducer.send(topic, msg1);
        LOG.log(Level.INFO, "Sending the following message: {0}", msg2);
        jmsProducer.send(topic, msg2);
        LOG.log(Level.INFO, "Sending the following message: {0}", msg3);
        jmsProducer.send(topic, msg3);

    }
}
