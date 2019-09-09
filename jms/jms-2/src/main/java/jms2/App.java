package jms2;


import java.io.File;
import java.util.HashSet;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.remoting.impl.invm.InVMAcceptorFactory;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.impl.ActiveMQServerImpl;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final HashSet<TransportConfiguration> transports = new HashSet<>();
    transports.add(new TransportConfiguration(InVMAcceptorFactory.class.getName()));

    final Configuration configuration = new ConfigurationImpl();
    configuration.setAcceptorConfigurations(transports);
    configuration.setSecurityEnabled(false);

    final File targetDir = new File(new File("").getAbsoluteFile(), "target");
    configuration.setBrokerInstance(targetDir);

    ActiveMQServer server = new ActiveMQServerImpl(configuration);
    server.start();

    final ActiveMQJMSConnectionFactory connectionFactory = new ActiveMQJMSConnectionFactory(
        "vm://0");
    Connection connection = connectionFactory.createConnection();
    connection.start();

    JMSContext context = connectionFactory.createContext();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    System.out.println("Session: " + session);

    final Destination destination = session.createQueue("TEST.FOO2");

    final JMSProducer producer = context.createProducer();
    System.out.println("PRODUCER: " + producer);

    final MessageConsumer consumer = session.createConsumer(destination);
    System.out.println("CONSUMER: " + consumer);

    final TextMessage message = session.createTextMessage("Hello world");

    producer.send(destination, message);

    final TextMessage received = (TextMessage) consumer.receive(5000);
    System.out.println("RECEIVED: " + received.getText());

    context.close();
    session.close();
    connection.close();
    server.stop(true);

    Util.checkSpan("java-jms", 2);
    System.exit(0);
  }

}