package com.qualizeal.community.feeder;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.qualizeal.community.feeder.exceptions.MappingException;
import com.qualizeal.community.feeder.transforms.Enricher;
import com.qualizeal.community.feeder.exceptions.DataInstantiationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvFeeder<T extends Enricher<T>> extends Feeder<T> {

    private final InputStreamReader inputStreamReader;

    public CsvFeeder(String file) {
        super();
        try {
            InputStream in = getClass().getResourceAsStream(file);
            if(Objects.isNull(in)) throw new DataInstantiationException();
            inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DataInstantiationException();
        }
    }

    @Override
    public List<Map<String, String>> toMaps() {
        try {
            List<Map<String, String>> maps = new ArrayList<>();
            List<String[]> contents = new CSVReader(inputStreamReader).readAll();
            String[] headers;
            if (!contents.isEmpty()) {
                headers = contents.get(0);
                IntStream.range(1, contents.size()).forEach(i -> {
                    String[] row = contents.get(i);
                    Map<String, String> keyValPair = new HashMap<>();
                    for (int j = 0; j < row.length; j++) {
                        keyValPair.put(headers[j], transform(row[j]));
                    }
                    maps.add(keyValPair);
                });
            }
            return maps;
        } catch (Exception e) {
            throw new MappingException();
        }
    }

    @Override
    public List<T> toObjects(Class<T> type) {
        try {
            return new CsvToBeanBuilder<T>(inputStreamReader)
                    .withType(type).build().parse()
                    .stream().map(Enricher::enrich)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MappingException();
        }
    }
}