SET SERVEROUTPUT ON;

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
    action VARCHAR2(4000),
    dateHeureAction DATE,
    ligneAvant VARCHAR2(4000),
    ligneApres VARCHAR2(4000),
    PRIMARY KEY (idAuteur,dateHeureAction)
);
--DROP TABLE log;

CREATE OR REPLACE TRIGGER triggerAthlete
BEFORE UPDATE OR DELETE ON Athlete
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idAthlete != :NEW.idAthlete THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.idAthlete, :NEW.idAthlete);
        ELSIF :OLD.nomAthlete != :NEW.nomAthlete THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.nomAthlete, :NEW.nomAthlete);
        ELSIF :OLD.prenomAthlete != :NEW.prenomAthlete THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.prenomAthlete, :NEW.prenomAthlete);
        ELSIF :OLD.surnom != :NEW.surnom THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.surnom, :NEW.surnom);
        ELSIF :OLD.genre != :NEW.genre THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.genre, :NEW.genre);
        ELSIF :OLD.dateNaissance != :NEW.dateNaissance THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.dateNaissance, :NEW.dateNaissance);
        ELSIF :OLD.dateDeces != :NEW.dateDeces THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.dateDeces, :NEW.dateDeces);
        ELSIF :OLD.taille != :NEW.taille THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.taille, :NEW.taille);
        ELSIF :OLD.poids != :NEW.poids THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.poids, :NEW.poids);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.idAthlete != :NEW.idAthlete THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idAthlete, :NEW.idAthlete);
        ELSIF :OLD.nomAthlete != :NEW.nomAthlete THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.nomAthlete, :NEW.nomAthlete);
        ELSIF :OLD.prenomAthlete != :NEW.prenomAthlete THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.prenomAthlete, :NEW.prenomAthlete);
        ELSIF :OLD.surnom != :NEW.surnom THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.surnom, :NEW.surnom);
        ELSIF :OLD.genre != :NEW.genre THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.genre, :NEW.genre);
        ELSIF :OLD.dateNaissance != :NEW.dateNaissance THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.dateNaissance, :NEW.dateNaissance);
        ELSIF :OLD.dateDeces != :NEW.dateDeces THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.dateDeces, :NEW.dateDeces);
        ELSIF :OLD.taille != :NEW.taille THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.taille, :NEW.taille);
        ELSIF :OLD.poids != :NEW.poids THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.poids, :NEW.poids);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggerSport
BEFORE UPDATE OR DELETE ON Sport
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.codeSport != :NEW.codeSport THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.codeSport, :NEW.codeSport);
        ELSIF :OLD.nomSport != :NEW.nomSPort THEN 
            INSERT INTO log VALUES (User,'Updattin',SYSDATE, :OLD.nomSport, :NEW.nomSport);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.codeSport != :NEW.codeSport THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.codeSport, :NEW.nomSport);
        ELSIF :OLD.nomSport != :NEW.nomSport THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.nomSport, :NEW.nomSport);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggerDiscipline
BEFORE UPDATE OR DELETE ON Discipline
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.codeDiscipline != :NEW.codeDiscipline THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.codeDiscipline, :NEW.codeDiscipline);
        ELSIF :OLD.nomDiscipline != :NEW.nomDiscipline THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.nomDiscipline, :NEW.nomDiscipline);
        ELSIF :OLD.codeSport != :NEW.codeSport THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.codeSport, :NEW.codeSport);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.codeDiscipline != :NEW.codeDiscipline THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.codeDiscipline, :NEW.codeDiscipline);
        ELSIF :OLD.nomDiscipline != :NEW.nomDiscipline THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.nomDiscipline, :NEW.nomDiscipline);
        ELSIF :OLD.codeSport != :NEW.codeSport THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.codeSport, :NEW.codeSport);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggereEquipe
