# ApplicationName/Ports
* Subscriber-bc / 8009
# OPEN API 
J'ai ajouté La dépendance OPEN API pour pouvoir consulter et comprendre facilement les differnts endpoint de l'application.

url: "http://localhost:8009/swagger-ui/index.html"
# Structure du projet
Le projet est articulé selon une structure MVC :
* SOURCES
    * package `controller`: contient les endpoints REST de l'application 
    * package `service` : contient les interfaces et les implémentations avec la logique métier 
    * package `model`: contient les entités persistantes.
    * package `repository`: contient les JPA repositories.
    * package `dto`: contient les objets pour le transfert de data.  
    * package `mapper`: contient les interfaces de mapping objet/dto avec Mapstruct. 
* TEST
    * package `service`

* ressources/application.properties: contient la config pour la connexion à la base H2.

# Choix des dépendances
pom.xml:

spring-boot-starter-data-jpa

spring-boot-starter-web

spring-boot-devtools : rechargement automatique (Live Reload).

h2: une base mémoire pour la persistance des données.

lombok: génération des setters/getters/constructeurs avec des annotations (code plus clair).

springdoc-openapi-starter-webmvc-ui: consulter les endpoints de l'application "http://localhost:8009/swagger-ui/index.html".

mapstruct: mapping des objets.


