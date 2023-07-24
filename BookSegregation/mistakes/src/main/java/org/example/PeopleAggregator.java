package org.example;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.model.Samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeopleAggregator {

    public Map<Gender, List<Person>> aggregatePeopleByGender(){
        List<Person> newListOfMale = new ArrayList<>(); //lista mezczyzn
        List<Person> newListOfFemale = new ArrayList<>(); //lista kobiet
        Map<Gender, List<Person>> aggregatedByGender = new HashMap<>();
        aggregatedByGender.put(Gender.FEMALE, newListOfFemale);
        aggregatedByGender.put(Gender.MALE, newListOfMale);
        for (Person person : Samples.getSampleListOfPeople()) {
            if (person.getGender() == Gender.MALE){
                    newListOfMale.add(person);
                    aggregatedByGender.replace(Gender.MALE, newListOfMale);
            }
           else if(person.getGender() == Gender.FEMALE){
                newListOfFemale.add(person);
                aggregatedByGender.replace(Gender.FEMALE, newListOfFemale);
            }
        }
        return aggregatedByGender;
    }
}
