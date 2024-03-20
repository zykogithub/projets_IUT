#include "xxx.h"
#include "fonctions_hors_de_la_classe.h"



using namespace std;

//classe Image
    //fiche NBGC
//constructeur avec les vecteurs couleurs
    Image::Image(vector<vector<unsigned>> &rouge,vector<vector<unsigned>> &vert, vector<vector<unsigned>> &bleu)
    {
        _rouge=rouge;
        _vert=vert;
        _bleu=bleu;

        for (size_t i = 0; i < _rouge.size(); i++)
        {
                if (!((_rouge.size()==_vert.size() && _vert.size()==_bleu.size())))
            {
                throw(logic_error("Les vecteurs ne sont pas de la même taile. Il sera donc impossible de les superposer pour créer une image en couleur"));

            }
            for (size_t j = 0; j < _rouge[i].size(); j++)
            {
                if ((!((_rouge[i].size()==_vert[i].size() && _vert[i].size()==_bleu[i].size()))) || (!((_rouge[i][j]>=0 && _rouge[i][j]<=255)&& (_vert[i][j]>=0 && _vert[i][j]<=255) && (_bleu[i][j]>=0 && _bleu[i][j]<=255))))
                {   
                    if (!((_rouge[i].size()==_vert[i].size() && _vert[i].size()==_bleu[i].size())))
                    {
                    throw(logic_error("Les vecteurs ne sont pas de la même taile. Il sera donc impossible de les superposer pour créer une image en couleur"));
                    }
                    else
                    {
                        if (_rouge[i][j]<0 || _rouge[i][j]>255)
                        {
                            throw(invalid_argument("La composante rouge a une valeur invalide"));
                        }
                        else if (_rouge[i][j]>0 || _rouge[i][j]>255)
                        {
                            throw(invalid_argument("La composante verte a une valeur invalide"));
                        }
                        else if (_bleu[i][j]<0 || _bleu[i][j]>255)
                        {
                            throw(invalid_argument("La composante rouge a une valeur invalide"));
                        }

                    }

                }
            }

        }
        _longueur = _rouge.size()-1;
        _largeur = _rouge[0].size()-1;





    }
// constructeur avec les dimensions
    Image::Image(unsigned longueur,unsigned largeur){
        _longueur = longueur;
        _largeur = largeur;
        vector<vector<unsigned>> _rouge (_longueur);
        vector<vector<unsigned>> _vert(_longueur);
        vector<vector<unsigned>> _bleu(_longueur);
        for (size_t i = 0; i < _longueur; i++)
        {
            for (size_t j = 0; j < _largeur; j++)
            {
                _rouge[i][j]=0;
                _vert[i][j]=0;
                _bleu[i][j]=0;
            }

        }

    }
// constructeur vide
    Image::Image()
    {
        vector<vector<unsigned>> _rouge(0);
        vector<vector<unsigned>> _bleue(0);
        vector<vector<unsigned>> _vert(0);
        _longueur=0;
        _largeur=0;
    }
// construteur avec le nom d'un fichier   
    Image::Image(const string &nomfichier){
        loadPicture(nomfichier,_rouge,_vert,_bleu);
    }

//affiche une image
    void Image::affiche()
    {
        cout << "vecteur rouge : ";
        for (unsigned i = 0; i < _rouge.size(); i++)
        {
            for (unsigned j = 0; j< _rouge[i].size(); j++)
            {
                cout << _rouge[i][j] << " ";
            }
            cout << endl;
        }
        cout << "vecteur vert : ";
        for (unsigned i = 0; i < _vert.size(); i++)
        {
            for (unsigned j = 0; j < _vert[i].size(); j++)
            {
                cout << _vert[i][j] << " ";
            }
            cout << endl;
        }

        cout << "vecteur bleue : ";
        for (unsigned i = 0; i < _bleu.size(); i++)
            for (unsigned j = 0; j < _bleu[i].size(); j++)
                cout << _bleu[i][j] << " ";
            cout << endl;
    }
