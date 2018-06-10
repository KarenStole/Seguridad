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
import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.json.Json;
import javax.xml.ws.Response;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import org.json.JSONML;
import javax.json.JsonObject;
import javax.json.JsonReader;

 
public class PeticionesGet {
 
    public static void sendGet(String prefixHash, String suffixHash) throws IOException {
        
               URL url;
        try {
            // Creando un objeto URL
            url = new URL("http://api.pwnedpasswords.com/range/"+prefixHash);
            System.out.print(url);
 
            // Realizando la petición GET
            URLConnection con = url.openConnection();
            con.setRequestProperty("User-Agent", "Pwnage-Checker-For-iOS");
            InputStream is = con.getInputStream();
 
            // Leyendo el resultado
           /* BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));*/
 
            int linea= is.read();

            System.out.println(linea);
            /*while (linea != null) {
                   if (linea.startsWith(suffixHash)) {
                        System.out.println("password found, count: " + linea.substring(linea.indexOf(":") + 1));
                        return;
                    }
            }*/
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
 

/*        okhttp3.OkHttpClient client = new OkHttpClient();
        String url = "https://api.pwnedpasswords.com/range/" + prefixHash;

        okhttp3.Request request = new Builder().url(url).build();
        okhttp3.Response response = client.newCall(request).execute();
        okhttp3.ResponseBody body = response.body();
            if (body != null) {
                String hashes = body.toString();
                String lines[] = hashes.split("\\r?\\n");

                for (String line : lines) {
                    if (line.startsWith(suffixHash)) {
                        System.out.println("password found, count: " + line.substring(line.indexOf(":") + 1));
                        return;
                    }
                }
                System.out.println("password not found");
            }*/
     
    }
}
