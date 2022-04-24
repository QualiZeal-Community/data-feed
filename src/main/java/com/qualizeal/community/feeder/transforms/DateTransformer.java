package com.qualizeal.community.feeder.transforms;

import com.qualizeal.community.feeder.beans.Beans;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class DateTransformer implements Transformer {
    @Getter
    private final SimpleDateFormat format;

    public DateTransformer() {
        this("dd/MM/yyyy");
    }

    public DateTransformer(String pattern) {
        format = new SimpleDateFormat(pattern);
    }

    @Override
    public String transform(String expression) {
        if (expression.startsWith("#{Date")) {
            int days = getDays(expression);
            if (isPast(expression)) {
                return format.format(Beans.getFakerInstance().date().past(days, TimeUnit.DAYS));
            } else {
                return format.format(Beans.getFakerInstance().date().future(days, TimeUnit.DAYS));
            }
        } else {
            return expression;
        }
    }

    private boolean isPast(String expression) {
        return (expression.toLowerCase().contains("past"));
    }

    private int getDays(String expression) {
        Pattern pattern = Pattern.compile("[^0-9]");
        String numberOnly = pattern.matcher(expression).replaceAll("");
        return Integer.parseInt(numberOnly);
    }
}