//permettant de créer une 
//nouvelle image à partir de l’image cible qui a la même composante Rouge et les autres 
// composantes à zéro.   
    Image Image::composanteRouge() 
    {
        Image image_retourne;
        for (size_t i = 0; i < _rouge.size(); i++)
        {
            image_retourne._rouge[i]=_rouge[i];

        }
        return image_retourne;
    }
    //change 
    Image Image::niveauxGris() 
    {
        Image image_retourne;
        for (size_t i = 0; i < _rouge.size(); i++) 
        {
          for (size_t j = 0; i < _rouge[i].size(); i++) 
          {
            float moy = ((_rouge[i][j] + _vert[i][j] + _bleu[i][j]) / 3);
            image_retourne._rouge[i][j]=moy;
            image_retourne._vert[i][j] = moy;
            image_retourne._bleu[i][j] = moy;
          }
        }
        return image_retourne;
    }
    Image Image::noirEtBlanc() 
    {
        Image image_a;
        for (size_t i = 0; i < _rouge.size(); i++) {
          for (size_t j = 0; i < _rouge[i].size(); i++) {
            if (niveauxGris()._rouge[i][j] > 150) 
            {
                image_a._rouge[i][j] = 255;
                image_a._vert[i][j]=255;
                image_a._bleu[i][j] = 255;
            } 
            else 
            {
                image_a._rouge[i][j] = 0;
            }
          }
        }
        return image_a;
    }
// retourne vrai si il existe un pixel de cette couleur dans l’image cible.
    bool Image::detection(unsigned r, unsigned v, unsigned b) 
    {
        unsigned i = 0;
        unsigned j = 0;
        bool verification = false;
        while (i < _rouge.size())
          while ( !verification && j < _rouge[i].size()) {
              i++;
              j++;
              verification = (_rouge[i][j] == r) && (_vert[i][j] == v) && (_bleu[i][j] == b);
            }
        if (verification) {
          return true;
          }
        else {
          return false;
        }
    }
    Image Image::copie(){
        Image image_prime = *this;
        return image_prime;
    }
// fonction pour modifier une image
//vector <unsigned> histogrammeGris();// retournant un vecteur d’entiers représentant l’histogramme de l’image en niveau de gris. 
//Un niveau de gris est une valeur comprise entre 0 et 255. Cet histogramme peut être représenté en C++ en utilisant un vecteur : 
// dans la case d’indice i on inscrit le nombre de pixels dont le niveau de gris est i.     
/*fonction pour modifier l'image en soit*/
    void Image::modification(){
    }
//rogne une partie de l'image dans différents côtés 
    Image Image::rogner(){
        Image res;
        unsigned nb_pixel;
        int choix_du_rogne;
        cout << "donnez le nombre de pixel à rogner : ";
        cin >> nb_pixel;
        cout << "tapez 1 pour rogner en haut, 2 pour rogner en bas, 3 pour rogner à droite et 4 pour rogner à gauche";
        cin >> choix_du_rogne;

        switch (choix_du_rogne)
        {
        case 1:
            while (nb_pixel>getlongeur())
            {
                cout << "donnez le nombre de pixel à rogner valide : ";
                cin >> nb_pixel;
            }
            res = rognerB(nb_pixel);

            break;
        case 2:
            while (nb_pixel>getlargeur())
            {
                cout << "donnez le nombre de pixel à rogner valide : ";
                cin >> nb_pixel;
            }
            res = rognerD(nb_pixel);

            break;
        case 3:
            while (nb_pixel>getlargeur())
            {
                cout << "donnez le nombre de pixel à rogner valide : ";
                cin >> nb_pixel;
            }
            res = rognerG(nb_pixel);

            break;
        case 4:
            while (nb_pixel>getlongeur())
            {
                cout << "donnez le nombre de pixel à rogner valide : ";
                cin >> nb_pixel;
            }
            res = rognerH(nb_pixel);
            break;
        default:
            throw(2);// choix inexistant
            break;
        }
        afficher_menu();
        return res;
    }
    Image Image::rognerD(unsigned nb){
        Image image_retourne = copie();
        for (size_t i = 0; i < _longueur; i++)
        {
           for (size_t j = _largeur; j > nb; j--)
           {
                image_retourne._rouge[i].pop_back();
                image_retourne._bleu[i].pop_back();
                image_retourne._vert[i].pop_back();
           }

        }
        return image_retourne;

    }
    Image Image::rognerG(unsigned nb){
        Image image_retourne = copie();
        for (size_t i = 0; i < _longueur; i++)
        {
           for (size_t j = 0; j < nb; j++)
           {
                image_retourne._rouge[i].pop_back();
                image_retourne._bleu[i].pop_back();
                image_retourne._vert[i].pop_back();
           }

        }
        return image_retourne;

    }
    Image Image::rognerH(unsigned nb){
        Image image_retourne = copie();
        for (size_t i = 0; i < nb; i++)
        {
           image_retourne._rouge.pop_back();
           image_retourne._bleu.pop_back();
           image_retourne._vert.pop_back();
        }
        return image_retourne;

    }
    Image Image::rognerB(unsigned nb){
        Image image_retourne = copie();
        for (size_t i = _longueur; i > nb; i--)
        {
           image_retourne._rouge.pop_back();
           image_retourne._bleu.pop_back();
           image_retourne._vert.pop_back();
        }
        return image_retourne;

    }
