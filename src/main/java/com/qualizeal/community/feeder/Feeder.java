package com.qualizeal.community.feeder;

import com.qualizeal.community.feeder.beans.Beans;
import com.qualizeal.community.feeder.exceptions.TransformationException;
import com.qualizeal.community.feeder.transforms.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Feeder<T> {

    List<Transformer> transformers;

    protected Feeder() {
        this.transformers = new ArrayList<>();
    }

    public abstract List<Map<String, String>> toMaps();

    public abstract List<T> toObjects(Class<T> type);

    public <t> Feeder<t> registerTransformer(Transformer transformer) {
        transformers.add(transformer);
        return (Feeder<t>) this;
    }

    protected String transform(String variable) {
        String resolvedExpression = variable.trim();
        if (variable.trim().startsWith("#{")) {
            resolvedExpression = Beans.getFakerInstance().expression(variable);
            int counter = transformers.size();
            while(resolvedExpression.equals(variable) && counter > 0) {
                resolvedExpression = transformers.get(counter-1).transform(variable.trim());
                counter--;
            }
            if (resolvedExpression.equals(variable)) {
                throw new TransformationException();
            }
        }
        return resolvedExpression;
    }
}
