
package com.campusboot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Container {

    private final Map<String, Object> beans = new HashMap<>();

    public void scan(Class<?>... classes) {

        for (Class<?> clazz : classes) {

            if (clazz.isAnnotationPresent(Component.class)) {

                try {
                    Component component = clazz.getAnnotation(Component.class);

                    String beanName = component.value();

                    if (beanName.isEmpty()) {
                        beanName = Character.toLowerCase(
                                clazz.getSimpleName().charAt(0)
                        ) + clazz.getSimpleName().substring(1);
                    }

                    Object instance = clazz.getDeclaredConstructor().newInstance();

                    beans.put(beanName, instance);

                    System.out.println("[Container] Registered: "
                            + beanName + " (" + clazz.getSimpleName() + ")");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {

                System.out.println("[Container] Skipped: "
                        + clazz.getSimpleName() + " (no @Component)");

            }
        }
    }

    public void injectValues(Map<String, String> config) {

        for (Object bean : beans.values()) {

            Class<?> clazz = bean.getClass();

            for (Field field : clazz.getDeclaredFields()) {

                if (field.isAnnotationPresent(Value.class)) {

                    try {
                        Value value = field.getAnnotation(Value.class);

                        String configKey = value.value();

                        String configValue = config.get(configKey);

                        if (configValue == null) {
                            throw new RuntimeException(
                                    "Missing configuration: " + configKey
                            );
                        }

                        field.setAccessible(true);

                        if (field.getType() == String.class) {

                            field.set(bean, configValue);

                        } else if (field.getType() == int.class) {

                            field.setInt(bean, Integer.parseInt(configValue));

                        } else {

                            throw new IllegalArgumentException(
                                    "Unsupported field type: " + field.getType()
                            );
                        }

                        System.out.println("[Container] Injected: "
                                + configKey + " -> "
                                + clazz.getSimpleName() + "." + field.getName());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    public Map<String, Object> getBeans() {
        return beans;
    }
}
