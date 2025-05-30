import com.example.java_spbstu.amqp.TaskCreatedEvent;
import com.example.java_spbstu.amqp.TaskEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestPublisher extends TaskEventPublisher {

    public TestPublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    public void publishTaskCreated(TaskCreatedEvent event) {
    }
}
