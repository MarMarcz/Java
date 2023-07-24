package org.example;

import org.example.model.Person;
import org.example.model.Samples;

import java.util.Arrays;
import java.util.Random;

public class BooksAggregator {

    public void aggregateBooksThroughPeople(){

        for (Person person: Samples.getSampleListOfPeople())
        {

            if (Samples.getAvailableBooks().size() > 0){
                int index = getRandomIndex();
                //if osoba juz ma ksiazek
                person.getBooks().add(Samples.getAvailableBooks().get(index)); //daj komuś ksiązke
                Samples.getAvailableBooks().get(index).setOwner(person);//nadaj ksiązce właściciela
                Samples.getAvailableBooks().remove(Samples.getAvailableBooks().get(index)); //usuń ją z dostepnych ksiązek
            }
        }
    }
    private int getRandomIndex(){
        return new Random().nextInt(Samples.getAvailableBooks().size());
    }


}
