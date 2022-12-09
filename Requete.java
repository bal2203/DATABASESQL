package requete;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.Date;
public class Requete{
	String type;
	String requete;
	boolean createT=false;
	boolean insert=false;
	boolean select=false;
	boolean show=false;
	public String gettype(){
		return this.type;
	}
	public String getrequete(){
		return this.requete;
	}
	public boolean getcreateT(){
		return this.createT;
	}
	public Requete(){}
	public Requete(String req)throws Exception{
	//try{
		this.get_typeReq(req);
	   //}catch(Exception e){System.out.println(e.getMessage());}
	}
	public boolean getinsert(){return this.insert;}
	public boolean getselect(){return this.select;}
	public boolean getshowT(){return this.show;}
	public void setcreateT(boolean b){this.createT=b;}
	public void setinsert(boolean b){this.insert=b;}
	public void setselect(boolean b){this.select=b;}
	public void setshowT(boolean b){this.show=b;}
	public void get_typeReq(String req)throws Exception{

		///if(req!=null){
			
			String ph=req.split(" ")[0];
			if (ph.compareToIgnoreCase("createT")==0){
				this.setcreateT(true);
				return ;	
			}
			if (ph.compareToIgnoreCase("insert")==0){
				this.setinsert(true);	
				return ;	
			}
			if (ph.compareToIgnoreCase("select")==0){
				this.setselect(true);
				return ;		
			}
			if (ph.compareToIgnoreCase("showT")==0){
				this.setshowT(true);	
				return ;	
			}
			else{throw new Exception("diso ny Req");}
	}
	public boolean isvariable(String reqcreaT)throws Exception{
		    String[]reqSep=reqcreaT.split(" ");
			String[]reqSep2=reqSep[1].split("\\(");
			String[]reqSep3=reqSep2[1].split("\\)");
			String[]reqSep4=reqSep3[0].split(",");
			int count=0;
			for (int i=0;i<reqSep4.length;i++){
				String reqSep5=new String();
			    reqSep5=reqSep4[i].split(":")[1];
				if(reqSep5.compareToIgnoreCase("int")==0){
					count++;
				}
				if (reqSep5.compareToIgnoreCase("real")==0) {
					count++;
				}
				if (reqSep5.compareToIgnoreCase("string")==0) {
					count++;
				}
				if (reqSep5.compareToIgnoreCase("date")==0) {
					count++;
				}
				/*else{
					throw new Exception("variable inconnue");
				}*/
			}
			////System.out.println(count+"ppppp"+(reqSep4.length));
			if(count==reqSep4.length){
				///System.out.println("ppp="+count);
				////System.out.println("ppppp"+reqSep5[1]);
				return true;	
			}
			///System.out.println(count);
			return false; 
	}
	public void iscreateT(String req,String ndatabase)throws Exception{
				String[]reqSep=req.split(" ");
				String[]reqSep2=reqSep[1].split("\\(");
				String[]reqSep3=reqSep2[1].split(",");
				
				
				//bd.
				//File ff=new File(ndatabase);
				if (this.verifierT(reqSep2[0],ndatabase)==false && this.isvariable(req)==true){
					//BufferedWriter b2=new BufferedWriter(new FileWriter(ndatabase));
					this.ecrire_Tab(req,ndatabase);
		  			BufferedWriter b=new BufferedWriter(new FileWriter(new File(reqSep2[0]+".txt"),true));
				}
				else{
					throw new Exception("la table existe dejat "+reqSep2[0]);
				}
	}
	void ecrire_Tab(String req,String ndb)throws Exception{
		try{
			LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
			///System.out.println(bd.getLineNumber());
			String[]reqSep=req.split(" ");
			String[]reqSep2=reqSep[1].split("\\(");
			String[]reqSep3=reqSep2[1].split("\\)");
			String[]reqSep4=reqSep3[0].split(",");
			BufferedWriter writedb=new BufferedWriter(new FileWriter(ndb,true));
			writedb.write(reqSep2[0]);
			writedb.write("/");
			
			for (int i=0;i<reqSep4.length;i++){
				String[]reqSep5=reqSep4[i].split(":");
				writedb.write(reqSep5[0]);
				writedb.write(",");
				writedb.write(reqSep5[1]);
				writedb.write("/");
				
			}
			writedb.newLine();
			writedb.close();
	    }catch(Exception e){e.printStackTrace();}


	}
	int countLine(String ndb)throws Exception{
		int nbline=0;
		LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
		while(bd.readLine()!=null){
			bd.setLineNumber(nbline);
			nbline++;
		}
		return nbline;
	}
	public boolean verifierT(String nt,String ndb)throws Exception{
		LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
		int nbline=countLine(ndb);
		String[]ls_tab=new String[nbline];
		if (nbline!=0) {
			
			for (int i=0;i<nbline;i++){
				ls_tab[i]=((bd.readLine()).split("/"))[0];
				if (i!=nbline-1){
					bd.setLineNumber(i+1);
				}
			}
			for (int n=0;n<nbline;n++){
				if(ls_tab[n].compareToIgnoreCase(nt)==0){
					//System.out.println(ls_tab[1]);
					return true;
				}
			}
		
		}
		return false;

	}
	public String[] isshowT(String ndb)throws Exception{
		LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
		int nbline=countLine(ndb);
		String[]ls_tab=new String[nbline];
		if (nbline!=0) {
			
			for (int i=0;i<nbline;i++){
				ls_tab[i]=((bd.readLine()).split("/"))[0];
				if (i!=nbline-1){
					bd.setLineNumber(i+1);
				}
			}
		}else{throw new Exception("aucun table");}
		return ls_tab;	
	}
	public String[] get_typeVar(String nomtab,String ndb)throws Exception{
		LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
		int tab=countLine(ndb);
		Vector vectVariable=new Vector();
		for (int z=0;z<tab;z++ ){
			String ls_tab=bd.readLine();
			if(ls_tab.split("/")[0].compareToIgnoreCase(nomtab)==0){
				String[]varTab=ls_tab.split("/");
				System.out.println(varTab.length);
				for(int n=0;n<varTab.length-1;n++){
					   vectVariable.add(varTab[n+1].split(",")[1]);			
				}
			}
		}
		String[]variableType=new String[vectVariable.size()]; 
		for (int i=0;i<vectVariable.size();i++){
				variableType[i]=(String)(vectVariable.elementAt(i));
			
		}
		return variableType;
	}
	public String[] makavalueInsert(String req){
		String[]valueSep1=req.split("\\(");
		String[]valueSep2=valueSep1[1].split("\\)");
		return valueSep2[0].split(",");

	}
	public boolean testType(String[] typevar,String[] value)throws Exception{
		int count=0;
		if(typevar.length==value.length){
			for (int i=0;i<typevar.length;i++){
				if (typevar[i].compareToIgnoreCase("int")==0){
					try{
					new Integer(value[i]).intValue();
					count++;
				    }catch(Exception e){return false;}
				}
				if(typevar[i].compareToIgnoreCase("String")==0){
					count++;
				}
				if(typevar[i].compareToIgnoreCase("real")==0){
					try{
					new Double(value[i]);
					count++;
				   }catch(Exception e){return false;}
				}
				if(typevar[i].compareToIgnoreCase("date")==0){
					try{
					new Date(120000).valueOf(value[i]);
					count++;
				   }catch(Exception e){return false;}
				}
			}
			if(count==typevar.length){
				return true;
			}
		}
		return false;
		/*else{
			throw new Exception("erreur insertion");
		}*/
	}
	public void isinsert(String req,String ndb)throws Exception{
				String[]reqSep=req.split(" ");
				String[]reqSep2=reqSep[1].split("\\(");
				String[]reqSep3=reqSep2[1].split(",");
				String[]valeurReq=makavalueInsert(req);
				String[]typevar=get_typeVar(reqSep2[0],ndb);
				boolean testType=testType(typevar,valeurReq);
				if (this.verifierT(reqSep2[0],ndb)==true && testType==true){
		  			BufferedWriter b=new BufferedWriter(new FileWriter(reqSep2[0]+".txt",true));
		  			for (int i=0;i<typevar.length;i++){
		  				if (i<typevar.length-1){
				  			b.write(valeurReq[i]);
				  			b.write(",");
		  				}else{
		  					b.write(valeurReq[i]);
		  				}
			  				
		  			}
		  			b.newLine();
		  			b.close();
				}
				else{
					throw new Exception("la table n'existe pas ou votre requete est faux");
				}
	}
	public boolean testselectplus(String req){
		String[]reqSep1=req.split(" ");
		for (int n=0;n<reqSep1.length;n++){
			if(reqSep1[n].compareToIgnoreCase("*")==0){
				return true;
			}
		}
		return false;
	}
	String[] toutValeurfile(String nomtab)throws Exception{
		LineNumberReader bd=new LineNumberReader(new FileReader(nomtab+".txt"));
		int nbline=countLine(nomtab+".txt");
		String[]value=new String[nbline];
		for (int i=0;i<nbline;i++){
			value[i]=bd.readLine();
		}
		return value;
	}
	public String[][] getfile(String nomtab)throws Exception{
		int nbline=countLine(nomtab+".txt");
		String[]toutvalue=toutValeurfile(nomtab);
		int nbcol=toutvalue[0].split(",").length;
		String[][] valuefile=new String[nbline][nbcol];
		for(int n=0;n<nbline;n++){
			for(int i=0;i<nbcol;i++){
				valuefile[n][i]=toutvalue[n].split(",")[i];
			}
		}
		return valuefile;
	}
	boolean testnomCol(String req,String ntab,String ndb)throws Exception{
		boolean testCol=false;
		try{
		String[]colch=ColChoix(req);
		int nbline=countLine(ndb);
		Vector lsCol=new Vector();
		LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
		for (int i=0;i<nbline;i++){
			String line=bd.readLine();
			String[]reqS1=line.split("/");
			///String[]lsCol=new String[reqS1.length];
			if(reqS1[0].compareToIgnoreCase(ntab)==0){
				int count=0;
				for (int n=1;n<reqS1.length;n++){
					lsCol.add(reqS1[n].split(",")[0]);
					count++;
				}
			}
		}
		int test=0;
		for (int j=0;j<colch.length;j++){
			for (int n=0;n<lsCol.size();n++){
				if (colch[j].compareToIgnoreCase((String)(lsCol.elementAt(n)))==0){
					test++;
				}
			}
		}
		if(test==colch.length){
			testCol=true;
		}
	} catch(Exception e){ e.printStackTrace();}
	return testCol;
	}
	public String[] ColChoix(String req){
		String[]reqSep1=req.split(" ");
		String[]reqSep2=reqSep1[1].split(",");
		String[]nomcol=new String[reqSep2.length];
		for (int i=0;i<reqSep2.length;i++){
			nomcol[i]=reqSep2[i];
		}
		return nomcol;
	}
	public String[][]isselect(String req,String ndb)throws Exception{
		
		int nbline=0;
		String reqSep1=new String();
		//System.out.println(reqSep1[reqSep1.length-1]+".txt");
		if(!isWhere(req)){
			reqSep1=req.split(" ")[3];	
		}
		if (isWhere(req)) {
			reqSep1=req.split("/")[0].split(" ")[3];
		}
		if(!verifierT(reqSep1,ndb)){
			throw new Exception("la table n'existe pas");
		}
		LineNumberReader bd=new LineNumberReader(new FileReader(reqSep1+".txt"));
		nbline=countLine(reqSep1+".txt");
		//System.out.println("ooo");
		String[]toutvalue=toutValeurfile(reqSep1);
		int nbcol=toutvalue[0].split(",").length;
		String[][]requete=new String[nbline][nbcol];
		//if(verifierT(reqSep1,ndb)==true){
			if(testselectplus(req)==true){
				requete=getfile(reqSep1);
			}
			if(isWhere(req)){
				requete=selectWhere(req,ndb);	
			}
			if(!testselectplus(req) && !isWhere(req)){

				requete=getfileCol(req,ndb);
			}
		/*else{
			throw new Exception("table n:existe pas "+reqSep1);
		}*/
		return requete;

	}
	public String makaALLNomCOl(String requete,String data)throws Exception{
		String reqSep=requete.split(" ")[3];
		BufferedReader buff=new BufferedReader(new FileReader(data));
		int line=countLine(data);
		///System.out.println(line);
		String val=new String();
		for (int i=0;i<line;i++){
			String tab=buff.readLine();
			///System.out.println(tab);
			if (tab.split("/")[0].compareToIgnoreCase(reqSep)==0){
				val=tab;
			}
		}
		return val;
	}
	public String[]getToutCol(String req,String data)throws Exception{
		String line1=makaALLNomCOl(req,data);
		String[]nomcol=new String[line1.split("/").length-1];
		int count=1;
		for (int n=0;n<nomcol.length;n++){
			nomcol[n]=line1.split("/")[count].split(",")[0];
			count++;
		}
		return nomcol;
	}
	public int[]compareTab(String[]listeCol,String[]listeColChoi)throws Exception{
		int[]lscol=new int[listeColChoi.length];
		//System.out.println(listeColChoi.length);
		try{
		for(int i=0;i<listeColChoi.length;i++){
			for (int n=0;n<listeCol.length;n++){
				if(listeColChoi[i].compareToIgnoreCase(listeCol[n])==0){
					lscol[i]=n;
				}
				/*else{
					throw new Exception("nomtab tss "+listeColChoi[i]);
				}*/
			}
			
		}
	    }catch(Exception e){e.printStackTrace();}
		return lscol;
	}
	public int[]indicenomcol(String req,String ndb)throws Exception{
		String[]nomcol=ColChoix(req);
		int[]indiceval=new int[nomcol.length];
			String ntab=req.split(" ")[req.split(" ").length-1];
			LineNumberReader bd=new LineNumberReader(new FileReader(ndb));
			int countl=countLine(ndb);
			int ind=0;
			for(int i=0;i<countl;i++){
				String nomtab=bd.readLine();
			    //System.out.println(nomtab.split("/")[0]);
			    //System.out.println("pp"+ntab);
			    if(nomtab.split("/")[0].compareToIgnoreCase(ntab)==0 && this.testnomCol(req,nomtab.split("/")[0],ndb)==true){
			    	String[]toutcol=new String[nomtab.split("/").length-1];
			    	for (int n=1;n<nomtab.split("/").length;n++){
			    		//System.out.println(nomtab.split("/")[n].split(",")[0]);
			    		toutcol[ind]=nomtab.split("/")[n].split(",")[0];
			    		//System.out.println(ind);
			    		ind++;
			    	}
			    	indiceval=compareTab(toutcol,nomcol);
			    	return indiceval;
			   }
			}
			if(ind==0){
				throw new Exception("nom de colone tsisy ou tab");
			}
		return indiceval;
	}
	String[][]getfileCol(String req,String ndb)throws Exception{
		int[]colvalue=indicenomcol(req,ndb);
		int nbcol=colvalue.length;
		String[]reqSep1=req.split(" ");
		String nomtab=reqSep1[reqSep1.length-1];
		int nbline=countLine(nomtab+".txt");
		String[]toutvalue=toutValeurfile(nomtab);
		String[][] valuefile=new String[nbline][nbcol];
		////LineNumberReader bd=new LineNumberReader(new FileReader(nomtab+".txt"));
			for(int n=0;n<nbline;n++){
				for(int i=0;i<nbcol;i++){
					valuefile[n][i]=toutvalue[n].split(",")[colvalue[i]];
				}
			}
		return valuefile;
	}
	String[]getValeurTab(String req,String ndb)throws Exception{
		String reqSep1=req.split("/")[1];
		String reqSep2=req.split("/")[0].split(" ")[3];
		String[]All_val=toutValeurfile(reqSep2);
		String[]lsCol=reqSep1.split(",");
		String requete=new String();
		for(int i=0;i<lsCol.length;i++){
			if (i<lsCol.length-1){
				requete+=lsCol[i].split("=")[0]+",";
			}else{
				requete+=lsCol[i].split("=")[0];
			}
		}

		String req2="select "+requete+" from "+reqSep2;
		int[]indiceCol=indicenomcol(req2,ndb);
		///System.out.println("pppppo"+req2);
		Vector indR=new Vector();
		for(int n=0;n<All_val.length;n++){
		    int count=0;
			for (int y=0;y<indiceCol.length;y++){
				if(All_val[n].split(",")[indiceCol[y]].compareToIgnoreCase(reqSep1.split(",")[y].split("=")[1])==0){
					///System.out.println("llllll");
					count++;
					////System.out.println(count);
				}
			}
			if(count==indiceCol.length){
				
				indR.add(n);
			}
		//System.out.println(count+"tayeh");
		}
		String[]RetourRep=new String[indR.size()];
		//System.out.println("pppp"+(int)(indR.elementAt(0)));
		for(int z=0;z<indR.size();z++){
			RetourRep[z]=All_val[(int)(indR.elementAt(z))];
		}
		return RetourRep;
	}
	String[][]selectWhere(String req,String ndb)throws Exception{
		String[]toutreq=getValeurTab(req,ndb);
		String[][]reqval=new String[toutreq.length][toutreq[0].split(",").length];
		if(isselectWhereplus(req)){
			for (int i=0;i<toutreq.length ;i++){
				for(int n=0;n<toutreq[0].split(",").length;n++){
					reqval[i][n]=toutreq[i].split(",")[n];
				}
			}
		}
		else{
			int[]indCol=indicenomcol(req.split("/")[0],ndb);
			String[][]reqval2=new String[toutreq.length][indCol.length];
			for (int i=0;i<toutreq.length ;i++){
				for(int n=0;n<indCol.length;n++){
					reqval2[i][n]=toutreq[i].split(",")[indCol[n]];
					///System.out.println(reqval2[i][n]);
				}
				
			}
			reqval=reqval2;
		}
		return reqval;
	}
	boolean isselectWhereplus(String req){
		String reqSep1=req.split("/")[0];
		if(testselectplus(reqSep1)){
			return true;
		}
		return false;
	}
	boolean isWhere(String req){
		if(req.split("/").length==2){
			return true;
		}
		return false;
	}
	public static int getport()throws Exception{
		BufferedReader bff=new BufferedReader(new FileReader("FichierConfig.config"));
		int port=new Integer(bff.readLine().split(">")[1].split(";")[0]).intValue();
		return port;
	}
	public static String getIp()throws Exception{
		BufferedReader bff=new BufferedReader(new FileReader("FichierConfig.config"));
		String Ip=new String();
		for(int i=0;i<2;i++){
			Ip=bff.readLine().split(">")[1].split(";")[0];
		}
		return Ip;

	}
	public static String getDirectory()throws Exception{
		BufferedReader bff=new BufferedReader(new FileReader("FichierConfig.config"));
		String Direct=new String();
		for(int i=0;i<3;i++){
			Direct=bff.readLine().split(">")[1].split(";")[0];
		}
		return Direct;
	}

}