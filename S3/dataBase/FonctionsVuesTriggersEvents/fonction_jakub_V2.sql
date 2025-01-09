DELIMITER //

CREATE OR REPLACE FUNCTION infos_internaute (p_id_internaute INT)
RETURNS BLOB
BEGIN
    DECLARE result JSON;
    DECLARE result_blob BLOB;
    
    SELECT JSON_OBJECT(
        'id', id_internaute,
        'nom', nom_internaute,
        'prenom', prenom_internaute,
        'adresse', adresse_postale,
        'email', courriel
    )
    INTO result
    FROM internaute
    WHERE id_internaute = p_id_internaute;
    
    SET result_blob = CONVERT(result USING utf8mb4);  
    RETURN result_blob;
END //

CREATE OR REPLACE FUNCTION groupes_utilisateur (p_id_internaute INT)
RETURNS BLOB
BEGIN
    DECLARE result JSON;
    DECLARE result_blob BLOB;
    
    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'id_groupe', g.id_groupe,
            'nom', g.nom_groupe,
            'couleur', g.couleur_groupe,
            'image', g.image
        )
    )
    INTO result
    FROM groupe g
    JOIN infos_membre im ON g.id_groupe = im.id_groupe
    WHERE im.id_internaute = p_id_internaute;
    
    SET result_blob = CONVERT(result USING utf8mb4);  
    RETURN result_blob;
END //

CREATE OR REPLACE FUNCTION rechercher_internaute (nom VARCHAR(255), prenom VARCHAR(255))
RETURNS BLOB
BEGIN
    DECLARE result JSON;
    DECLARE result_blob BLOB;
    
    SELECT JSON_ARRAYAGG(
        JSON_OBJECT(
            'id', id_internaute
        )
    )
    INTO result
    FROM internaute
    WHERE nom_internaute = nom AND prenom_internaute = prenom;
    
    SET result_blob = CONVERT(result USING utf8mb4);  
    RETURN result_blob;
END //

CREATE OR REPLACE PROCEDURE creer_utilisateur (
    IN p_nom VARCHAR(255),
    IN p_prenom VARCHAR(255),
    IN p_adr_postal VARCHAR(255),
    IN p_mail VARCHAR(255),
    IN p_hmdp VARCHAR(255)
)
BEGIN
    INSERT INTO internaute (
        nom_internaute, prenom_internaute, adresse_postale, courriel, hashageMDP
    )
    VALUES (p_nom, p_prenom, p_adr_postal, p_mail, p_hmdp);
END //

CREATE OR REPLACE PROCEDURE modifs_infos_internaute (
    IN p_id_internaute INT,
    IN p_nom VARCHAR(255),
    IN p_prenom VARCHAR(255),
    IN p_adr_postal VARCHAR(255),
    IN p_mail VARCHAR(255),
    IN p_hmdp VARCHAR(255)
)
BEGIN
    UPDATE internaute
    SET nom_internaute = p_nom,
        prenom_internaute = p_prenom,
        adresse_postale = p_adr_postal,
        courriel = p_mail,
        hashageMDP = p_hmdp
    WHERE id_internaute = p_id_internaute;
END //

CREATE OR REPLACE PROCEDURE supprimer_internaute (IN p_id_internaute INT)
BEGIN
    DELETE FROM internaute WHERE id_internaute = p_id_internaute;
END //

CREATE PROCEDURE creer_groupe (
    IN p_nom VARCHAR(255),
    IN p_couleur VARCHAR(50),
    IN p_image VARCHAR(50),
    IN p_budget INT,
    IN p_nbj_dft_vote INT,
    IN p_nbj_dft_discuss INT
)
BEGIN
    INSERT INTO groupe (nom_groupe, couleur_groupe, image, budget, nbj_dft_vote, nbj_dft_discuss)
    VALUES (p_nom, p_couleur, p_image, p_budget, p_nbj_dft_vote, p_nbj_dft_discuss);
END //

CREATE OR REPLACE PROCEDURE ajouter_membre (
    IN p_id_groupe INT,
    IN p_id_internaute INT
)
BEGIN
    INSERT INTO notification (config_notifs)
    VALUES (NULL);
    INSERT INTO infos_membre (id_groupe, id_internaute, id_notification, id_role)
    VALUES (p_id_groupe, p_id_internaute, (SELECT MAX(id_notification) FROM notification), 1);
END //

CREATE OR REPLACE TRIGGER verifier_email_unique_avant_insert
BEFORE INSERT ON internaute
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM internaute WHERE courriel = NEW.courriel) > 0 THEN
        SIGNAL SQLSTATE '46001'
        SET MESSAGE_TEXT = 'Erreur : cet email est deja utilise par un autre utilisateur.';
    END IF;
END //

CREATE OR REPLACE TRIGGER verifier_nom_groupe_unique_avant_insert
BEFORE INSERT ON groupe
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM groupe WHERE nom_groupe = NEW.nom_groupe) > 0 THEN
        SIGNAL SQLSTATE '46002'
        SET MESSAGE_TEXT = 'Erreur : un groupe avec ce nom existe deja.';
    END IF;
END //

DELIMITER ;