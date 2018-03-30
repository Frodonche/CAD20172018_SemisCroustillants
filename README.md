# CAD20172018_SemisCroustillants

Detail des packages et leur but/contenu
- main : launcher
- modele : modele, game, joueur
- view : les vues
- controllers : les controleurs
- epoques : epoque(interface) et les epoques concretes
- usines : usine(classe abstraite) et les usines concretes
- bateaux : bateau(classe abstraite),les bateaux concrets et la classe case(un bateau est composé de plusieurs cases)
- factory : les sprite factory (+ sound factory par la suite)

Notes :
- On stocke les differentes epoques dans le modele pour pouvoir en selectionner une facilement a partir de l'ecran titre.
- Quand on ajoute une nouvelle epoque, il faut egalement ajouter l'usine et les bateaux qui seront instancies a partir de l'epoque en question.

Menu pas encore fonctionnel (fenetre vide pour l'instant)
