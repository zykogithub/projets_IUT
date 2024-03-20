#include "xxx.h"
#include "flou.h"
#include "fonctions_hors_de_la_classe.h"

using namespace std;

int main(){
//initialisation
    vector<vector<unsigned>> rouge = {{30,10,255,255},\
                                    {0,21,78,0}};
    vector<vector<unsigned>>vert =  {{0,0,25,16},\
                                    {0,255,255,0}};
    vector<vector<unsigned>> bleu = {{0,45,200,255},\
                                    {0,255,255,45}};
    Image image_a(rouge, vert, bleu);
    //string nomfichier = saisir_nom_fichier();
    //Image image_b(nomfichier);

//debut
    //image_a.affiche();
    afficher_menu();
    vector<vector<float>> action = {{0.0625,0.125,0.0625},{0.125,0.25,0.125},{0.0625,0.125,0.0625}};
    // rayon de 1
    flou flouG3(action);
//gestion des erreurs%
    try
    {   
        
        vector<vector<unsigned>> rouge,vert,bleu;
        Image image_beugue= Image(rouge,vert,bleu);
        savePicture(image_beugue);

    }
    catch(invalid_argument except)
    {
        cerr << except.what() << endl;
    }
    try
    {
        afficher_menu();
        flou filtre_beug(1);
        vector<vector<unsigned>> rouge,vert,bleu;
        Image image_beugue= Image(rouge,vert,bleu);
        image_beugue.applicationFiltre();

    }
    catch(int except)
    {
        switch (except){
        
        case 1:
            cerr <<  "Le rayon donnée ne correspond pas au filtre choisi."<< '\n';
            break;
        case 2:
            cerr << "Le choix effectué n'existe pas." << '\n';
            break;
        
        case 3 :
            cerr << "L'image ne s'ouvre pas" << endl;
            break;
        }
        
    }
    
    return 0;
}