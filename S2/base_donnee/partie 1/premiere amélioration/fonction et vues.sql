CREATE OR REPLACE VIEW MEDAILLES_ATHLETES
AS  
    SELECT pi.idathlete,nomAthlete,prenomAthlete,
    COUNT(CASE WHEN medaille='Gold' THEN medaille END) AS nbMedailleOr,COUNT(CASE WHEN medaille='Silver' THEN medaille END) AS nbMedailleArgent,
    COUNT(CASE WHEN medaille='Bronze' THEN medaille END) AS nbMedailleBronze,COUNT(medaille) AS nbMedaille
    FROM participation_individuelle PI
    INNER JOIN athlete A ON pi.idathlete = a.idathlete
    GROUP BY pi.idathlete,nomAthlete,prenomAthlete
    ORDER BY nbMedailleOr DESC,nbMedailleArgent DESC,nbMedailleBronze DESC,nbMedaille DESC,nomAthlete,prenomAthlete,pi.idathlete;
CREATE OR REPLACE VIEW MEDAILLES_NOC
AS
    SELECT  e.noc,e.nomequipe,
    COUNT(CASE WHEN medaille='Gold' THEN medaille END) AS nbMedailleOr,COUNT(CASE WHEN medaille='Silver' THEN medaille END) AS nbMedailleArgent,
    COUNT(CASE WHEN medaille='Bronze' THEN medaille END) AS nbMedailleBronze,COUNT(medaille) AS nbMedaille
    FROM equipe E
    INNER JOIN participation_equipe PC ON pc.idequipe = e.idequipe
    GROUP BY e.noc,e.nomequipe
    ORDER BY nbMedailleOr DESC,nbMedailleArgent DESC,nbMedailleBronze DESC,nbMedaille DESC,e.noc;

SELECT *
FROM medailles_athletes;
