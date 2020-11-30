import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class Publisher {

    private final static String TOPIC = "Dentists";

    private final static String BROKER = "tcp://localhost:1883";

    private final static String USER_ID = "test-publisher";

    private final IMqttClient middleware;

    public Publisher() throws MqttException {
        middleware = new MqttClient(BROKER, USER_ID);
        middleware.connect();
    }

    void close() throws MqttException {
        middleware.disconnect();
        middleware.close();
    }

    void sendMessage(DentistRegistry messageTest) throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage();
        String msg = messageTest.toString();
        message.setPayload(msg.getBytes()); /*move messageTest into payload*/
        middleware.publish(TOPIC, message);
    }
}
