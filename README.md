# PLAYER-SERVICE
## ENDPOINTS
Base URL : /Player

1. POST /Player : Créer un nouveau joueur.

Paramètres :
- DTO PlayerCreateDTO.


2. GET /Player/{id} : Récupérer un joueur par ID.

Paramètres :
- id : Identifiant unique du joueur.

3. PUT /Player/{id} : Mettre à jour un joueur.

Paramètres :
- id : Identifiant unique du joueur.
- DTO PlayerUpdateDTO.

4. DELETE /Player/{id} : Supprimer un joueur par ID.

Paramètres :
- id : Identifiant unique du joueur (path variable).

## Logique métier
La logique métier du Player Service est centrée sur la gestion des joueurs et de leurs relations d'amitié :

Gestion des joueurs :

Les utilisateurs peuvent s'inscrire et créer un profil via le contrôleur PlayerController.

Les informations des joueurs, telles que leur nom et leur email, sont stockées dans la base de données et validées lors de la création ou de la mise à jour.

Relations d'amitié :

Les joueurs peuvent ajouter des amis via le contrôleur FriendController.

Une relation d'amitié est représentée par une entrée dans la table Friend qui relie deux joueurs.

Validation des données :

Les DTOs et les annotations de validation (@Valid, @NotNull, etc.) assurent l'intégrité des données avant leur persistance.

Ces décisions garantissent une expérience utilisateur fluide et un modèle de données cohérent, tout en permettant une intégration facile avec d'autres services comme le Game Service.

## Workflow
Étapes de Fonctionnement

1. Création d'un joueur :

- Un utilisateur envoie une requête POST au service Player via l'endpoint /Player avec un DTO PlayerCreateDTO.

- Le Player Service valide les données et stocke le joueur dans la table Player.

2. Ajout d'amis :

- Un utilisateur envoie une requête POST à l'endpoint /Friend avec un DTO AddFriendDTO contenant les IDs des joueurs concernés.

- Le Player Service enregistre cette relation dans la table Friend.

3. Création d'une partie :

- Un utilisateur envoie une requête POST au service Game via l'endpoint /Party avec un DTO PartyDTO.

- Le Game Service stocke les données de la partie dans la table Party.

4. Participation à une partie :

- Un utilisateur envoie une requête POST à l'endpoint /Participation avec un DTO ParticipationDTO contenant l'ID du joueur et de la partie.

- Le Game Service vérifie que le joueur existe en interrogeant le Player Service et enregistre la participation dans la table Participation.

5. Consultation des données :

- Les utilisateurs peuvent récupérer les informations des joueurs, des parties ou des participations via les endpoints GET appropriés.

- Chaque service interagit directement avec sa base de données respective pour fournir les données demandées.