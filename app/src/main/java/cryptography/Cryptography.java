package cryptography;

import android.util.Base64;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



// This class helping us to encrypt and decrypt Data
// for simplicity we chose the Key as the Email of the user
public class Cryptography {
    private String AES = "AES";

    private String getAES() {
        return AES;
    }

    public String encryptWithKey(String Key, String stringToEncrypt) throws Exception {
        SecretKeySpec key = generateKey(Key);
        Cipher c = Cipher.getInstance(getAES());
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(stringToEncrypt.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    public String encrypt(String stringToEncrypt) throws Exception {
        SecretKeySpec key = generateKey(stringToEncrypt);
        Cipher c = Cipher.getInstance(getAES());
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(stringToEncrypt.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }


    // first String is the encrypted String
    // second String is the KEY
    /////////////////////////// Explanation //////////////////////////////////////
    // if you use crypto.encrypt() func, so when you will decrypt using this    //
    // func, the 'encryptedString' should be the encrypted String and the       //
    // Key should be the predicted answer.                                      //
    // for example String a = crypto.encrypt("test");                           //
    // String b = crypto.decrypt(a,"test");                                     //
    //                                  BUT !                                   //
    // if you using encryptWithKey func, the second String is the key!          //
    // for example :                                                            //
    // String a = crypto.encryptWithKey("123KEY","test");                       //
    // to decrypt:                                                              //
    // String b = ctypto.decrypt(a,"123KEY");                                   //
    public String decrypt(String encryptedString, String Key) throws Exception {
        SecretKeySpec key = generateKey(Key);
        Cipher c = Cipher.getInstance(getAES());
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeValue = Base64.decode(encryptedString, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodeValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public SecretKeySpec generateKey(String Key) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = Key.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

}
