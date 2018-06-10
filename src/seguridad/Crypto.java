package seguridad;

import EncryptAlgoritms.AESSymetricCrypto;
import EncryptAlgoritms.HashCode;
import java.io.*;
import java.nio.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Crypto
{
    private static String comando;
    private static String file;
    private static String clave;
    
	public static void parse() throws NoSuchAlgorithmException, IOException, SQLException, InvalidKeyException, InvalidKeySpecException
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
	public static void register() throws NoSuchAlgorithmException, IOException, SQLException, InvalidKeyException, InvalidKeySpecException{
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
                String suffixHash = hash.substring(5);
                //PeticionesGet.sendGet(fiveFirst, suffixHash);

                /**********Registro de la persona*******/
                DataBaseController.resgistrarUsuario(user, hash);
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
	public static void login() throws SQLException, NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidKeySpecException{
            System.out.println("****************LOGIN*******************");
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Usuario: ");
            String user = inputScanner.next();
            System.out.print("Contraseña: ");
            String pass = inputScanner.next();
            String hash = HashCode.hashPass(pass);
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
	public static void encrypt() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException{
           System.out.println("encrypt");
           Scanner inputScanner = new Scanner(System.in);
           System.out.print("Usuario: ");
           Crypto.clave = inputScanner.next();
           System.out.print("Contraseña: ");
           Crypto.file = inputScanner.next();
           
           AESSymetricCrypto.Encriptar(file, clave);
        }
	public static void decrypt() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException{
           System.out.println("decrypt");
           Scanner inputScanner = new Scanner(System.in);
           System.out.print("Clave: ");
           Crypto.clave = inputScanner.next();
           System.out.print("Archivo a desencriptar: ");
           Crypto.file = inputScanner.next();
           
           AESSymetricCrypto.Desencriptar(file, clave);
            }


	private byte[] readFile(String filename)
	{
		/*byte[] Bytes = Files.readAllBytes(new File(filename).toPath());
		return Bytes;*/

		return null;
	}

	private void saveFile(String filename,byte[] data)
	{
		
	}

	private PrivateKey getPrivateKey(String filename)
	{
		/*byte[] Key= Crypto.readFile(filename);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Key);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);*/

		return null;
	}
		
}
