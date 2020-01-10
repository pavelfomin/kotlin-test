package net.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaServiceTest {

    private JavaService service = new JavaService();

    @Test
    public void getType() {

        assertEquals("java", service.getType());
    }
}