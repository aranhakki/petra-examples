package com.cognitionbox.petra.examples.trading.strategy.data;

import com.cognitionbox.petra.ast.terms.External;

import java.util.HashMap;
import java.util.Map;

@External public final class Singleton {
    private final static Map<Class<?>,Object> singletons = new HashMap<>();

    public static <T> T get(Class<T> clazz){
        return (T) singletons.computeIfAbsent(clazz, k-> {
            try {
                return clazz.newInstance();
            } catch (Exception e) {}
                return null;
            });
    }
}
