CREATE TABLE aptitude_mental (
    numAptitudeMental           INTEGER NOT NULL,
    description_aptitude_mental TEXT    NOT NULL,
    PRIMARY KEY (
        numAptitudeMental
    )
);
CREATE TABLE bilan_aptitude (
    num_de_licencie  INTEGER NOT NULL,
    aptitude         INTEGER NOT NULL,
    numBilanAptitude INTEGER,
    niveauAptitude   INTEGER REFERENCES niveau_evaluation (numNiveauEvaluation),
    PRIMARY KEY (
        numBilanAptitude
    ),
    FOREIGN KEY (
        aptitude
    )
    REFERENCES aptitude_mental (numAptitudeMental),
    FOREIGN KEY (
        num_de_licencie
    )
    REFERENCES licencie (numdelicencie),
    FOREIGN KEY (
        niveauAptitude
    )
    REFERENCES niveau_evaluation (numNiveauEvaluation) 
);
CREATE TABLE bilan_capacite (
    num_de_licencie   INTEGER NOT NULL,
    capacite          INTEGER NOT NULL,
    numBilanTechnique INTEGER,
    niveauTechnique   INTEGER REFERENCES niveau_acquisition (numNiveauAcquisition),
    PRIMARY KEY (
        num_de_licencie,
        capacite
    ),
    FOREIGN KEY (
        capacite
    )
    REFERENCES capacite_technique (numCapaciteTechnique),
    FOREIGN KEY (
        num_de_licencie
    )
    REFERENCES licencie (numdelicencie),
    FOREIGN KEY (
        niveauTechnique
    )
    REFERENCES niveau_acquisition (numNiveauAcquisition) 
);
CREATE TABLE bilan_collectif (
    num_de_licencie INTEGER NOT NULL,
    stat_collective INTEGER NOT NULL,
    FOREIGN KEY (
        stat_collective
    )
    REFERENCES statistique_collective (numStatistiqueCollective),
    FOREIGN KEY (
        num_de_licencie
    )
    REFERENCES licencie (numdelicencie),
    PRIMARY KEY (
        num_de_licencie,
        stat_collective
    )
);
CREATE TABLE bilan_individuel (
    num_licencie    INTEGER NOT NULL,
    stat_individuel INTEGER NOT NULL,
    FOREIGN KEY (
        num_licencie
    )
    REFERENCES licencie (numdelicencie),
    FOREIGN KEY (
        stat_individuel
    )
    REFERENCES statistique_individuel (numStatistiqueIndividuel),
    PRIMARY KEY (
        num_licencie,
        stat_individuel
    )
);
CREATE TABLE capacite_technique (
    numCapaciteTechnique   INTEGER NOT NULL,
    nom_capacite_technique INTEGER NOT NULL,
    PRIMARY KEY (
        numCapaciteTechnique
    )
);
CREATE TABLE categorie (
    numCategorie          INTEGER NOT NULL,
    nom_categorie         TEXT    NOT NULL,
    age_categorie_minimum INTEGER NOT NULL,
    age_categorie_maximum INTEGER NOT NULL,
    effectif_maximum      INTEGER NOT NULL,
    nb_seeance            INTEGER NOT NULL,
    PRIMARY KEY (
        numCategorie
    )
);
CREATE TABLE creneau (
    numCreneau  INTEGER NOT NULL,
    heure_debut TIME    NOT NULL,
    heure_fin   TIME    NOT NULL,
    terrain     INTEGER NOT NULL,
    FOREIGN KEY (
        terrain
    )
    REFERENCES terrain (numTerrain),
    PRIMARY KEY (
        numCreneau
    )
);
CREATE TABLE creneau_categorie (
    num_categorie INTEGER NOT NULL,
    num_creneau   INTEGER NOT NULL,
    FOREIGN KEY (
        num_creneau
    )
    REFERENCES creneau (numCreneau),
    FOREIGN KEY (
        num_categorie
    )
    REFERENCES categorie (numCategorie),
    PRIMARY KEY (
        num_categorie,
        num_creneau
    )
);
CREATE TABLE disponibilite (
    num_habit  INTEGER     NOT NULL,
    nom_taille VARCHAR (2) NOT NULL,
    quantité   INTEGER,
    PRIMARY KEY (
        num_habit,
        nom_taille
    ),
    FOREIGN KEY (
        num_habit
    )
    REFERENCES habit (numHabit),
    FOREIGN KEY (
        nom_taille
    )
    REFERENCES taille (nomTaille) 
);
CREATE TABLE entraineur (
    num_entriaineur INTEGER NOT NULL,
    num_categorie   INTEGER NOT NULL,
    FOREIGN KEY (
        num_categorie
    )
    REFERENCES categorie (numCategorie),
    FOREIGN KEY (
        num_entriaineur
    )
    REFERENCES licencie (numdelicencie),
    PRIMARY KEY (
        num_entriaineur,
        num_categorie
    )
);
CREATE TABLE groupe (
    numGroupe     INTEGER NOT NULL,
    numero_groupe INTEGER NOT NULL,
    categorie     INTEGER NOT NULL,
    FOREIGN KEY (
        categorie
    )
    REFERENCES categorie (numCategorie),
    PRIMARY KEY (
        numGroupe
    )
);
CREATE TABLE habillement (
    num_de_licencie INTEGER NOT NULL,
    num_habit       INTEGER NOT NULL,
    FOREIGN KEY (
        num_de_licencie
    )
    REFERENCES licencie (numdelicencie),
    FOREIGN KEY (
        num_habit
    )
    REFERENCES habit (numHabit),
    PRIMARY KEY (
        num_de_licencie,
        num_habit
    )
);
CREATE TABLE habit (
    numHabit         INTEGER NOT NULL,
    nomhabit         TEXT    NOT NULL,
    descriptionhabit TEXT    NOT NULL,
    PRIMARY KEY (
        numHabit
    )
);
CREATE TABLE licencie (
    numdelicencie      INTEGER      NOT NULL,
    prenom_licencie    TEXT         NOT NULL,
    nom_licencie       TEXT         NOT NULL,
    telephone          VARCHAR (10) NOT NULL,
    email              TEXT         NOT NULL,
    annee_de_naissance DATE         NOT NULL,
    dunkerquois        BOOL         NOT NULL,
    president          INTEGER      NOT NULL,
    lieu               INTEGER      NOT NULL,
    presence           INTEGER      NOT NULL
                                    REFERENCES presence (numPresence),
    categorie          INTEGER      REFERENCES categorie (numCategorie),
    PRIMARY KEY (
        numdelicencie
    ),
    FOREIGN KEY (
        lieu
    )
    REFERENCES ville (numVille),
    FOREIGN KEY (
        president
    )
    REFERENCES licencie (numdelicencie),
    FOREIGN KEY (
        categorie
    )
    REFERENCES categorie (numCategorie),
    FOREIGN KEY (
        presence
    )
    REFERENCES presence (numPresence) 
);
CREATE TABLE lieu_categorie (
    num_categorie INTEGER NOT NULL,
    num_terrain   INTEGER,
    FOREIGN KEY (
        num_categorie
    )
    REFERENCES categorie (numCategorie),
    FOREIGN KEY (
        num_terrain
    )
    REFERENCES terrain (numTerrain),
    PRIMARY KEY (
        num_categorie
    )
);
CREATE TABLE match_foot (
    numMatchFoot      INTEGER     NOT NULL,
    resultat          VARCHAR (3) NOT NULL,
    description_match TEXT,
    date              DATE        NOT NULL,
    lieu_du_match     INTEGER     NOT NULL
                                  REFERENCES ville (numVille),
    PRIMARY KEY (
        numMatchFoot
    ),
    FOREIGN KEY (
        lieu_du_match
    )
    REFERENCES ville (numVille) 
);
CREATE TABLE niveau_acquisition (
    numNiveauAcquisition  INTEGER         NOT NULL,
    nom_niveau_acquisitio VARCHAR2 (1024),
    PRIMARY KEY (
        numNiveauAcquisition
    )
);
CREATE TABLE niveau_evaluation (
    numNiveauEvaluation   INTEGER         NOT NULL,
    nom_niveau_evaluation VARCHAR2 (1024),
    PRIMARY KEY (
        numNiveauEvaluation
    )
);
CREATE TABLE presence (
    numPresence           INTEGER NOT NULL,
    nombre_presence       INTEGER NOT NULL,
    nombre_absence_excuse INTEGER NOT NULL,
    PRIMARY KEY (
        numPresence
    )
);
CREATE TABLE statistique_collective (
    numStatistiqueCollective           INTEGER NOT NULL,
    description_statistique_collective TEXT    NOT NULL,
    valeu_statistique_collective       INTEGER NOT NULL,
    match                              INTEGER NOT NULL
                                               REFERENCES match_foot (numMatchFoot),
    PRIMARY KEY (
        numStatistiqueCollective
    ),
    FOREIGN KEY (
        match
    )
    REFERENCES match_foot (numMatchFoot) 
);
CREATE TABLE statistique_individuel (
    numStatistiqueIndividuel           INTEGER NOT NULL,
    description_statistique_individuel TEXT    NOT NULL,
    valeur_statistique_individuel      INTEGER NOT NULL,
    match                              INTEGER NOT NULL
                                               REFERENCES match_foot (numMatchFoot),
    PRIMARY KEY (
        numStatistiqueIndividuel
    ),
    FOREIGN KEY (
        match
    )
    REFERENCES match_foot (numMatchFoot) 
);
CREATE TABLE stock_materiel (
    numStockMateriel INTEGER NOT NULL,
    nom_materiel     TEXT    NOT NULL,
    a_l_entraineur   INTEGER NOT NULL,
    FOREIGN KEY (
        a_l_entraineur
    )
    REFERENCES licencie (numdelicencie),
    PRIMARY KEY (
        numStockMateriel
    )
);
CREATE TABLE taille (
    nomTaille VARCHAR (2) NOT NULL,
    PRIMARY KEY (
        nomTaille
    )
);
CREATE TABLE terrain (
    numTerrain   INTEGER NOT NULL,
    nomterrain   TEXT    NOT NULL,
    type_terrain TEXT    NOT NULL,
    PRIMARY KEY (
        numTerrain
    )
);
CREATE TABLE ville (
    numVille   INTEGER NOT NULL,
    codepostal INTEGER NOT NULL,
    nom_ville  TEXT    NOT NULL,
    PRIMARY KEY (
        numVille
    )
);
