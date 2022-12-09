package server;
import requete.Requete;
import java.net.*;
import java.io.*;
import traitement.TraitementReq;
public class ServerVrai extends Thread{
    @Override
    public void run(){
        try {
            ServerSocket ss = new ServerSocket(Requete.getport());
            while(true){
                Socket s = ss.accept();
                //System.out.println("client connecte");
                new TraitementReq(s).start();
            }
        }catch(Exception e){System.out.println(e.getMessage());}    
    }  
    public static void main(String[] args) throws IOException {
	new ServerVrai().start();
    } 
}         