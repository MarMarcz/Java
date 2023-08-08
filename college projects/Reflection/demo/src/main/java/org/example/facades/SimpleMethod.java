package org.example.facades;

import java.lang.reflect.Method;
import java.util.Locale;

public class SimpleMethod implements IMethodFacade {
    Method method;

    public SimpleMethod(Method method) {
        this.method = method;
    }

    @Override
    public boolean isPublic() {
        if (method.getModifiers() == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean paramCountEquals(int n) {
        if(method.getParameterCount()==n)
            return true;
        else
            return false;
    }

    @Override
    public boolean startsWith(String prefix) {
        if(prefix.startsWith(prefix))
            return true;
        else
            return false;
    }

    @Override
    public boolean isVoid() {
        if(method.getReturnType().equals(Void.TYPE))
            return true;
        else
            return false;
    }

    @Override
    public boolean isSetter() {
        if (method.getModifiers() == 1 && method.getReturnType().equals(Void.TYPE) && method.getName().startsWith("set") && method.getParameterCount()==1 )
            return true;
        else
            return false;
    }

    @Override
    public boolean isGetter() {
        if(method.getModifiers() == 1 && !method.getReturnType().equals(Void.TYPE) && (method.getName().startsWith("get") ||  method.getName().startsWith("is")) && method.getParameterCount()==0)
            return true;
        else
            return false;
    }

    @Override
    public String getFieldName() {
        String fieldName = method.getName().replace("get", "");
        fieldName = fieldName.replace("is", "");
        fieldName = fieldName.replace("set", "");
        fieldName = fieldName.toLowerCase(Locale.ROOT);
        return fieldName;
    }

    @Override
    public Method getUnderlyingMethod() {
        return method;
    }
}
