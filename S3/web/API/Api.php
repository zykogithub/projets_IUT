<?php

require_once "config/connexion.php";
include "ActionPossible.php";
include "CodeDeRetourApi.php";

/**
 * 
 * Résumé de Api : classe qui collection les méthode api rest vers la base de donnée
 * toutes les actions avec cette api sont préparés, si votre requete n'a pas de parametre, 
 * donnez un tableau de parametre vide pour executer, sinon cela bloquera
 * 
 * pour obtenir le message d'erreur, exeuctez la methode getMessaDerreur() 
 * pour obtenir le code d'erreur, executez la méthode getCodeDerreur()
 * pour obtenir la valeur retournez par l'execution de la requete  executez la méthode getValeurRetourne()
 * pour obtenir le nombre de parametre d'une fonction de la base, executer la fonction nombreDeParametre($methode) avec $methode, un enum qui est l'une des action possibles
 */
class Api
{
    private CodeDeRetourApi $retourApi;
    private int $codeDeRetourApi;
    private string $messageDeRetour;
    private mixed $valeurRetourne;
    private array $arrayRetour; 

    /**
     * Donne le message d'erreur 
     * @return string
     */
    public function getMessaDerreur() : string{return $this->messageDeRetour;}
    /**
     * Donne le code d'erreur
     * @return int
     */
    public function getCodDeRetourApi() : int{return $this->codeDeRetourApi;}
    /**
     * Donne la valeur retourne par l'execution de la requete
     * @return mixed
     */
    public function getValeurRetourne() : mixed {return $this->valeurRetourne;}
    /**
     * Summary of serialize : affiche sous format json le code et le message de retour
     * @return string
     */
    private function serialize() : string{return json_encode($this->arrayRetour);}

