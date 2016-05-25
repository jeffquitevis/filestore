import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by jeff on 5/10/2016.
 */
public class PersonDataStoreFile implements DataStore {

    private static final File FILE_RECORD = new File("c:/temp/jeff.txt");
    private static final HashMap<Integer, Integer> MAP = new HashMap<Integer, Integer>();

    public PersonDataStoreFile() throws IOException {

        if (FILE_RECORD.exists()){
            reindexMap();
        }
    }

    private void reindexMap() throws IOException{


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);


        try(DataInputStream dis = new DataInputStream(new FileInputStream(FILE_RECORD))){



            Person tempPerson = null;

            //Initialize tempFileSize
            int tempFileSize = 0;



            for (int x = 0; x < FILE_RECORD.length(); x = baos.size() ){

                //Read existing FILE_RECORD and add the Person element to list
                tempPerson = new Person(dis.readBoolean(),dis.readInt(),dis.readUTF(),dis.readUTF());

                //Write the Person element to a temp FILE_RECORD buffer
                dos.writeBoolean(tempPerson.getDelete());
                dos.writeInt(tempPerson.getId());
                dos.writeUTF(tempPerson.getFirstName());
                dos.writeUTF(tempPerson.getLastName());

                //Re-index the Person
                MAP.put(tempPerson.getId(),tempFileSize);
                tempFileSize =  baos.size();

            }

        } finally {

            dos.close();
        }
    }


    @Override
    public void addPerson(Person person) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException, IOException {

       // EncryptRecordUtils.generateKeys();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_RECORD, true))) {

            int size = (int) FILE_RECORD.length();
            byte[] encryptedRecord = EncryptRecordUtils.encrypt(person);

            dos.write(encryptedRecord);
            MAP.put(person.getId(), size);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public Person getPerson(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        Person person = null;
        int bufferSize = 128;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_RECORD))) {



            if (MAP.containsKey(id)) {

                dis.skipBytes(MAP.get(id));
                byte[] buffer = new byte[bufferSize];
                dis.read(buffer);
                person =  EncryptRecordUtils.decrypt(buffer);

                if (person.getDelete() == false) {

                    return person;

                }
            }
            return null;
        }
    }

    @Override
    public Person delete(int id) throws IOException {

        Person deletePerson = null;

        if (MAP.containsKey(id)) {

            try (RandomAccessFile raf = new RandomAccessFile(FILE_RECORD, "rw")) {
                raf.skipBytes(MAP.get(id));
                raf.writeBoolean(true);
            }


            try(DataInputStream dis = new DataInputStream(new FileInputStream(FILE_RECORD))){

                dis.skipBytes(MAP.get(id));
                deletePerson = new Person(dis.readBoolean(),dis.readInt(),dis.readUTF(),dis.readUTF());

                return deletePerson;
            }

        }
        return deletePerson;
    }
}



















