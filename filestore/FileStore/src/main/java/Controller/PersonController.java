package Controller;

import DataStore.Person;
import Model.PersonStoreModel;
import View.PersonView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by jeff on 5/30/2016.
 */
public class PersonController {

    private PersonView view;
    private PersonStoreModel model;

    public PersonController(PersonView view, PersonStoreModel model){

        this.view = view;
        this.model = model;
    }

    public void add(Person person) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {
        model.addUser(person);
    }

    public void search(int id) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        Person temp = model.search(id);
        view.printPersonDetail(temp.getId(),temp.getFirstName(),temp.getLastName());
    }

    public void getAllRecord() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, IOException {
        List<Person> tempList = model.getAllRecords();
        view.listAllPerson(tempList);
    }

    public void delete(int id) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {
        model.delete(id);
    }


}
