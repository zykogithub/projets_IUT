<?php
	include "../../../route.php";
	require_once($chemin."//API//Api.php");
	require_once($chemin."//src//modele//Internaute.php");
	if(isset($_POST["courriel"],$_POST["mdp"])){
		$courriel=$_POST["courriel"];
		$mdp= $_POST["mdp"];
		$api= new Api();
		$requeteVerif = "SELECT hashageMDP FROM internaute WHERE courriel=?";
		$api->get([$courriel],null,$requeteVerif);
		if($api->getCodDeRetourApi()==CodeDeRetourApi::OK->value){
			$check=$api->getValeurRetourne();
			if (is_array($check) && count($check) == 1) {
				$mdp = $check[0]["hashageMDP"];
				if (password_verify($_POST["mdp"], $mdp)) {
					$requete = "SELECT * FROM internaute I WHERE courriel=?";
					$api->get([$courriel],null,$requete);
					if($api->getCodDeRetourApi()==CodeDeRetourApi::OK->value){
						$check=$api->getValeurRetourne();
						if (is_array($check) && count($check) == 1) {
							$internaute = new internaute(
								$check[0]["id_internaute"],
								$check[0]["nom_internaute"],
								$check[0]["prenom_internaute"],
								$check[0]["adresse_postale"],
								$check[0]["courriel"],
								$check[0]["hashageMDP"]	
							);
						}
						elseif ($check===0) {}
					} else{
						$api->getMessaDerreur();
					}
				}
				else echo "Adresse mail ou mot de passe oublié";
			}
			elseif ($check===0) {}
		} else{
			$api->getMessaDerreur();
		}
	} else{
		echo "champs non renségné(s) !";
	}
?>