// donner une teinte de la couleur r,v,b à l'image
    Image Image::teinte(unsigned r,unsigned v,unsigned b){
        vector<vector<unsigned>> rouge,vert,bleu;
        for (size_t i = 0; i < _longueur; i++)
        {
            for (size_t j = 0; j < _largeur; j++)
            {
                rouge[i][j]+=r;
                vert[i][j]+=v;
                bleu[i][j]+=b;
                if (rouge[i][j]>255 || rouge[i][j]<0)
                {
                    if(rouge[i][j]>255)rouge[i][j]=255;
                    else rouge[i][j]=0;
                }
                if (vert[i][j]>255 || vert[i][j]<0)
                {
                    if(vert[i][j]>255)vert[i][j]=255;
                    else vert[i][j]=0;
                }
                if (bleu[i][j]>255 || bleu[i][j]<0)
                {
                    if(bleu[i][j]>255)bleu[i][j]=255;
                    else bleu[i][j]=0;
                }
            }

        }

        return Image(rouge,vert,bleu);
    }  
//retourne une image de différents côtés par rapport à l'axe vertical ou horizontal    
   Image Image::rotation(){
        int choix_de_la_rotation;
        Image res;
        cout << "tapez 1 pour rotation en haut, \n 2 pour rotation en bas, \n 3 pour rotation à droite et \4 pour rotation à gauche ";
        cin >> choix_de_la_rotation;
        switch (choix_de_la_rotation)
        {
        case 1:
            res = rotationH();
            break;
        case 2:
            res = rotationB();
            break;
        case 3:
            res = rotationD();
            break;
        case 4:
            res = rotationG();
            break;
    
        default:
            throw(2);// choix inexistant
            break;
        }
        afficher_menu();
        return res;
    }
    Image Image::rotationH(){
        vector<vector<unsigned>> rouge,vert,bleu;
        rouge = getrouge();
        vert = getvert();
        bleu = getbleu();
        for (size_t i = 0; i<getlargeur()/2 ; i++){ // une fois la moitié 
        // atteinte, toutes les cases sont inverséées, en allant au-délà, on
        //  inverserai les cases de la deuxième partie entre elles.
        // Donc, il faut arrêter le parcours à la moitié de l'image pour éviter
        // cela.
                vector<unsigned> tempR, tempV, tempB;
                tempR = rouge[i];
                tempV = vert[i];
                tempB = bleu[i];
                rouge[i] = rouge[rouge.size()-1-i];
                vert[i] = vert[vert.size()-1-i];
                bleu[i] = bleu[bleu.size()-1-i];
                rouge[rouge.size()-1-i] = tempR;
                vert[vert.size()-1-i] = tempV;
                bleu[bleu.size()-1-i] = tempB;
        
        }
        return Image(rouge,vert,bleu);
    }
    Image Image::rotationB(){
        vector<vector<unsigned>> rouge,vert,bleu;
        rouge = getrouge();
        vert = getvert();
        bleu = getbleu();
        for (size_t i = 0; i<getlongeur()/2 ; i++){
                vector<unsigned>tempR,tempV,tempB;
                tempR = rouge[rouge.size()-1-i];
                tempV = vert[vert.size()-1-i];
                tempB = bleu[bleu.size()-1-i];
                rouge[rouge.size()-1-i] = rouge[i];
                vert[vert.size()-1-i] = vert[i];
                bleu[bleu.size()-1-i] = bleu[i];
                rouge[i] = tempR;
                vert[i] = tempV;
                bleu[i] = tempB;
            
        }
        return Image(rouge,vert,bleu);
    }
    Image Image::rotationD(){
        vector<vector<unsigned>> rouge,vert,bleu;
        rouge = getrouge();
        vert = getvert();
        bleu = getbleu();
        for (size_t i = 0; i<getlongeur() ; i++){
            for (size_t j =0; j<getlargeur()/2; j++){ // une fois la moitié 
        // atteinte, toutes les cases sont inverséées, en allant au-délà, on
        //  inverserai les cases de la deuxième partie entre elles.
        // Donc, il faut arrêter le parcours à la moitié de l'image pour éviter
        // cela.
                unsigned tempR,tempV,tempB;
                tempR = rouge[i][j];
                tempV = vert[i][j];
                tempB = bleu[i][j]; 
                rouge[i][j] = rouge[i][rouge[i].size()-1-j];
                vert[i][j] = vert[i][vert[i].size()-1-j];
                bleu[i][j] = bleu[i][bleu[i].size()-1-j];
                rouge[i][rouge[i].size()-1-j] = tempR;
                vert[i][vert[i].size()-1-j] = tempV;
                bleu[i][bleu[i].size()-1-j] = tempB;
            }
        }
        return Image(rouge,vert,bleu);
    }
    Image Image::rotationG(){
        vector<vector<unsigned>> rouge,vert,bleu;
        rouge = getrouge();
        vert = getvert();
        bleu = getbleu();
        for (size_t i = 0; i<getlongeur() ; i++){
            for (size_t j =0; j<getlargeur()/2; j++){
                unsigned tempR,tempV,tempB;
                tempR = rouge[i][rouge[i].size()-1-j];
                tempV = vert[i][vert[i].size()-1-j];
                tempB = bleu[i][bleu[i].size()-1-j]; 
                rouge[i][rouge[i].size()-1-j] = rouge[i][j];
                vert[i][vert[i].size()-1-j] = vert[i][j];
                bleu[i][bleu[i].size()-1-j] = bleu[i][j];
                rouge[i][j] = tempR;
                vert[i][j] = tempV;
                bleu[i][j] = tempB;
            }
        }
        return Image(rouge,vert,bleu);
    }
