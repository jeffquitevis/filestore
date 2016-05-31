package UnitTest;

import DataStore.Person;
import Model.PersonStoreModel;
import junit.framework.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import DataStore.MemoryDataStore;

/**
 * Created by jeff on 5/11/2016.
 */
public class UnitTestMemoryStore {




    @Test
    public void testSearchID() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");

        ps.addUser(p1);

        Person result = ps.search(1);
        Assert.assertEquals(result.getFirstName(),p1.getFirstName());

    }

    @Test
    public void testSearchNotValidID() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");

        ps.addUser(p1);

        Person result = ps.search(10);
        Assert.assertNull(result);
    }

    @Test
    public void testSearchEdgeCaseFirstRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
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
    public void testSearchEdgeCaseLastRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
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
    public void testDelete() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);

        ps.delete(1);

        Assert.assertNull(MemoryDataStore.map.get(1));


    }

    @Test
    public void testDeleteIdDoesNotExist() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);

        ps.delete(3);

        Assert.assertNull(MemoryDataStore.map.get(3));

    }

    @Test
    public void testDeleteEdgeCaseFirstRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        ps.delete(1);

       Assert.assertNull(MemoryDataStore.map.get(1));

    }

    @Test
    public void testDeleteEdgeCaseLastRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());
        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        ps.delete(3);

        Assert.assertEquals(MemoryDataStore.map.get(3),null);

    }

    @Test
    public  void testGetAll() throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        PersonStoreModel ps = new PersonStoreModel(new MemoryDataStore());

        Person p1 = new Person(false,1,"jeff","quitevis");
        Person p2 = new Person(false,2,"stephen","quitevis");
        Person p3 = new Person(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        List<Person> result = ps.getAllRecords();


        List<Person> test  = new ArrayList<Person>();
        test.add(p1);
        test.add(p2);
        test.add(p3);


        System.out.print(result.get(1).getFirstName());
        System.out.print(test.get(1).getFirstName());
    }



}
