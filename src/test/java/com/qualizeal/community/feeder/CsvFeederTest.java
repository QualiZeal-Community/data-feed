package com.qualizeal.community.feeder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import com.qualizeal.community.feeder.exceptions.DataInstantiationException;
import com.qualizeal.community.feeder.exceptions.MappingException;
import org.junit.jupiter.api.Test;

class CsvFeederTest {
    @Test
    void testConstructor() {
        assertThrows(DataInstantiationException.class, () -> new CsvFeeder<>("File"));
        assertTrue((new CsvFeeder<>("")).transformers.isEmpty());
    }

    @Test
    void testToMaps() {
        List<Map<String, String>> actualToMapsResult = (new CsvFeeder<>("")).toMaps();
        assertEquals(4, actualToMapsResult.size());
        assertEquals(1, actualToMapsResult.get(0).size());
    }

    @Test
    void testToObjects2() {
        assertThrows(MappingException.class, () -> (new CsvFeeder<>("")).toObjects(null));
    }
}

