package EncryptAlgoritms;

import static java.awt.PageAttributes.MediaType.A;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javafx.util.Pair;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Ejemplo de encriptado y desencriptado con algoritmo AES.
 * Se apoya en RSAAsymetricCrypto.java para salvar en fichero
 * o recuperar la clave de encriptación.
 * 
 * @author Chuidiang
 *
 */
public class AESSymetricCrypto {

    public static void Encriptar(String file, String clave) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException
       {

             //pasar clave a la clase SecretKey
          try{
           SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
           DESKeySpec kspec = new DESKeySpec(clave.getBytes());
           SecretKey ks = skf.generateSecret(kspec);

          //Inicializar el cifrado
             try{
                Cipher cifrado = Cipher.getInstance("DES");

                //Escojo modo cifrado o descifrado segun sea el caso

                
                   cifrado.init(Cipher.ENCRYPT_MODE, ks);//MODO CIFRAR
               /* if (comando2.equals(args[0])){
                   cifrado.init(Cipher.DECRYPT_MODE, ks);}//MODO DESCIFRAR*/


                //Leer fichero

                InputStream archivo = new FileInputStream(file);
                OutputStream fich_out = new FileOutputStream ( "Salida" );

                byte[] buffer = new byte[1024];
                byte[] bloque_cifrado;
                String textoCifrado = new String();
                int fin_archivo = -1;
                int leidos;//numero de bytes leidos

                leidos = archivo.read(buffer);

                while( leidos != fin_archivo ) {
                   bloque_cifrado = cifrado.update(buffer,0,leidos);
                   textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1"); 
                   leidos = archivo.read(buffer);          
                }

                archivo.close();

                bloque_cifrado = cifrado.doFinal();
                textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1");
                //ISO-8859-1 es ISO-Latin-1

                fich_out.write(textoCifrado.getBytes("ISO-8859-1"));//escribir fichero

                }
                //Inicializacion de cifrado
                catch(javax.crypto.NoSuchPaddingException nspe) {} //Instanciacion DES
                catch(javax.crypto.IllegalBlockSizeException ibse) {}//metodo doFinal
                catch(javax.crypto.BadPaddingException bpe) {}//metodo doFinal
             }
             //leer del teclado la clave como String
             catch(java.io.IOException ioex) {}
          }
    
    public static void Desencriptar(String file, String clave) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException
       {

             //pasar clave a la clase SecretKey
          try{
           SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
           DESKeySpec kspec = new DESKeySpec(clave.getBytes());
           SecretKey ks = skf.generateSecret(kspec);

          //Inicializar el cifrado
             try{
                Cipher cifrado = Cipher.getInstance("DES");

                //Escojo modo cifrado o descifrado segun sea el caso

                
                   //cifrado.init(Cipher.ENCRYPT_MODE, ks);//MODO CIFRAR
               
                   cifrado.init(Cipher.DECRYPT_MODE, ks);//MODO DESCIFRAR*/


                //Leer fichero

                InputStream archivo = new FileInputStream(file);
                OutputStream fich_out = new FileOutputStream ( "Salida2" );

                byte[] buffer = new byte[1024];
                byte[] bloque_cifrado;
                String textoCifrado = new String();
                int fin_archivo = -1;
                int leidos;//numero de bytes leidos

                leidos = archivo.read(buffer);

                while( leidos != fin_archivo ) {
                   bloque_cifrado = cifrado.update(buffer,0,leidos);
                   textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1"); 
                   leidos = archivo.read(buffer);          
                }

                archivo.close();

                bloque_cifrado = cifrado.doFinal();
                textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1");
                //ISO-8859-1 es ISO-Latin-1

                fich_out.write(textoCifrado.getBytes("ISO-8859-1"));//escribir fichero

                }
                //Inicializacion de cifrado
                catch(javax.crypto.NoSuchPaddingException nspe) {} //Instanciacion DES
                catch(javax.crypto.IllegalBlockSizeException ibse) {}//metodo doFinal
                catch(javax.crypto.BadPaddingException bpe) {}//metodo doFinal
             }
             //leer del teclado la clave como String
             catch(java.io.IOException ioex) {}
          }
          }
       
       

    

