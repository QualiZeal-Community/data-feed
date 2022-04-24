package com.qualizeal.community.feeder;

import com.qualizeal.community.feeder.transforms.Enricher;

public class Foo implements Enricher<Foo> {
    @Override
    public Foo enrich() {
        return null;
    }
}
