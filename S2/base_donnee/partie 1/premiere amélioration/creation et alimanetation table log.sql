SET SERVEROUTPUT ON;

CREATE TABLE LOG (
    IDAUTEUR NUMBER,
    ACTION VARCHAR2(4000),
    DATEHEUREACTION DATE,
    LIGNEAVANT VARCHAR2(4000),
    LIGNEAPRES VARCHAR2(4000),
    PRIMARY KEY (IDAUTEUR, DATEHEUREACTION)
);

--DROP TABLE log;

CREATE OR REPLACE TRIGGER TRIGGERATHLETE BEFORE
    UPDATE OR DELETE ON ATHLETE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDATHLETE != :NEW.IDATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.IDATHLETE,
                :NEW.IDATHLETE
            );
        ELSIF :OLD.NOMATHLETE != :NEW.NOMATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.NOMATHLETE,
                :NEW.NOMATHLETE
            );
        ELSIF :OLD.PRENOMATHLETE != :NEW.PRENOMATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.PRENOMATHLETE,
                :NEW.PRENOMATHLETE
            );
        ELSIF :OLD.SURNOM != :NEW.SURNOM THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.SURNOM,
                :NEW.SURNOM
            );
        ELSIF :OLD.GENRE != :NEW.GENRE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.GENRE,
                :NEW.GENRE
            );
        ELSIF :OLD.DATENAISSANCE != :NEW.DATENAISSANCE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.DATENAISSANCE,
                :NEW.DATENAISSANCE
            );
        ELSIF :OLD.DATEDECES != :NEW.DATEDECES THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.DATEDECES,
                :NEW.DATEDECES
            );
        ELSIF :OLD.TAILLE != :NEW.TAILLE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.TAILLE,
                :NEW.TAILLE
            );
        ELSIF :OLD.POIDS != :NEW.POIDS THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.POIDS,
                :NEW.POIDS
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDATHLETE != :NEW.IDATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDATHLETE,
                :NEW.IDATHLETE
            );
        ELSIF :OLD.NOMATHLETE != :NEW.NOMATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOMATHLETE,
                :NEW.NOMATHLETE
            );
        ELSIF :OLD.PRENOMATHLETE != :NEW.PRENOMATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.PRENOMATHLETE,
                :NEW.PRENOMATHLETE
            );
        ELSIF :OLD.SURNOM != :NEW.SURNOM THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.SURNOM,
                :NEW.SURNOM
            );
        ELSIF :OLD.GENRE != :NEW.GENRE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.GENRE,
                :NEW.GENRE
            );
        ELSIF :OLD.DATENAISSANCE != :NEW.DATENAISSANCE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.DATENAISSANCE,
                :NEW.DATENAISSANCE
            );
        ELSIF :OLD.DATEDECES != :NEW.DATEDECES THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.DATEDECES,
                :NEW.DATEDECES
            );
        ELSIF :OLD.TAILLE != :NEW.TAILLE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.TAILLE,
                :NEW.TAILLE
            );
        ELSIF :OLD.POIDS != :NEW.POIDS THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.POIDS,
                :NEW.POIDS
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGERSPORT BEFORE
    UPDATE OR DELETE ON SPORT FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.CODESPORT != :NEW.CODESPORT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.CODESPORT,
                :NEW.CODESPORT
            );
        ELSIF :OLD.NOMSPORT != :NEW.NOMSPORT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updattin',
                SYSDATE,
                :OLD.NOMSPORT,
                :NEW.NOMSPORT
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.CODESPORT != :NEW.CODESPORT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.CODESPORT,
                :NEW.NOMSPORT
            );
        ELSIF :OLD.NOMSPORT != :NEW.NOMSPORT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOMSPORT,
                :NEW.NOMSPORT
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGERDISCIPLINE BEFORE
    UPDATE OR DELETE ON DISCIPLINE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.CODEDISCIPLINE != :NEW.CODEDISCIPLINE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.CODEDISCIPLINE,
                :NEW.CODEDISCIPLINE
            );
        ELSIF :OLD.NOMDISCIPLINE != :NEW.NOMDISCIPLINE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOMDISCIPLINE,
                :NEW.NOMDISCIPLINE
            );
        ELSIF :OLD.CODESPORT != :NEW.CODESPORT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.CODESPORT,
                :NEW.CODESPORT
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.CODEDISCIPLINE != :NEW.CODEDISCIPLINE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.CODEDISCIPLINE,
                :NEW.CODEDISCIPLINE
            );
        ELSIF :OLD.NOMDISCIPLINE != :NEW.NOMDISCIPLINE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOMDISCIPLINE,
                :NEW.NOMDISCIPLINE
            );
        ELSIF :OLD.CODESPORT != :NEW.CODESPORT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.CODESPORT,
                :NEW.CODESPORT
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGEREEQUIPE BEFORE
    UPDATE OR DELETE ON EQUIPE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDEQUIPE != :NEW.IDEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDEQUIPE,
                :NEW.IDEQUIPE
            );
        ELSIF :OLD.NOMEQUIPE != :NEW.NOMEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOMEQUIPE,
                :NEW.NOMEQUIPE
            );
        ELSIF :OLD.NOC != :NEW.NOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOC,
                :NEW.NOC
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDEQUIPE != :NEW.IDEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDEQUIPE,
                :NEW.IDEQUIPE
            );
        ELSIF :OLD.NOMEQUIPE != :NEW.NOMEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOMEQUIPE,
                :NEW.NOMEQUIPE
            );
        ELSIF :OLD.NOC != :NEW.NOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOC,
                :NEW.NOC
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGERPARICIPATIONEQUIPE BEFORE
    UPDATE OR DELETE ON PARTICIPATION_EQUIPE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDEVENEMENT != :NEW.IDEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDEVENEMENT,
                :NEW.IDEVENEMENT
            );
        ELSIF :OLD.IDEQUIPE != :NEW.IDEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDEQUIPE,
                :NEW.IDEQUIPE
            );
        ELSIF :OLD.RESULTAT != :NEW.RESULTAT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.RESULTAT,
                :NEW.RESULTAT
            );
        ELSIF :OLD.MEDAILLE != :NEW.MEDAILLE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.MEDAILLE,
                :NEW.MEDAILLE
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDEVENEMENT != :NEW.IDEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDEVENEMENT,
                :NEW.IDEVENEMENT
            );
        ELSIF :OLD.IDEQUIPE != :NEW.IDEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDEQUIPE,
                :NEW.IDEQUIPE
            );
        ELSIF :OLD.RESULTAT != :NEW.RESULTAT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.RESULTAT,
                :NEW.RESULTAT
            );
        ELSIF :OLD.MEDAILLE != :NEW.MEDAILLE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.MEDAILLE,
                :NEW.MEDAILLE
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGEREPARTICIPATIONINDIVIDUELLE BEFORE
    UPDATE OR DELETE ON PARTICIPATION_INDIVIDUELLE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDATHLETE != :NEW.IDATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDATHLETE,
                :NEW.IDATHLETE
            );
        ELSIF :OLD.IDEVENT != :NEW.IDEVENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDEVENT,
                :NEW.IDEVENT
            );
        ELSIF :OLD.RESULTAT != :NEW.RESULTAT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.RESULTAT,
                :NEW.RESULTAT
            );
        ELSIF :OLD.NOC != :NEW.NOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOC,
                :NEW.NOC
            );
        ELSIF :OLD.MEDAILLE != :NEW.MEDAILLE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.MEDAILLE,
                :NEW.MEDAILLE
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDATHLETE != :NEW.IDATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDATHLETE,
                :NEW.IDATHLETE
            );
        ELSIF :OLD.IDEVENT != :NEW.IDEVENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDEVENT,
                :NEW.IDEVENT
            );
        ELSIF :OLD.RESULTAT != :NEW.RESULTAT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.RESULTAT,
                :NEW.RESULTAT
            );
        ELSIF :OLD.NOC != :NEW.NOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOC,
                :NEW.NOC
            );
        ELSIF :OLD.MEDAILLE != :NEW.MEDAILLE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.MEDAILLE,
                :NEW.MEDAILLE
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGEREEVENEMENT BEFORE
    UPDATE OR DELETE ON EVENEMENT FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDEVENEMENT != :NEW.IDEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDEVENEMENT,
                :NEW.IDEVENEMENT
            );
        ELSIF :OLD.NOMEVENEMENT != :NEW.NOMEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOMEVENEMENT,
                :NEW.NOMEVENEMENT
            );
        ELSIF :OLD.STATUTEVENEMENT != :NEW.STATUTEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.STATUTEVENEMENT,
                :NEW.STATUTEVENEMENT
            );
        ELSIF :OLD.CODEDISCIPLINE != :NEW.CODEDISCIPLINE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.CODEDISCIPLINE,
                :NEW.CODEDISCIPLINE
            );
        ELSIF :OLD.IDHOTE != :NEW.IDHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDHOTE,
                :NEW.IDHOTE
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDEVENEMENT != :NEW.IDEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDEVENEMENT,
                :NEW.IDEVENEMENT
            );
        ELSIF :OLD.NOMEVENEMENT != :NEW.NOMEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOMEVENEMENT,
                :NEW.NOMEVENEMENT
            );
        ELSIF :OLD.STATUTEVENEMENT != :NEW.STATUTEVENEMENT THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.STATUTEVENEMENT,
                :NEW.STATUTEVENEMENT
            );
        ELSIF :OLD.CODEDISCIPLINE != :NEW.CODEDISCIPLINE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.CODEDISCIPLINE,
                :NEW.CODEDISCIPLINE
            );
        ELSIF :OLD.IDHOTE != :NEW.IDHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDHOTE,
                :NEW.IDHOTE
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGERENOC BEFORE
    UPDATE OR DELETE ON NOC FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.CODENOC != :NEW.CODENOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.CODENOC,
                :NEW.CODENOC
            );
        ELSIF :OLD.NOMNOC != :NEW.NOMNOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOMNOC,
                :NEW.NOMNOC
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.CODENOC != :NEW.CODENOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.CODENOC,
                :NEW.CODENOC
            );
        ELSIF :OLD.NOMNOC != :NEW.NOMNOC THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOMNOC,
                :NEW.NOMNOC
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGEREHOTE BEFORE
    UPDATE OR DELETE ON HOTE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDHOTE != :NEW.IDHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDHOTE,
                :NEW.IDHOTE
            );
        ELSIF :OLD.NUMEROHOTE != :NEW.NUMEROHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NUMEROHOTE,
                :NEW.NUMEROHOTE
            );
        ELSIF :OLD.LIBELLEHOTE != :NEW.LIBELLEHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.LIBELLEHOTE,
                :NEW.LIBELLEHOTE
            );
        ELSIF :OLD.ANNEEHOTE != :NEW.ANNEEHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.ANNEEHOTE,
                :NEW.ANNEEHOTE
            );
        ELSIF :OLD.SAISON != :NEW.SAISON THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.SAISON,
                :NEW.SAISON
            );
        ELSIF :OLD.VILLEHOTE != :NEW.VILLEHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.VILLEHOTE,
                :NEW.VILLEHOTE
            );
        ELSIF :OLD.CODENOCHOTE != :NEW.CODENOCHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.CODENOCHOTE,
                :NEW.CODENOCHOTE
            );
        ELSIF :OLD.DATEOUVERTURE != :NEW.DATEOUVERTURE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.DATEOUVERTURE,
                :NEW.DATEOUVERTURE
            );
        ELSIF :OLD.DATEFERMETURE != :NEW.DATEFERMETURE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.DATEFERMETURE,
                :NEW.DATEFERMETURE
            );
        ELSIF :OLD.DATESCOMPETITION != :NEW.DATESCOMPETITION THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.DATESCOMPETITION,
                :NEW.DATESCOMPETITION
            );
        ELSIF :OLD.NOTES != :NEW.NOTES THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.NOTES,
                :NEW.NOTES
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDHOTE != :NEW.IDHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDHOTE,
                :NEW.IDHOTE
            );
        ELSIF :OLD.NUMEROHOTE != :NEW.NUMEROHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NUMEROHOTE,
                :NEW.NUMEROHOTE
            );
        ELSIF :OLD.LIBELLEHOTE != :NEW.LIBELLEHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.LIBELLEHOTE,
                :NEW.LIBELLEHOTE
            );
        ELSIF :OLD.ANNEEHOTE != :NEW.ANNEEHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.ANNEEHOTE,
                :NEW.ANNEEHOTE
            );
        ELSIF :OLD.SAISON != :NEW.SAISON THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.SAISON,
                :NEW.SAISON
            );
        ELSIF :OLD.VILLEHOTE != :NEW.VILLEHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.VILLEHOTE,
                :NEW.VILLEHOTE
            );
        ELSIF :OLD.CODENOCHOTE != :NEW.CODENOCHOTE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.CODENOCHOTE,
                :NEW.CODENOCHOTE
            );
        ELSIF :OLD.DATEOUVERTURE != :NEW.DATEOUVERTURE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.DATEOUVERTURE,
                :NEW.DATEOUVERTURE
            );
        ELSIF :OLD.DATEFERMETURE != :NEW.DATEFERMETURE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.DATEFERMETURE,
                :NEW.DATEFERMETURE
            );
        ELSIF :OLD.DATESCOMPETITION != :NEW.DATESCOMPETITION THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.DATESCOMPETITION,
                :NEW.DATESCOMPETITION
            );
        ELSIF :OLD.NOTES != :NEW.NOTES THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.NOTES,
                :NEW.NOTES
            );
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER TRIGGERECOMPOSITIONEQUIPE BEFORE
    UPDATE OR DELETE ON COMPOSITION_EQUIPE FOR EACH ROW
BEGIN
    IF UPDATING THEN
        IF :OLD.IDEQUIPE != :NEW.IDEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDEQUIPE,
                :NEW.IDEQUIPE
            );
        ELSIF :OLD.IDATHLETE != :NEW.IDATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Updating',
                SYSDATE,
                :OLD.IDATHLETE,
                :NEW.IDATHLETE
            );
        END IF;
    ELSIF DELETING THEN
        IF :OLD.IDEQUIPE != :NEW.IDEQUIPE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDEQUIPE,
                :NEW.IDEQUIPE
            );
        ELSIF :OLD.IDATHLETE != :NEW.IDATHLETE THEN
            INSERT INTO LOG VALUES (
                USER,
                'Deleting',
                SYSDATE,
                :OLD.IDATHLETE,
                :NEW.IDATHLETE
            );
        END IF;
    END IF;
END;