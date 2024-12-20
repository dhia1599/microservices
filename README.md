# Microservices avec Spring Boot et Spring Cloud Gateway

## Description

Ce projet implémente une architecture basée sur des microservices pour la gestion d'entités comme des étudiants (students), des écoles (schools) et un service d'authentification (auth-service). L'ensemble est orchestré à l'aide d'un serveur Eureka et d'une passerelle API (Gateway). Chaque service est isolé et communique via des appels HTTP.

### Services inclus
1. **Student Service** : Gestion des étudiants.
2. **School Service** : Gestion des écoles.
3. **Auth Service** : Gestion de l'authentification et des utilisateurs.
4. **Gateway** : Filtrage et routage des requêtes.
5. **Eureka Server** : Découverte des services.

## Architecture

L'architecture est composée des éléments suivants :

- **Auth Service** : Responsable de l'authentification et de la génération de jetons JWT. Il valide également les jetons pour sécuriser l'accès aux autres services.
- **Gateway** : Point d'entrée unique pour les requêtes client. Elle vérifie la validité des jetons auprès d'Auth Service avant de transmettre les requêtes aux services concernés.
- **Eureka Server** : Gère l'enregistrement des services et facilite leur découverte.
- **Student Service** et **School Service** : Fournissent respectivement les fonctionnalités relatives aux étudiants et aux écoles.

Les services communiquent via HTTP avec l'aide d'un équilibrage de charge grâce à Eureka.

## Scénarios de test avec Postman

### 1. Authentification

**Endpoint** : `POST http://localhost:8089/api/auth/login`

- **Description** : Générez un token JWT en fournissant un nom d'utilisateur et un mot de passe.
- **Exemple de corps de requête** :

```json
{
    "username": "user1",
    "password": "password123"
}
```

- **Réponse attendue** :
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### 3. Accès au service Student

**Endpoint** : `GET /api/students`

- **Description** : Récupérez la liste des étudiants.
- **Headers** :
  - Authorization : `Bearer {token}`

- **Réponse attendue** :
```json
[
    {
        "id": "676032ab020da52d646a6648",
        "name": "Xi Lin",
        "age": 25,
        "gender": "F",
        "schoolId": 12,
        "school": {
            "id": 12,
            "name": "School F",
            "city": "Amsterdam"
        }
    },
    {
        "id": "676032ab020da52d646a6643",
        "name": "Wang Wei",
        "age": 22,
        "gender": "H",
        "schoolId": 7,
        "school": {
            "id": 7,
            "name": "School A",
            "city": "Paris"
        }
    }
]
```

### 4. Accès au service School

**Endpoint** : `GET /api/schools`

- **Description** : Récupérez la liste des écoles.
- **Headers** :
  - Authorization : `Bearer {token}`

- **Réponse attendue** :
```json
[
    {
        "id": 12,
        "name": "School F",
        "city": "Amsterdam"
    },
    {
        "id": 7,
        "name": "School A",
        "city": "Paris"
    }
]
```

### 5. Création d'un utilisateur

**Endpoint** : `POST http://localhost:8089/api/auth/register`

- **Description** : Créez un nouvel utilisateur dans le système.
- **Exemple de corps de requête** :

```json
{
    "username": "newuser",
    "password": "securepassword",
    "role": "USER"
}
```

- **Réponse attendue** :
```json
{
    "id": 3,
    "username": "newuser",
    "role": "USER"
}
```

## Pré-requis

- **Java** : Version 17 ou supérieure.
- **Maven** : Pour la gestion des dépendances.
- **PostgreSQL** : Base de données utilisée pour le Auth Service.
- **Postman** : Pour les tests des API.
