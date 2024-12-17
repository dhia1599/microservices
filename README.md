# microservices

## Description du Projet
Ce projet est un microservice développé avec **Spring Boot** permettant de gérer des **étudiants**. Il s'intègre dans une architecture **microservices** où chaque étudiant est associé à une école dont les informations sont récupérées dynamiquement via un autre microservice nommé **School Service**.

L'intégration entre les deux services repose sur **Eureka Server** pour la découverte des services et sur **RestTemplate** avec un équilibrage de charge pour faciliter les appels inter-services.