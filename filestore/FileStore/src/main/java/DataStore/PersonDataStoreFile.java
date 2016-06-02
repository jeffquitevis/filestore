package DataStore;

import Model.PersonModel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                    PersonModel tempPersonModel = EncryptRecordUtils.decrypt(buffer, privateKey);

                    //Encrypt the record, DataStore.EncryptRecordUtils.decrypt(buffer) method will return fix 128 size on each record.
                    byte[] tempEncryptedRecord = EncryptRecordUtils.encrypt(tempPersonModel, publicKey);
                    //Write 128byte size on every record
                    dos.writeBoolean(tempPersonModel.getDelete());
                    dos.write(tempEncryptedRecord);

                    //Put Model.PersonModel ID and byte size(increment by 128 on each record)
                    MAP.put(tempPersonModel.getId(), tempFileSize);
                    tempFileSize = baos.size();


            }

        }
        finally {
            dos.close();
            baos.close();
        }
    }




    @Override
    public void addPerson(PersonModel personModel) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException, IOException {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_RECORD, true))) {

            int size = (int) FILE_RECORD.length();
            byte[] encryptedRecord = EncryptRecordUtils.encrypt(personModel,key.getPublicKey());


            //Write to file
            //[isDelete][cipherText]
            dos.writeBoolean(personModel.getDelete());
            dos.write(encryptedRecord);


            MAP.put(personModel.getId(), size);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public PersonModel getPerson(int id) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, ClassNotFoundException {

        PersonModel personModel = null;
        int bufferSize = 128;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_RECORD))) {

            if (MAP.containsKey(id)) {

                dis.skipBytes(MAP.get(id));
                byte[] buffer = new byte[bufferSize];


                if (dis.readBoolean() == false) {

                    dis.read(buffer);
                    personModel =  EncryptRecordUtils.decrypt(buffer,key.getPrivateKey());

                }
            }

            return personModel;
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

    @Override
    public List<PersonModel> getAllPerson() throws IOException, ClassNotFoundException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        List<PersonModel> personModelList = new ArrayList<>();
        byte[] buffer = new byte[128];
        int tempSize = buffer.length;

        try(DataInputStream dis = new DataInputStream(new FileInputStream(FILE_RECORD))){

            for (int x = 0; x < FILE_RECORD.length(); x = tempSize ){

                   if (dis.readBoolean() == false){
                    dis.read(buffer);
                    personModelList.add(EncryptRecordUtils.decrypt(buffer,key.getPrivateKey()));

                   }else {
                       dis.read(buffer);
                   }




                tempSize += buffer.length;
            }
        }

        return personModelList;
    }
}
















