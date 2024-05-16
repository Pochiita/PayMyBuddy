# PayMyBuddy 🤝️

## Projet Openclassroom-6 : Conception d'une application web Java de A à Z

<h2>Modèle physique des données :</h2>
![Model Physique des Données](https://github.com/Pochiita/PayMyBuddy/blob/dev/img/model_db.png?raw=true)

<h2>Diagramme UML</h2>
![Diagramme UML](https://github.com/Pochiita/PayMyBuddy/blob/dev/img/diagramme_uml.png?raw=true)

[Lien vers le script SQL](./img/dump.sql "Telecharger le script SQL")

<h1>Mettre en place l'application.properties</h1>
Pour que l'application fonctionne correctement, vous devez remplacer les variables dans le fichier `application.properties` par vos logs de votre environnement.

Si vous utilisez IntelliJ IDEA, vous pouvez également utiliser les variables d'environnement IntelliJ suivantes :

| Variable      | Description                            |
|---------------| ---                                    |
| `APP_DB_HOST` | Adresse IP ou nom du serveur de base de données |
| `APP_DB_PORT` | Port utilisé par la base de données         |
| `APP_DB_USER` | Nom d'utilisateur de la base de données      |
| `APP_DB_PASS` | Mot de passe de l'utilisateur              |

```properties
# Exemple d'application.properties
spring.application.name=pochiita
spring.datasource.url=jdbc:mysql://${APP_DB_HOST}:${APP_DB_PORT}/paymybuddy?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&characterEncoding=utf8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=${APP_DB_USER}
spring.datasource.password=${APP_DB_PASS}
spring.jpa.properties.hibernate.dialect =  org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.type=auto
spring.main.allow-bean-definition-overriding=true
```