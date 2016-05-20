import java.io.*;
import java.util.HashMap;

/**
 * Created by jeff on 5/10/2016.
 */
public class PersonDataStoreFile implements DataStore {

    private final File file = new File("c:/temp/jeff.txt");

    private final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public PersonDataStoreFile() throws IOException {

        if (file.exists()){

            reindexMap();

        }
    }

    private void reindexMap() throws IOException{


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);


        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){



            Person tempPerson = null;

            //Initialize tempFileSize
            int tempFileSize = 0;

<<<<<<< HEAD

            for (int x = 0; x < file.length(); x = baos.size() ){
=======
            for (int x = 0; x < file.length(); x = baos.size()){
>>>>>>> 7d61bc04ac36dee3b22dacaf7107fcd2d29c31d9

                //Read existing file and add the Person element to list
                tempPerson = new Person(dis.readBoolean(),dis.readInt(),dis.readUTF(),dis.readUTF());

                //Write the Person element to a temp file buffer
                dos.writeBoolean(tempPerson.getDelete());
                dos.writeInt(tempPerson.getId());
                dos.writeUTF(tempPerson.getFirstName());
                dos.writeUTF(tempPerson.getLastName());

                //Re-index the Person
                map.put(tempPerson.getId(),tempFileSize);
                tempFileSize =  baos.size();

            }

<<<<<<< HEAD
        } finally {
=======
        }catch (EOFException ex){
            System.out.print("");
        }
        finally {
>>>>>>> 7d61bc04ac36dee3b22dacaf7107fcd2d29c31d9
            dos.close();
        }
    }


    @Override
    public void addPerson(Person person) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, true))) {

            int size = (int) file.length();

            dos.writeBoolean(person.getDelete());
            dos.writeInt(person.getId());
            dos.writeUTF(person.getFirstName());
            dos.writeUTF(person.getLastName());

            map.put(person.getId(), size);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Person getPerson(int id)throws IOException{

        Person person = null;

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {

            if (map.containsKey(id)) {

                dis.skipBytes(map.get(id));
                person = new Person(dis.readBoolean(),dis.readInt(), dis.readUTF(), dis.readUTF());

<<<<<<< HEAD
                deletePerson = new Person(dis.readInt(), dis.readUTF(), dis.readUTF());

            }else if (!map.containsKey(id)){

                return null;
            }

        }


        //Loop through file and save it to memory
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {

            Person tempPerson = null;
            baos = new ByteArrayOutputStream();
            dosMemory = new DataOutputStream(baos);




            for (int x = 0; x < file.length(); x = baos.size()) {

                tempPerson = new Person(dis.readInt(), dis.readUTF(), dis.readUTF());

                if (deletePerson.getId() != tempPerson.getId()) {

                    dosMemory.writeInt(tempPerson.getId());
                    dosMemory.writeUTF(tempPerson.getFirstName());
                    dosMemory.writeUTF(tempPerson.getLastName());
                }


            }
            baos.close();
            dosMemory.close();
        }

        //Write the Person from memory to file
        try(DataInputStream dis = new DataInputStream(new ByteArrayInputStream(baos.toByteArray()))){

            DataOutputStream dosFile = new DataOutputStream(new FileOutputStream(file));

            map.clear();

            for (int x = 0; x < baos.size(); x++){

                dosFile.writeInt(dis.readInt());
                dosFile.writeUTF(dis.readUTF());
                dosFile.writeUTF(dis.readUTF());

            }

            dosFile.close();

        }
        reindexMap();

        return deletePerson;
=======
                if (person.getDelete() == false){
                    return person;
                }
            }

        }catch (EOFException ex){
            System.out.print("");
        }
        return null;
>>>>>>> 7d61bc04ac36dee3b22dacaf7107fcd2d29c31d9
    }

    @Override
    public Person delete(int id) throws IOException {
        return null;
    }


//    @Override
//    public Person delete(int id) throws IOException {
//
//        Person deletePerson = null;
//        ByteArrayOutputStream baos = null;
//        DataOutputStream dosMemory = null;
//
//        //Get the person to be deleted
//        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
//
//            if(map.containsKey(id)) {
//
//                dis.skipBytes(map.get(id));
//
//                deletePerson = new Person(dis.readInt(), dis.readUTF(), dis.readUTF());
//
//            }else if (!map.containsKey(id)){
//
//                return null;
//            }
//
//
//        }
//
//
//        //Loop tru file and save it to memory
//        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))){
//
//            Person tempPerson = null;
//            baos = new ByteArrayOutputStream();
//            dosMemory = new DataOutputStream(baos);
//
//                for (int x = 0; x < file.length(); x++){
//
//                    tempPerson = new Person(dis.readInt(),dis.readUTF(),dis.readUTF());
//
//                    if (deletePerson.getId() != tempPerson.getId()){
//
//                        dosMemory.writeInt(tempPerson.getId());
//                        dosMemory.writeUTF(tempPerson.getFirstName());
//                        dosMemory.writeUTF(tempPerson.getLastName());
//                    }
//                }
//
//        }finally {
//
//            baos.close();
//            dosMemory.close();
//        }
//
//        //Write the Person from memory to file
//        try(DataInputStream dis = new DataInputStream(new ByteArrayInputStream(baos.toByteArray()))){
//
//            DataOutputStream dosFile = new DataOutputStream(new FileOutputStream(file));
//
//            map.clear();
//
//            for (int x = 0; x < baos.size(); x++){
//
//                dosFile.writeInt(dis.readInt());
//                dosFile.writeUTF(dis.readUTF());
//                dosFile.writeUTF(dis.readUTF());
//
//            }
//
//            dosFile.close();
//
//        }
//        reindexMap();
//
//        return deletePerson;
    }



















