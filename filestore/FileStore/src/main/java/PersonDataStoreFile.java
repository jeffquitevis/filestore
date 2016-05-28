import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

/**
 * Created by jeff on 5/10/2016.
 */
public class PersonDataStoreFile implements DataStore {

    private static final File FILE_RECORD = new File("c:/temp/jeff.txt");
    private static  HashMap<Integer, Integer> MAP = new HashMap<Integer, Integer>();
    private Keys key = null;

    public PersonDataStoreFile(Keys key) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
        this.key = key;

        key.generateKeys();

        if (FILE_RECORD.exists()){
            reindexMap(key.getPrivateKey(),key.getPublicKey());
        }
    }

    private void reindexMap(PrivateKey privateKey, PublicKey publicKey) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);


        try(DataInputStream dis = new DataInputStream(new FileInputStream(FILE_RECORD))){

            int tempFileSize = 0;
            int  bufferSize = 128;

            byte[] buffer = new byte[bufferSize];

            for (int x = 0; x < FILE_RECORD.length(); x = baos.size()){

                //Read existing FILE_RECORD then decrypt the record
                dis.readBoolean();

                    dis.read(buffer);
                    Person tempPerson = EncryptRecordUtils.decrypt(buffer, privateKey);

                    //Encrypt the record, EncryptRecordUtils.decrypt(buffer) method will return fix 128 size on each record.
                    byte[] tempEncryptedRecord = EncryptRecordUtils.encrypt(tempPerson, publicKey);
                    //Write 128byte size on every record
                    dos.writeBoolean(tempPerson.getDelete());
                    dos.write(tempEncryptedRecord);

                    //Put Person ID and byte size(increment by 128 on each record)
                    MAP.put(tempPerson.getId(), tempFileSize);
                    tempFileSize = baos.size();


            }

        }
        finally {
            dos.close();
            baos.close();
        }
    }




    @Override
    public void addPerson(Person person) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException, IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_RECORD, true))) {

            int size = (int) FILE_RECORD.length();
            byte[] encryptedRecord = EncryptRecordUtils.encrypt(person,key.getPublicKey());


            //Write to file
            //[isDelete][cipherText]
            dos.writeBoolean(person.getDelete());
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


                if (dis.readBoolean() == false) {

                    dis.read(buffer);
                    person =  EncryptRecordUtils.decrypt(buffer,key.getPrivateKey());

                }
            }

            return person;
        }
    }

    @Override
    public void delete(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        if (MAP.containsKey(id)) {

            try(RandomAccessFile raf = new RandomAccessFile(FILE_RECORD,"rw")){

                raf.skipBytes(MAP.get(id));
                raf.writeBoolean(true);
                raf.close();
            }
        }
    }
}
















