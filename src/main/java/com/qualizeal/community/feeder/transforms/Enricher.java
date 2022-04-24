package com.qualizeal.community.feeder.transforms;

public interface Enricher<T> {
    T enrich();
}
