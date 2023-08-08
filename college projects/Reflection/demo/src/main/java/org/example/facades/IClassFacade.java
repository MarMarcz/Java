package org.example.facades;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface IClassFacade {
  List<IMethodFacade> getPublicDeclaredMethods();
  List<IMethodFacade> getPublicSetters();
  List<IMethodFacade> getPublicGetters();
  List<Field> getFieldsForPublicProperties();
}
