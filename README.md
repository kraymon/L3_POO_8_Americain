# Règles

Le jeu se joue à au moins deux joueurs et au maximum 6 joueurs.

## Mise en place

Chaque joueur reçoit 8 cartes tirées aléatoirement et une carte aléatoire est posée au milieu.

## Déroulé de la partie

Les joueurs jouent les uns après les autres et doivent déposer une carte et s'ils ne peuvent pas, en piochent une.

### Comment jouer une carte ?

* Il n'est possible de déposer une carte seulement si elle est de même couleur et/ou valeur que la carte posée au milieu.
* Si un joueur possède une ou plusieurs cartes de la même valeur que la carte qu'il peut poser, alors il peut toutes les poser lors d'un même tour.
* Huit : les joueurs peuvent poser cette carte sans contrainte de valeur ou de couleur. De plus, elle permet de choisir la couleur de la prochaine carte jouée.
* Valet : le joueur qui pose cette carte change le sens du jeu. S'il n'y a que deux joueurs, alors elle lui permet de rejouer.
* Dix : le joueur qui pose cette carte peut rejouer.
* Sept : le joueur qui pose cette carte bloque le tour du prochain joueur.
* As : le joueur qui pose cette carte fait piocher 2 cartes au prochain joueur sauf si ce dernier est également en possession d'un as. Dans ce cas, le nombre de carte à piocher se cumule pour le prochain (multiple de deux).

## Fin de la partie

Le gagnant est le premier joueur à ne plus avoir de carte dans la main.

### Détail des classes principales

Un exemple de jeu

* Local la version du jeu supportant le jeu en local
* GameEngine le moteur du jeu
* GameNetworkPlayer le joueur distant en cas de partie réseau
* GameNetworkEngine la version du jeu supportant le réseau


# Protocole réseau

> Le protocole réseau définit les séquences des commandes échangées entre les différentes parties prenantes. Il doit contenir, pour chaque commande, l'expéditeur, le destinataire, le nom de la commande et le contenu du corps de la commande.

![protocole](doc/protocle.png)


