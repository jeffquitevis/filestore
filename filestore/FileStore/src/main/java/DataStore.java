import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by jeff on 5/10/2016.
 */
public interface DataStore {

    public void addPerson(Person person) throws IOException;
    public Person getPerson(int id) throws IOException;
    public Person delete(int id) throws IOException;

}
