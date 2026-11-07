package com.example.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseInitializerTest {
    @Test
    void shouldInitializeDatabase() {
        assertDoesNotThrow(DatabaseInitializer::initialize);
    }
}