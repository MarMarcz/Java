package org.example.facades;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SimpleClass implements IClassFacade{
    Class<?> clazz;

    public SimpleClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<IMethodFacade> getPublicDeclaredMethods() {
        List<IMethodFacade> declaredMethods = Arrays.stream(clazz.getDeclaredMethods())
                .map(m-> (IMethodFacade)new SimpleMethod(m))
                .toList();
        declaredMethods = declaredMethods.stream()
                .filter(m-> m.isPublic())
                .toList();

        return declaredMethods;
    }

    @Override
    public List<IMethodFacade> getPublicSetters() {
        List<IMethodFacade> declaredMethods = Arrays.stream(clazz.getDeclaredMethods())
                .map(m-> (IMethodFacade)new SimpleMethod(m))
                .toList();
        declaredMethods = declaredMethods.stream()
                .filter(m-> m.isSetter()&& m.isPublic())
                .toList();

        return declaredMethods;
    }

    @Override
    public List<IMethodFacade> getPublicGetters() {
        List<IMethodFacade> declaredMethods = Arrays.stream(clazz.getDeclaredMethods())
                .map(m-> (IMethodFacade)new SimpleMethod(m))
                .toList();
        declaredMethods = declaredMethods.stream()
                .filter(m-> m.isGetter()&& m.isPublic())
                .toList();
        return declaredMethods;
    }

    @Override
    public List<Field> getFieldsForPublicProperties() {

        List<Field> fields =  Arrays.stream(clazz.getDeclaredFields())
                .toList();

        List<Field> fieldsWithBoth = new ArrayList<>();

        List<IMethodFacade> declaredMethods = Arrays.stream(clazz.getDeclaredMethods())
                .map(m-> (IMethodFacade)new SimpleMethod(m))
                .toList();

        for (Field field: fields) {

            int hasBoth = 0;

            for (IMethodFacade method: declaredMethods) {
                if(method.isGetter() && method.getFieldName().toLowerCase(Locale.ROOT).equals(field.getName())){
                        hasBoth++;
                }
                if(method.isSetter() && method.getFieldName().equals(field.getName())){
                    hasBoth++;
                }
            }
            if(hasBoth==2){
                fieldsWithBoth.add(field);
            }

        }

        return fieldsWithBoth;
    }
}
