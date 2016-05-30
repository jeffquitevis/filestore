

/**
 * Created by jeff on 4/27/2016.
 */
public class App {





    public static void main(String[] args) throws Exception {


        DataStore pdsf = new PersonDataStoreFile(new RSAKey());
        PersonStore ps = new PersonStore(pdsf);


//        ps.addUser(new Person(false,1,"jeff","quitevis"));
//        ps.addUser(new Person(false,2,"marcus","quitevis"));
//        ps.addUser(new Person(false,3,"susan","quitevis"));


            System.out.print(ps.search(3));
//         System.out.print(ps.delete(2));
    }
}
