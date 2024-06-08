	LOADDIADRSI
boucle 	INCSI
	LOADADI
    	CMPSIA     
    	JMPPZ palindrome
    	LOADAADRSI      
    	LOADBADRDI
    	CMPBA           
    	JMPNZ non_palin
	DECDI  
    	JMP boucle     
palindrome	LOADA #1
	JMP fin
non_palin	LOADA #-1       
fin	JMP fin       