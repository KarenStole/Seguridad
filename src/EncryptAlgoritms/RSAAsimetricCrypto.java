/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncryptAlgoritms;

/**
 *
 * @author Karen
 */
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;

//Encriptación y desencriptación con RSA
public class RSAAsimetricCrypto {

public static Key encryptRSA(String texto) throws Exception {

    System.out.println("Crear clave pública y privada");
    //Creación y obtención del par de claves
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(512);//tamaño de la clave
    KeyPair clavesRSA = keyGen.generateKeyPair();

    //Clave privada

    PrivateKey clavePrivada = clavesRSA.getPrivate();

    //Clave pública
    PublicKey clavePublica = clavesRSA.getPublic();

    //Se pueden mostrar las claves para ver cuáles son, aunque esto no es aconsejable
    System.out.println("clavePublica: " + clavePublica);
    System.out.println("clavePrivada: " + clavePrivada);

    //Texto plano
    byte[] bufferClaro = texto.getBytes();

    //Ciframos con clave pública el texto plano utilizando RSA
    Cipher cifrador = Cipher.getInstance("RSA");
    cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
    System.out.println("Cifrar con clave pública el Texto:");
    mostrarBytes(bufferClaro);

    //Realización del cifrado del texto plano
    byte[] bufferCifrado = cifrador.doFinal(bufferClaro);
    System.out.println("Texto CIFRADO");
    mostrarBytes(bufferCifrado);
    System.out.println("\n_______________________________");
    return clavePrivada;

    }

    public static void mostrarBytes(byte[] buffer) throws IOException {
    System.out.write(buffer);
    }
    
    public static String descryptRSA(Key clavePrivada, String cifrado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException{
            //Desencriptación utilizando la clave privada
        Cipher cifrador = Cipher.getInstance("RSA");
        cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
        System.out.println("Descifrar con clave privada");
        byte[] bufferClaro = cifrado.getBytes();

        //Obtener y mostrar texto descifrado
        byte[] bufferDesifrado = cifrador.doFinal(bufferClaro);
        System.out.println("Texto DESCIFRADO");
        mostrarBytes(bufferDesifrado);
        
        System.out.println("\n_______________________________");
        return Arrays.toString(bufferDesifrado);
    }
}
