INSERT INTO proposition (titre_proposition, description_proposition, date_publication, budget, id_groupe, id_thematique) VALUES
('Proposition G5.1', 'Le titre est assez clair, vous n''ête pas d''accord ?', (SUBDATE(CURDATE(),77)), 153800, 5, 2),
('Proposition G5.2', 'A-t-on vraiment besoin de plus de détails ?', (SUBDATE(CURDATE(),255)), 49120, 5, 2),
('Proposition G5.3', 'Et si on évitait de parler pour ne rien dire ?', (SUBDATE(CURDATE(),155)), 26100, 5, 2),
('Proposition G5.4', 'Le titre est assez clair, vous n''ête pas d''accord ?', (SUBDATE(CURDATE(),70)), 38900, 5, 1),
('Proposition G5.5', 'A-t-on vraiment besoin de plus de détails ?', (SUBDATE(CURDATE(),190)), 71450, 5, 2),
('Proposition G5.6', 'Et si on évitait de parler pour ne rien dire ?', (SUBDATE(CURDATE(),139)), 26950, 5, 1),
('Proposition G5.7', 'Le titre est assez clair, vous n''ête pas d''accord ?', (SUBDATE(CURDATE(),306)), 19800, 5, 3),
('Proposition G5.8', 'A-t-on vraiment besoin de plus de détails ?', (SUBDATE(CURDATE(),98)), 10250, 5, 2),
('Proposition G5.9', 'Et si on évitait de parler pour ne rien dire ?', (SUBDATE(CURDATE(),122)), 16750, 5, 4),
('Proposition G5.10', 'Le titre est assez clair, vous n''ête pas d''accord ?', (SUBDATE(CURDATE(),80)), 150260, 5, 1),
('Proposition G5.11', 'A-t-on vraiment besoin de plus de détails ?', (SUBDATE(CURDATE(),100)), 10500, 5, 1),
('Proposition G5.12', 'Et si on évitait de parler pour ne rien dire ?', (SUBDATE(CURDATE(),45)), 50380, 5, 1);

INSERT INTO vote (date_fin_vote, id_proposition, id_scrutin) VALUES
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 16), INTERVAL 44 DAY)), 16, 1),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 17), INTERVAL 44 DAY)), 17, 1),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 18), INTERVAL 44 DAY)), 18, 2),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 19), INTERVAL 44 DAY)), 19, 1),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 20), INTERVAL 44 DAY)), 20, 3),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 21), INTERVAL 44 DAY)), 21, 1),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 22), INTERVAL 44 DAY)), 22, 1),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 23), INTERVAL 44 DAY)), 23, 3),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 24), INTERVAL 44 DAY)), 24, 2),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 25), INTERVAL 44 DAY)), 25, 1),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 26), INTERVAL 44 DAY)), 26, 2),
((SELECT DATE_ADD((SELECT date_publication FROM proposition P WHERE P.id_proposition = 27), INTERVAL 44 DAY)), 27, 3);

INSERT INTO choix (id_vote, id_choix, libelle_choix) VALUES
(5, 1, 'Oui'),
(5, 2, 'Non'),
(6, 1, 'Pour'),
(6, 2, 'Contre'),
(7, 1, 'Choix 1'),
(7, 2, 'Choix 2'),
(7, 3, 'Choix 3'),
(8, 1, 'Oui'),
(8, 2, 'Non'),
(9, 1, 'Choix 1'),
(9, 2, 'Choix 2'),
(9, 3, 'Choix 3'),
(9, 4, 'Choix 4'),
(10, 1, 'Oui'),
(10, 2, 'Non'),
(11, 1, 'Pour'),
(11, 2, 'Contre'),
(12, 1, 'Choix 1'),
(12, 2, 'Choix 2'),
(12, 3, 'Choix 3'),
(12, 4, 'Choix 4'),
(13, 1, 'Choix 1'),
(13, 2, 'Choix 2'),
(13, 3, 'Choix 3'),
(14, 1, 'Oui'),
(14, 2, 'Non'),
(15, 1, 'Choix 1'),
(15, 2, 'Choix 2'),
(15, 3, 'Choix 3'),
(16, 1, 'Choix 1'),
(16, 2, 'Choix 2'),
(16, 3, 'Choix 3'),
(16, 4, 'Choix 4');

