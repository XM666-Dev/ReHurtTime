package com.xm666.rehurttime.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;

import java.util.Map;
import java.util.function.Predicate;

public class PredicateUtil {
    static {
        AviatorEvaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
    }

    public static Predicate<Map<String, Object>> compile(String expression) {
        var compiled = AviatorEvaluator.compile(expression, true);
        return variables -> (boolean) compiled.execute(variables);
    }
}
