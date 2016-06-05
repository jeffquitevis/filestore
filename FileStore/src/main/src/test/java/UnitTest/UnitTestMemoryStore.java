package UnitTest;

import com.filestore.Model.PersonModel;
import com.filestore.DataStore.PersonStore;
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

import com.filestore.DataStore.MemoryDataStore;

/**
 * Created by jeff on 5/11/2016.
 */
public class UnitTestMemoryStore {




    @Test
    public void testSearchID() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");

        ps.addUser(p1);

        PersonModel result = ps.search(1);
        Assert.assertEquals(result.getFirstName(),p1.getFirstName());

    }

    @Test
    public void testSearchNotValidID() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");

        ps.addUser(p1);

        PersonModel result = ps.search(10);
        Assert.assertNull(result);
    }

    @Test
    public void testSearchEdgeCaseFirstRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,2,"marcus","quitevis");
        PersonModel p3 = new PersonModel(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        PersonModel result = ps.search(1);

        Assert.assertEquals(result.getFirstName(),p1.getFirstName());
    }

    @Test
    public void testSearchEdgeCaseLastRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,2,"marcus","quitevis");
        PersonModel p3 = new PersonModel(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

       PersonModel result = ps.search(3);

        Assert.assertEquals(result.getFirstName(),p3.getFirstName());
    }


    @Test
    public void testDelete() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,2,"stephen","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);

        ps.delete(1);

        Assert.assertNull(MemoryDataStore.map.get(1));


    }

    @Test
    public void testDeleteIdDoesNotExist() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,2,"stephen","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);

        ps.delete(3);

        Assert.assertNull(MemoryDataStore.map.get(0));

    }

    @Test
    public void testDeleteEdgeCaseFirstRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,2,"stephen","quitevis");
        PersonModel p3 = new PersonModel(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        ps.delete(1);

       Assert.assertNull(MemoryDataStore.map.get(1));

    }

    @Test
    public void testDeleteEdgeCaseLastRecord() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());
        PersonModel p1 = new PersonModel(false,1,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,2,"stephen","quitevis");
        PersonModel p3 = new PersonModel(false,3,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        ps.delete(3);

        Assert.assertEquals(MemoryDataStore.map.get(3),null);

    }

    @Test
    public  void testGetAll() throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        PersonStore ps = new PersonStore(new MemoryDataStore());

        PersonModel p1 = new PersonModel(false,0,"jeff","quitevis");
        PersonModel p2 = new PersonModel(false,1,"stephen","quitevis");
        PersonModel p3 = new PersonModel(false,2,"susan","quitevis");

        ps.addUser(p1);
        ps.addUser(p2);
        ps.addUser(p3);

        List<PersonModel> result = ps.getAllRecords();


        List<PersonModel> test  = new ArrayList<PersonModel>();
        test.add(p1);
        test.add(p2);
        test.add(p3);

        for (int x = 0; x < result.size(); x++){

            Assert.assertEquals(result.get(x).getFirstName(),test.get(x).getFirstName());
        }

   }



}
