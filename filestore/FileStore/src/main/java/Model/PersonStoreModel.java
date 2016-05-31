package Model;

import DataStore.DataStore;
import DataStore.Person;
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
public class PersonStoreModel {

    private DataStore dataStore;

    private final Map<Integer, Person> map = new HashMap<>();

    public PersonStoreModel(DataStore dataStore){
        this.dataStore = dataStore;
    }

    public void addUser(Person person) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        dataStore.addPerson(person);
    }


    public Person search(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        Person person = dataStore.getPerson(id);

       return person;
    }

     public void delete(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
            dataStore.delete(id);
     }

     public List<Person> get() throws IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, ClassNotFoundException {
        List<Person> tempList = dataStore.getAllPerson();

         return tempList;

     }



}
