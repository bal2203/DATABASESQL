=>FichierConfig
	=>ip
	=>port
	=>directory (ne pas changer le nom du dossier)
=>lancez le cmdserver.bat
=>lancez le cmdclient.bat
=>ecrivez votre requete comme(
		=>showt pour afficher les listes des tables
		=>createT ntab(ncol:typevar,...)
			=>type variable(real,string,int,date)
		=>insertT nTab(donnee,...)
		=>select * from ntab
		=>select * from ntab/ncol=valeur,...(select and)
		=>select ncol1,ncol2,... from ntab avec ou sans condition
		)
N-B=>ne touche jamais les structures du fichier	