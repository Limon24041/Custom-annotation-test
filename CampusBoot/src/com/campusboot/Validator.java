
package com.campusboot;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {

            if (field.isAnnotationPresent(Range.class)) {

                try {
                    field.setAccessible(true);

                    int actualValue = field.getInt(obj);

                    Range range = field.getAnnotation(Range.class);

                    if (actualValue < range.min() || actualValue > range.max()) {

                        errors.add(
                                field.getName() + " - "
                                        + range.message()
                                        + " (actual: " + actualValue + ")"
                        );
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return errors;
    }
}
