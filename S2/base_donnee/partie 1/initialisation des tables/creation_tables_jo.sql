--------------------------------------------------------
--  DDL for Table ATHLETE
--------------------------------------------------------

CREATE TABLE "ATHLETE" (
    "IDATHLETE" NUMBER(10,0), 
    "NOMATHLETE" NVARCHAR2(50), 
    "PRENOMATHLETE" NVARCHAR2(50), 
    "SURNOM" NVARCHAR2(250), 
    "GENRE" VARCHAR2(10), 
    "DATENAISSANCE" DATE, 
    "DATEDECES" DATE, 
    "TAILLE" NUMBER(4,0), 
    "POIDS" NUMBER(4,0)
);

--------------------------------------------------------
--  DDL for Table COMPOSITION_EQUIPE
--------------------------------------------------------

CREATE TABLE "COMPOSITION_EQUIPE" (
    "IDEQUIPE" NUMBER(10,0), 
    "IDATHLETE" NUMBER(10,0)
);

--------------------------------------------------------
--  DDL for Table DISCIPLINE
--------------------------------------------------------

CREATE TABLE "DISCIPLINE" (
    "CODEDISCIPLINE" CHAR(3), 
    "NOMDISCIPLINE" VARCHAR2(50), 
    "CODESPORT" CHAR(2)
);

--------------------------------------------------------
--  DDL for Table EQUIPE
--------------------------------------------------------

CREATE TABLE "EQUIPE" (
    "IDEQUIPE" NUMBER(10,0), 
    "NOMEQUIPE" VARCHAR2(100), 
    "NOC" VARCHAR2(3)
);

--------------------------------------------------------
--  DDL for Table EVENEMENT
--------------------------------------------------------

CREATE TABLE "EVENEMENT" (
    "IDEVENEMENT" NUMBER(10,0), 
    "NOMEVENEMENT" VARCHAR2(200),
    "STATUTEVENEMENT" VARCHAR2(25),
    "CODEDISCIPLINE" CHAR(3), 
    "IDHOTE" NUMBER(3,0)
);

--------------------------------------------------------
--  DDL for Table HOTE
--------------------------------------------------------

CREATE TABLE "HOTE" (
    "IDHOTE" NUMBER(3,0), 
    "NUMEROHOTE" VARCHAR2(10), 
    "LIBELLEHOTE" VARCHAR2(100), 
    "ANNEEHOTE" NUMBER(4,0), 
    "SAISON" VARCHAR2(20), 
    "VILLEHOTE" VARCHAR2(50), 
    "CODENOCHOTE" VARCHAR2(3), 
    "DATEOUVERTURE" VARCHAR2(20), 
    "DATEFERMETURE" VARCHAR2(20), 
    "DATESCOMPETITION" VARCHAR2(50), 
    "NOTES" VARCHAR2(500)
);

--------------------------------------------------------
--  DDL for Table NOC
--------------------------------------------------------

CREATE TABLE "NOC" (
    "CODENOC" VARCHAR2(3), 
    "NOMNOC" VARCHAR2(50)
);

--------------------------------------------------------
--  DDL for Table PARTICIPATION
--------------------------------------------------------

CREATE TABLE "PARTICIPATION_INDIVIDUELLE" (
    "IDATHLETE" NUMBER(10,0), 
    "IDEVENT" NUMBER(10,0), 
    "RESULTAT" VARCHAR2(50), 
    "MEDAILLE" VARCHAR2(10), 
    "NOC" VARCHAR2(3)
);

--------------------------------------------------------
--  DDL for Table PARTICIPATION_EQUIPE
--------------------------------------------------------

CREATE TABLE "PARTICIPATION_EQUIPE" (
    "IDEVENEMENT" NUMBER(10,0), 
    "IDEQUIPE" NUMBER(10,0), 
    "RESULTAT" VARCHAR2(25), 
    "MEDAILLE" VARCHAR2(10)
);

--------------------------------------------------------
--  DDL for Table SPORT
--------------------------------------------------------

CREATE TABLE "SPORT" (
    "CODESPORT" CHAR(2), 
    "NOMSPORT" VARCHAR2(50)
);
