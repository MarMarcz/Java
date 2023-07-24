package org.example.rules;

import org.example.validation.ValidationResult;

public interface ICheckValidationRule {
    public <T> void validate(ValidationResult<T> result);
}
