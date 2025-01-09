<?php
/**
 * enum qui collecte tous les code des retours possibles
 */
enum CodeDeRetourApi : int {
    /**
     * si le serveur a reçu qu'une partie de la requete
     */
    case Continue = 100;
    /**
     * si la requete a réussi
     */
    case OK = 200;
    /**
     * si la ressource est crée
     */
    case Cree = 201;
    /**
     * si la requete est accepté
     */
    case Acceptee = 202;
    /**
     * si la requete a réussi mais ne renvoie aucune donée
     */
    case PasDeContenu = 204;
    /**
     * si la requete est mal faite
     */
    case MauvaiseRequete = 400;
    /**
     * si la ressource n'existe pas dans la base de donnée
     */
    case NonTrouve = 404;
    /**
     * si la requete rentre en conflit avec une autre donnée de la base de donée
     */
    case Conflit = 409;
    /**
     * si la requete est bien formé mais renvoie des erreurs
     */
    case Erreurrequete = 422;
    /**
     * si le serveur est indisponible
     */
    case ServiceNonDisponible = 503;
    /**
     * Si la base de donnée renvoie une erreur non déterminé
     */
    case ErreurInatenduService = 504;
    

}