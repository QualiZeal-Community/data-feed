package com.qualizeal.community.feeder.transforms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

class DateTransformerTest {

    @Test
    void testConstructor1() {
        SimpleDateFormat format = (new DateTransformer()).getFormat();
        assertEquals("dd/MM/yyyy", format.toPattern());
        assertTrue(format.isLenient());
    }

    @Test
    void testConstructor2() {
        SimpleDateFormat format = (new DateTransformer("42")).getFormat();
        assertEquals("42", format.toPattern());
        assertTrue(format.isLenient());
    }

    @Test
    void testTransform() {
        assertEquals("Expression", (new DateTransformer()).transform("Expression"));
        assertEquals("Expression", (new DateTransformer()).transform("Expression"));
        assertEquals("42", (new DateTransformer("42")).transform("#{Date42"));
    }
}