// changement de la luminosité de l'image
    Image Image::luminosity(){
        float val;
    
        do
        {
            cout << "De combien voulez-vous changer la luminosite de l'image ?" << endl;
            cin >> val;
        } while (val<0.0);
        if(val>1) return luminosityUp(val);
        else return luminosityDown(val);
    
    }
    Image Image::luminosityUp(float facteur){

    vector<vector<unsigned int>> rouge = facteur*_rouge;
    vector<vector<unsigned int>> vert = facteur*_vert;
    vector<vector<unsigned int>> bleu = facteur*_bleu;
    for (size_t i = 0; i < _longueur; i++)
    {
        for (size_t j = 0; j < _largeur; j++)
        {
            if (rouge[i][j]>255 || rouge[i][j]<0)
            {
                if(rouge[i][j]>255)rouge[i][j]=255;
                else rouge[i][j]=0;
            }
            if (vert[i][j]>255 || vert[i][j]<0)
            {
                if(vert[i][j]>255)vert[i][j]=255;
                else vert[i][j]=0;
            }
            if (bleu[i][j]>255 || bleu[i][j]<0)
            {
                if(bleu[i][j]>255)bleu[i][j]=255;
                else bleu[i][j]=0;
            }   
        }  
    }

    return Image(rouge,vert,bleu);

}
    Image Image::luminosityDown(float facteur){

    vector<vector<unsigned int>> rouge = facteur*_rouge;
    vector<vector<unsigned int>> vert = facteur*_vert;
    vector<vector<unsigned int>> bleu = facteur*_bleu;
    for (size_t i = 0; i < _longueur; i++)
    {
        for (size_t j = 0; j < _largeur; j++)
        {
            if (rouge[i][j]>255 || rouge[i][j]<0)
            {
                if(rouge[i][j]>255)rouge[i][j]=255;
                else rouge[i][j]=0;
            }
            if (vert[i][j]>255 || vert[i][j]<0)
            {
                if(vert[i][j]>255)vert[i][j]=255;
                else vert[i][j]=0;
            }
            if (bleu[i][j]>255 || bleu[i][j]<0)
            {
                if(bleu[i][j]>255)bleu[i][j]=255;
                else bleu[i][j]=0;
            }
        }    
    }

    return Image(rouge,vert,bleu);
}

    Image Image::contraste(){

        float val;
        do
        {
            cout << "De combien voulez-vous changer le contraste de l'image ?" << endl;
            cin >> val;
        } while (val<0.0);
        if(val>1) return contrasteUp(val);
        else return contrasteDown(val);

    }
    Image Image::contrasteUp(float facteur){
    vector<vector<unsigned int>> rouge, vert , bleu ;
    for (size_t i = 0; i < _longueur; i++)
    {
        for (size_t j = 0; j < _largeur; j++)
        {
            rouge[i][j] = _rouge[i][j]+facteur*abs(int(_rouge[i][j])-128);
            vert[i][j] = _vert[i][j]+facteur*abs(int(_vert[i][j])-128);
            bleu[i][j] = _bleu[i][j]+facteur*abs(int(_bleu[i][j])-128);
            if (rouge[i][j]>255 || rouge[i][j]<0)
            {
                if(rouge[i][j]>255)rouge[i][j]=255;
                else rouge[i][j]=0;
            }
            if (vert[i][j]>255 || vert[i][j]<0)
            {
                if(vert[i][j]>255)vert[i][j]=255;
                else vert[i][j]=0;
            }
            if (bleu[i][j]>255 || bleu[i][j]<0)
            {
                if(bleu[i][j]>255)bleu[i][j]=255;
                else bleu[i][j]=0;
            }
        }

    }
    return Image(rouge,vert,bleu);
}
    Image Image::contrasteDown(float facteur){
    vector<vector<unsigned int>> rouge, vert , bleu ;
    for (size_t i = 0; i < _longueur; i++)
    {
        for (size_t j = 0; j < _largeur; j++)
        {
            rouge[i][j] = _rouge[i][j]+facteur*abs(int(_rouge[i][j])-128);
            vert[i][j] = _vert[i][j]+facteur*abs(int(_vert[i][j])-128);
            bleu[i][j] = _bleu[i][j]+facteur*abs(int(_bleu[i][j])-128);
            if (rouge[i][j]>255 || rouge[i][j]<0)
            {
                if(rouge[i][j]>255)rouge[i][j]=255;
                else rouge[i][j]=0;
            }
            if (vert[i][j]>255 || vert[i][j]<0)
            {
                if(vert[i][j]>255)vert[i][j]=255;
                else vert[i][j]=0;
            }
            if (bleu[i][j]>255 || bleu[i][j]<0)
            {
                if(bleu[i][j]>255)bleu[i][j]=255;
                else bleu[i][j]=0;
            }
        }

    }
    return Image(rouge,vert,bleu);
}
Image Image::applicationFiltre(){
    string choix;
    Image res;
    string reponse;
    cout << "Connaissez-vous le rayon du filtre que vous voulez appliquer ?\n\
    Tapez O pour oui , N pour non " << endl;
    cin >> reponse;
    choix+=reponse;
    cout << "Connaissez-vous le filtre à appliquer ?\n\
    Tapez O pour oui , N pour non " << endl;
    cin >> reponse;
    choix+=reponse;

    if  (choix=="OO"){
        int rayon_a;
        float contenu_action;
        vector<vector<float>> action_a;
        cout << "Donnez le rayon du filtre :  "<< endl;
        cin >> rayon_a;
        for (size_t i = 0; i < rayon_a*2; i++)
        {
            for (size_t j = 0; j < rayon_a*2; j++)
            {
                cout << "Veuillez entrer des valeurs pour votre filtre . \
                Veillez bien à entrer des chiffres à virgule sur la forme : a.b ou sous la forme a/b" << endl;
                cin >> contenu_action;
                action_a[i].push_back(contenu_action);
            }
        }
        flou filtre_a(action_a,rayon_a);
        cout << "filtre créé."<< endl;
        cout << "Application du filtre en fonction du flou choisi" << endl;
        res = filtre_a.application(*this);
    }
    else if  (choix=="NN"){
        flou filtre_b;
        cout << "filtre créé."<< endl;
        cout << "Application du filtre en fonction du flou choisi" << endl;
        res = filtre_b.application(*this);
        }
    else if  (choix=="NO"){
        float contenu_action;
        int rayon, nb_valeur;
        vector<vector<float>> action_b;
        cout << "Un filtre est un matrice carré de taille impair. Ainsi, combien de valeurs allez-vous entrer pour votre filtre ?" << endl;
      cin >> nb_valeur;
      rayon = sqrtl(nb_valeur)/2;
      while(rayon%2!=0){
        cout << "Veuillez entrer un nombre valide." << endl;
        cin >> nb_valeur;
      }
        for (size_t i = 0; i < rayon; i++)
        {
            for (size_t j = 0; j < rayon; j++)
            {
                cout << "Veuillez entrer des valeurs pour votre filtre . \
                Veillez bien à entrer des chiffres à virgule sur la forme : x.x ." << endl;
                cin >> contenu_action;
                action_b[i].push_back(contenu_action);
            }
        }
        flou filtre_c(action_b);
        cout << "filtre créé."<< endl;
        cout << "Application du filtre en fonction du flou choisi" << endl;
        res = filtre_c.application(*this);
        }
    else if  (choix=="ON"){
        int rayon;
        cout << "Donnez le rayon du filtre :  "<< endl;
        cin >> rayon;
        flou filtre_d(rayon);
        cout << "filtre créé." << endl;
        cout << "Application du filtre en fonction du flou choisi" << endl;
        res = filtre_d.application(*this);
        }
    else throw (2);

    return res;
}
/*Image Image::contourSobel(){
    vector<vector<float>> gx= {{float(0),float(-1),float(0)},{float(-1),float(5),float(-1)},{float(0),float(-1),float(0)}};
    vector<vector<float>> gy = {{float(-1),float(-2),float(-1)},{float(0),float(0),float(0)},{float(1),float(2),float(1)}};
    vector<vector<unsigned>> r,v,b;
    for (size_t i = 0; i < getlongeur(); i++)
    {
        for (size_t j = 0; j < getlargeur(); j++)
        {
            r[i][j],v[i][j],b[i][j] = sqrt(pow(gx[i][j],2)+pow(gy[i][j],2));
        }
        
    }
    return Image(r,v,b);
    
}*/

