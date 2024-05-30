package News.Company.BoboUpdate.Utils;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Encryptor {

    public static String encryptPassword(String inputPassword) {
        if (!isValidPassword(inputPassword)) throw new BoboUpdateException("Invalid password: ");
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.encryptPassword(inputPassword);
    }

    public static boolean checkPassword(String inputPassword, String encryptedStoredPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor.checkPassword(inputPassword, encryptedStoredPassword);
    }

    public static boolean isValidPassword(String password){
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
