import com.example.java_spbstu.dto.UserDto;
import com.example.java_spbstu.entity.Notification;
import com.example.java_spbstu.entity.Task;
import com.example.java_spbstu.entity.User;
import com.example.java_spbstu.repo.NotificationRepository;
import com.example.java_spbstu.repo.SimpleNotificationRepository;
import com.example.java_spbstu.repo.SimpleTaskRepository;
import com.example.java_spbstu.repo.SimpleUserRepository;
import com.example.java_spbstu.repo.TaskRepository;
import com.example.java_spbstu.repo.UserRepository;
import com.example.java_spbstu.service.NotificationServiceImpl;
import com.example.java_spbstu.service.TaskServiceImpl;
import com.example.java_spbstu.service.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ServiceTest {
    private final TaskRepository taskRepository = new SimpleTaskRepository();
    private final TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);
    private final NotificationRepository notificationRepository = new SimpleNotificationRepository();
    private final NotificationServiceImpl notificationService = new NotificationServiceImpl(notificationRepository);
    private final UserRepository userRepository = new SimpleUserRepository();
    private final UserServiceImpl userService = new UserServiceImpl(userRepository);

    @Test
    void testSaveGetUser() {
        UserDto dto = new UserDto();
        dto.setUsername("user");
        User reg = userService.register(dto);
        assertEquals("user", reg.getUsername());
        User login = userService.login("user");
        assertEquals("user", login.getUsername());
    }

    @Test
    void testGetTasks() {
        Task task = new Task();
        task.setUserId("1");
        taskRepository.save(task);
        List<Task> result = taskService.getAllTasks("1");
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getUserId());
    }

    @Test
    void testGetNotifications() {
        Notification n = new Notification();
        n.setUserId("1");
        notificationService.save(n);
        List<Notification> result = notificationService.getAll("1");
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getUserId());
    }

    @Test
    void testGetNotificationsPending() {
        Notification n = new Notification();
        n.setUserId("2");
        n.setProcessed(false);
        notificationService.save(n);
        List<Notification> result = notificationService.getPending("2");
        assertEquals(1, result.size());
        assertFalse(result.get(0).isProcessed());
    }
}
