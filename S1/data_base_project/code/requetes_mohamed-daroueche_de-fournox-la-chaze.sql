--donner les habits de taille s vendues
SELECT num_de_licencie,nomhabit
FROM habit H
INNER JOIN habillement HL ON numhabit = hl.num_de_licencie
INNER JOIN disponibilite D ON h.numhabit = d.num_habit
WHERE d.nom_Taille = 'S';

--donners les groupes qui ont au moins un joueur hors de l'aglomération dunkerquoise
SELECT numgroupe,numero_groupe
FROM groupe G
INNER JOIN licencie L ON g.categorie = l.categorie
WHERE dunkerquois=false;

--donner les joueurs ayant fait au moins 3 passes décésives lors des 5 derniers matchs
SELECT numdelicencie,nom_licencie,prenom_licencie
FROM licencie
INNER JOIN bilan_individuel BI ON numdelicencie = bi.num_licencie
INNER JOIN statistique_individuel SI ON stat_individuel = numstatistiqueIndividuel
INNER JOIN bilan_match_individuel BMI ON si.numstatistiqueIndividuel = bmi.num_statistique_indivduel 
INNER JOIN match_foot MF ON bmi.num_match = numMatchFoot
WHERE description_statistique_individuel = 'passe décisive'
GROUP BY numdelicencie,numstatistiqueIndividuel,numMatchFoot
HAVING COUNT(numdelicencie)>=3 AND COUNT (numMatchFoot)=5;

-- donner les villes qui acceuillent plus de 2 matchs
SELECT nom_ville
FROM ville
INNER JOIN match_foot MF ON numville = lieu_du_match
GROUP BY numville, numMatchFoot
HAVING COUNT(numMatchFoot)>2;

-- donner les groupes qui ont plus de 50% de possession en moyenne
SELECT numgroupe,numero_groupe
FROM groupe G
INNER JOIN bilan_collectif BC ON g.numgroupe = bc.num_groupe
INNER JOIN statistique_collective SC ON bc.stat_collective = sc.numStatistiqueCollective
INNER JOIN bilan_match_collective BMC ON sc.numStatistiqueCollective = bmc.num_statisqtique_collective 
WHERE description_statistique_collective = 'possession'
GROUP BY sc.numStatistiqueCollective, valeur_statistique_collective
HAVING AVG(valeur_statistique_collective)>50;

--donner les catégories qui ont plus de 2 groupes
SELECT numcategorie,nom_categorie
FROM categorie C 
INNER JOIN groupe G ON c.numcategorie = g.categorie
GROUP BY numcategorie,numgroupe
HAVING COUNT(numgroupe)>2;

--calculer le nombre de séance râté pour chaque licencié

SELECT numdelicencie,COUNT(presence)-COUNT(nb_seance) AS "nb séance râté"
FROM licencie L
INNER JOIN categorie C ON l.categorie = c.numCategorie;

--donner la moyenne de séance raté par catégorie

SELECT numcategorie, AVG(presence)
FROM categorie C
INNER JOIN licencie L ON c.numcategorie = l.categorie
GROUP BY numcategorie;

--donner le nombre de catégorie qui ne peuvent plus acceuillir de nouveaux joueurs
SELECT COUNT(numcategorie) AS "nombre categorie"
FROM categorie C
INNER JOIN licencie L ON c.numcategorie = l.categorie
GROUP BY numcategorie
HAVING effectif_maximum-COUNT(numdelicencie)=0;

--donner les joueurs de la catégorie U12 qui ont non acquis pour l'aptitude savoir faire de fair-play
SELECT numdelicencie,nom_licencie, prenom_licencie
FROM licencie L
INNER JOIN bilan_aptitude BA ON l.numdelicencie = ba.num_de_licencie
INNER JOIN niveau_evaluation NE ON ba.niveauAptitude = numNiveauEvaluation
INNER JOIN aptitude_mental M ON ba.niveauAptitude = m.numAptitudeMental
WHERE nom_niveau_evaluation='non acquis' 
    AND categorie = 'U12' 
    AND description_aptitude_mental = 'savoir faire de fair-play';

-- donner les joueurs de la catégorie U18 qui ont très bien acquis le tir de cou-franc
SELECT numdelicencie,nom_licencie, prenom_licencie
FROM licencie L
INNER JOIN bilan_capacite BC ON l.numdelicencie = bc.num_de_licencie
INNER JOIN  capacite_technique  ON capacite = numCapaciteTechnique
INNER JOIN niveau_acquisition  ON niveauTechnique = numNiveauAcquisition
WHERE nom_niveau_acquisition='très bien acquis' 
    AND categorie = 'U18' 
    AND nom_capacite_technique = 'cou-franc';
    
