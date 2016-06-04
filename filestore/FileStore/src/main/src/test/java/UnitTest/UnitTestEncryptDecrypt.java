package UnitTest;

import com.filestore.DataStore.EncryptRecordUtils;
import com.filestore.Model.PersonModel;
import junit.framework.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;

/**
 * Created by jeff on 6/1/2016.
 */
public class UnitTestEncryptDecrypt {

    private PublicKey publicKey;
    private PrivateKey privateKey;
    private PublicKey wrongPublicKey;
    private PrivateKey wrongPrivateKey;

    @Test
    public void testEncryptWontChangeTheRecord() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        generateRSAKey();

        PersonModel p1 = new PersonModel(false, 1, "jeff", "quitevis");
        byte[] encryptedRecord = EncryptRecordUtils.encrypt(p1,publicKey);
        PersonModel result =  EncryptRecordUtils.decrypt(encryptedRecord,privateKey);

        Assert.assertEquals(result.getId(),p1.getId());
        Assert.assertEquals(result.getDelete(),p1.getDelete());
        Assert.assertEquals(result.getFirstName(),p1.getFirstName());
        Assert.assertEquals(result.getLastName(),p1.getLastName());


    }

    @Test
    public void testEncryptByteSize() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {


        generateRSAKey();
        int byteSize = 128;
        PersonModel p1 = new PersonModel(false, 1, "jeff", "quitevis");
        byte[] encryptedRecord = EncryptRecordUtils.encrypt(p1,publicKey);
        Assert.assertEquals(encryptedRecord.length,byteSize);
    }

    @Test
    public void testDecryptTheEncryptedRecord() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        generateRSAKey();
        PersonModel p1 = new PersonModel(false, 1, "jeff", "quitevis");
        byte[] encryptedRecord = EncryptRecordUtils.encrypt(p1,publicKey);
        PersonModel result =  EncryptRecordUtils.decrypt(encryptedRecord,privateKey);

        Assert.assertEquals(result.getId(),p1.getId());
        Assert.assertEquals(result.getDelete(),p1.getDelete());
        Assert.assertEquals(result.getFirstName(),p1.getFirstName());
        Assert.assertEquals(result.getLastName(),p1.getLastName());


    }

    @Test
    public void testWrongPrivateKeyToDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        generateRSAKey();
        generateSecondRSAKey();

        boolean thrown = false;

        PersonModel p1 = new PersonModel(false, 1, "jeff", "quitevis");
        byte[] encryptedRecord = EncryptRecordUtils.encrypt(p1,publicKey);

        try{
            EncryptRecordUtils.decrypt(encryptedRecord,wrongPrivateKey);
        }catch (BadPaddingException e){
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }


    @Test
    public void testWrongPublicKeyToEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {

        generateRSAKey();
        generateSecondRSAKey();

        boolean thrown = false;

        PersonModel p1 = new PersonModel(false, 1, "jeff", "quitevis");
        byte[] encryptedRecord = EncryptRecordUtils.encrypt(p1,wrongPublicKey);

        try{
            EncryptRecordUtils.decrypt(encryptedRecord,privateKey);
        }catch (BadPaddingException e){
            thrown = true;
        }

        Assert.assertTrue(thrown);
    }

    public void generateRSAKey() throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

    }

    public void generateSecondRSAKey() throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        wrongPublicKey = keyPair.getPublic();
        wrongPrivateKey = keyPair.getPrivate();

    }


}
