package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SafetyNetAlertsApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    void testMain() {
        // On vérifie simplement que l'application se lance sans exception
        assertDoesNotThrow(() -> SafetyNetAlertsApplication.main(new String[] {}));
    }
	
}
