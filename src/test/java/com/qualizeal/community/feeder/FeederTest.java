package com.qualizeal.community.feeder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.qualizeal.community.feeder.transforms.Transformer;
import org.junit.jupiter.api.Test;

class FeederTest {
    @Test
    void testRegisterTransformer() {
        CsvFeeder<Foo> csvFeeder = new CsvFeeder<>("");
        assertSame(csvFeeder, csvFeeder.registerTransformer(mock(Transformer.class)));
    }

    @Test
    void testTransform() {
        assertEquals("Variable", (new CsvFeeder<>("")).transform("Variable"));
        assertThrows(RuntimeException.class, () -> (new CsvFeeder<>("")).transform("#{"));
        assertThrows(RuntimeException.class, () -> (new CsvFeeder<>("")).transform("#{#{"));
    }
}

