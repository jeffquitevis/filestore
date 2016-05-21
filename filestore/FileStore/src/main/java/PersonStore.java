import javax.xml.crypto.Data;
import java.io.IOException;
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

    public void addUser(Person person) throws IOException {
        dataStore.addPerson(person);
    }


    public Person search(int id) throws IOException {

        Person person = dataStore.getPerson(id);

       return person;
    }

     public Person delete(int id) throws IOException{

         Person deletePerson = null;

            deletePerson =  dataStore.delete(id);

         return deletePerson;
     }




}
