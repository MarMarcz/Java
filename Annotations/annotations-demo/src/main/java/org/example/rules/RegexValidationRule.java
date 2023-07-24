package org.example.rules;

import org.example.annotations.NotNull;
import org.example.annotations.Regex;
import org.example.validation.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RegexValidationRule implements ICheckValidationRule {
    @Override
    public <T> void validate(ValidationResult<T> result) {
        Class clazz = result.getValidatedObject().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            if(f.isAnnotationPresent(Regex.class))
            {
                var regex = f.getAnnotation(Regex.class);
                try {
                    f.setAccessible(true);
                    if (!f.get(result.getValidatedObject()).equals(regex.pattern())){
                        result.setValid(false);
                        result.getNotValidFields().putIfAbsent(f.getName(), new ArrayList<>());
                        result.getNotValidFields().get(f.getName()).add(regex.message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
