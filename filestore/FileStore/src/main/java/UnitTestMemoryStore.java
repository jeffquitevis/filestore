//import junit.framework.Assert;
//import org.junit.Test;
//
//import java.io.IOException;
//
///**
// * Created by jeff on 5/11/2016.
// */
//public class UnitTestMemoryStore {
//
//    @Test
//    public void testSearchID() throws IOException {
//
//        PersonStore ps = new PersonStore(new MemoryDataStore());
//        Person p1 = new Person(1,"jeff","quitevis");
//
//        ps.addUser(p1);
//
//        Person result = ps.search(1);
//        Assert.assertEquals(result.getFirstName(),p1.getFirstName());
//
//    }
//
//    @Test
//    public void testSearchNotValidID() throws IOException {
//
//        PersonStore ps = new PersonStore(new MemoryDataStore());
//        Person p1 = new Person(1,"jeff","quitevis");
//
//        ps.addUser(p1);
//
//        Person result = ps.search(10);
//        Assert.assertEquals(result,null);
//    }
//
//    @Test
//    public void testDeleteID() throws Exception {
//        PersonStore ps = new PersonStore(new MemoryDataStore());
//        Person p1 = new Person(1,"jeff","quitevis");
//        Person p2 = new Person(2,"stephen","quitevis");
//
//        ps.addUser(p1);
//        ps.addUser(p2);
//        Person result = ps.delete(1);
//        Assert.assertEquals(result.getFirstName(),p1.getFirstName());
//
//    }
//
//    @Test
//    public void testDeleteNotValidID() throws Exception {
//        PersonStore ps = new PersonStore(new MemoryDataStore());
//        Person p1 = new Person(1,"jeff","quitevis");
//        Person p2 = new Person(2,"stephen","quitevis");
//
//        ps.addUser(p1);
//        ps.addUser(p2);
//        Person result = ps.delete(3);
//        Assert.assertEquals(result,null);
//
//    }
//
//
//}