//  effectue un changement de teinte de l’image de la façon suivante 
// : le rouge devient vert, le vert devient bleu et le bleu devient rouge.
Image Image::rotationCouleur(){
    vector<vector<unsigned>> r,v,b;
    unsigned temp=0;
    r = _rouge;
    v = _vert;
    b =  _bleu;
    for (size_t i = 0; i < _longueur; i++)
    {
        for (size_t j = 0; j < _largeur; j++)
        {
            temp = r[i][j];
            r[i][j] = v[i][j];
            v[i][j] = b[i][j];
            b[i][j] = temp;
        }
        
    }
    
    return Image(r,v,b);
}

// prend en paramètre 3 entiers représentant le code RGB d’une couleur. La méthode cherche 
// tous les pixels dont la couleur est « proche » de cette couleur et change la couleur de 
// ces pixels par du blanc. 
// Une couleur (r,v,b) sera proche d’une couleur (rouge,vert,bleu) si  
//  (sqrt((rouge−r)²+(vert−v)²+(bleu−b)²))/ 
//  (sqrt(rouge²+vert²+bleu²)) ≤ 0,1.
Image Image::blanchirCouleur (unsigned r, unsigned v,unsigned b){
    vector<vector<unsigned>> ra,va,ba;
    ra = _rouge;
    va = _vert;
    ba =  _bleu;
    for (size_t i = 0; i < _longueur; i++)
    {
        for (size_t j = 0; j < _largeur; j++)
        {
            if ((sqrt(pow(r-_rouge[i][j],2)+pow(v-_vert[i][j],2)+pow(b-_bleu[i][j],2)))/(sqrt(pow(r,2)+pow(v,2)+pow(b,2)))<=0.1)
            {
                ra[i][j],va[i][j],ba[i][j] = 0;
            }
            
        }
        
    }
    return Image (ra,va,ba);
}