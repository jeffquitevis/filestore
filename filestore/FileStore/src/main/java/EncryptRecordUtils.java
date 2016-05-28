import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;


/**
 * Created by jeff on 5/24/2016.
 */
public class EncryptRecordUtils {


    private static final String ALGORITHM = "RSA";


    public static byte[] encrypt(Person person, PublicKey publicKey) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, ClassNotFoundException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {


        Cipher cipher = Cipher.getInstance(ALGORITHM);


        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] tempEncryptedRecord = null;


        //WRITE TO BUFFER -> ENCRYPT -> RETURN ENCRYPTED BYTE[]
        try (DataOutputStream dosMemory = new DataOutputStream(baos)) {

            dosMemory.writeBoolean(person.getDelete());
            dosMemory.writeInt(person.getId());
            dosMemory.writeUTF(person.getFirstName());
            dosMemory.writeUTF(person.getLastName());

            tempEncryptedRecord = cipher.doFinal(baos.toByteArray());

        }

        return tempEncryptedRecord;
    }

    public static Person decrypt(byte[] encryptedRecord, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, ClassNotFoundException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Person output = null;
        byte[] tempDecryptedRecord = null;


        //CIPHER INIT
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        tempDecryptedRecord = cipher.doFinal(encryptedRecord);

        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(tempDecryptedRecord))) {

            output = new Person(dis.readBoolean(), dis.readInt(), dis.readUTF(), dis.readUTF());
        }

        return output;
    }
}

