CREATE TABLE groupe(
   id_groupe INT PRIMARY KEY AUTO_INCREMENT,
   nom_groupe TEXT NOT NULL,
   couleur_groupe VARCHAR(50) NOT NULL,
   image BLOB,
   budget INT NOT NULL,
   date_fin_debat DATE NOT NULL,
   duree_min_discuss VARCHAR(50) NOT NULL
);

CREATE TABLE type_scrutin(
   Id_scrutin INT PRIMARY KEY AUTO_INCREMENT,
   nom_type VARCHAR(50) NOT NULL
);

CREATE TABLE internaute(
   Id_internaute INT PRIMARY KEY AUTO_INCREMENT,
   non_internaute VARCHAR(50) NOT NULL,
   prenom_internaute VARCHAR(50) NOT NULL,
   adresse_postale VARCHAR(50),
   courriel VARCHAR(200) NOT NULL,
   mot_de_passe VARCHAR(50) NOT NULL
);

CREATE TABLE thematique(
   Id_thematique INT PRIMARY KEY AUTO_INCREMENT,
   nom_thematique VARCHAR(50) NOT NULL
);

CREATE TABLE role(
   Id_role INT PRIMARY KEY AUTO_INCREMENT,
   nom_role VARCHAR(50) NOT NULL
);

CREATE TABLE droit(
   Id_droit INT PRIMARY KEY AUTO_INCREMENT,
   nom_droit VARCHAR(50) NOT NULL
);

CREATE TABLE notification(
   Id_notification INT PRIMARY KEY AUTO_INCREMENT,
   config_notifs BLOB NOT NULL
);

CREATE TABLE reaction(
   id_reaction INT PRIMARY KEY AUTO_INCREMENT,
   nom_reaction VARCHAR(50) NOT NULL
);

CREATE TABLE infos_membre(
   id_groupe INT,
   Id_internaute INT,
   Id_notification INT NOT NULL,
   Id_role INT NOT NULL,
   PRIMARY KEY(id_groupe, Id_internaute),
   FOREIGN KEY(id_groupe) REFERENCES groupe(id_groupe),
   FOREIGN KEY(Id_internaute) REFERENCES internaute(Id_internaute),
   FOREIGN KEY(Id_notification) REFERENCES notification(Id_notification),
   FOREIGN KEY(Id_role) REFERENCES role(Id_role)
);

CREATE TABLE proposition(
   Id_proposition INT PRIMARY KEY AUTO_INCREMENT,
   titre_proposition VARCHAR(50) NOT NULL,
   description_proposition TEXT NOT NULL,
   date_publication DATE NOT NULL,
   evaluation_chiffree INT NOT NULL,
   signalee BOOLEAN NOT NULL,
   id_groupe INT NOT NULL,
   Id_thematique INT NOT NULL,
   FOREIGN KEY(id_groupe) REFERENCES groupe(id_groupe),
   FOREIGN KEY(Id_thematique) REFERENCES thematique(Id_thematique)
);

CREATE TABLE commentaire(
   Id_commentaire INT PRIMARY KEY AUTO_INCREMENT,
   contenu_message TEXT NOT NULL,
   date_publication DATE NOT NULL,
   signale BOOLEAN NOT NULL,
   id_groupe INT NOT NULL,
   Id_internaute INT NOT NULL,
   Id_proposition INT NOT NULL,
   FOREIGN KEY(id_groupe, Id_internaute) REFERENCES infos_membre(id_groupe, Id_internaute),
   FOREIGN KEY(Id_proposition) REFERENCES proposition(Id_proposition)
);

CREATE TABLE reaction_commentaire(
   id_groupe INT,
   Id_internaute INT,
   Id_commentaire INT NOT NULL,
   id_reaction INT NOT NULL,
   PRIMARY KEY(id_groupe, Id_internaute, Id_commentaire),
   FOREIGN KEY(id_groupe, Id_internaute) REFERENCES infos_membre(id_groupe, Id_internaute),
   FOREIGN KEY(Id_commentaire) REFERENCES commentaire(Id_commentaire),
   FOREIGN KEY(id_reaction) REFERENCES reaction(id_reaction)
);

CREATE TABLE Reaction_proposition(
   id_groupe INT,
   Id_internaute INT,
   Id_proposition INT,
   id_reaction INT NOT NULL,
   PRIMARY KEY(id_groupe, Id_internaute, Id_proposition),
   FOREIGN KEY(id_groupe, Id_internaute) REFERENCES infos_membre(id_groupe, Id_internaute),
   FOREIGN KEY(Id_proposition) REFERENCES proposition(Id_proposition),
   FOREIGN KEY(id_reaction) REFERENCES reaction(id_reaction)
);

CREATE TABLE vote(
   Id_vote INT PRIMARY KEY AUTO_INCREMENT,
   date_fin_vote DATE NOT NULL,
   Id_proposition INT NOT NULL,
   Id_scrutin INT NOT NULL,
   UNIQUE(Id_proposition),
   FOREIGN KEY(Id_proposition) REFERENCES proposition(Id_proposition),
   FOREIGN KEY(Id_scrutin) REFERENCES type_scrutin(Id_scrutin)
);

CREATE TABLE choix(
   Id_vote INT,
   id_choix INT,
   libelle_choix VARCHAR(50) NOT NULL,
   PRIMARY KEY(Id_vote, id_choix),
   FOREIGN KEY(Id_vote) REFERENCES vote(Id_vote)
);

CREATE TABLE droit_d_un_role(
   Id_role INT,
   Id_droit INT,
   PRIMARY KEY(Id_role, Id_droit),
   FOREIGN KEY(Id_role) REFERENCES role(Id_role),
   FOREIGN KEY(Id_droit) REFERENCES droit(Id_droit)
);

CREATE TABLE theme_groupe(
   id_groupe INT,
   Id_thematique INT,
   budget_thematique INT NOT NULL,
   PRIMARY KEY(id_groupe, Id_thematique),
   FOREIGN KEY(id_groupe) REFERENCES groupe(id_groupe),
   FOREIGN KEY(Id_thematique) REFERENCES thematique(Id_thematique)
);

CREATE TABLE vote_membre(
   Id_vote INT,
   id_choix INT,
   id_groupe INT,
   Id_internaute INT,
   PRIMARY KEY(Id_vote, id_choix, id_groupe, Id_internaute),
   FOREIGN KEY(Id_vote, id_choix) REFERENCES choix(Id_vote, id_choix),
   FOREIGN KEY(id_groupe, Id_internaute) REFERENCES infos_membre(id_groupe, Id_internaute)
);

CREATE TABLE demande_vote(
   Id_proposition INT,
   id_groupe INT,
   Id_internaute INT,
   PRIMARY KEY(Id_proposition, id_groupe, Id_internaute),
   FOREIGN KEY(Id_proposition) REFERENCES proposition(Id_proposition),
   FOREIGN KEY(id_groupe, Id_internaute) REFERENCES infos_membre(id_groupe, Id_internaute)
);
