package org.example;


import org.example.model.Geography;
import org.example.model.SampleGeographiesData;
import org.example.model.abstraction.IHaveHierarchicalStructure;
import org.example.tools.Hierarchy;
import org.example.tools.HierarchyBuilder;
import org.example.tools.abstractions.IParse;
import org.example.tools.geographies.GeographyParser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Geography sample = new Geography();
        sample.setId(0);
        sample.setCode("ab");
        sample.setName("xyz");
        sample.setParentId(null);
        sample.setParentId(-1);


        IParse<Geography> geographyParser;


        geographyParser = new GeographyParser();


        List<Geography> geographies = new ArrayList<>();

       for (String line : SampleGeographiesData.data) {
           geographies.add(geographyParser.parse(line));
       }

        IHaveHierarchicalStructure<Geography> geography = new Geography();


        geography.setParent(new Geography());
        List<Geography> children = geography.getChildren();
        Geography parent = geography.getParent();
        int id = geography.getId();
        Integer parentId = geography.getParentId();

        IHaveHierarchicalStructure<Person> person = new Person();

        person.setParent(new Person());
        List<Person> sons = person.getChildren();
        Person father = person.getParent();
        int personId = person.getId();
        Integer fatherId = person.getParentId();


        HierarchyBuilder<Geography> geographyHierarchyBuilder = new HierarchyBuilder<>();

        geographyHierarchyBuilder.setElements(geographies); //Niech builder przyjmie kolekcje geografii wygenerowany wcześniej
        geographyHierarchyBuilder.buildHierarchy(); // niech zbuduje hierarchie
        Geography rootGeography = geographyHierarchyBuilder.getRootElement();// niech zwróci rodzica wszystkichj geografii, czyli 'world'


        HierarchyBuilder<Person> personHierarchyBuilder = new HierarchyBuilder<>();

        personHierarchyBuilder.setElements(Person.sample);
        personHierarchyBuilder.buildHierarchy();
        Person rootPerson = personHierarchyBuilder.getRootElement();

        if(!Person.isEverythingOk()){
            System.out.println("Hierarchia jest chyba, źle zbudowana :(");
            return;
        }

        Hierarchy<Geography> geoHierarchy = new Hierarchy<>();
        geoHierarchy.setRootElement(rootGeography);
        Geography nilphamariZila = geoHierarchy.findElementById(555);

        if(!nilphamariZila.getName().equals("Nilphamari zila")){
            System.out.println("Chyba nie znalazł dobrego elementu :(");
            return;
        }

        Hierarchy<Person> personHierarchy = new Hierarchy<>();
        personHierarchy.setRootElement(rootPerson);
        Person p4 = personHierarchy.findElementById(3);
        if(Person.p4.id!=p4.id){
            System.out.println("Nie znalazł osoby o id 3 :(");
            return;
        }

        System.out.println("Udało się !! Wszystko działa :)");
    }
}


class Person implements IHaveHierarchicalStructure<Person> {
    List<Person> children = new ArrayList<>();
    Person parent;
    int id;
    Integer parentId;

    public Person() {
    }

    public Person(int id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    @Override
    public List<Person> getChildren() {
        return children;
    }

    @Override
    public Person getParent() {
        return parent;
    }

    @Override
    public void setParent(Person parent) {
        this.parent = parent;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    static Person p1 = new Person(0, null);
    static Person p2 = new Person(1, 0);
    static Person p3 = new Person(2, 0);
    static Person p4 = new Person(3, 1);
    static Person p5 = new Person(4, 2);
    static List<Person> sample = List.of(
            p1, p2, p3, p4, p5
    );

    public static boolean isEverythingOk() {
        return p1.children.size() == 2 && p1.parent==null
                && p2.children.size() == 1 && p2.parent.id == 0
                && p3.children.size() == 1 && p3.parent.id == 0
                && p4.children.size() == 0 && p4.parent.id == 1
                && p5.children.size() == 0 && p5.parent.id == 2;

    }

}