    public function __construct()
    {
            $this->retourApi = CodeDeRetourApi::OK;
        $this->codeDeRetourApi = $this->retourApi->value;
        $this->messageDeRetour = "Aucune action effectuée";
        $this->valeurRetourne = null;
        $this->arrayRetour = [
            "code" => $this->codeDeRetourApi,
            "message" => $this->messageDeRetour,
            "data" => $this->valeurRetourne
        ];
    }
    
    
    /**
     * Résumé de Post : ajoute une nouvelle instance dans la base de donnée
     * @param array $parameters les parametres de la fonction utilisé
     * @param PostMethode|null $postMethode les actions post par défaut possible
     * @param string|null $requete : la requete SQL si aucune action par défault n'est utilisée
     
     **/
    public function post(array $parameters,
        ?PostMethode $postMethode = null,
        ?string $requete = null
    ): void
    {
        try {
            $this->verificationValeurDonne($postMethode,$requete);
            $requeteFinal = "";
            if ($postMethode != null)$requeteFinal = $postMethode->value;
            elseif ($requete != null ) {
                $this->verificationFormatage($parameters,$requete);
                $this->verificationBonneAction($requete,"/INSERT/i");
                $requeteFinal = $requete;
            }
            $pdo = $this->connexionBaseDeDonne();
            $this->valeurRetourne = $this->executeRequete($pdo,$requeteFinal,$parameters);
            
        } catch (Exception $e){
            $this->setParametreErreur($e->getCode(),$e->getMessage());
        }
        if ($this->codeDeRetourApi==CodeDeRetourApi::OK->value) $this->messageDeRetour = "Ressource crée avec succès";
        $this->reponsApi();
        
    }
    /**
     * Résumé de patch : met une partie d'une donnée de la base de donnée
     * @param array $parameters les parametres de la fonction utilisé
     * @param PatchMethode|null $patchMethode les actions patch par défaut possible
     * @param string|null $requete : la requete SQL si aucune action par défault n'est utilisée
     * 
     */
    public function patch(array $parameters,
        ?PatchMethode $patchMethode = null,
        ?string $requete = null
    ): void
    {
        
        try {
            $this->verificationValeurDonne($patchMethode,$requete);
            $requeteFinal = "";
            if ($patchMethode != null)$requeteFinal = $patchMethode->value;
            elseif ($requete != null ) {
                $this->verificationFormatage($parameters,$requete);
                $this->verificationBonneAction($requete,"/UPDATE/i");
                $requeteFinal = $requete;
            }
            $pdo = $this->connexionBaseDeDonne();
            $this->valeurRetourne = $this->executeRequete($pdo,$requeteFinal,$parameters);
            
        } catch (Exception $e){
            $this->setParametreErreur($e->getCode(),$e->getMessage());
        }
        if ($this->codeDeRetourApi==CodeDeRetourApi::OK->value) $this->messageDeRetour = "Ressource modifié avec succès";
        $this->reponsApi();
        
    }
    /**
     * Résumé de delete : supprimme une donnée de l'application
     * @param array $parameters les parametres de la fonction utilisé
     * @param DeleteMethode|null $deleteMethode les actions delete par défaut possible
     * @param string|null $requete : la requete SQL si aucune action par défault n'est utilisée
     */
    public function delete(array $parameters,
        ?DeleteMethode $deleteMethode = null,
        ?string $requete = null
    ): void
    {
        
        try {
            $this->verificationValeurDonne($deleteMethode,$requete);
            $requeteFinal = "";
            if ($deleteMethode != null)$requeteFinal = $deleteMethode->value;
            elseif ($requete != null ) {
                $this->verificationFormatage($parameters,$requete);
                $this->verificationBonneAction($requete,"/DELETE/i");
                $requeteFinal = $requete;
            }
            $pdo = $this->connexionBaseDeDonne();
            $this->valeurRetourne = $this->executeRequete($pdo,$requeteFinal,$parameters);
            
        } catch (Exception $e){
            $this->setParametreErreur($e->getCode(),$e->getMessage());
        }
        if ($this->codeDeRetourApi==CodeDeRetourApi::OK->value) $this->messageDeRetour = "Ressource supprimé avec succès";
        $this->reponsApi();
        
    }
    /**
     * Résumé de get : retourne une donnée de la base de donnée
     * @param array $parameters : les paramètres de la fonction utilisé
     * @param GetMethode|null $getMethode : les action get par défault possible     
     * @param string|null $requete : la requete SQL si aucune action par défault n'est utilisée
     * @return void
     */
    public function get(array $parameters,
        ?GetMethode $getMethode = null,
        ?string $requete = null
    ): void
    {
        
        try {
            $this->verificationValeurDonne($getMethode,$requete);
            $requeteFinal = "";
            if ($getMethode != null)$requeteFinal = $getMethode->value;
            elseif ($requete != null ) {
                $this->verificationFormatage($parameters,$requete);
                $this->verificationBonneAction($requete,"/SELECT/i");
                $requeteFinal = $requete;
            }
            $pdo = $this->connexionBaseDeDonne();
            $this->valeurRetourne = $this->executeRequete($pdo,$requeteFinal,$parameters);
            
        } catch (Exception $e){
            $this->setParametreErreur($e->getCode(),$e->getMessage());
        }
        if ($this->codeDeRetourApi==CodeDeRetourApi::OK->value) $this->messageDeRetour = "Ressource obtenu avec succès";
        $this->reponsApi();
        
    }
    /**
     * Envoie une réponse JSON formatée avec le code HTTP approprié.
     *
     * @param mixed|null $data Données facultatives.
     */
    private function reponsApi(mixed $data = null): void
    {
        

        $this->arrayRetour["code"] = $this->codeDeRetourApi;
        $this->arrayRetour["message"] = $this->messageDeRetour;
        $this->arrayRetour['data'] = $data !== null ? $data : "" ;

        
    }
    private function verificationValeurDonne(
        ?Methode $postMethode = null,
        ?string $requete = null
    ) : void
    {
        if (($postMethode == null && $requete == null)) throw new InvalidArgumentException("Aucune requete ou fonction donnée",CodeDeRetourApi::MauvaiseRequete->value);
    }
    private function executeRequete(PDO $pdo,
        string $requete,
        array $parameters
    ) : mixed
    {
        try {
            $requetePrepare = $pdo->prepare($requete,array(PDO::ATTR_CURSOR => PDO::CURSOR_SCROLL,PDO::ERRMODE_EXCEPTION=>true));
            $executionReussi = $requetePrepare->execute($parameters);
            if($requetePrepare===false) throw new RuntimeException("Erreur lors de la préparation de la requete",CodeDeRetourApi::ErreurInatenduService->value);
            elseif (!$executionReussi) throw new RuntimeException("Erreur lors de l'execution de la requete",CodeDeRetourApi::ErreurInatenduService->value);
            else {
                $valeurRetourne = $requetePrepare->fetchAll();
                $aucunValeurRetourne = count($valeurRetourne) === 0;
                return $aucunValeurRetourne ? 0 : $valeurRetourne;
            }
        } catch (PDOException $pDOException) {
            throw new PDOException("Erreur inattendu au sein du serveur" .  " " . $pDOException->getMessage(),CodeDeRetourApi::ErreurInatenduService->value); 
        }
    }
    private function setParametreErreur(int $codeDerreur, 
        string $messageDeRetour) : void
    {
        $this->messageDeRetour = $messageDeRetour;
        $this->retourApi = CodeDeRetourApi::tryFrom($codeDerreur);
        if ($this->retourApi!=null) $this->codeDeRetourApi = $this->retourApi->value;
        else throw new Exception("erreur innattendu", CodeDeRetourApi::ErreurInatenduService->value);
    }
    private function verificationFormatage(
        array $parameters,
        string $requete = null
    ) : void
    {
        $nombreDeParametereDonne = count($parameters);
        $estNonPrepare = !preg_match_all('/\?/',$requete) && !preg_match_all('/\:/',$requete);
        if ($nombreDeParametereDonne>0) {
            if ($estNonPrepare) throw new InvalidArgumentException("Requete non préparé donc refusé",CodeDeRetourApi::MauvaiseRequete->value);
            else {
                $matches = [];
                if (preg_match_all('/\?/',$requete,$matches)) $nombreDeParametereRequete = count($matches[0]);
                elseif(preg_match_all('/\:/',$requete,$matches)) $nombreDeParametereRequete = count($matches[0]);
                if($nombreDeParametereDonne!=$nombreDeParametereRequete) throw new InvalidArgumentException("Nombre de parametre donnees differents des parametres de la requete",CodeDeRetourApi::MauvaiseRequete->value);
            }
        }
        else {
            if(!$estNonPrepare) throw new InvalidArgumentException("Requete préparé alors qu'aucun parametre n'est donnée",CodeDeRetourApi::MauvaiseRequete->value);
        }

        
    }
    private function verificationBonneAction(
        string $requete,
        string $actionAttendu
    ) : void
    {
        $matches = [];
        if (!preg_match_all($actionAttendu,$requete,$matches)) {print_r($matches);echo "$actionAttendu $requete";throw new LogicException("Requete illogique au vu de la méthode api utilisé", CodeDeRetourApi::MauvaiseRequete->value);};
        
    }
    private function connexionBaseDeDonne() : PDO
    {
        try {
            Connexion::connect();
        } catch (PDOException $pDOException) {
            throw $pDOException;    
        }
        return Connexion::pdo();
    }
    
    
    
    
}

