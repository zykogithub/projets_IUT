#ifndef FONCTIONHORS_H_FONCTIONHORS
#define FONCTIONHORS_H_FONCTIONHORS

#include "xxx.h"
#include "flou.h"

using namespace std;

//constantes pour le menu 
const int CHOIX_TREIZE = 13;
const int CHOIX_DOUZE = 12;
const int CHOIX_ONZE=11;
const int CHOIX_DIX=10;
const int CHOIX_NEUF = 9;
const int CHOIX_HUIT = 8;
const int CHOIX_SEPT = 7;
const int CHOIX_SIX=6;
const int CHOIX_CINQ=5;
const int CHOIX_QUATRE=4;
const int CHOIX_TROIS=3;
const int CHOIX_DEUX=2;
const int CHOIX_UN=1;


void afficher_menu();
void loadPicture(const string &picture, vector<vector<unsigned>> &red,vector<vector<unsigned>> &green,vector<vector<unsigned>>  &blue);
                                        
void savePicture(Image&);
string saisir_nom_fichier(string&);
vector<int> couleur_souhaite();
//surcharge des opérateurs
vector<vector<unsigned>> operator*(vector<vector<unsigned>>&,vector<vector<unsigned>>&);
vector<vector<unsigned>> operator*(unsigned int,vector<vector<unsigned>>&);
vector<vector<unsigned>> operator*(float,vector<vector<unsigned>>&);
vector<vector<unsigned>> operator*(vector<vector<unsigned>>&,unsigned int);
vector<vector<unsigned>> operator*(vector<vector<unsigned>>&,float);
vector<vector<float>> operator*(vector<vector<float>>&,float);




#endif
