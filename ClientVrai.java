package client;
import java.io.*;
import java.net.*;
import java.util.*;
import requete.Requete;
public class ClientVrai{
	public static void main(String[] args) {
			 try {
            System.out.println("Bienvenue sur MYDATABASE");
            BufferedReader port= new BufferedReader(new InputStreamReader(System.in));
            System.out.println("port:");
            String port1=port.readLine();
            System.out.println("ip:");
            String ip=port.readLine();
            int port2=new Integer(port1).intValue();
            Requete r=new Requete();
            Socket s = new Socket(ip,port2);
            
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while(true){
                BufferedReader bf1;
                bf1 = new BufferedReader(new InputStreamReader(System.in));
                String req = bf1.readLine();
            
                PrintWriter pr = new PrintWriter(os);
                pr.println(req);
                pr.flush();
                
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bf = new BufferedReader(isr);
                while(true){
                String str1 = bf.readLine();
                if (str1!=null) {
                    
                System.out.println("SQl =>"+str1);
                if(str1.compareTo("*/")==0)
                    break;
                }
                }
                /*if( str1.equalsIgnoreCase("bye") ){
                    break;
                }*/
            }
            ///is.close();
            ///os.close();
            ///s.close();
            
        } catch(Exception e){System.out.println(e.getMessage());
        }
    }
}