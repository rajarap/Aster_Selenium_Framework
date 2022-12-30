package testUtils;

import utils.LoadPropertiesFileUtils;

import static constants.GeneralConstants.CREDENTIALS_DATA_PROPERTIES_FILE_NAME;
import static utils.EncryptionDecryptionUtils.decrypt;

public class TestingUser {
    String username;
    String password;
    String role;

    public TestingUser(String user) {
        this.username = decrypt(LoadPropertiesFileUtils.getItemFromProperties(CREDENTIALS_DATA_PROPERTIES_FILE_NAME, user.toLowerCase() + "UserName"));
        this.password = decrypt(LoadPropertiesFileUtils.getItemFromProperties(CREDENTIALS_DATA_PROPERTIES_FILE_NAME, user.toLowerCase() + "Password"));
        this.role = LoadPropertiesFileUtils.getItemFromProperties(CREDENTIALS_DATA_PROPERTIES_FILE_NAME, user.toLowerCase() + "Role");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}