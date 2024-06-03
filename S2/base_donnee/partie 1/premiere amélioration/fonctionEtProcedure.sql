SET SERVEROUTPUT ON;

CREATE OR REPLACE FUNCTION BIOGRAPHIE(
    ID_ATHLETE NUMBER
) RETURN CLOB AS
    CHAINERETOUR CLOB;
    VARRETOUR    JSON_OBJECT_T;
BEGIN
    SELECT
        JSON_OBJECT( 'nom' VALUE A.NOMATHLETE, 'prenom' VALUE A.PRENOMATHLETE, 'surnom' VALUE SURNOM, 'genre' VALUE GENRE, 'dateNaissance' VALUE TO_CHAR(DATENAISSANCE, 'yyyy-mm-dd'), 'dateDeces' VALUE TO_CHAR(DATEDECES), 'taille' VALUE TAILLE, 'poids' VALUE POIDS, 'medaillesOr' VALUE MA.MEDAILLES_D_OR, 'medaillesArgent' VALUE MA.MEDAILLES_D_ARGENT, 'medaillesBronze' VALUE MA.MEDAILLES_BRONZE, 'medailles' VALUE MA.TOTAL_MEDAILLES ) INTO CHAINERETOUR
    FROM
        ATHLETE A
        INNER JOIN medailles_ath
    WHERE
        A.IDATHLETE = ID_ATHLETE;
    RETURN CHAINERETOUR;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20011, 'Athlete inconnu');
END BIOGRAPHIE;
/

CREATE OR REPLACE FUNCTION resultats(
    p_event_id NUMBER
) RETURN CLOB
IS
    l_json CLOB;
    l_event_count INTEGER;
    l_is_individual BOOLEAN := FALSE;
BEGIN
    -- Vérifier si l'événement existe et déterminer s'il est individuel ou collectif
    SELECT COUNT(*)
    INTO l_event_count
    FROM (
        SELECT 1 FROM PARTICIPATION_INDIVIDUELLE WHERE idevent = p_event_id
        UNION ALL
        SELECT 1 FROM PARTICIPATION_EQUIPE WHERE idevenement = p_event_id
    );

    IF l_event_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20012, 'Événement non trouvé');
    END IF;

    -- Déterminer si c'est un événement individuel
    SELECT COUNT(*)
    INTO l_event_count
    FROM PARTICIPATION_INDIVIDUELLE
    WHERE idevent = p_event_id;

    IF l_event_count > 0 THEN
        l_is_individual := TRUE;
    END IF;

    -- Construire l'objet JSON en fonction du type d'événement
    IF l_is_individual THEN
        SELECT JSON_OBJECT(
                    'résultats' VALUE JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'position' VALUE pi.resultat,
                            'athlète(s)' VALUE a.prenomathlete || ' ' || a.nomathlete,
                            'noc' VALUE pi.noc,
                            'médaille' VALUE pi.medaille
                        )
                    )
               ) INTO l_json
        FROM ATHLETE a
        JOIN PARTICIPATION_INDIVIDUELLE pi ON a.idathlete = pi.idathlete
        WHERE pi.idevent = p_event_id;
    ELSE
        SELECT JSON_OBJECT(
                    'résultats' VALUE JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'position' VALUE pe.resultat,
                            'athlète(s)' VALUE (
                                SELECT LISTAGG(a.prenomathlete || ' ' || a.nomathlete, ', ') WITHIN GROUP (ORDER BY a.nomathlete)
                                FROM ATHLETE a
                                JOIN PARTICIPATION_INDIVIDUELLE pi ON a.idathlete = pi.idathlete
                                WHERE pi.idevent = p_event_id
                            ),
                            'noc' VALUE (
                                SELECT LISTAGG(DISTINCT pi.noc, ', ') WITHIN GROUP (ORDER BY pi.noc)
                                FROM PARTICIPATION_INDIVIDUELLE pi
                                WHERE pi.idevent = p_event_id
                            ),
                            'médaille' VALUE pe.medaille
                        )
                    )
               ) INTO l_json
        FROM PARTICIPATION_EQUIPE pe
        WHERE pe.idevenement = p_event_id;
    END IF;

    RETURN l_json;

END;
/
EXECUTE DBMS_OUTPUT.put_line(BIOGRAPHIE(124));
--TODO reprendre la fonction du tout début en précisant ce que c'est la position 
CREATE OR REPLACE PROCEDURE ajouter_resultat_individuel(id_evenement, id_athlete, code_noc, resultat)
AS
    idEvenementComparaison EVENEMENT.IDEVENEMENT%TYPE;
    idAthleteComparaison ATHLETE.IDATHLETE%TYPE;
    nocComparaison NOC.CODENOC%TYPE;
    resultatMedaille MEDAILLES_ATHLETES%TYPE;
    nbReponse NUMBER;
