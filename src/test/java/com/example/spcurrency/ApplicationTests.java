package com.example.spcurrency;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
@Data
public class ApplicationTests {

    @Autowired
    private UsersService usersService;

    @Autowired
    private HistoryService historyService;

    @Test
    public void contextLoads() {
        // empty test that would fail if our Spring configuration does not load correctly
    }

//    @Test
//    public void testAddUser() {
//        Users user = usersService.addUser("John", "Doe",
//                "johndoe", "password", UserRole.USER);
//        assertNotNull(user);
//        assertNotNull(user.getIdUser());
//        assertEquals("John", user.getName());
//        assertEquals("password", user.getPassword());
//        assertEquals("Doe", user.getSurname());
//        assertEquals("johndoe", user.getUsername());
//    }

//    @Test
//    public void testGetUserByUsername() {
//        Users user = usersService.addUser("John", "password", "Doe", "johndoe");
//        Users otherUser = usersService.getUserByUsername("johndoe");
//        assertNotNull(otherUser);
//        assertEquals(user, otherUser);
//    }
//
//    @Test
//    public void testAddHistory() {
//        Users user = usersService.addUser("John", "password", "Doe", "johndoe");
//        History history = historyService.addHistory("USD", "UAH", user);
//        assertNotNull(history);
//        assertNotNull(history.getId());
//        assertEquals("USD", history.getFirst());
//        assertEquals("UAH", history.getSecond());
//        assertEquals(user, history.getUser());
//        assertNotNull(history.getDate());
//    }
//
//    @Test
//    public void testGetAllHistory() {
//        Users user = usersService.addUser("John", "password", "Doe", "johndoe");
//        History history1 = historyService.addHistory("USD", "UAH", user);
//        History history2 = historyService.addHistory("USD", "EUR", user);
//        List<History> histories = historyService.getAllHistory();
//        assertEquals(2, histories.size());
//        assertTrue(histories.contains(history1));
//        assertTrue(histories.contains(history2));
//    }
}
