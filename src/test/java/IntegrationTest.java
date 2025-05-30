import com.example.java_spbstu.amqp.TaskEventPublisher;
import com.example.java_spbstu.dto.UserDto;
import com.example.java_spbstu.entity.Notification;
import com.example.java_spbstu.entity.Task;
import com.example.java_spbstu.entity.User;
import com.example.java_spbstu.repo.NotificationRepository;
import com.example.java_spbstu.repo.TaskRepository;
import com.example.java_spbstu.repo.UserRepository;
import com.example.java_spbstu.service.NotificationServiceImpl;
import com.example.java_spbstu.service.TaskServiceImpl;
import com.example.java_spbstu.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {

    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskEventPublisher taskEventPublisher;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private NotificationServiceImpl notificationService;
    @InjectMocks
    private TaskServiceImpl taskService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testSaveGetUser() {
        UserDto dto = new UserDto();
        dto.setUsername("user");

        User mockUser = new User();
        mockUser.setUsername("user");

        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(userRepository.findByUsername("user")).thenReturn(mockUser);

        User reg = userService.register(dto);
        assertEquals("user", reg.getUsername());

        User login = userService.login("user");
        assertEquals("user", login.getUsername());
    }

    @Test
    void testGetTasks() {
        Task task = new Task();
        task.setUserId("1");

        when(taskRepository.findAllByUserId("1")).thenReturn(Collections.singletonList(task));

        List<Task> result = taskService.getAllTasks("1");
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getUserId());
    }

    @Test
    void testGetNotifications() {
        Notification notification = new Notification();
        notification.setUserId("1");

        when(notificationRepository.findAll("1")).thenReturn(Collections.singletonList(notification));

        List<Notification> result = notificationService.getAll("1");
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getUserId());
    }

    @Test
    void testGetNotificationsPending() {
        Notification notification = new Notification();
        notification.setUserId("2");
        notification.setProcessed(false);

        when(notificationRepository.findPending("2")).thenReturn(Collections.singletonList(notification));

        List<Notification> result = notificationService.getPending("2");
        assertEquals(1, result.size());
        assertFalse(result.get(0).isProcessed());
    }
}
