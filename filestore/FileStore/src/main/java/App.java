import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jeff on 4/27/2016.
 */
public class App {

    public static void main(String[] args) throws Exception {


        DataStore pdsf = new PersonDataStoreFile();
        PersonStore ps = new PersonStore(pdsf);


//        ps.addUser(new Person(1,"jeff","quitevis"));
//        ps.addUser(new Person(2,"marcus","quitevis"));
//        ps.addUser(new Person(3,"susan","quitevis"));


//
//         System.out.print(ps.delete(2));
         System.out.print(ps.search(3));



    }
}
