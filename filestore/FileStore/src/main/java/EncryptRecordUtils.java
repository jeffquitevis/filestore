import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeff on 5/24/2016.
 */
public class EncryptRecordUtils {

    public static final String PUBLIC_KEY_FILE = "c:/keys/public.keys";
    public static final String PRIVATE_KEY_FILE = "c:/keys/private.keys";
    public static final String ALGORITHM = "RSA";


    public static byte[] encrypt(Person person) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, ClassNotFoundException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {


        Cipher cipher = Cipher.getInstance(ALGORITHM);
        PublicKey publicKey = null;

        //GET THE PUBLIC KEY
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE))){

            publicKey = (PublicKey) ois.readObject();
        }

        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] tempEncryptedRecord = null;


        //WRITE TO BUFFER -> ENCRYPT -> RETURN ENCRYPTED BYTE[]
        try(DataOutputStream dosMemory = new DataOutputStream(baos)){

            dosMemory.writeBoolean(person.getDelete());
            dosMemory.writeInt(person.getId());
            dosMemory.writeUTF(person.getFirstName());
            dosMemory.writeUTF(person.getLastName());

            tempEncryptedRecord =  cipher.doFinal(baos.toByteArray());

        }

        return tempEncryptedRecord;
    }

    public static Person decrypt(byte[] encryptedRecord) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, ClassNotFoundException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        PrivateKey privateKey = null;
        Person output = null;
        byte[] tempDecryptedRecord = null;

        //READ PRIVATE KEY FILE
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE))){

            privateKey = (PrivateKey) ois.readObject();
        }


        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        tempDecryptedRecord = cipher.doFinal(encryptedRecord);

        try(DataInputStream dis = new DataInputStream(new ByteArrayInputStream(tempDecryptedRecord))){

                output = new Person(dis.readBoolean(),dis.readInt(),dis.readUTF(),dis.readUTF());
        }

        return output;
    }


    public static void generateKeys() throws NoSuchAlgorithmException, IOException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);

        KeyPair keyPair = keyGen.generateKeyPair();
        File publicKeyFile = new File(PUBLIC_KEY_FILE);
        File privateKeyFile = new File(PRIVATE_KEY_FILE);

        if (privateKeyFile.getParentFile() != null){

            privateKeyFile.getParentFile().mkdir();
        }
        privateKeyFile.createNewFile();

        if (publicKeyFile.getParentFile() != null){

            publicKeyFile.getParentFile().mkdir();
        }

        publicKeyFile.createNewFile();


        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(publicKeyFile))){

            PublicKey publicKey = keyPair.getPublic();
            oos.writeObject(publicKey);

        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(privateKeyFile))){

            PrivateKey privateKey = keyPair.getPrivate();
            oos.writeObject(privateKey);

        }

    }

}
