package cryptography;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.securevault19.securevault2019.record.Record;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import local_database.DatabaseClient;
import view.records.RecordRecycler_Activity;


// Cryptography class -------> need to hide it in some way.


public class Cryptography {
private String AES = "AES";
private String getAES(){
    return AES;
}


        //The first parameter will be the Key which we encrypt with, The Second parameter will be the data to encrypt

        public String encrypt(String Key, String Data) throws Exception {
        SecretKeySpec key = generateKey(Key);
        Cipher c = Cipher.getInstance(getAES());
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedValue;
    }

//    public String encryptUsername(String username) throws Exception {
//        SecretKeySpec key = generateKey(username);
//        Cipher c = Cipher.getInstance(getAES());
//        c.init(Cipher.ENCRYPT_MODE,key);
//        byte[] encVal = c.doFinal(username.getBytes());
//        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
//        return encryptedValue;
//    }

    public String decrypt (String outputString, String Data) throws Exception{
        SecretKeySpec key = generateKey(Data);
        Cipher c = Cipher.getInstance(getAES());
        c.init( Cipher.DECRYPT_MODE, key);
        byte[] decodeValue = Base64.decode( outputString, Base64.DEFAULT );
        byte[] decValue = c.doFinal(decodeValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public  SecretKeySpec generateKey(String Data) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = Data.getBytes("UTF-8");
        digest.update( bytes,0,bytes.length );
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec( key,"AES" );
        return secretKeySpec;
    }

    //expiring-date fields are seperated so we concat them into one string.
    //String expiringDate_arr[] = {expiringDateDay, expiringDateMonth, expiringDateYear};
    //final String expiringDate = expiringDate_arr.toString();

}
