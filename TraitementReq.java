package traitement;
import requete.Requete;
import java.net.*;
import java.io.*;
public class TraitementReq extends Thread{
	Requete req;
	Socket soc;
	public Requete getreq(){return this.req;}
	public void setreq(Requete r){this.req=r;}
	public Socket getsoc(){return this.soc;}
	public void setsoc(Socket s){this.soc=s;}
	public TraitementReq(Socket s){
		super();
		this.soc=s;
	}
	@Override 
	public void run(){
		try{
			while(true){
			InputStream inp=soc.getInputStream();
			InputStreamReader read=new InputStreamReader(inp);
			OutputStream out=soc.getOutputStream();
			BufferedReader lire=new BufferedReader(read);
			PrintWriter outlire=new PrintWriter(out);
			String requete=lire.readLine();
		    req=null;
			try{
			   req=new Requete(requete);
			}
			catch(Exception e){
			 	outlire.println(e.getMessage());
			 	outlire.println("*/");
			 	outlire.flush();
			 	continue;
			}	
			String data=Requete.getDirectory()+"DATABASESQL\\DATABASE\\DataBase.txt";
			if(req.getcreateT()){
				try{
				    req.iscreateT(requete,data);
				    outlire.println("votre table est creer");
				    outlire.println("*/");
				    outlire.flush();
			    }catch(Exception e){
			    	outlire.println(e.getMessage());
				    outlire.println("*/");
			    	outlire.flush();
			    }

			}
			if(req.getinsert()){
				try{
				   req.isinsert(requete,data);
			       outlire.println("donne insert");
			       outlire.println("*/");
			       outlire.flush();
			    }catch(Exception e){
			    	outlire.println(e.getMessage());
			    	outlire.println("*/");
			    	outlire.flush();
			    }
			}
			if (req.getshowT()){
				try{
					String[]table=req.isshowT(data);
					String t=new String();
					outlire.println("votre table sont:");
					for(int i=0;i<table.length;i++){
						outlire.println("table numero "+i +"=>"+table[i]);
					}
			        outlire.println("*/");
					outlire.flush();
				}catch(Exception e){
					outlire.println(e.getMessage());
					outlire.println("*/");
					outlire.flush();
				}
			}
			if(req.getselect()){
				try{
					try{
						req.isselect(requete,data);	
					}catch(Exception e){
						outlire.println(e.getMessage());
						outlire.println("*/");
						outlire.flush();
					}
					String[][]valiny=req.isselect(requete,data);
					String[]Col=req.ColChoix(requete);
					if(req.testselectplus(requete)){
						String[]coloneName=req.getToutCol(requete.split("/")[0],data);
						for (int ind=0;ind<coloneName.length;ind++){
							outlire.print(coloneName[ind]);
							outlire.print("|");
						}
						outlire.print("");
						///outlire.println("*/");
						outlire.flush();
					}
					for (int z=0;z<Col.length ;z++){
						outlire.print(Col[z]);
						outlire.print("|");
					}
						outlire.println("");
					for (int n=0;n<valiny.length ;n++ ) {
						for (int i=0;i<valiny[0].length ;i++){
							outlire.print(valiny[n][i]);
							outlire.print("|");
						}
							outlire.println("");
						//ss+=" ligne numero "+n;
						outlire.println(" ligne numero "+n);
					    //outlire.flush();	
					}
						outlire.println("*/");
						outlire.flush();
					
				}catch(Exception e){
					outlire.println(e.getMessage());
					outlire.flush();
				}
				
			}
			else{
				try{
					req.get_typeReq(requete);
				}catch(Exception e){
				  outlire.println(e.getMessage());
				  outlire.println("*/");
				  outlire.flush();
					
				}
			}
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}


}