#include "xxx.h"
#include "fonctions_hors_de_la_classe.h"

using namespace std;

//affichage de toutes les fonctionnalités
void afficher_menu() 
{
    string nom_fichier;
    int choix_utilisateur;
    vector<int> choix; //utilisé pour les fonctions de détextions et de teinte
    saisir_nom_fichier(nom_fichier);
    Image image(nom_fichier);
    Image res;
    cout << "Menu :" << endl;
    cout << "1. Créer une nouvelle image avec seulement la composante Rouge" << endl;
    cout << "2. Vérifier si un pixel de la couleur donnée existe dans l'image" << endl;
    cout << "3. Convertir l'image en niveaux de gris" << endl;
    cout << "4. Convertir l'image en image noir et blanc" << endl;
    cout << "5. appliquer une teinte d'une couleur à l'image" << endl;
    cout << "6. Rogner l'image" << endl;
    cout << "7. Retourner l'image" << endl;
    cout << "8. Changer la  contraste" << endl;
    cout << "9. Changer le luminosité " << endl;
    cout << "10. Appliquer un filtre" << endl;
    cout << "11. faire une rotation des coueleurs " << endl;
    cout << "12. blanchir une image " << endl;
    cout << "13. arrêter " << endl;
    cout << "Entrez votre choix : ";
    cin >> choix_utilisateur;
    while (choix_utilisateur!=CHOIX_TREIZE)
    {
        if  (choix_utilisateur == CHOIX_DOUZE)
        {
            choix = couleur_souhaite();
            res = image.blanchirCouleur(choix[0],choix[1],choix[2]);

        }
        else if  (choix_utilisateur == CHOIX_ONZE)
        {

            res=image.rotationCouleur();

        }
        else if (choix_utilisateur==CHOIX_DIX)
        {
            res = image.applicationFiltre();
            
        }    
        else if  (choix_utilisateur == CHOIX_NEUF)
        {
            res = image.luminosity();
        }

        else if  (choix_utilisateur == CHOIX_HUIT)
        {
            res = image.contraste();
            
            
        }
        else if  (choix_utilisateur == CHOIX_SEPT)
        {
            res = image.rotation();
            
            
        }
        else if  (choix_utilisateur == CHOIX_SIX)
        {
            res = image.rogner();
            
            
        }
        else if  (choix_utilisateur == CHOIX_CINQ)
        {
            choix = couleur_souhaite();
            res=image.teinte(choix[0],choix[1],choix[2]);
            
            
        }
        else if  (choix_utilisateur == CHOIX_QUATRE)
        
        {
            res = image.niveauxGris();
            
            
        }
        else if  (choix_utilisateur == CHOIX_TROIS)
        {

            res = image.niveauxGris();
            
            
            
        }
        else if  (choix_utilisateur == CHOIX_DEUX)
        {

            choix = couleur_souhaite();
            image.detection(choix[0],choix[1],choix[2]);
            
        }
        else if  (choix_utilisateur == CHOIX_UN)
        {

            res=image.composanteRouge();
            
            
        }

    savePicture(res);
    cout << "Entrez votre nouveau choix : ";
    cin >> choix_utilisateur;   
    }  
    cout << "Au revoir . " << endl;
}
    
