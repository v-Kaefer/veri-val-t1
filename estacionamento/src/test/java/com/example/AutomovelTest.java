package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AutomovelTest {

    @Test
    public void testCriacaoAutomovel() {
        Automovel automovel = new Automovel("ABC-1234", false);

        assertEquals("ABC-1234", automovel.getPlaca());
        assertFalse(automovel.isVip());
    }

    @Test
    public void testAutomovelVip() {
        Automovel automovel = new Automovel("VIP-5678", true);

        assertEquals("VIP-5678", automovel.getPlaca());
        assertTrue(automovel.isVip());
    }
}
