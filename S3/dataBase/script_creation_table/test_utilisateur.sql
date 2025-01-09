use `saes3-mmarti32`;
SELECT *
    FROM internaute I
    INNER JOIN infos_membre ifo ON ifo.id_internaute = I.id_internaute
    INNER JOIN role R ON ifo.id_role = R.id_role
    INNER JOIN groupe G ON ifo.id_groupe = G.id_groupe
    WHERE G.id_groupe=5 AND R.id_role=3;
SELECT *
        FROM infos_membre IFO
        INNER JOIN role R ON IFO.id_role = R.id_role
        WHERE R.id_role = 3 AND id_internaute=9;
