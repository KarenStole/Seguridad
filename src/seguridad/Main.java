package seguridad;

import java.io.*;
import seguridad.Crypto;


public class Main{

	
   static public void main(String argv[]) {    
     try {
		Crypto.parse();
	      
    	} catch (Exception e) {
          e.printStackTrace();
    }
  }


}


