/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import EncryptAlgoritms.AESSymetricCrypto;
import java.security.Key;
import javafx.util.Pair;

/**
 *
 * @author Karen
 */
public class Seguridad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Pair<byte[], Key> par = AESSymetricCrypto.EncryptPass("hola");
        byte[] encriptado = par.getKey();
        
        
    }
    
}
