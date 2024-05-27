# Instruction d'installation
## Création des dossiers
Tout d'abord, vous devez créer les dossiers suivants :
- `db` : ce dossier contiendra la base de données
- `pictures` : ce dossier contiendra les images des biens immobiliers

## Configuration des variables d'environnement
Ouvrez le fichier `.env` et modifiez les variables d'environnement suivantes :
- DB_HOST: Le nom du service de la base de données dans le fichier `docker-compose.yml` (par défaut : `db`)
- DB_PORT: Le port de la base de données (par défaut : `3306`)
- DB_NAME: Le nom de la base de données
- DB_USERNAME: Le nom d'utilisateur de la base de données
- DB_PASSWORD: Le mot de passe de la base de données
- DB_DUMP_FOLDER: Le chemin du dossier contenant le dump de la base de données
- DB_PATH: Le chemin du dossier contenant la base de données
- JWT_KEY: La clé secrète pour générer les tokens JWT (génerez une clé avec la commande `openssl rand -hex 32`)
- PICTURES_PATH: Le chemin du dossier contenant les images des biens immobiliers

## Modifiez le script de création de la base de données
Par défaut le script de création de la base de données contient des erreurs, le remplacer comme suit :

```
CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE,
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp,
  FOREIGN KEY (`owner_id`) REFERENCES `USERS`(`id`)
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp,
  FOREIGN KEY (`rental_id`) REFERENCES `RENTALS`(`id`),
  FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`)
);
```

## Lancer le projet
Lancez le projet en utilisant la commande `docker compose up -d`