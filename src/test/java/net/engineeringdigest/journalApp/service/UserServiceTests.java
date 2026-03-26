package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindUserByUserName() {
        assertNotNull(userRepository.findByUserName("sanath"));
    }

    @ParameterizedTest
    @CsvSource({
            "3,3,6",
            "1,1,3",
            "2,2,4"
    })
    public void test(int a,int b,int expected) {
        assertEquals(expected,a+b);
    }
}
