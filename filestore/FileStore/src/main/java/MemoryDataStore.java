
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeff on 5/11/2016.
 */
public class MemoryDataStore implements DataStore {


    public static Map<Integer,Person> map = new HashMap<Integer, Person>();

    @Override
    public void addPerson(Person person) throws IOException {

        map.put(person.getId(),person);
    }


    @Override
    public Person getPerson(int id) throws IOException{

        Person person = null;

        if (map.containsKey(id)){

           person = map.get(id);
        }else{

            return null;
        }
        return person;
    }


    @Override
    public void delete(int id) {

        if (map.containsKey(id)){
           map.remove(id);
        }
    }



}