// loadPicture : prend le nom d'un fichier contenant une image au format ppm,
// remplit 3 tableaux 2D avec les donnees de cette image, et renvoie la taille
// effective de ces tableaux (identique pour les 3 tableaux).
// parametres (D) image : chaine de caracteres
//  		 (R) tabR, tabB, tabG : tableaux [0,MAX-1][0,MAX-1] d'entiers
void loadPicture(const string &picture, vector<vector<unsigned int>> &red,
                                        vector<vector<unsigned int>> &green,
                                        vector <vector<unsigned int>> &blue)
{
    // Declaration des variables
    string line; // pour recuperer les lignes du fichier image au format .ppm, qui est code en ASCII.
    string format; //pour recuperer le format de l'image : celui-ci doit être de la forme P3
    string name; // au cas où l'utilisateur se trompe dans le nom de l'image a charger, on redemande le nom.
    int taille;
    vector <int> mypixels; // pour recuperer les donnees du fichier de maniere lineaire. On repartira ensuite ces donnees dans les tableaux correspondants
    ifstream entree; // Declaration d'un "flux" qui permettra ensuite de lire les donnees de l'image.
    int hauteur; // pour bien verifier que l'image est carree, et de taille respectant les conditions fixees par l'enonce
    // Initialisation des variables
    name = picture;
    // Permet d'ouvrir le fichier portant le nom picture
    // ouverture du fichier portant le nom picture
    entree.open(name);
    // On verifie que le fichier a bien ete ouvert. Si cela n'est pas le cas, on redemande un nom de fichier valide
    while (!entree){
        //cin.rdbuf(oldbuf);
        cerr << "Erreur! Impossible de lire de fichier " << name << " ! " << endl;
        cerr << "Redonnez le nom du fichier a ouvrir SVP. Attention ce fichier doit avoir un nom du type nom.ppm." << endl;
        cin >> name;
        entree.open(name); // relance
    }
    // Lecture du nombre definissant le format (ici P3)
    entree >> format;
    // on finit de lire la ligne (caractere d'espacement)
    getline(entree, line);
    // Lecture du commentaire
    getline(entree, line);
    //lecture des dimensions
    entree >> taille >> hauteur;
    getline(entree, line); // on finit de lire la ligne (caractere d'espacement)
    // On verifie que l'image a une taille qui verifie bien les conditions requises par l'enonce.
    // Si cela n'est pas le cas, on redemande un fichier valide, et ce, tant que necessaire.
    while (format != "P3"){
        if (format != "P3"){
            cerr << "Erreur! L'image que vous nous avez donnee a un format ne verifiant pas les conditions requises." << endl;
            cerr << "L'image que vous nous avez donnee doit etre codee en ASCII et non en brut." << endl;
        }
  entree.close();
        // On va redemander un nom de fichier valide.
        do{
            cerr << "Veuillez redonner un nom de fichier qui respecte les conditions de format et de taille. Attention, ce nom doit etre de la forme nom.ppm." << endl;
            cin >> name;
            entree.open(name); // relance
        }while(!entree);
         // Lecture du nombre definissant le format (ici P3)
         entree >> format;
         getline(entree, line); // on finit de lire la ligne (caractere d'espacement)
        // Lecture du commentaire
        getline(entree, line);
        //lecture des dimensions
        entree >> taille >> hauteur; // relance
        getline(entree, line); // on finit de lire la ligne (caractere d'espacement)
    }
    //Lecture de la valeur max
    getline(entree, line);
    //Lecture des donnees et ecriture dans les tableaux :
    // Pour plus de simplicite, on stocke d'abord toutes les donnees dans mypixels
    // dans l'ordre de lecture puis ensuite on les repartira dans les differents tableaux.
    //Les donnees stockees dans mypixels sont de la forme RGB RGB RGB ....
    // Il faudra donc repartir les valeurs R correspondant a la composante rouge de l'image
    // dans le tableau red, de même pour G et B.
    int pix;
    mypixels.resize(3*taille*hauteur); // taille fixe : on alloue une fois pour toutes
    for (int i = 0; i < 3*taille*hauteur; i++){
      entree >> pix;
      mypixels[i]=pix;
    }
    // Remplissage des 3 tableaux : on repartit maintenant les valeurs dans les bonnes composantes
    // Comme dans mypixels, les donnees sont stockees de la maniere suivante : RGB RGB RGB, il faut mettre
    // les valeurs correspondant a la composante rouge dans red, ...
    // Ainsi, les valeurs de la composante rouge correspondent aux valeurs stockes aux indices
    // congrus a 0 mod 3 dans mypixels, que les valeurs de la composante verte correspond aux valeurs
    // stockes aux indices sont congrus a 1 mod 3, ...
     // les valeurs d'une ligne
    int val;
    red.resize(hauteur);
    green.resize(hauteur);
    blue.resize(hauteur);
    for (int i = 0; i < hauteur; i++){
      vector <unsigned int> ligneR(taille);
      vector <unsigned int> ligneB(taille);  // les lignes ont toutes la même taille
      vector <unsigned int> ligneG(taille);
      for (int j = 0; j < taille; j++){
            val =  mypixels[3*j + 3*taille*i];
            ligneR[j]=val;
            val = mypixels[3*j + 1 + 3*taille*i];
            ligneG[j]=val;
            val = mypixels[3*j + 2 + 3*taille*i];
            ligneB[j]=val;
        }
        red[i]=ligneR;
        green[i]=ligneG;
        blue[i]=ligneB;
    }
    // Informations a l'utilisateur pour dire que tout s'est bien passe
    cout << " L'image " << name << " a bien ete chargee dans les tableaux ." << endl;
     entree.close();
 }
