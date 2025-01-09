DELIMITER //

CREATE OR REPLACE PROCEDURE ajouter_proposition(
    IN p_titre_proposition VARCHAR(50),
    IN p_id_thematique INT,
    IN p_description_proposition TEXT,
    IN p_budget INT,
    IN p_id_groupe INT
)
BEGIN
    INSERT INTO proposition (
        titre_proposition,
        description_proposition,
        date_publication,
        budget,
        id_groupe,
        id_thematique,
        signalee
    ) VALUES (
        p_titre_proposition,
        p_description_proposition,
        CURDATE(),
        p_budget,
        p_id_groupe,
        p_id_thematique,
        FALSE
    );
END //

CREATE OR REPLACE PROCEDURE demander_vote(
    IN p_id_proposition INT,
    IN p_id_internaute INT
)
BEGIN
    INSERT INTO demande_vote (
        id_proposition,
        id_groupe,
        id_internaute
    ) VALUES (
        p_id_proposition,
        (SELECT id_groupe FROM proposition WHERE id_proposition = p_id_proposition),
        p_id_internaute
    );
END //

CREATE OR REPLACE PROCEDURE enregistrer_commentaire(
    IN p_id_proposition INT,
    IN p_id_internaute INT,
    IN p_contenu_message TEXT
)
BEGIN
    INSERT INTO commentaire (
        contenu_message,
        horodatage,
        signale,
        id_groupe,
        Id_internaute,
        Id_proposition
    ) VALUES (
        p_contenu_message,
        CURDATE(),
        FALSE,
        (SELECT id_groupe FROM proposition WHERE Id_proposition = p_id_proposition),
        p_id_internaute,
        p_id_proposition
    );
END //

CREATE OR REPLACE PROCEDURE reagir_commentaire(
    IN p_id_commentaire INT,
    IN p_id_reaction INT,
    IN p_id_internaute INT
)
BEGIN
    INSERT INTO reaction_commentaire (
        id_groupe,
        id_internaute,
        id_commentaire,
        id_reaction
    ) VALUES (
        (SELECT id_groupe FROM commentaire WHERE id_commentaire = p_id_commentaire),
        p_id_internaute,
        p_id_commentaire,
        p_id_reaction
    );
END //

CREATE OR REPLACE PROCEDURE reagir_proposition(
    IN p_id_proposition INT,
    IN p_id_reaction INT,
    IN p_id_internaute INT
)
BEGIN
    INSERT INTO reaction_proposition (
        id_groupe,
        id_internaute,
        id_proposition,
        id_reaction
    ) VALUES (
        (SELECT id_groupe FROM proposition WHERE id_proposition = p_id_proposition),
        p_id_internaute,
        p_id_proposition,
        p_id_reaction
    );
END //

CREATE OR REPLACE PROCEDURE signaler_commentaire(
    IN p_id_commentaire INT
)
BEGIN
	DECLARE nbs INT;
	SELECT nb_signalement INTO nbs
    FROM commentaire
    WHERE id_commentaire=p_id_commentaire;
    
    UPDATE commentaire
    SET nb_signalement = nbs+1
    WHERE id_commentaire = p_id_commentaire;
END //

CREATE OR REPLACE PROCEDURE signaler_groupe(
    IN p_id_groupe INT
)
BEGIN
	DECLARE nbs INT;
	SELECT nb_signalement INTO nbs
    FROM groupe
    WHERE id_groupe=p_id_groupe;
    
    UPDATE groupe
    SET nb_signalement = nbs+1
    WHERE id_groupe = p_id_groupe;
END //

CREATE OR REPLACE PROCEDURE signaler_proposition(
    IN p_id_proposition INT
)
BEGIN
	DECLARE nbs INT;
	SELECT nb_signalement INTO nbs
    FROM proposition
    WHERE id_proposition=p_id_proposition;
    
    UPDATE groupe
    SET nb_signalement = nbs+1
    WHERE id_proposition = p_id_proposition;
END //

CREATE OR REPLACE PROCEDURE supprimer_commentaire(
    IN p_id_commentaire INT
)
BEGIN
    DELETE FROM commentaire
    WHERE id_commentaire = p_id_commentaire;
END //

CREATE OR REPLACE PROCEDURE supprimer_groupe(
    IN p_id_groupe INT
)
BEGIN
    DELETE FROM groupe
    WHERE id_groupe = p_id_groupe;
END //

CREATE OR REPLACE PROCEDURE supprimer_proposition(
    IN p_id_proposition INT
)
BEGIN
    DELETE FROM proposition
    WHERE id_proposition = p_id_proposition;
END //

DELIMITER ;