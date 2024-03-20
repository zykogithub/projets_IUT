#ifndef XXX_H_XXX
#define XXX_H_XXX

#include <iostream>
#include <vector>
#include <string>
#include <fstream> 
#include <stdlib.h>
#include <cmath>

using namespace std;


                
class Image
{
private:
    
    vector<vector<unsigned>> _rouge;
    vector<vector<unsigned>> _vert;
    vector<vector<unsigned>> _bleu;
    unsigned _longueur; // nombre de vecteur dans la coleur
    unsigned _largeur; // nombre d'élément dans les vecteur de vecteurs
public:
    //fiche NBGC
    Image(vector<vector<unsigned>>&,vector<vector<unsigned>>&,vector<vector<unsigned>>&);
    Image(const string&);
    Image();
    Image(unsigned,unsigned);
    Image composanteRouge() ;
    void affiche();
    Image noirEtBlanc();
    Image niveauxGris();
    bool detection(unsigned , unsigned , unsigned );
    Image copie();
    void modification(); 
    Image rogner();
    Image rotation();
    Image rognerD(unsigned);
    Image rognerG(unsigned);
    Image rognerH(unsigned);
    Image rognerB(unsigned);
    Image rotationD();
    Image rotationH();
    Image rotationB();
    Image rotationG();
    Image luminosity();
    Image luminosityUp(float);
    Image luminosityDown(float);
    Image contraste();
    Image contrasteUp(float);
    Image contrasteDown(float); 
    Image teinte(unsigned,unsigned,unsigned);
    Image applicationFiltre();
    Image rotationCouleur();
    Image blanchirCouleur(unsigned r, unsigned v,unsigned b);
    // Image contourSobel();
    //methodes get 
    vector<vector<unsigned>> getrouge() {return _rouge;};
    vector<vector<unsigned>> getvert() {return _vert;};
    vector<vector<unsigned>> getbleu() {return _bleu;};
    unsigned getlongeur(){return _longueur;};
    unsigned getlargeur(){return _largeur;};



};

#endif