//pour sauvegarder une image
 void savePicture(Image &image)
 {
      
        string nom_fichier;
        saisir_nom_fichier(nom_fichier);
        ofstream ecriture;
        ecriture.open(nom_fichier);
        if(!(ecriture.is_open())) throw(3);
        else
        {
        ecriture << "P3" << endl;
        ecriture << "#Created by GIMP version 2.10.28 PNM plug-in" << endl;
        ecriture << image.getlongeur()  << ' ' << image.getlargeur() << endl;    
        ecriture << "255" << endl;

        for (size_t i = 0; i < image.getlongeur(); i++){
           for (size_t j = 0; j < image.getlargeur(); j++){
              ecriture << image.getrouge()[i][j] << endl;
              ecriture << image.getvert()[i][j] << endl;
              ecriture << image.getbleu()[i][j] << endl;
           }
          cout << endl;
    }   


  }
}


// pour saisir le nom d'un fichier
string saisir_nom_fichier(string& nom_fichier) {
      
      cout << "Entrez le nom du fichier image au format ppm : ";
      cin >> nom_fichier;
      return nom_fichier;}
// fonction pour récolté une couleur voulu
vector<int> couleur_souhaite(){
    int rouge,vert,bleu;
        cout << "donner le rouge, le vert et le bleu du pixel cherché" << endl;
        cin >> rouge;
        cin >> vert;
        cin >> bleu;
        if (rouge<0 || rouge>255)
        {
            throw(invalid_argument("La composante rouge a une valeur invalide"));
        }
        else if (vert>0 || vert>255)
        {
            throw(invalid_argument("La composante verte a une valeur invalide"));
        }
        else if (bleu<0 || bleu>255)
        {
            throw(invalid_argument("La composante bleu a une valeur invalide"));
        }
    vector<int> retour = {rouge,vert,bleu};
    return retour;
}
//surcharge avec deux vecteurs
vector<vector<unsigned>> operator*(vector<vector<unsigned>> &A,vector<vector<unsigned>> &B){
    vector<vector<unsigned>> retour;
    unsigned ligneA = A.size();
    unsigned colonneA = A[0].size();
    unsigned ligneB = B.size();
    unsigned colonneB = B[0].size();

    if (colonneA != ligneB) {
        cerr << "Les dimensions des matrices ne sont pas compatibles pour la multiplication.\n";
        return vector<vector<unsigned>>();
    }

    // Initialiser la matrice résultante avec des zéros
    vector<vector<unsigned>> resultat(ligneA, vector<unsigned>(colonneB, 0)); // il y a ligneA ligne et colonneB colonne

    // Effectuer la multiplication
    for (int i = 0; i < ligneA; ++i) {
        for (int j = 0; j < colonneB; ++j) {
            for (int k = 0; k < colonneA; ++k) {
                resultat[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    return resultat;
}
//surcharge avec d'un nombre avec un vecteur
vector<vector<unsigned>> operator*(unsigned int lambda,vector<vector<unsigned>> &B){
    size_t ligne = B.size();
    size_t colonne = B[0].size();
    for (size_t i = 0; i< ligne ; i++)
    {
        for (size_t j = 0; j< colonne; j++)
        {
            B[i][j]*=lambda;
        }

    }
    return B;

}
vector<vector<unsigned>> operator*(float lambda,vector<vector<unsigned>> &B){

    size_t ligne = B.size();
    size_t colonne = B[0].size();
    for (size_t i = 0; i< ligne ; i++)
    {
        for (size_t j = 0; j< colonne; j++)
        {
            B[i][j]*=lambda;
        }

    }
    return B;

}
vector<vector<unsigned>> operator*(vector<vector<unsigned>> &B,unsigned int lambda){

    size_t ligne = B.size();
    size_t colonne = B[0].size();
    for (size_t i = 0; i< ligne ; i++)
    {
        for (size_t j = 0; j< colonne; j++)
        {
            B[i][j]*=lambda;
        }

    }
    return B;

}
vector<vector<unsigned>> operator*(vector<vector<unsigned>> &B,float lambda){

    size_t ligne = B.size();
    size_t colonne = B[0].size();
    for (size_t i = 0; i< ligne ; i++)
    {
        for (size_t j = 0; j< colonne; j++)
        {
            B[i][j]*=lambda;
        }

    }
    return B;

}
vector<vector<float>> operator*(vector<vector<float>> &B ,float lambda){
    
    size_t ligne = B.size();
    size_t colonne = B[0].size();
    for (size_t i = 0; i< ligne ; i++)
    {
        for (size_t j = 0; j< colonne; j++)
        {
            B[i][j]*=lambda;
        }

    }
    return B;
}
