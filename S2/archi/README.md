##### TAOUS louai - MOHAMED DAROUECHE Naherry          | 2023-2024        Groupe 2D          | Projet comme bas niveau 
# Projet communication bas niveau

- [Projet communication bas niveau](#projet-communication-bas-niveau)
	- [Créations du langages](#créations-du-langages)
		- [Création des champs](#création-des-champs)
		- [Création des instructions](#création-des-instructions)
	- [Programme](#programme)
		- [Palindrome](#palindrome)
		- [Majuscule](#majuscule)

## Créations du langages

### Création des champs

Voici tous les champs, qui sont donées du bits de poids fort au bits de poids faible

    - SAUT : 00000 : sur 5 bits, ce champs regroupe toutes les instructions de sauts
    - MUX1 : sur 2 bits, ce champs désigne le multiplexeur 1 qui sélectionne d'où viendra la donnée de l'opérande 1 de UAL entre registre B, SI, DI et RAM des données
    - MUX0 : sur 1 bit, ce champs désigne le multiplexeur 0 qui sélectionne d'où viendra la donnée de l'opérande 1 de UAL entre registre A et le champs data ou adresse
    - MUX2 : sur 1 bit, ce champs désigne le multiplexeur 2 qui choisi registre sera utilisé pour pointer sur la RAM des données
    - DEC : sur 3 bits, ce champs désigne le décodeur 1 qui sélectionne dans quel registre va être écrite la donnée
    - ALU : sur 4 bits, ce champs désigne le code opératoir de l'unité arithmétique-logique  

### Création des instructions

| Instructions   | Fonctionnement                                                                                           | Code machine | Valeur des champs                 |
|----------------|---------------------------------------------------------------------------------------------------------|--------------|-----------------------------------|
| LOADA #valeur  | Charge une valeur immédiate dans le registre A.                                                        | 0011 VVVV    | SAUT: 00000 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 001 ALU: 0001 |
| LOADSI #valeur | Charge une valeur immédiate dans le registre SI.                                                        | 0031 VVVV    | SAUT: 00000 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 011 ALU: 0001 |
| LOADADI        | Copie le contenu du registre DI dans le registre A.                                                     | 0412 XXXX    | SAUT: 00000 MUX1: 10 MUX0: 0 MUX2: 0 DECODEUR: 001 ALU: 0010 |
| LOADAADRSI     | Charge dans le registre A la donnée située à l'adresse spécifiée dans le registre SI.                   | 0612 XXXX    | SAUT: 00000 MUX1: 11 MUX0: 0 MUX2: 0 DECODEUR: 001 ALU: 0010 |
| LOADBADRDI     | Charge dans le registre B la donnée située à l'adresse spécifiée dans le registre DI.                   | 06a2 XXXX    | SAUT: 00000 MUX1: 11 MUX0: 0 MUX2: 1 DECODEUR: 010 ALU: 0010 |
| LOADDIADRSI    | Charge dans le registre DI la donnée située à l'adresse spécifiée dans le registre SI.                  | 0642 XXXX    | SAUT: 00000 MUX1: 11 MUX0: 0 MUX2: 0 DECODEUR: 010 ALU: 0010 |
| INCSI          | Incrémente la valeur contenue dans le registre SI de 1.                                                 | 0235 XXXX    | SAUT: 00000 MUX1: 01 MUX0: 0 MUX2: 0 DECODEUR: 011 ALU: 0101 |
| DECDI          | Décrémente la valeur contenue dans le registre DI de 1.                                                 | 0446 XXXX    | SAUT: 00000 MUX1: 10 MUX0: 0 MUX2: 0 DECODEUR: 100 ALU: 0110 |
| CMPSIA         | Compare la valeur contenue dans le registre SI avec celle contenue dans le registre A.                  | 030b XXXX    | SAUT: 00000 MUX1: 01 MUX0: 1 MUX2: 0 DECODEUR: 000 ALU: 1011 |
| CMPBA          | Compare la valeur contenue dans le registre B avec celle contenue dans le registre A.                   | 010B XXXX    | SAUT: 00000 MUX1: 00 MUX0: 1 MUX2: 0 DECODEUR: 000 ALU: 1011 |
| JMP <label>    | Effectue un saut inconditionnel à l'instruction étiquetée par <label>.                                  | 8000 XXXX    | SAUT: 10000 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 000 ALU: 0000 |
| JMPNZ <label>  | Effectue un saut à l'instruction étiquetée par <label> si le résultat de l'instruction précédente n'est pas nul. | 2000 XXXX    | SAUT: 00100 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 000 ALU: 0000 |
| JMPPZ <label>  | Effectue un saut à l'instruction étiquetée par <label> si le résultat de l'instruction précédente est positif ou nul. | 0800 XXXX    | SAUT: 00001 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 000 ALU: 1000 |
| LOADASI        | Charge la valeur contenue dans le registre SI dans le registre A.                                       | 0212 XXXX    | SAUT: 00000 MUX1: 01 MUX0: 0  MUX2: 0 DECODEUR: 001 ALU: 0010 |
| LOADADRSIB     | Charge la valeur contenue à l'adresse spécifiée par B dans le registre A.                               | 0052 XXXX    | SAUT: 00000 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 101 ALU: 0010 |
| CMPDIA         | Compare la valeur contenue dans le registre DI avec celle contenue dans le registre A.                   | 050b XXXX    | SAUT: 00000 MUX1: 10 MUX0: 1 MUX2: 0 DECODEUR: 000 ALU: 1011 |
| CMPB #valeur   | Compare la valeur immédiate spécifiée avec celle contenue dans le registre B.                           | 000b VVVV    | SAUT: 00000 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 000 ALU: 1011 |
| SUBB #valeur   | Soustrait une valeur immédiate spécifiée à celle contenue dans le registre B et stocke le résultat dans B. | 0028 VVVV    | SAUT: 00000 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 010 ALU: 1000 |
| JMPN <label>   | Effectue un saut à l'instruction étiquetée par <label> si le résultat de l'instruction précédente est négatif. | 1000 XXXX    | SAUT: 00010 MUX1: 00 MUX0: 0 MUX2: 0 DECODEUR: 000 ALU: 0000 |
| LOADBADRSI     | Charge la valeur contenue à l'adresse spécifiée par SI dans le registre B.                               | 0622 XXXX    | SAUT: 00000 MUX1: 11 MUX0: 0 MUX2: 0 DECODEUR: 010 ALU: 0010 |


## Programme

### Palindrome

```
		LOADDIADRSI
reco	INCSI
		LOADADI
		CMPSIA
		JMPPZ oui
		LOADBADRDI
		LOADAADRSI
		CMPBA
		JMPNZ non
		DECDI
		JMP reco
non		LOADA #-1
		JMP fin
oui		LOADA #1
fin 	JMP fin

```

Ce code fonctionne ainsi :\
	- Charge dans les registres DI l'adressess de fin chaine obtenu grâce à la longueur qui est stocké à la case 0000 <sub>16</sub> de la RAM \
	- Incrémente SI qui pointe désormais à l'adresse suivante de la RAM \
	- Charge dans A la valeur stocké dans DI, soit la l'adresse de fin de chaine \
	- Vérifie l'adresse pointé par SI et par DI \
	- Saute à la ligne 13 du programme si le résultat est positif ou nul, ce qui signifierait qui SI et DI point à la même case pour une chaine de longueur impair, ou que SI pointe à case après DI pour une chaine de longueur pari \
	- Charge dans B,la valeur à la case pointé par DI \
	- Charge dans A,la valeur à la case pointé par SI \
	- Vérifie l'égalité des lettres entre avec B et A : \
    	- On saute à la ligne 11 du programme si B et A n'est pas égal, ce qui signifie que la chaine de caractère n'est pas une chaine de caractère \
    	- Sinon : \
        	- on décrémente DI qui pointe désormais à la case précédente \
        	- On saute à la ligne à ligne 2 du programme pour recommencer \
  	- Charge dans A \
    	- -1 si ce n'est pas un palindromme car on aura comparé B et A et sauté à cette ligne ensuite \
    	- 1 si c'est un palindrome car on aura pas chargé -1 et DI pointera à une case égale ou précédante à SI, ce qui signifie qu'on aura parcouru toute la chaine \


### Majuscule

```

		LOADDIADRSI 
boucle	LOADASI
		CMPDIA
		JMPN fin
		INCSI
		LOADBADRSI
        CMPB #96
		JMPN boucle
		CMPB #123
		JMPPZ boucle
		SUBB #32
		LOADADRSIB
		JMP boucle
fin		JMP fin

```

Ce code fonctionne ainsi :
	- Charge dans les registres DI et A  respectivement l'adressess de fin chaine obtenu grâce à la longueur qui est stocké à la case 0000<sub>16</sub> de la RAM et l'adresse courante pointé par SI \
	- Compare DI et A \
	- Saute à la ligne 14 du programme si le résultat est négatif, ce qui signifierait qui SI pointe hors du tableau car DI pointe à la dernière case de la chaine, donc dernière - dernière+1<0 \
	- Incrémente le registre SI qui désormais, pointe à la case suivante du programme \
	- Charge dans B,la valeur à la case pointé par SI \
	- Vérifie si la donnée est bien une lettre : \
    	- On compare B avec 96<sub>10</sub> et on saute à la ligne 2 si le résultat est strictement négatif, ce qui impliquerait que B contient un caractère qui à un code ASCII strictement plus petit que a, ce qui implique que B ne contient pas une lettre. \
    	- On compare B avec 123<sub>10</sub> et on saute à la ligne 2 si le résultat est positif ou nul, ce qui impliquerait que B contient un caractère qui à un code ASCII plus grand ou égal que le caractère qui suit z. Ce qui implique que B ne contient pas une lettre. \
  	- Retire 32<sub>10</sub> à B et stocke le résultat dans B \
  	- Charge la valeur de B dans la RAM \
  	- Saute à la ligne 2

