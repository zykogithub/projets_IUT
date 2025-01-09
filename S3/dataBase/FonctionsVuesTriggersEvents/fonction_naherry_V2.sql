DELIMITER //

CREATE OR REPLACE PROCEDURE demarrer_vote(
    IN p_id_proposition INT, 
    IN p_id_type_scrutin INT,     
    IN p_date_fin_vote DATE        
)
BEGIN
    INSERT INTO vote (date_fin_vote, id_proposition, id_scrutin)
    VALUES (p_date_fin_vote, p_id_proposition, p_id_type_scrutin);
END //

CREATE OR REPLACE PROCEDURE enregistrer_choix_vote(
    IN p_id_vote INT,
    IN p_libelle_choix VARCHAR(50)      
)
BEGIN
    DECLARE max_id_choix INT;

    SELECT IFNULL(MAX(id_choix), 0) INTO max_id_choix
    FROM choix
    WHERE id_vote = p_id_vote;

    INSERT INTO choix (id_vote, id_choix, libelle_choix)
    VALUES (p_id_vote, max_id_choix + 1, p_libelle_choix);
END //


CREATE OR REPLACE PROCEDURE enregistrer_vote(
    IN p_id_vote INT,
    IN p_id_choix INT,
    IN p_id_groupe INT,
    IN p_id_internaute INT
)
BEGIN
    INSERT INTO vote_membre (id_vote, id_choix, id_groupe, id_internaute)
    VALUES (p_id_vote, p_id_choix, p_id_groupe, p_id_internaute);
END //

CREATE OR REPLACE FUNCTION obtenir_budget_proposition(
    IN p_id_proposition INT
)
RETURNS INT
BEGIN
    DECLARE budget_proposition INT;
    SELECT budget INTO budget_proposition
    FROM proposition
    WHERE id_proposition = p_id_proposition;
    RETURN budget_proposition;
END //

CREATE OR REPLACE FUNCTION budget_utilise_theme(
    IN p_id_thematique INT,
    IN p_id_groupe INT
)
RETURNS INT
BEGIN
    DECLARE sommeDesPropositions INT;
    SELECT SUM(budget) INTO sommeDesPropositions
    FROM proposition
    WHERE id_thematique = p_id_thematique AND id_groupe = p_id_groupe;
    RETURN sommeDesPropositions;
END //

CREATE OR REPLACE FUNCTION budget_utilise_groupe(
    IN p_id_groupe INT
)
RETURNS INT
BEGIN
    DECLARE sommeDesThematiques INT;
    SELECT SUM(budget_utilise_theme(id_thematique,p_id_groupe)) INTO sommeDesThematiques
    FROM theme_groupe
    WHERE id_groupe = p_id_groupe;
    RETURN sommeDesThematiques;
END //

CREATE OR REPLACE PROCEDURE modifier_config_notifs(
    IN p_id_notif INT,
    IN p_nouvelle_config BLOB
)
BEGIN
    UPDATE notification
    SET config_notifs = p_nouvelle_config
    WHERE id_notification = p_id_notif;
END // 

CREATE OR REPLACE FUNCTION votes_effectues(
    IN p_id_vote INT,
    IN p_id_groupe INT
)
RETURNS BLOB
BEGIN
    DECLARE result JSON;
    DECLARE result_blob BLOB;

    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'prenom', i.prenom_internaute,
            'nom', i.nom_internaute,
            'libelleChoix', c.libelle_choix
        )
    ) INTO result
    FROM vote_membre vm
    INNER JOIN choix c ON vm.id_choix = c.id_choix AND vm.id_vote = c.id_vote
    INNER JOIN internaute i ON vm.id_internaute = i.id_internaute
    WHERE vm.id_vote = p_id_vote;

    SET result_blob = CONVERT(result USING utf8mb4);
    RETURN result_blob;
END //

DELIMITER ;