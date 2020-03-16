# WebShooting
Un super site de partage d'albums photos !
### Cahier de charge

On d�sire concevoir une application Web qui g�re des albums photos d�utilisateurs. Un visiteur du site
pourra, sans �tre authentifi�, voir les photos qui sont en acc�s libre. Les photos sont plac�es dans des
albums permettant de grouper les photos d�un m�me th�me (vacance, sport, etc.).

Un album peut �tre d�clar� public ou priv�. Si l�album est priv�, seuls les utilisateurs enregistr�s et
autoris�s par le propri�taire de l�album peuvent le consulter. 


S�il est public, il est accessible � tous les
utilisateurs et � tous les visiteurs, m�me ceux qui ne sont pas identifi�s. 

Seul le propri�taire de l�album peut faire des modifications (insertion d�images, suppression d�images, modification d�images). Enfin, un
utilisateur peut cr�er autant d�albums qu�il souhaite.

Une image appartient n�cessairement � un album. De ce fait, elle appartient �galement � un utilisateur
(le propri�taire de l�album). 


Elle poss�de un titre, une description, une hauteur et une largeur, des motscl�s,
une date de cr�ation et une date de mise � jour, et bien s�r, un fichier image. Lors de la pr�sentation
de la liste des images, si on clique sur l�image, elle s�affiche sous forme de popup. Si on clique sur le nom
de l�album � laquelle elle appartient, le d�tail de l�album s�affiche, et si on clique sur le nom de l�image,
les informations concernant l�image s�affichent.

Les utilisateurs de l�application sont de deux types : les administrateurs et les utilisateurs simples. En effet,
les visiteurs qui s�inscrivent deviennent des utilisateurs simples et peuvent ainsi publier des albums et des
photos. Quant aux administrateurs, en plus de partager des albums, ils ont la possibilit� de g�rer les
utilisateurs. Ainsi, ils peuvent ajouter, modifier ou supprimer des utilisateurs ainsi que tous les albums et
photos qui leur sont associ�s. Toutefois, un compte administrateur ne peut �tre cr�� que par un
administrateur. Ce qui veut dire qu�au moins un administrateur devra pr�alablement �tre inscrit dans
l�application.



### NB
- N'oubliez de changer le jdk sur le projet g�n�re des erreurs.
- Charger la base de donn�es, modifier les acc�es, les images upload�es sont dans le fichier imagesuploaded

### R�alisation du projet

*Page d'accueil de l'application*
