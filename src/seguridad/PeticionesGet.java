/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

/**
 *
 * @author Karen
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
 
public class PeticionesGet {
 
    public static void sendGet(String firstFiveChars) {
 
        URL url;
        try {
            // Creando un objeto URL
            url = new URL("http://api.pwnedpasswords.com/ranges/"+firstFiveChars);
 
            // Realizando la petición GET
            URLConnection con = url.openConnection();
 
            // Leyendo el resultado
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
 
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
 
    }
}
