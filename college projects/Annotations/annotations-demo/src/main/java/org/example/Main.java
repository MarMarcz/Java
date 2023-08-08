package org.example;


import org.example.rules.ICheckValidationRule;
import org.example.rules.NotNullValidationRule;
import org.example.rules.RangeValidationRule;
import org.example.rules.RegexValidationRule;
import org.example.validation.ValidationResult;
import org.example.validation.Validator;

public class Main {
    public static void main(String[] args) throws Exception {


        createValidationRuleForNotNullAnnotation();
        createValidationRuleForRangeAnnotation();
        createValidationRuleForRegexAnnotation();
        implementaValidatorClass();
    }




    private static void createValidationRuleForNotNullAnnotation() throws Exception {
        var sample = new SampleObject(null, null,-1);
        ValidationResult<SampleObject> validationResult = new ValidationResult<>();
        validationResult.setValidatedObject(sample);
        validationResult.setValid(true);


        ICheckValidationRule notNullRule = new NotNullValidationRule();
        notNullRule.validate(validationResult);


        if(validationResult.isValid()) throw new Exception("Obiekt posiada pola które są ustawione na null, a nie powinny być.");

        var notValidFieldNames = validationResult.getNotValidFields().keySet();
        if(notValidFieldNames.size()!=2 || !notValidFieldNames.contains("email")|| !notValidFieldNames.contains("name"))
            throw new Exception("Pola które są null to name i email - walidator powinien to wychwycić");


        var validationMessagesForName = validationResult.getNotValidFields().get("name")
                .stream()
                .findFirst()
                .orElse(null);
        var validationMessagesForEmail = validationResult.getNotValidFields().get("email")
                .stream()
                .findFirst()
                .orElse(null);
        if(!validationMessagesForName.equalsIgnoreCase("pole jest null")
        || !validationMessagesForEmail.equalsIgnoreCase("field is null"))
            throw new Exception("złe wiadomości o błędzie");

    }

    private static void createValidationRuleForRangeAnnotation() throws Exception {
        var sample = new SampleObject("", "",-1);
        ValidationResult<SampleObject> validationResult = new ValidationResult<>();
        validationResult.setValidatedObject(sample);
        validationResult.setValid(true);

        ICheckValidationRule rangeValidationRule = new RangeValidationRule();
        rangeValidationRule.validate(validationResult);

        if(validationResult.isValid()) throw new Exception("Obiekt posiada pola które są poza zakresem");

        var notValidFieldNames = validationResult.getNotValidFields().keySet();
        if(notValidFieldNames.size()!=1 || !notValidFieldNames.contains("number"))
            throw new Exception("Pole które jest poza zakresem to number - walidator powinien to wychwycić");

        var validationMessagesForNumber = validationResult.getNotValidFields().get("number")
                .stream()
                .findFirst()
                .orElse(null);

        if(!validationMessagesForNumber.equalsIgnoreCase("number is out of range [%d,%d]" .formatted(0, 10)))
            throw new Exception("złe wiadomości o błędzie");

    }

    private static void createValidationRuleForRegexAnnotation() throws Exception {
        var sample = new SampleObject("", "jakis napis",-1);
        var validationResult = new ValidationResult<SampleObject>();
        validationResult.setValidatedObject(sample);
        validationResult.setValid(true);

        ICheckValidationRule regexValidationRule = new RegexValidationRule();
        regexValidationRule.validate(validationResult);

        if(validationResult.isValid()) throw new Exception("Obiekt posiada pola które jest nie poprawnego formatu regex");

        var notValidFieldNames = validationResult.getNotValidFields().keySet();
        if(notValidFieldNames.size()!=1 || !notValidFieldNames.contains("email"))
            throw new Exception("pole email ma źle wpisany email - walidator powinien to wychwycić");

        var validationMessagesForNumber = validationResult.getNotValidFields().get("email")
                .stream()
                .findFirst()
                .orElse(null);

        if(!validationMessagesForNumber.equalsIgnoreCase("email should be in correct format"))
            throw new Exception("złe wiadomości o błędzie");

    }

    private static void implementaValidatorClass() throws Exception {

        var sampleObject = new SampleObject(null, "jakis napis",-1);
        Validator validator = new Validator();

        validator.addRule(new NotNullValidationRule())
                .addRule(new RangeValidationRule())
                .addRule(new RegexValidationRule());

        ValidationResult<SampleObject> result = validator.validate(sampleObject);

        var resultNames = result.getNotValidFields().keySet();
        if(resultNames.size()!=3)
            throw new Exception("3 pola w klasie są złej wartości - walidator powinien to wychwycić");

    }

}

