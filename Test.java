package requete;
import java.io.*;
import java.lang.*;
import java.sql.*;
public class Test{
	public static void main(String[] args){
		try{
		String cc="H:\\SocketExam222Vrai\\test.txt";
		 //BufferedWriter b=new BufferedWriter(new FileWriter("test.txt"));
		// StringBuffer bp=new StringBuffer();
		/// b.write("v");
		/// b.close();
		///C:\\Bitnami\\tomcatstack-10.0.7-0\\apache-tomcat\\webapps\\Examevao2vao2\\WEB-INF\\classes\\
		Requete req=new Requete("select but from bo");
		int count=0;
		String req2=new String("createT ty(vv:int)");
		//req.iscreateT(req2,cc);
		//System.out.println(req.isshowT("test.txt")[1]);
		//String[]nn=req.get_typeVar("tay",cc);
			Integer mm=new Integer(8);
			//req.isinsert("insert bol(7,GGgg)",cc);
		//System.out.println(new Date(2222).valueOf("2002-02-20"));
		//System.out.println(count++);
			//String[]kk=req.ColChoix("select bb,nn from tay");
			//String[][]ii=req.isselect("select * from bol",cc);
			String[]ii2={"ll","pp","kk","io"};
			String[]ii3={"pp","io"};
			//System.out.println(req.isvariable("createT personne(mm:int,jj:real)"));
			////String[]coltab=req.ColChoix("select but,nn from bol");
			///System.out.println(Requete.getDirectory()+"test.txt");
			//req.iscreateT(req2,cc);
			String pp="select * from joueurs";
			///String data=Requete.getDirectory()+"SocketExam222Vrai\\DATABASE\\DataBase.txt";
			System.out.println(req.getToutCol("select * from joueurs","D:\\SocketExam222Vrai\\DATABASE\\DataBase.txt").length);
			System.out.println(pp.split("/")[0]);
	   }catch(Exception e){e.getMessage();}
		//
		
	}
}