GRANT SELECT ON ATHLETE TO AnalyseJO;
GRANT SELECT ON COMPOSITION_EQUIPE TO AnalyseJO;
GRANT SELECT ON DISCIPLINE TO AnalyseJO;
GRANT SELECT ON EQUIPE TO AnalyseJO;
GRANT SELECT ON EVENEMENT TO AnalyseJO;
GRANT SELECT ON HOTE TO AnalyseJO;
GRANT SELECT ON NOC TO AnalyseJO;
GRANT SELECT ON PARTICIPATION_INDIVIDUELLE TO AnalyseJO;
GRANT SELECT ON PARTICIPATION_EQUIPE TO AnalyseJO;
GRANT SELECT ON SPORT TO AnalyseJO;
GRANT SELECT ON LOG TO AnalyseJO;
--ne pas oublier : Donner accès en consultation aux vues et en exécution à vos procédures/fonctions stockées si elles ne modifient pas la base pour AnalyseJO.
GRANT SELECT,UPDATE,INSERT,DELETE ON ATHLETE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON COMPOSITION_EQUIPE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON DISCIPLINE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON EQUIPE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON EVENEMENT TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON HOTE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON NOC TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON PARTICIPATION_INDIVIDUELLE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON PARTICIPATION_EQUIPE TO GestionJO;
GRANT SELECT,UPDATE,INSERT,DELETE ON SPORT TO GestionJO;
GRANT SELECT ON LOG TO AnalyseJO;
--Il aura en revanche accès à toutes les fonctions/procédures qui permettent de consulter ou modifier la base.

CREATE TABLE log (
    idAuteur NUMBER,
    action VARCHAR2(50),
    dateHeureAction DATE,
    ligneAvant VARCHAR2(4000),
    ligneApres VARCHAR2(4000),
    PRIMARY KEY (idAuteur)
);
--DROP TABLE log;

CREATE OR REPLACE TRIGGER triggerAthlete
BEFORE UPDATE OR DELETE ON Athlete
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        --parcourir chaque colonne et comparer leur ancienne et nouvelles versions 
        --stocker les changement dans deux variables
        -- utiliser les variables pour insérer la ligne dans la table log
    IF UPDATING THEN 
        --parcourir chaque colonne et comparer leur ancienne et nouvelles versions 
        --stocker les changement dans deux variables
        -- utiliser les variables pour insérer la ligne dans la table log
