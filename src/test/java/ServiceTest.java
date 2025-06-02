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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testLoginNoSuchUser() {
        assertThrows(NoSuchElementException.class, () -> userService.login("0987654321"));
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
    void testGetTasksOfNonExistingUser() {
        Task task = new Task();
        task.setUserId("6");
        taskRepository.save(task);
        List<Task> result = taskService.getAllTasks("7");
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetTasksPending() {
        Task task = new Task();
        task.setUserId("2");
        task.setDeleted(false);
        task.setCompleted(false);
        taskRepository.save(task);
        List<Task> result = taskService.getPendingTasks("2");
        assertEquals(1, result.size());
        assertEquals("2", result.get(0).getUserId());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task();
        task.setUserId("3");
        task.setDeleted(false);
        task.setCompleted(false);
        taskRepository.save(task);
        List<Task> result = taskService.getAllTasks("3");
        assertEquals(1, result.size());
        Task taskFromRepo = result.get(0);
        assertEquals("3", taskFromRepo.getUserId());

        taskService.markTaskAsDeleted(taskFromRepo.getId());

        List<Task> resultAfterDeletion = taskService.getAllTasks("3");
        assertEquals(1, resultAfterDeletion.size());
        assertTrue(resultAfterDeletion.get(0).isDeleted());
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
