package com.xm666.rehurttime.util;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import com.googlecode.aviator.runtime.function.ClassMethodFunction;
import com.googlecode.aviator.utils.Reflector;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class PredicateUtil {
    static {
        AviatorEvaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
        try {
            addMethodFunctions();
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addMethodFunctions() throws IllegalAccessException, NoSuchMethodException {
        var instance = AviatorEvaluator.getInstance();

        Map<String, List<Method>> methodMap = Reflector.findMethodsFromClass(Util.class, true);

        for (Map.Entry<String, List<Method>> entry : methodMap.entrySet()) {
            String methodName = entry.getKey();
            List<Method> methods = entry.getValue();
            methods.add(null);
            instance.addFunction(new ClassMethodFunction((Class<?>) Util.class, true, methodName, methodName, methods));
            methods.removeLast();
        }
    }

    public static Object execute(String expression, Object... values) {
        return AviatorEvaluator.execute(expression, AviatorEvaluator.newEnv(values), true);
    }
}
