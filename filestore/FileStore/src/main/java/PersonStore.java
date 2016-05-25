import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.crypto.Data;
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

    private final Map<Integer,Person> map = new HashMap<>();

    public PersonStore(DataStore dataStore){
        this.dataStore = dataStore;
    }

    public void addUser(Person person) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        dataStore.addPerson(person);
    }


    public Person search(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        Person person = dataStore.getPerson(id);

       return person;
    }

     public Person delete(int id) throws IOException{

         Person deletePerson = null;

            deletePerson =  dataStore.delete(id);

         return deletePerson;
     }




}
