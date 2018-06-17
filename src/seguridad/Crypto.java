package seguridad;

import EncryptAlgoritms.DESSymetricCrypto;
import EncryptAlgoritms.DESSymetricCrypto;
import EncryptAlgoritms.HashCode;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JPasswordField;

public class Crypto
{
    private static String comando;
    private static String file;
    private static String clave;
    
	public static void parse() throws NoSuchAlgorithmException, IOException, SQLException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
  	{
            
                Crypto.printHelp();
                        switch(comando)
                        {
                                case("-r"):
                                case("register"):Crypto.register();
                                break;

                                case("-l"):
                                case("login"):Crypto.login();
                                break;

                                case("-s"):
                                case("sign"):Crypto.sign(args[1],args[2]);
                                break;

                                case("-v"):
                                case("verify"):Crypto.verify(args[1],args[2]);

                                case("-e"):
                                case("encrypt"):Crypto.encrypt();
                                break;

                                case("-d"):
                                case("decrypt"):Crypto.decrypt();

                                case("-x"): return;
                                
                                case("-h"):
                                case("--help"):
                                default: Crypto.printHelp();
                                
                                


                        }

                


  	}

	public static void printHelp()
  	{
                        System.out.println("Usage: java -jar crypt.jar [options]");
                        System.out.println("Opptions:");
                        System.out.println("-r, register        register new user");
                        System.out.println("-l, login           user login");
                        System.out.println("-s, sign            sign the input file, return file.sign");
                        System.out.println("-v, verify          verify sign ");
                        System.out.println("-e, encrypt         encript input file");
                        System.out.println("-d, decrypt         decrypt input file");
                        System.out.println("-x, exit            exit aplication");
                        Scanner inputScanner = new Scanner(System.in);
                        System.out.print("Comando: ");
                        Crypto.comando= inputScanner.next();
                        

  	}
	

        @SuppressWarnings("empty-statement")
	public static void register() throws NoSuchAlgorithmException, IOException, SQLException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
            System.out.println("****************REGISTRO*******************");
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Usuario: ");
            String user = inputScanner.next();
            /****Si el usuario ya existe no se debe registrar****/
            boolean validado = DataBaseController.verificarUsuario(user);
            if (validado){
                System.out.print("Password: ");
                String pass = inputScanner.next();
                String hash = HashCode.hashPass(pass);
                /***** Parte de haveibennpwned*******/
                String fiveFirst= hash.substring(0,5);
                String suffixHash = hash.substring(5).toUpperCase();
                boolean notValid= PeticionesGet.sendGet(fiveFirst, suffixHash);
                while (notValid){
                    System.out.print("Password: ");
                    pass = inputScanner.next();
                    notValid= PeticionesGet.sendGet(fiveFirst, suffixHash);
                }
                /**********Registro de la persona*******/
                String key = Crypto.genetateRandomKey();
                String hashKey=
                DataBaseController.resgistrarUsuario(user, hash, key);
                System.out.println("*******************************************************");
                System.out.println("Felicidades: "+ user +" te has registrado exitosamente!");
                System.out.println("*******************************************************");
                parse();
            }
            else{
                System.out.println("******************************");
                System.out.println("El usuario ya esta registrado. Ingrese un nuevo usuario.");
                System.out.println("******************************");
                parse();
            }
            

        }
	public static void login() throws SQLException, NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
            System.out.println("****************LOGIN*******************");
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Usuario: ");
            String user = inputScanner.next();
            System.out.print("Contraseña: ");
            JPasswordField pass = new JPasswordField();
            pass.setActionCommand(inputScanner.next().concat(user));

            String hash = HashCode.hashPass(pass.toString());
            boolean validacion = DataBaseController.validarLogin(user, hash);
            if(validacion){
                System.out.println("****************USUARIO LOGUEADO*******************");
                parse();
            }
            else {
                System.out.println("****************USUARIO Y/O CONTRASEÑA INCORRECTOS*******************");
                parse();
            }
            
        }
	
	public static void sign(String fileKey,String file)	
	{
		
		/*PivateKey privada=Crypto.getPrivateKey(filekey);
		byte[] document= Crypto.readFile(file);

		Signature signature = Signature.getInstance("SHA2withRSA");
		signaturr.initSing(privada);
		signature.update(document);
		
		String signame=file.substring(0,file.lastIndexOf('.'));

		Crypto.saveFile(signame,signature.sign());		 		*/

		System.out.println("Archivo firmado ok!!");
	}
	

	public static void verify(String fileKey,String file){System.out.println("verify");}
	public static void encrypt() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
           System.out.println("encrypt");
           Scanner inputScanner = new Scanner(System.in);
           System.out.print("Archivo: ");
           Crypto.file = inputScanner.next();
           System.out.print("Clave para encriptar: ");
           String key = inputScanner.next();
           /******Encripto el archivo con la primer clave******/
           byte[] document=Crypto.readFile(file);
           SecretKey aeskey = new SecretKeySpec(key.getBytes(),"AES");
           Cipher encryptCipher = Cipher.getInstance("AES");
           encryptCipher.init(Cipher.ENCRYPT_MODE, aeskey);
           byte[] encryptedBytes = encryptCipher.doFinal(document);
           /********Guardo el archivo encriptado y la clave encriptada ******/
           String filename=file+".aes";
           Crypto.saveFile(filename,encryptedBytes);
           System.out.println("File "+filename+" encripted Ok.");
           
        }
	public static void decrypt() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
           System.out.println("decrypt");
           Scanner inputScanner = new Scanner(System.in);
           System.out.print("Archivo a desencriptar: ");
           Crypto.file = inputScanner.next();
           System.out.print("Contraseña: ");
           Crypto.clave = inputScanner.next();
           
           /*Desincripto primero la clave con la contraseña*/
           Cipher decryptCipher = Cipher.getInstance("AES");
           
           /*Cuando tengo la clave desencriptada deincripto el archivo*/
           byte[] cryptodoc=Crypto.readFile(file);
           SecretKey aeskey2 = new SecretKeySpec(clave.getBytes(),"AES");
    	   decryptCipher.init(Cipher.DECRYPT_MODE, aeskey2);
            byte[] document = decryptCipher.doFinal(cryptodoc);
            /*Guardo el archivo desincriptado, y la clave tambien pero ENCRIPTADA*/
            String filename=file.substring(0,file.lastIndexOf('.'));
            Crypto.saveFile(filename,document);
		System.out.println("File "+filename+" decripted Ok.");
            }


	private static byte[] readFile(String filename) throws IOException
	{
		byte[] Bytes = Files.readAllBytes(new File(filename).toPath());
		return Bytes;
	}

	private static void saveFile(String filename,byte[] data) throws IOException
	{
            	Path path= Paths.get(filename);
		Files.write(path,data);	
		
	}

	private PrivateKey getPrivateKey(String filename)
	{
		/*byte[] Key= Crypto.readFile(filename);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Key);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);*/

		return null;
	}
        private static String genetateRandomKey(){
               
            String uuid = UUID.randomUUID().toString();
            return uuid;
    
        }
		
}
