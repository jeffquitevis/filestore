package com.filestore.DataStore;

import com.filestore.Model.PersonModel;

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


    public static byte[] encrypt(PersonModel personModel, PublicKey publicKey) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, ClassNotFoundException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {


        Cipher cipher = Cipher.getInstance(ALGORITHM);


        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] tempEncryptedRecord = null;


        //WRITE TO BUFFER -> ENCRYPT -> RETURN ENCRYPTED BYTE[]
        try (DataOutputStream dosMemory = new DataOutputStream(baos)) {

            dosMemory.writeBoolean(personModel.getDelete());
            dosMemory.writeInt(personModel.getId());
            dosMemory.writeUTF(personModel.getFirstName());
            dosMemory.writeUTF(personModel.getLastName());

            tempEncryptedRecord = cipher.doFinal(baos.toByteArray());

        }

        return tempEncryptedRecord;
    }

    public static PersonModel decrypt(byte[] encryptedRecord, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, ClassNotFoundException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        PersonModel output = null;
        byte[] tempDecryptedRecord = null;


        //CIPHER INIT
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        tempDecryptedRecord = cipher.doFinal(encryptedRecord);

        try (DataInputStream dis = new DataInputStream(new ByteArrayInputStream(tempDecryptedRecord))) {

            output = new PersonModel(dis.readBoolean(), dis.readInt(), dis.readUTF(), dis.readUTF());
        }

        return output;
    }
}

