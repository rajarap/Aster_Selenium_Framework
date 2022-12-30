package testUtils;

import org.testng.annotations.Test;
import utils.LoadPropertiesFileUtils;

import static constants.GeneralConstants.DATA_PROPERTIES_FILE_NAME;
import static utils.EncryptionDecryptionUtils.decrypt;
import static utils.EncryptionDecryptionUtils.encrypt;

public class EncryptDecryptTool extends BaseTest {

    /**
     * This method can be used for encryption of a String.
     * Define it in originalString and run the test.
     * Take the encrypted string from the console
     */
    @Test
    public void encryptTool() {
        properties = LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);

        String originalString = "Rajimma@1221";

        String encryptedString = encrypt(originalString);

        System.out.println(originalString + " was encrypted and the result is: \r\n" + encryptedString);
    }

    /**
     * This method can be used for decryption of a String.
     * Define it in encryptedString and run the test.
     * Take the decrypted string from the console
     */
    @Test
    public void decryptTool() {
        properties = LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);

        String encryptedString = "StringToEncrypt";

        String decryptedString = decrypt(encryptedString);

        System.out.println(encryptedString + " was decrypted and the result is: \r\n" + decryptedString);
    }
}
