package DataStore;

import java.io.*;
import java.security.*;

/**
 * Created by jeff on 5/28/2016.
 */
public class RSAKey implements Keys {

    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;
    private static final String ALGORITHM = "RSA";
    private static final String PUBLIC_KEY_PATH = "c:/temp/keys/public.key";
    private static final String PRIVATE_KEY_PATH = "c:/temp/keys/private.key";


    @Override
    public void generateKeys() throws NoSuchAlgorithmException, IOException {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);

        KeyPair keyPair = keyGen.generateKeyPair();
        File publicKeyFile = new File(PUBLIC_KEY_PATH);
        File privateKeyFile = new File(PRIVATE_KEY_PATH);

        if (!privateKeyFile.exists() && !publicKeyFile.exists()){

            privateKeyFile.getParentFile().mkdir();
            publicKeyFile.getParentFile().mkdir();
            privateKeyFile.createNewFile();
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

    @Override
    public PublicKey getPublicKey() throws IOException, ClassNotFoundException {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_PATH))){

            publicKey = (PublicKey) ois.readObject();
        }

        return publicKey;
    }

    @Override
    public  PrivateKey getPrivateKey() throws IOException, ClassNotFoundException {

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_PATH))){

            privateKey = (PrivateKey) ois.readObject();
        }

        return privateKey;
    }


}
