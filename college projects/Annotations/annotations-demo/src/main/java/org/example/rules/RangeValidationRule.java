package org.example.rules;

import org.example.annotations.Range;
import org.example.validation.ValidationResult;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RangeValidationRule implements ICheckValidationRule {
    @Override
    public <T> void validate(ValidationResult<T> result) {
        Class clazz = result.getValidatedObject().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            if(f.isAnnotationPresent(Range.class))
            {
                var range = f.getAnnotation(Range.class);
                try {
                    f.setAccessible(true);
                    if ((int) f.get(result.getValidatedObject()) > range.min() && (int) f.get(result.getValidatedObject()) > range.max() ){
                        //ok
                    }else{
                        result.setValid(false);
                        result.getNotValidFields().putIfAbsent(f.getName(), new ArrayList<>());
                        result.getNotValidFields().get(f.getName()).add(range.message());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
