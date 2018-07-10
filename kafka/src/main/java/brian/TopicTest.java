package brian;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class TopicTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        AdminClient adminClient = AdminClient.create(properties);
        NewTopic topic = new NewTopic("topic-test2", 1, (short) 1);
        ArrayList<NewTopic> newTopics = new ArrayList<NewTopic>();
        newTopics.add(topic);
        CreateTopicsResult result = adminClient.createTopics(newTopics);
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
