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



            for (int x = 0; x < file.length(); x = baos.size() ){

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

        } finally {

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

                person = new Person(dis.readBoolean(), dis.readInt(), dis.readUTF(), dis.readUTF());

                if (person.getDelete() == false){

                    return person;

                }
            }

            }
        return null;

        }

    @Override
    public Person delete(int id) throws IOException {
        return null;
    }
}



















