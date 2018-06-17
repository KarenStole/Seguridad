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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PeticionesGet {
   public static boolean sendGet(String hash, String suffixHash) {
      boolean find= false;
       try {
         // Se abre la conexión
         URL url = new URL("https://api.pwnedpasswords.com/range/"+hash);
         URLConnection conexion = url.openConnection();
         conexion.setRequestProperty("User-Agent", "App de Prueba");
         conexion.connect();
         
         // Lectura
         InputStream is = conexion.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         char[] buffer = new char[1000];
         int leido;
         while ((leido = br.read(buffer)) > 0) {
            String [] response = new String(buffer, 0, leido).split("\\r?\\n");

            for (String line : response) {
              if (line.startsWith(suffixHash)) {
                find= true;
                System.out.println(
                "Password encontrada, veces: " + line.substring(line.indexOf(":") + 1));
                System.out.println(
                "Has un mejor intento ;)");
                return find;
          }
        }
        
      }
         System.out.println("Wow, excelente opción :D");
         return find;
      } catch (MalformedURLException e) {
          return find;
      } catch (IOException e) {
          return find;
      }
   }
}
