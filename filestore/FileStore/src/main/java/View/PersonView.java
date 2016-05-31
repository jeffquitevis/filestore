package View;

import DataStore.Person;

import java.util.List;

/**
 * Created by jeff on 5/30/2016.
 */
public class PersonView {


    public void printPersonDetail(int id, String firstName, String lastName){

        System.out.println("Person ID: " + id);
        System.out.println("Person FIRSTNAME: " + firstName);
        System.out.println("Person LASTNAME: " + lastName);
    }

    public void listAllPerson(List<Person> personList){

        for (Person person : personList){
            System.out.println("Person ID: " + person.getId());
            System.out.println("Person FIRSTNAME: " + person.getFirstName());
            System.out.println("Person LASTNAME: " + person.getFirstName());
        }
    }
}
