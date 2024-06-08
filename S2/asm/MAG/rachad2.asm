	LOADSI #1
traitement_caractere LOADBADRSI
	CMPB #97
	JMPN prochain
	CMPB #123
	JMPPZ prochain
majuscule SUBB #32
	LOADADRSIB
prochain INCSI
	LOADASI
	CMPDIA
	JMPN fin
	JMP traitement_caractere
fin JMP fin 