BEFORE UPDATE OR DELETE ON Equipe
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idEquipe != :NEW.idEquipe THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idEquipe, :NEW.idEquipe);
        ELSIF :OLD.nomEquipe != :NEW.nomEquipe THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.nomEquipe, :NEW.nomEquipe);
        ELSIF :OLD.noc != :NEW.noc THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.noc, :NEW.noc);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.idEquipe != :NEW.idEquipe THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idEquipe, :NEW.idEquipe);
        ELSIF :OLD.nomEquipe != :NEW.nomEquipe THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.nomEquipe, :NEW.nomEquipe);
        ELSIF :OLD.noc != :NEW.noc THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.noc, :NEW.noc);END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggerParicipationEquipe
BEFORE UPDATE OR DELETE ON participation_equipe
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idEvenement != :NEW.idEvenement THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idEvenement, :NEW.idEvenement);
        ELSIF :OLD.idEquipe != :NEW.idEquipe THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idEquipe, :NEW.idEquipe);
        ELSIF :OLD.resultat != :NEW.resultat THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.resultat, :NEW.resultat);
        ELSIF :OLD.medaille != :NEW.medaille THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.medaille, :NEW.medaille);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.idEvenement != :NEW.idEvenement THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idEvenement, :NEW.idEvenement);
        ELSIF :OLD.idEquipe != :NEW.idEquipe THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idEquipe, :NEW.idEquipe);
        ELSIF :OLD.resultat != :NEW.resultat THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.resultat, :NEW.resultat);
        ELSIF :OLD.medaille != :NEW.medaille THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.medaille, :NEW.medaille);
        END IF;    
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggereParticipationIndividuelle
BEFORE UPDATE OR DELETE ON Participation_Individuelle
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idAthlete != :NEW.idAthlete THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idAthlete, :NEW.idAthlete);
        ELSIF :OLD.idEvent != :NEW.idEvent THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idEvent, :NEW.idEvent);
        ELSIF :OLD.resultat != :NEW.resultat THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.resultat, :NEW.resultat);
        ELSIF :OLD.noc != :NEW.noc THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.noc, :NEW.noc);
        ELSIF :OLD.medaille != :NEW.medaille THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.medaille, :NEW.medaille);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.idAthlete != :NEW.idAthlete THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idAthlete, :NEW.idAthlete);
        ELSIF :OLD.idEvent != :NEW.idEvent THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idEvent, :NEW.idEvent);
        ELSIF :OLD.resultat != :NEW.resultat THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.resultat, :NEW.resultat);
        ELSIF :OLD.noc != :NEW.noc THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.noc, :NEW.noc);
        ELSIF :OLD.medaille != :NEW.medaille THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.medaille, :NEW.medaille);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggereEvenement
BEFORE UPDATE OR DELETE ON evenement
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idEvenement != :NEW.idEvenement THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idEvenement, :NEW.idEvenement);
        ELSIF :OLD.nomEvenement != :NEW.nomEvenement THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.nomEvenement, :NEW.nomEvenement);
        ELSIF :OLD.statutEvenement != :NEW.statutEvenement THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.statutEvenement, :NEW.statutEvenement);
        ELSIF :OLD.codeDiscipline != :NEW.codeDiscipline THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.codeDiscipline, :NEW.codeDiscipline);
        ELSIF :OLD.idHote != :NEW.idHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idHote, :NEW.idHote);
        END IF;
        
    ELSIF DELETING THEN 
        IF :OLD.idEvenement != :NEW.idEvenement THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idEvenement, :NEW.idEvenement);
        ELSIF :OLD.nomEvenement != :NEW.nomEvenement THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.nomEvenement, :NEW.nomEvenement);
        ELSIF :OLD.statutEvenement != :NEW.statutEvenement THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.statutEvenement, :NEW.statutEvenement);
        ELSIF :OLD.codeDiscipline != :NEW.codeDiscipline THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.codeDiscipline, :NEW.codeDiscipline);
        ELSIF :OLD.idHote != :NEW.idHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idHote, :NEW.idHote);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggereNoc
