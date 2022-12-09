package server;
import java.io.*;
import java.net.*;
import requete.*;
public class ServerSGBD {
	Requete sql;
	//@Override
	//public class run(){
		//try{
			/*ServerSocket ServS=new ServerSocket(123);
			Socket sok=ServS.accept();
			InputStream in=sok.getInputStream();
			InputStreamReader read=new InputStreamReader(in);
			BufferedReader lire=new BufferedReader(read);
			String req=lire.readLine();
			OutputStream out=sok.getOutputStream();
			PrintWriter outlire=new PrintWriter(out);
			sql=new Requete();
			sql.get_typeReq(req);

			outlire.println("votre table est creer");*/



		/*}
		catch(Exception e){e.printStackTrace();}*/

	public static void main(String[] args) {
		 //new ServerSGBD().start();
		try{
		ServerSocket ServS=new ServerSocket(123);
			Socket sok=ServS.accept();
			InputStream in=sok.getInputStream();
			InputStreamReader read=new InputStreamReader(in);
			BufferedReader lire=new BufferedReader(read);
			String req=lire.readLine();
			OutputStream out=sok.getOutputStream();
			PrintWriter outlire=new PrintWriter(out);
			Requete sql=new Requete();
			String cc="D:\\DATA\\SocketExam\\test.txt";
			sql.get_typeReq(req);
			if (sql.getcreateT()==true){
				sql.iscreateT(req,cc);
				outlire.println("votre table est creer");
			}
			if(sql.getcreateT()==true) {
				
			}
			outlire.println("votre table est creer");
		}catch(Exception e){e.printStackTrace();}
	}
}