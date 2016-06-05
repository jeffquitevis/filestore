package com.filestore.DataStore;

import com.filestore.Model.PersonModel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 /**
 * Created by jeff on 5/10/2016.
 */
public class PersonStore {

    private DataStore dataStore;

    private final Map<Integer, PersonModel> map = new HashMap<>();

    public PersonStore(DataStore dataStore){
        this.dataStore = dataStore;
    }

    public void addUser(PersonModel personModel) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        dataStore.addPerson(personModel);
    }


    public PersonModel search(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonModel personModel = dataStore.getPerson(id);

       return personModel;
    }

     public void delete(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
            dataStore.delete(id);
     }

     public List<PersonModel> getAllRecords() throws IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, ClassNotFoundException {

        List<PersonModel> tempList = dataStore.getAllPerson();

         return tempList;

     }



}
