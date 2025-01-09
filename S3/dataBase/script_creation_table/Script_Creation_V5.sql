CREATE TABLE groupe(
   id_groupe INT PRIMARY KEY AUTO_INCREMENT,
   nom_groupe TEXT NOT NULL,
   couleur_groupe VARCHAR(50) NOT NULL,
   image VARCHAR(255),
   budget INT NOT NULL,
   nbj_dft_vote INT NOT NULL,
   nbj_dft_discuss INT NOT NULL,
   nb_signalement INT DEFAULT 0
);

CREATE TABLE type_scrutin(
   id_scrutin INT PRIMARY KEY AUTO_INCREMENT,
   nom_type VARCHAR(50) NOT NULL
);

CREATE TABLE internaute(
   id_internaute INT PRIMARY KEY AUTO_INCREMENT,
   nom_internaute VARCHAR(50) NOT NULL,
   prenom_internaute VARCHAR(50) NOT NULL,
   adresse_postale VARCHAR(50),
   courriel VARCHAR(200) NOT NULL,
   hashageMDP VARCHAR(100) NOT NULL
);

CREATE TABLE thematique(
   id_thematique INT PRIMARY KEY AUTO_INCREMENT,
   nom_thematique VARCHAR(50) NOT NULL
);

CREATE TABLE role(
   id_role INT PRIMARY KEY AUTO_INCREMENT,
   nom_role VARCHAR(50) NOT NULL
);

CREATE TABLE droit(
   id_droit INT PRIMARY KEY AUTO_INCREMENT,
   nom_droit VARCHAR(50) NOT NULL
);

CREATE TABLE notification(
   id_notification INT PRIMARY KEY AUTO_INCREMENT,
   config_notifs BLOB
);

CREATE TABLE reaction(
   id_reaction INT PRIMARY KEY AUTO_INCREMENT,
   nom_reaction VARCHAR(50) NOT NULL
);

CREATE TABLE infos_membre(
   id_groupe INT,
   id_internaute INT,
   id_notification INT NOT NULL,
   id_role INT NOT NULL,
   PRIMARY KEY(id_groupe, id_internaute),
   FOREIGN KEY(id_groupe) REFERENCES groupe(id_groupe) ON DELETE CASCADE,
   FOREIGN KEY(id_internaute) REFERENCES internaute(id_internaute) ON DELETE CASCADE,
   FOREIGN KEY(id_notification) REFERENCES notification(id_notification),
   FOREIGN KEY(id_role) REFERENCES role(id_role)
);

CREATE TABLE proposition(
   id_proposition INT PRIMARY KEY AUTO_INCREMENT,
   titre_proposition VARCHAR(200) NOT NULL,
   description_proposition TEXT NOT NULL,
   date_publication DATE NOT NULL,
   budget INT NOT NULL,
   nb_signalement INT DEFAULT 0,
   id_groupe INT NOT NULL,
   id_thematique INT NOT NULL,
   FOREIGN KEY(id_groupe) REFERENCES groupe(id_groupe) ON DELETE CASCADE,
   FOREIGN KEY(id_thematique) REFERENCES thematique(id_thematique) ON DELETE CASCADE
);

CREATE TABLE commentaire(
   id_commentaire INT PRIMARY KEY AUTO_INCREMENT,
   contenu_message TEXT NOT NULL,
   horodatage TIMESTAMP NOT NULL,
   nb_signalement INT DEFAULT 0,
   id_groupe INT NOT NULL,
   id_internaute INT NOT NULL,
   id_proposition INT NOT NULL,
   FOREIGN KEY(id_groupe, id_internaute) REFERENCES infos_membre(id_groupe, id_internaute) ON DELETE CASCADE,
   FOREIGN KEY(id_proposition) REFERENCES proposition(id_proposition) ON DELETE CASCADE
);

CREATE TABLE reaction_commentaire(
   id_groupe INT,
   id_internaute INT,
   id_commentaire INT,
   id_reaction INT NOT NULL,
   PRIMARY KEY(id_groupe, id_internaute, id_commentaire),
   FOREIGN KEY(id_groupe, id_internaute) REFERENCES infos_membre(id_groupe, id_internaute) ON DELETE CASCADE,
   FOREIGN KEY(id_commentaire) REFERENCES commentaire(id_commentaire) ON DELETE CASCADE,
   FOREIGN KEY(id_reaction) REFERENCES reaction(id_reaction)
);

CREATE TABLE reaction_proposition(
   id_groupe INT,
   id_internaute INT,
   id_proposition INT,
   id_reaction INT NOT NULL,
   PRIMARY KEY(id_groupe, id_internaute, id_proposition),
   FOREIGN KEY(id_groupe, id_internaute) REFERENCES infos_membre(id_groupe, id_internaute) ON DELETE CASCADE,
   FOREIGN KEY(id_proposition) REFERENCES proposition(id_proposition) ON DELETE CASCADE,
   FOREIGN KEY(id_reaction) REFERENCES reaction(id_reaction)
);

CREATE TABLE vote(
   id_vote INT PRIMARY KEY AUTO_INCREMENT,
   date_fin_vote DATE NOT NULL,
   id_proposition INT NOT NULL,
   id_scrutin INT NOT NULL,
   UNIQUE(id_proposition),
   FOREIGN KEY(id_proposition) REFERENCES proposition(id_proposition) ON DELETE CASCADE,
   FOREIGN KEY(id_scrutin) REFERENCES type_scrutin(id_scrutin)
);

CREATE TABLE choix(
   id_vote INT,
   id_choix INT,
   libelle_choix VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_vote, id_choix),
   FOREIGN KEY(id_vote) REFERENCES vote(id_vote) ON DELETE CASCADE
);

CREATE TABLE droit_d_un_role(
   id_role INT,
   id_droit INT,
   PRIMARY KEY(id_role, id_droit),
   FOREIGN KEY(id_role) REFERENCES role(id_role) ON DELETE CASCADE,
   FOREIGN KEY(id_droit) REFERENCES droit(id_droit) ON DELETE CASCADE
);

CREATE TABLE theme_groupe(
   id_groupe INT,
   id_thematique INT,
   budget_thematique INT NOT NULL,
   PRIMARY KEY(id_groupe, id_thematique),
   FOREIGN KEY(id_groupe) REFERENCES groupe(id_groupe) ON DELETE CASCADE,
   FOREIGN KEY(id_thematique) REFERENCES thematique(id_thematique) ON DELETE CASCADE
);

CREATE TABLE vote_membre(
   id_vote INT,
   id_choix INT,
   id_groupe INT,
   id_internaute INT,
   PRIMARY KEY(id_vote, id_choix, id_groupe, id_internaute),
   FOREIGN KEY(id_vote, id_choix) REFERENCES choix(id_vote, id_choix) ON DELETE CASCADE,
   FOREIGN KEY(id_groupe, id_internaute) REFERENCES infos_membre(id_groupe, id_internaute) ON DELETE CASCADE
);

CREATE TABLE demande_vote(
   id_proposition INT,
   id_groupe INT,
   id_internaute INT,
   PRIMARY KEY(id_proposition, id_groupe, id_internaute),
   FOREIGN KEY(id_proposition) REFERENCES proposition(id_proposition) ON DELETE CASCADE,
   FOREIGN KEY(id_groupe, id_internaute) REFERENCES infos_membre(id_groupe, id_internaute) ON DELETE CASCADE
);
