# projet-jeux-olympique

## Consigne

- Veuillez créer une branche pour chaque membre
- Voici les fichiers à modifier dans la branche main :
  - README
  - .gitignore
- Les autres fichiers sont à modifier dans votre branche
- Procédure pour gérer les branches :

```bash
git branch branche_Prénom # créer une branche
git switch branche_Prénom # pour changer de branche
git push --set-upstream origin branche_Prénom # pour créer un dépot distant de la branche créer

#pour fusionner votre branche avec la branche principal
git switch branche_Prénom 
git merge main 
```

- En cas de soucis ou de question, veuillez me prévenir pour gérer cela
- Ne pas oublier de push dans votre branche aussi
- Ne pas oublier de fusionner votre branche avec la main lorsqu'il y a un changement dans la branche main
- Veuillez ne pas commit sur les branches des autres personnes sans leur autorisation
- On fusionnera nos branches vers la branche main lors de la fin du projet
- Dans le drive, ce n'est pas un clone de la repo mais uniquement une version téléchargée. Cette version sera mise à jour ainsi car desktop.ini empeche tout push et pull
  
## Répartition du travail

- Naherry : controleur pour gestion athlete, gestion equipe et emploi du temps
- Ambre : vue emploi du temps
- Lyanne : vue gestion des equipe et des athlete
- Yousra : modèle gestion equipe, gestion athlete et emploi du temps
