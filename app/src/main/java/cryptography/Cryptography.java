package cryptography;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.securevault19.securevault2019.record.Record;
import com.securevault19.securevault2019.user.User;

import java.security.Key;
import java.security.MessageDigest;
import java.util.zip.Checksum;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import local_database.DatabaseClient;
import view.records.RecordRecycler_Activity;


// Cryptography class -------> need to hide it in some way. ( proxy class ? )
// ===================================== //
// To make it simple                    //
// for now we use the KEY as UserName  //
// ================================== //


public class Cryptography {
private String AES = "AES";
private String getAES(){
    return AES;
}

    // check the checkSum
    // first String is what we want to encrypt
    // second String is the encrypter ?
        public String encryptWithKey(String Key, String stringToEncrypt) throws Exception {
        SecretKeySpec key = generateKey(Key);
        Cipher c = Cipher.getInstance(getAES());
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(stringToEncrypt.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedValue;
    }
// maybe we should not use this func
    public String encrypt(String stringToEncrypt) throws Exception {
        SecretKeySpec key = generateKey(stringToEncrypt);
        Cipher c = Cipher.getInstance(getAES());
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(stringToEncrypt.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedValue;
    }


    // first String is the encrypted String
    // second String is the KEY
    // PAY ATTENTION!! APP CAN CRASH IF THE RESULT IS FALSE!
    // PAY ATTENTION!!
    //////////////////////////////////////////////////////////////////////////////
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
    public  String decrypt (String encryptedString, String Key) throws Exception{
        SecretKeySpec key = generateKey(Key);
        Cipher c = Cipher.getInstance(getAES());
        c.init( Cipher.DECRYPT_MODE, key);
        byte[] decodeValue = Base64.decode(encryptedString, Base64.DEFAULT );
        byte[] decValue = c.doFinal(decodeValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public  SecretKeySpec generateKey(String Key) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = Key.getBytes("UTF-8");
        digest.update( bytes,0,bytes.length );
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec( key,"AES" );
        return secretKeySpec;
    }


    //expiring-date fields are seperated so we concat them into one string.
    //String expiringDate_arr[] = {expiringDateDay, expiringDateMonth, expiringDateYear};
    //final String expiringDate = expiringDate_arr.toString();

}
