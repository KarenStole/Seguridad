/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncryptAlgoritms;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Karen
 */
public class HashCode {
    public static String hashPass(String pass) throws NoSuchAlgorithmException{
        
        /* SHA-384 */

        MessageDigest objSHA384 = MessageDigest.getInstance("SHA-384");
        byte[] bytSHA384 = objSHA384.digest(pass.getBytes());
        BigInteger intNumSHA384 = new BigInteger(1, bytSHA384);
        String hcSHA384 = intNumSHA384.toString(16);
        while (hcSHA384.length() < 96) {
            hcSHA384 = "0" + hcSHA384;
        }
        return hcSHA384;
    }

    
}
