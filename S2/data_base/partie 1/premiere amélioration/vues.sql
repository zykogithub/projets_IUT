--vue 1
CREATE OR REPLACE VIEW MEDAILLES_ATHLETES AS
SELECT idAthlete, nomAthlete, prenomAthlete,
SUM(medaillesOr) AS MEDAILLES_D_OR,
SUM(medaillesArgent) AS MEDAILLES_D_ARGENT,
SUM(medaillesBronze) AS MEDAILLES_DE_BRONZE,
SUM(medaillesOr + medaillesArgent + medaillesBronze) AS TOTAL_MEDAILLES
FROM
(SELECT A.idAthlete, prenomAthlete, nomAthlete, 
COUNT(CASE WHEN PI.medaille = 'Gold' THEN 1 ELSE NULL END) AS medaillesOr,
COUNT(CASE WHEN PI.medaille = 'Silver' THEN 1 ELSE NULL END) AS medaillesArgent,
COUNT(CASE WHEN PI.medaille = 'Bronze' THEN 1 ELSE NULL END) AS medaillesBronze
FROM Athlete A
LEFT JOIN Participation_Individuelle PI ON A.idAthlete = PI.idAthlete
GROUP BY A.idAthlete, prenomAthlete, nomAthlete

UNION ALL

SELECT A.idAthlete, prenomAthlete, nomAthlete, 
COUNT(CASE WHEN PE.medaille = 'Gold' THEN 1 ELSE NULL END) AS medaillesOr,
COUNT(CASE WHEN PE.medaille = 'Silver' THEN 1 ELSE NULL END) AS medaillesArgent,
COUNT(CASE WHEN PE.medaille = 'Bronze' THEN 1 ELSE NULL END) AS medaillesBronze
FROM Athlete A
LEFT JOIN Composition_Equipe CE ON A.idAthlete = CE.idAthlete
INNER JOIN Participation_Equipe PE ON CE.idEquipe = PE.idEquipe
GROUP BY A.idAthlete, prenomAthlete, nomAthlete) combined_results
GROUP BY idAthlete, prenomAthlete, nomAthlete
ORDER BY MEDAILLES_D_OR DESC, MEDAILLES_D_ARGENT DESC,
         MEDAILLES_DE_BRONZE DESC, TOTAL_MEDAILLES DESC,
         nomAthlete, prenomAthlete, idAthlete;
-- vue 2
CREATE OR REPLACE VIEW MEDAILLES_NOC ("CODENOC", "NOMNOC", "MEDAILLES_D_OR", "MEDAILLES_D_ARGENT", "MEDAILLES_BRONZE", "TOTAL_MEDAILLES") AS 
  SELECT 
    N.CODENOC,
    N.NOMNOC,
    SUM(CASE WHEN PE.MEDAILLE = 'Gold' THEN 1 ELSE 0 END) AS MEDAILLES_D_OR,
    SUM(CASE WHEN PE.MEDAILLE = 'Silver' THEN 1 ELSE 0 END) AS MEDAILLES_D_ARGENT,
    SUM(CASE WHEN PE.MEDAILLE = 'Bronze' THEN 1 ELSE 0 END) AS MEDAILLES_BRONZE,
    COUNT(PE.MEDAILLE) AS TOTAL_MEDAILLES
FROM 
    NOC N
INNER JOIN 
    EQUIPE E ON N.CODENOC = E.IDEQUIPE
INNER JOIN
    PARTICIPATION_EQUIPE PE ON E.IDEQUIPE = PE.IDEQUIPE
GROUP BY 
    N.CODENOC, N.NOMNOC
ORDER BY 
    MEDAILLES_D_OR DESC, MEDAILLES_D_ARGENT DESC, MEDAILLES_BRONZE DESC, TOTAL_MEDAILLES DESC, 
    N.CODENOC, N.NOMNOC
;