BEGIN
    SELECT IDEVENEMENT,IDATHLETE,NOC INTO idEvenementComparaison,idAthleteComparaison,nocComparaison
    FROM EQUIPE E
    INNER JOIN PARTICIPATION_EQUIPE P
    E ON E.IDEQUIPE=pe.IDEQUIPE
    INNER JOIN COMPOSITION_EQUIPE CO ON e.IDEQUIPE = CO.IDEQUIPE
    WHERE CO.IDATHLETE=idAthleteComparaison AND e.NOC=nocComparaison AND PE.IDEVENEMENT=idEvenementComparaison;
    IF idEvenementComparaison IS NULL OR idAthleteComparaison IS NULL OR nocComparaison IS NULL THEN
        RAISE_APPLICATION_ERROR(-200001,'Athlète inexistant" (ou "Événement inexistant" ou "NOC inexistant');
    ELSE
        SELECT COUNT(*) INTO nbReponse
        FROM PARTICIPATION_INDIVIDUELLE
        WHERE idAthlete = id_athlete AND idevenement = id_evenement
        IF nbReponse > 0 THEN  
            RAISE_APPLICATION_ERROR(-20002,"Il y a deja une participation à l'événement pour cet athlte");
        END IF;
    END IF;
    SELECT COUNT(NOC) INTO nbReponse
    FROM PARTICIPATION_INDIVIDUELLE PI
    WHERE pi.NOC=noc
    IF nbReponse=0 THEN
        RAISE_APPLICATION_ERROR(-20003,"NOC incoherant")
    END IF;

    -- TODO : condition pour :
    --      - un athlète a déjà un résultat pour cet événement, on rejettera aussi
    --      - un athlète a déjà participé (on ne regardera que les participations individuelles) 
    --     dans un événement de cette édition des JO, alors son NOC doit être identique, et 
    --      on rejettera une participation sous une autre bannière.
    SELECT COUNT(NOC) INTO nbReponse
    FROM PARTICIPATION_INDIVIDUELLE PI
    WHERE pi.resultat=resultat
    IF nbReponse=1 THEN 
        RAISE_APPLICATION_ERROR(-20004,"NOC incoherant")
    END IF;
    IF resultat="1" OR resultat="=1" THEN 
        resultatMedaille:="Gold";
    ELSIF resultat="2" OR resultat="=2" THEN 
        resultatMedaille:="Silver";
    ELSIF resultat="3" OR resultat="=3" THEN 
        resultatMedaille:="Bronze";
    END IF;
    -- TODO : Enfin, on se servira du résultat pour déterminer 
    -- la médaille obtenue si l'événement est Olympic ou Intercalated

END;
/
CREATE OR REPLACE PROCEDURE ajouter_resultat_equipe(id_evenement, id_equipe, code_noc, resultat)
AS
    idEvenementComparaison EVENEMENT.IDEVENEMENT%TYPE;
    idEquipeComparaison EQUIPE.IDATHLETE%TYPE;
    nocComparaison NOC.CODENOC%TYPE;
    resultatMedaille MEDAILLES_ATHLETES%TYPE;
    nbReponse NUMBER;
BEGIN
    SELECT P.IDEVENEMENT,E.IDEQUIPE,E.NOC INTO idEvenementComparaison,idAthleteComparaison,nocComparaison
    FROM EQUIPE E
    INNER JOIN PARTICIPATION_EQUIPE P
    E ON E.IDEQUIPE=pe.IDEQUIPE
    INNER JOIN COMPOSITION_EQUIPE CO ON e.IDEQUIPE = CO.IDEQUIPE
    WHERE CO.IDATHLETE=idAthleteComparaison AND e.NOC=nocComparaison AND PE.IDEVENEMENT=idEvenementComparaison;
    IF idEvenementComparaison IS NULL OR idAthleteComparaison IS NULL OR nocComparaison IS NULL THEN
        RAISE_APPLICATION_ERROR(-200001,'Equipe inexistant" (ou "Événement inexistant');
    ELSE
        SELECT COUNT(*) INTO nbReponse
        FROM PARTICIPATION_INDIVIDUELLE
        WHERE idAthlete = id_athlete AND idevenement = id_evenement
        IF nbReponse > 0 THEN  
            RAISE_APPLICATION_ERROR(-20002,"Équipe déjà classée");
        END IF;
    END IF;

    -- TODO : condition pour :
    --      - un athlète a déjà un résultat pour cet événement, on rejettera aussi
    --      - un athlète a déjà participé (on ne regardera que les participations individuelles) 
    --     dans un événement de cette édition des JO, alors son NOC doit être identique, et 
    --      on rejettera une participation sous une autre bannière.
    SELECT COUNT(NOC) INTO nbReponse
    FROM PARTICIPATION_INDIVIDUELLE PI
    WHERE pi.resultat=resultat
    IF nbReponse=1 THEN 
        RAISE_APPLICATION_ERROR(-20003,"Position déjà occupée")
    END IF;
    IF resultat="1" OR resultat="=1" THEN 
        resultatMedaille:="Gold";
    ELSIF resultat="2" OR resultat="=2" THEN 
        resultatMedaille:="Silver";
    ELSIF resultat="3" OR resultat="=3" THEN 
        resultatMedaille:="Bronze";
    END IF;
    -- TODO : Enfin, on se servira du résultat pour déterminer 
    -- la médaille obtenue si l'événement est Olympic ou Intercalated

END;
/