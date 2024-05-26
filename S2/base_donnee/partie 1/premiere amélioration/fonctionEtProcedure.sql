SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION 
biographie(id_athlete NUMBER)
RETURN CLOB
AS
    chaineRetour CLOB;
    varRetour JSON_OBJECT_T;
BEGIN
    SELECT JSON_OBJECT(
               'nom' VALUE nomAthlete,
               'prenom' VALUE prenomAthlete,
               'surnom' VALUE surnom,
               'genre' VALUE genre,
               'dateNaissance' VALUE dateNaissance,
               'dateDeces' VALUE dateDeces,
               'taille' VALUE taille,
               'poids' VALUE poids,
               'medaillesOr' VALUE nbmedailleor,
               'medaillesArgent' VALUE nbmedailleargent,
               'medaillesBronze' VALUE nbmedaillebronze,
               'medailles' VALUE nbmedaille
           )
    INTO chaineRetour
    FROM medailles_athletes
    WHERE idAthlete = id_athlete;
    RETURN chaineRetour;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20011,'Athlète inconnu');
END biographie;
/
EXECUTE dbms_output.put_line(biographie(1000));

--TODO : affecter la ligne courante à chaine retour
-- TODO : trouver la bonne synthaxe pour l'instruction ci-dessus
-- TODO : trouver la bonne synthaxe pour l'instruction ci-dessus
    
CREATE OR REPLACE FUNCTION 
resultats(id_evenement evenement.idEvenement%TYPE)
RETURN CLOB
AS
    chaineRetour CLOB;
    varRetour JSON_OBJECT_T;
BEGIN
    SELECT JSON_OBJECT(
               
           )
    INTO chaineRetour
    FROM evenment E
    INNER JOIN participation_individuelle PI ON e.idEvenement = pi.idevent
    INNER JOIN participation_equipe PE ON e.idEvenement = pe.idEvenement 
    INNER JOIN equipe Eq ON pe.idEquipe = eq.idEquipe
    INNER JOIN athlete A ON pi.idathlete = a.idathlete
    WHERE idAthlete = id_athlete;
    RETURN chaineRetour;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20011,'Athlète inconnu');
END biographie;
/
EXECUTE dbms_output.put_line(biographie(1000));

