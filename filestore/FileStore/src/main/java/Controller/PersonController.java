package Controller;

import Model.PersonModel;
import DataStore.PersonStore;
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
    private PersonStore personStore;
    private PersonModel personModel;

    public PersonController(PersonView view, PersonStore personStore){
        this.view = view;
        this.personStore = personStore;
    }

    public void add(PersonModel personModel) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {
        personStore.addUser(personModel);
    }

    public void search(int id) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        personModel = personStore.search(id);
        view.printPersonDetail(personModel.getId(),personModel.getFirstName(),personModel.getLastName());
    }

    public void getAllRecord() throws BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, IOException {
        List<PersonModel> tempList = personStore.getAllRecords();
        view.listAllPerson(tempList);
    }

    public void delete(int id) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {
        personStore.delete(id);
    }


}
