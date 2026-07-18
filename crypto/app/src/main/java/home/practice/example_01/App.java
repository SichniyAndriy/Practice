package home.practice.example_01;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App {

    private final static Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws IOException {
        Provider provider =  new BouncyCastleProvider();
        LOGGER.log(Level.INFO,"Initialized BouncyCastle provider");
        Properties properties = new Properties();

        getPropsFromFile(properties);
        checkProps(properties);

        getPropsFromRes(properties);
        checkProps(properties);

        try {
            Cipher cipher = Cipher.getInstance(properties.getProperty(), provider);
            LOGGER.log(Level.INFO,"Initialized AES cipher with BouncyCastle provider");
            Security.addProvider(provider);
            Provider[] providers = Security.getProviders();
            for (Provider p : providers) {
                LOGGER.log(Level.INFO,p.toString());
            }
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            LOGGER.warning("Failed to initialize AES cipher with BouncyCastle provider: %s".formatted(e.getMessage()));
        }
    }

    private static void getPropsFromFile(Properties properties) throws IOException {
        properties.load(new FileReader("./secrets/crypto-config.properties"));
        LOGGER.log(Level.INFO,"Loaded crypto-config.properties");
    }

    private static void getPropsFromRes(Properties properties) throws IOException {
        InputStream in = App.class.getClassLoader().getResourceAsStream("secrets/crypto-config.properties");
        assert in != null;
        properties.load(in);
        in.close();
    }

    private static void checkProps(Properties properties) {
        for (Object key : properties.keySet()) {
            LOGGER.log(Level.INFO, "key - %s, value - %s".formatted(key, properties.get(key)));
        }
    }

}
