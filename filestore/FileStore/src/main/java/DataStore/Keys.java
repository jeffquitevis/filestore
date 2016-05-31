package DataStore;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by jeff on 5/28/2016.
 */
public interface Keys {

    public void generateKeys() throws NoSuchAlgorithmException, IOException;
    public PrivateKey getPrivateKey() throws IOException, ClassNotFoundException;
    public PublicKey getPublicKey() throws IOException, ClassNotFoundException;

}
