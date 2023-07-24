package org.example.validation;

import org.example.rules.ICheckValidationRule;
import org.example.rules.NotNullValidationRule;
import org.example.rules.RangeValidationRule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    List<ICheckValidationRule> rules = new ArrayList<>();

    public <T> ValidationResult<T> validate(T sampleObject) {
        ValidationResult<T> object = new ValidationResult<>();
        object.setValid(true);
        object.setValidatedObject(sampleObject);
        for (ICheckValidationRule rule: rules) {
            rule.validate(object);
        }
        return object;
    }

    public Validator addRule(ICheckValidationRule validationRule) {
        this.rules.add(validationRule);
        return this;
    }

}
