DELIMITER //

CREATE EVENT update_vote_end_date
ON SCHEDULE EVERY 1 DAY
STARTS '2024-12-21 00:00:00'
DO
BEGIN
    UPDATE vote v
    INNER JOIN proposition p ON v.id_proposition = p.id_proposition
    INNER JOIN groupe g ON g.id_groupe = p.id_groupe
    SET v.date_fin_vote = CURDATE() + INTERVAL (SELECT nbj_dft_vote FROM g2 WHERE g2.id_groupe=g.id_groupe) DAY
    WHERE v.date_fin_vote IS NULL
    AND p.date_publication + INTERVAL (SELECT nbj_dft_discuss FROM g3 WHERE g3.id_groupe=g.id_groupe) DAY <= CURDATE();
END//

DELIMITER ;