--donner le nombre de victoire des U18
SELECT COUNT(numMatchFoot) AS "nombre de victoire"
FROM match_foot MF
INNER JOIN bilan_match_collective BMC ON mf.numMatchFoot = bmc.num_match
INNER JOIN statistique_collective SC ON bmc.num_statisqtique_collective= sc.numstatistiqueCollective
INNER JOIN bilan_collectif  ON sc.numStatistiqueCollective = stat_collective
INNER JOIN groupe ON num_groupe = numgroupe
INNER JOIN categorie ON categorie = numcategorie
WHERE nom_categorie = 'U18'
GROUP BY nummatchfoot
HAVING INSTR('victoire',description_match)!=0; --on suppose que pour chaque victoire, la valeur de description_match contient la chaine victoire
--ce qui semble cohérent pour décrire un match

--donner le nombre de fille entre les U15 et les U18
SELECT COUNT(DISTINCT(numdelicencie)) AS "nombrr de fille"
FROM licencie
WHERE sexe='femme' AND (categorie = 'U15' 
                        OR categorie = 'U16' 
                        OR categorie = 'U17' 
                        OR categorie = 'U18');
--donner la ville ayant le plus de membre hors de l'aglomération dunkerquoise
SELECT numville,nom_ville, COUNT(numdelicencie) AS "nb_de_licencie"
FROM ville 
INNER JOIN licencie ON numville = lieu_de_vie
WHERE NOT dunkerquois
GROUP BY numville, nom_ville
ORDER BY nb_de_licencie DESC
LIMIT 1; --étant donné que la table est trié du plus grand nombre au plus petit nombre, 
--il suffit d'affihcer le premier de la table pour avoir le maximum


--donner le nombre d'achat de chaque licencié
SELECT COUNT(num_habit) AS "habit acheté"
FROM habillement
INNER JOIN licencie ON num_de_licencie = numdelicencie
GROUP BY numdelicencie, nom_licencie,prenom_licencie;


-- donner le nombre de personne n'ayant acheté aucun habit
SELECT COUNT(numdelicencie) AS "nb de licencie"
FROM habillement
INNER JOIN licencie ON num_de_licencie = numdelicencie
GROUP BY numdelicencie, nom_licencie,prenom_licencie
HAVING COUNT(num_habit) = 0;

-- donner les joueurs n'ayant fait aucune séance sur un mois
SELECT numdelicencie, nom_licencie,prenom_licencie
FROM licencie
INNER JOIN presence ON presence = numPresence
INNER JOIN categorie ON categorie = numCategorie
WHERE nombre_presence = 0;

-- donner les joueurs en double double (+10 buts, +10 passes)
SELECT numdelicencie, nom_licencie,prenom_licencie
FROM licencie
INNER JOIN bilan_individuel ON numdelicencie = num_licencie
INNER JOIN statistique_individuel SI ON stat_individuel = si.numstatistiqueindividuel
INNER JOIN bilan_match_individuel BMI ON si.numstatistiqueIndividuel = bmi.num_statistique_indivduel
GROUP BY numdelicencie, nom_licencie,prenom_licencie
HAVING SUM(CASE WHEN description_statistique_individuel = 'but' THEN valeur_statistique_individuel END)>=10 
        AND  
        SUM(CASE WHEN description_statistique_individuel = 'passe décisive' THEN valeur_statistique_individuel END)>=10; 
--on effectue la sommme nuniquemet si la valeur de description_statistique_individuel vaut 'passe décisive' ou 'but'

-- donner les joeurs pouvant être de bon capitaine 
--(aptitude fair-play, encouragement à très bien acquis, la moyenne de ses capacités technques doit être à bien acquis)
SELECT numdelicencie, nom_licencie,prenom_licencie
FROM licencie
INNER JOIN  bilan_aptitude ON numdelicencie = num_de_licencie
INNER JOIN niveau_evaluation ON niveauaptitude= numNiveauEvaluation
INNER JOIN aptitude_mental ON aptitude = numAptitudeMental
WHERE nom_niveau_evaluation='très bien acquis' AND 
    (description_aptitude_mental = 'savoir faire de fair-play'
    OR description_aptitude_mental = 'savoir encourager')
GROUP BY numdelicencie, nom_licencie,prenom_licencie
HAVING AVG(nom_niveau_evaluation)= 'bien acquis';

-- un entraineur souhaite constitué la meilleure équipe pour son prochain match
--donner les requetes pour que cela soit possible en listant les meilleurs joueurs à chaque capacité technique


SELECT numdelicencie, nom_licencie,prenom_licencie,nom_capacite_technique,nom_niveau_acquisition
FROM licencie
INNER JOIN  bilan_capacite ON numdelicencie = num_de_licencie
INNER JOIN niveau_acquisition ON capacite = numNiveauAcquisition
INNER JOIN capacite_technique ON niveauTechnique = numCapaciteTechnique
WHERE nom_capacite_technique = 'défense'
ORDER BY nom_niveau_acquisition DESC
LIMIT 1;

--et on répète cela pour toutes les capacités techniques