INSERT INTO vote_membre (id_vote, id_choix, id_groupe, id_internaute) VALUES
(5, 1, 5, 8),
(5, 1, 5, 9),
(5, 1, 5, 15),
(5, 1, 5, 23),
(5, 1, 5, 33),
(5, 1, 5, 50),
(5, 1, 5, 52),
(5, 1, 5, 54),
(5, 1, 5, 61),
(5, 1, 5, 72),
(5, 2, 5, 76),
(5, 2, 5, 91),
(5, 2, 5, 95),
(5, 2, 5, 96),
(5, 2, 5, 115),
(6, 2, 5, 8),
(6, 2, 5, 9),
(6, 2, 5, 15),
(6, 2, 5, 23),
(6, 2, 5, 33),
(6, 2, 5, 50),
(6, 2, 5, 52),
(6, 1, 5, 54),
(6, 1, 5, 61),
(6, 1, 5, 72),
(6, 1, 5, 76),
(6, 1, 5, 91),
(6, 1, 5, 95),
(6, 1, 5, 96),
(6, 1, 5, 115),
(7, 1, 5, 8),
(7, 1, 5, 9),
(7, 1, 5, 15),
(7, 1, 5, 23),
(7, 1, 5, 33),
(7, 3, 5, 50),
(7, 2, 5, 52),
(7, 2, 5, 54),
(7, 2, 5, 61),
(7, 2, 5, 72),
(7, 3, 5, 76),
(7, 3, 5, 91),
(7, 3, 5, 95),
(7, 3, 5, 96),
(7, 3, 5, 115),
(8, 1, 5, 8),
(8, 1, 5, 9),
(8, 1, 5, 15),
(8, 1, 5, 23),
(8, 1, 5, 33),
(8, 1, 5, 50),
(8, 1, 5, 52),
(8, 1, 5, 54),
(8, 1, 5, 61),
(8, 1, 5, 72),
(8, 1, 5, 76),
(8, 1, 5, 91),
(8, 1, 5, 95),
(8, 1, 5, 96),
(8, 1, 5, 115),
(9, 1, 5, 8),
(9, 1, 5, 9),
(9, 1, 5, 15),
(9, 1, 5, 23),
(9, 1, 5, 33),
(9, 2, 5, 50),
(9, 2, 5, 52),
(9, 2, 5, 54),
(9, 4, 5, 61),
(9, 3, 5, 72),
(9, 3, 5, 76),
(9, 3, 5, 91),
(9, 3, 5, 95),
(9, 3, 5, 96),
(9, 3, 5, 115),
(10, 2, 5, 8),
(10, 2, 5, 9),
(10, 2, 5, 15),
(10, 2, 5, 23),
(10, 2, 5, 33),
(10, 2, 5, 50),
(10, 2, 5, 52),
(10, 1, 5, 54),
(10, 1, 5, 61),
(10, 1, 5, 72),
(10, 1, 5, 76),
(10, 1, 5, 91),
(10, 1, 5, 95),
(10, 1, 5, 96),
(10, 1, 5, 115),
(11, 1, 5, 8),
(11, 1, 5, 9),
(11, 1, 5, 15),
(11, 1, 5, 23),
(11, 1, 5, 33),
(11, 1, 5, 50),
(11, 1, 5, 52),
(11, 1, 5, 54),
(11, 1, 5, 61),
(11, 1, 5, 72),
(11, 1, 5, 76),
(11, 1, 5, 91),
(11, 2, 5, 95),
(11, 2, 5, 96),
(11, 2, 5, 115),
(12, 1, 5, 8),
(12, 1, 5, 9),
(12, 1, 5, 15),
(12, 1, 5, 23),
(12, 2, 5, 33),
(12, 2, 5, 50),
(12, 2, 5, 52),
(12, 2, 5, 54),
(12, 2, 5, 61),
(12, 2, 5, 72),
(12, 2, 5, 76),
(12, 3, 5, 91),
(12, 4, 5, 95),
(12, 3, 5, 96),
(12, 4, 5, 115),
(13, 1, 5, 8),
(13, 1, 5, 9),
(13, 1, 5, 15),
(13, 1, 5, 23),
(13, 1, 5, 33),
(13, 1, 5, 50),
(13, 2, 5, 52),
(13, 2, 5, 54),
(13, 2, 5, 61),
(13, 2, 5, 72),
(13, 2, 5, 76),
(13, 2, 5, 91),
(13, 2, 5, 95),
(13, 3, 5, 96),
(13, 3, 5, 115),
(14, 2, 5, 8),
(14, 2, 5, 9),
(14, 2, 5, 15),
(14, 2, 5, 23),
(14, 2, 5, 33),
(14, 2, 5, 50),
(14, 2, 5, 52),
(14, 2, 5, 54),
(14, 2, 5, 61),
(14, 1, 5, 72),
(14, 1, 5, 76),
(14, 1, 5, 91),
(14, 1, 5, 95),
(14, 1, 5, 96),
(14, 1, 5, 115),
(15, 1, 5, 8),
(15, 1, 5, 9),
(15, 1, 5, 15),
(15, 1, 5, 23),
(15, 1, 5, 33),
(15, 2, 5, 50),
(15, 2, 5, 52),
(15, 2, 5, 54),
(15, 2, 5, 61),
(15, 2, 5, 72),
(15, 2, 5, 76),
(15, 3, 5, 91),
(15, 3, 5, 95),
(15, 3, 5, 96),
(15, 3, 5, 115),
(16, 1, 5, 8),
(16, 1, 5, 9),
(16, 1, 5, 15),
(16, 2, 5, 23),
(16, 2, 5, 33),
(16, 2, 5, 50),
(16, 2, 5, 52),
(16, 2, 5, 54),
(16, 2, 5, 61),
(16, 3, 5, 72),
(16, 3, 5, 76),
(16, 3, 5, 91),
(16, 4, 5, 95),
(16, 4, 5, 96),
(16, 4, 5, 115);