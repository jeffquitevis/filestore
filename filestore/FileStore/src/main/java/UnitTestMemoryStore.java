import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jeff on 5/11/2016.
 */
public class UnitTestMemoryStore {


    

    @Test
    public void testSearchID() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");

        ps.addUser(p1);

        Person result = ps.search(1);
        Assert.assertEquals(result.getFirstName(),p1.getFirstName());

    }

    @Test
    public void testSearchNotValidID() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");

        ps.addUser(p1);

        Person result = ps.search(10);
        Assert.assertNull(result);
    }

    @Test
    public void testSearchEdgeCaseFirstRecord() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"marcus","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        Person result = ps.search(1);

        Assert.assertEquals(result.getFirstName(),p1.getFirstName());
    }

    @Test
    public void testSearchEdgeCaseLastRecord() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"marcus","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        Person result = ps.search(3);

        Assert.assertEquals(result.getFirstName(),p3.getFirstName());
    }


    @Test
    public void testDelete() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);

        Person result = ps.delete(1);

        Assert.assertEquals(result.getFirstName(),p1.getFirstName());


    }

    @Test
    public void testDeleteIdDoesNotExist() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);

        Person result = ps.delete(3);

        Assert.assertNull(result);

    }

    @Test
    public void testDeleteEdgeCaseFirstRecord() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        Person result = ps.delete(1);

       Assert.assertEquals(result.getFirstName(),p1.getFirstName());

    }

    @Test
    public void testDeleteEdgeCaseLastRecord() throws IOException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        Person result = ps.delete(3);

        Assert.assertEquals(result.getFirstName(),p3.getFirstName());

    }


}
