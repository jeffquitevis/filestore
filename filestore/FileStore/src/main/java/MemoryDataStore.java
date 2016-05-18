
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jeff on 5/11/2016.
 */
public class MemoryDataStore implements DataStore {


    Map<Integer,Person> map = new HashMap<>();

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
    public Person delete(int id) {

        Person person = null;

        if (map.containsKey(id)){

            person = map.remove(id);

        }else{

            return null;
        }

        return person;
    }

}
