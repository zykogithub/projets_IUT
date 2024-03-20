#include "flou.h"
#include "fonctions_hors_de_la_classe.h"


using namespace std;
//différents constructeurs pour tous les cas possibles
flou::flou(vector<vector<float>>&filtre)
{
    _action = filtre;
    _rayon = int(_action.size()/2);

}
//différents constructeurs pour tous les cas possibles
flou::flou(int rayon)
{
    int choix;
    vector<vector<float>> action;
    _rayon = rayon;
    cout << "Veuillez choisir votre filtre : \
    Tapez 1 pour avoir un flou gossien, 2 flou gradien, 3 un autre flou gradient\
    4 un flou contrasté, 5 pour un filtre par défault " << endl;
    cin >> choix;
    switch (choix)
    {
    case 1:

        if(rayon==2){action = {{float(1.0),float(4.0),float(6.0),float(4.0),float(1.0)},{float(4),float(16),float(24),float(16),float(4)},{float(6),float(24),float(36),float(24),float(6)},{float(4),float(16),float(24),float(16),float(4)},{float(1),float(4),float(6),float(4),float(1)}};
        float gossien = 1/256;
        action = action*gossien;}
        else throw(1);
        break;
    case 2:
        if(rayon==1)action = {{float(-1),float(0),float(1)},{float(-2),float(0),float(2)},{float(-1),float(0),float(1)}};
        else throw(1);
        break;
    case 3:
        if(rayon==1)action = {{float(-1),float(-2),float(-1)},{float(0),float(0),float(0)},{float(1),float(2),float(1)}};
        else throw(1);
        break;
    case 4:
        if(rayon==1)action = {{float(0),float(-1),float(0)},{float(-1),float(5),float(-1)},{float(0),float(-1),float(0)}};
        else throw(1);
        break;
    case 5:
        if(rayon==1)action = {{float(0.0625),float(0.125),float(0.0625)},{float(0.125),float(0.25),float(0.125)},{float(0.0625),float(0.125),float(0.0625)}};
        else throw(1);
        break;
    default:
        throw(2); // choix inexistant
        break;
    }
    _action = action;

}
flou::flou(){
    vector<vector<float>> action;
    int choix;
    _rayon = int(_action.size()/2);
    cout << "Veuillez choisir votre filtre : \
    Tapez 1 pour avoir un flou gossien, 2 flou gradien, 3 un autre flou gradient\
    4 un flou contrasté, 5 un flou par défaut " << endl;
    cin >> choix;
    switch (choix)
    {
    case 1:

        if(_rayon==2){action = {{float(1.0),float(4.0),float(6.0),float(4.0),float(1.0)},{float(4),float(16),float(24),float(16),float(4)},{float(6),float(24),float(36),float(24),float(6)},{float(4),float(16),float(24),float(16),float(4)},{float(1),float(4),float(6),float(4),float(1)}};
        float gossien = 1/256;
        action = action*gossien;}
        else throw(1);
        break;
    case 2:
        if(_rayon==1)action = {{float(-1),float(0),float(1)},{float(-2),float(0),float(2)},{float(-1),float(0),float(1)}};
        else throw(1);
        break;
    case 3:
        if(_rayon==1)action = {{float(-1),float(-2),float(-1)},{float(0),float(0),float(0)},{float(1),float(2),float(1)}};
        else throw(1);
        break;
    case 4:
        if(_rayon==1)action = {{float(0),float(-1),float(0)},{float(-1),float(5),float(-1)},{float(0),float(-1),float(0)}};
        else throw(1);
        break;
    case 5:
        if(_rayon==1)action = {{float(0.0625),float(0.125),float(0.0625)},{float(0.125),float(0.25),float(0.125)},{float(0.0625),float(0.125),float(0.0625)}};
        else throw(1);
        break;
    default:
        throw(2); // choix inexistant
        break;
    }
    _action = action;
    

}
//différents constructeurs pour tous les cas possibles
flou::flou(vector<vector<float>>&filtre,int rayon)
{
    _action = filtre;
    if (rayon==int(_action.size()/2))
    {
        _rayon = rayon;
    }
    else
    {
        cerr << "Le rayon passé en paramètre est de la mauvaise taille. Génération d'un rayon d'un code de la bonne taille en cours" << endl;
        _rayon = int(_action.size()/2);
    }
    
}
//L’image créée est l’image résultante de 
//l’application du filtre sur l'image en paramètre.
Image flou::application(Image &image){

    vector<vector<unsigned>> rouge = image.getrouge();
    vector<vector<unsigned>> vert = image.getvert();
    vector<vector<unsigned>> bleu = image.getbleu();
    vector<vector<float>> action = getAction();
    int rayon = getRayon();
    /*on entourne les vecteurs rouge, vert et bleu de rayon 0 afin que lors du 
    calcul du filtre si on tombe sur un bord, alors cela sera multiplié par 0. */
    for (size_t i = 0; i < image.getlongeur()+rayon; i++)
    {
        for (size_t j = 0; j < image.getlargeur()+rayon; j++)
        {
            if (i-rayon<0 || i+rayon>rouge.size())
            {
                rouge.push_back(vector<unsigned>(0,rouge.size()));
                vert.push_back(vector<unsigned>(0,rouge.size()));
                bleu.push_back(vector<unsigned>(0,rouge.size()));
                if (i-rayon<0)
                {   
                    rouge[0],rouge[rouge.size()-1] = rouge[rouge.size()-1],rouge[0];
                    vert[0],vert[rouge.size()-1] = vert[rouge.size()-1],vert[0];
                    bleu[0],bleu[rouge.size()-1] = bleu[rouge.size()-1],bleu[0];
                }
            }
            else if (j-rayon<0 || j+rayon>rouge[i].size())
            {
                rouge[i].push_back(0);
                vert[i].push_back(0);
                bleu[i].push_back(0);
                if (j-rayon<0){
                    rouge[i][0],rouge[i][rouge.size()-1] = rouge[i][rouge.size()-1],rouge[i][0];   
                    vert[i][0],vert[i][rouge.size()-1] = vert[i][rouge.size()-1],vert[i][0];
                    bleu[i][0],bleu[i][rouge.size()-1] = bleu[i][rouge.size()-1],bleu[i][0];
                }        
            }
        
        }
    }
    vector<vector<unsigned>> r,v,b;
    /*les vecteurs r,v,b seront les vecteurs renvoyés. Ce sont eux qui recevront
    les résultats des calculs des filtres. */
    for (size_t i = 0; i < image.getlongeur(); i++)
    {
        for (size_t j = 0; j < image.getlargeur(); j++)
        {
            for (size_t k = 0; k < action.size(); k++)
            {
                for (size_t l = 0; l < action[k].size(); l++)
                {
                    r[i][j]+=action[k][l]*rouge[i+k][j+l];
                    v[i][j]+=action[k][l]*vert[i+k][j+l];
                    b[i][j]+=action[k][l]*bleu[i+k][j+l];
                }
                
            }
            
        }
        
    }
    return Image(r,v,b);
    
}
