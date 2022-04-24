package com.qualizeal.community.feeder.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeansTest {
    @Test
    void testGetFakerInstance() {
        Assertions.assertNotNull(Beans.getFakerInstance());
    }
}

