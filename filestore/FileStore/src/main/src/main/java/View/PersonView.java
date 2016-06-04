package View;

import Model.PersonModel;

import java.util.List;

/**
 * Created by jeff on 5/30/2016.
 */
public class PersonView {


    public void printPersonDetail(int id, String firstName, String lastName){

        System.out.println("PersonModel ID: " + id);
        System.out.println("PersonModel FIRSTNAME: " + firstName);
        System.out.println("PersonModel LASTNAME: " + lastName);
    }

    public void listAllPerson(List<PersonModel> personModelList){

        for (PersonModel personModel : personModelList){
            System.out.println("PersonModel ID: " + personModel.getId());
            System.out.println("PersonModel FIRSTNAME: " + personModel.getFirstName());
            System.out.println("PersonModel LASTNAME: " + personModel.getLastName());
            System.out.println("-------------------------------------------------");
        }
    }
}
