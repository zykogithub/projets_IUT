#ifndef FLOU_H_FLOU
#define FLOU_H_FLOU

#include "xxx.h"

using namespace std;

class flou
{
private:
    vector<vector<float>> _action;
    int _rayon;
public:
    flou(vector<vector<float>>&);
    flou(int rayon);
    flou(vector<vector<float>>&filtre,int rayon);
    Image application(Image&);
    //getteurs
    vector<vector<float>> getAction(){return _action;};
    int getRayon(){return _rayon;};
    flou();
    
};


#endif