BEFORE UPDATE OR DELETE ON Noc
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.codeNOC != :NEW.codeNOC THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.codeNOC, :NEW.codeNOC);
        ELSIF :OLD.nomNOC != :NEW.nomNOC THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.nomNOC, :NEW.nomNOC);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.codeNOC != :NEW.codeNOC THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.codeNOC, :NEW.codeNOC);
        ELSIF :OLD.nomNOC != :NEW.nomNOC THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.nomNOC, :NEW.nomNOC);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggereHote
BEFORE UPDATE OR DELETE ON Hote
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idHote != :NEW.idHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idHote, :NEW.idHote);
        ELSIF :OLD.numeroHote != :NEW.numeroHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.numeroHote, :NEW.numeroHote);
        ELSIF :OLD.libelleHote != :NEW.libelleHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.libelleHote, :NEW.libelleHote);
        ELSIF :OLD.anneeHote != :NEW.anneeHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.anneeHote, :NEW.anneeHote);
        ELSIF :OLD.saison != :NEW.saison THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.saison, :NEW.saison);
        ELSIF :OLD.villeHote != :NEW.villeHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.villeHote, :NEW.villeHote);
        ELSIF :OLD.codeNOCHote != :NEW.codeNOCHote THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.codeNOCHote, :NEW.codeNOCHote);
        ELSIF :OLD.dateOuverture != :NEW.dateOuverture THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.dateOuverture, :NEW.dateOuverture);
        ELSIF :OLD.dateFermeture != :NEW.dateFermeture THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.dateFermeture, :NEW.dateFermeture);
        ELSIF :OLD.DATESCOMPETITION != :NEW.DATESCOMPETITION THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.DATESCOMPETITION, :NEW.DATESCOMPETITION);
        ELSIF :OLD.notes != :NEW.notes THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.notes, :NEW.notes);
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.idHote != :NEW.idHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idHote, :NEW.idHote);
        ELSIF :OLD.numeroHote != :NEW.numeroHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.numeroHote, :NEW.numeroHote);
        ELSIF :OLD.libelleHote != :NEW.libelleHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.libelleHote, :NEW.libelleHote);
        ELSIF :OLD.anneeHote != :NEW.anneeHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.anneeHote, :NEW.anneeHote);
        ELSIF :OLD.saison != :NEW.saison THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.saison, :NEW.saison);
        ELSIF :OLD.villeHote != :NEW.villeHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.villeHote, :NEW.villeHote);
        ELSIF :OLD.codeNOCHote != :NEW.codeNOCHote THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.codeNOCHote, :NEW.codeNOCHote);
        ELSIF :OLD.dateOuverture != :NEW.dateOuverture THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.dateOuverture, :NEW.dateOuverture);
        ELSIF :OLD.dateFermeture != :NEW.dateFermeture THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.dateFermeture, :NEW.dateFermeture);
        ELSIF :OLD.DATESCOMPETITION != :NEW.DATESCOMPETITION THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.DATESCOMPETITION, :NEW.DATESCOMPETITION);
        ELSIF :OLD.notes != :NEW.notes THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.notes, :NEW.notes);
        END IF;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER triggereCompositionEquipe
BEFORE UPDATE OR DELETE ON Composition_Equipe
FOR EACH ROW
BEGIN 
    IF UPDATING THEN 
        IF :OLD.idEquipe != :NEW.idEquipe THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idEquipe, :NEW.idEquipe);
        ELSIF :OLD.idAthlete != :NEW.idAthlete THEN 
            INSERT INTO log VALUES (User,'Updating',SYSDATE, :OLD.idAthlete, :NEW.idAthlete);        
        END IF;
    ELSIF DELETING THEN 
        IF :OLD.idEquipe != :NEW.idEquipe THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idEquipe, :NEW.idEquipe);
        ELSIF :OLD.idAthlete != :NEW.idAthlete THEN 
            INSERT INTO log VALUES (User,'Deleting',SYSDATE, :OLD.idAthlete, :NEW.idAthlete);
        END IF;
    END IF;
END;


