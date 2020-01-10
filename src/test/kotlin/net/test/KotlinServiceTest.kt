package net.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinServiceTest {

    private val service = KotlinService()

    @Test
    fun getType() {

        assertEquals("kotlin", service.getType())
    }
}