
package com.campusboot;

import java.lang.reflect.Method;

public class Router {

    public static String dispatch(Container c, String path) {

        for (Object bean : c.getBeans().values()) {

            Class<?> clazz = bean.getClass();

            for (Method method : clazz.getDeclaredMethods()) {

                if (method.isAnnotationPresent(Route.class)) {

                    Route route = method.getAnnotation(Route.class);

                    if (route.path().equals(path)) {

                        try {
                            method.setAccessible(true);

                            return (String) method.invoke(bean);

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        return "404 Not Found: " + path;
    }
}
