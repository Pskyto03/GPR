# GPR

markdown

# Gestión de Productos Reactivo

Proyecto básico para aprender **Spring Boot**, **Spring Security**, **programación reactiva con WebFlux**, **internacionalización (i18n)** en 3 idiomas (español, inglés, francés), y conexión con **MongoDB** usando operaciones CRUD.


## Tecnologías Usadas

-	Java 17
-	Spring Boot 3.x
-	Spring WebFlux (reactivo)
-	Spring Security
-	Spring Data Reactive MongoDB
-	Internacionalización (i18n)
-	Postman (para pruebas)
-	Lombok

---
## Internacionalización Endpoint de prueba:
GET /api/productos/mensaje?lang=es | en | fr
yaml

---
## Seguridad

-	Autenticación básica (HTTP Basic)
-	Usuario: `usuario`
-	Contraseña: `clave123`

---
 
## Endpoints CRUD (Protegidos)

Método	Endpoint	Descripción
GET	`/api/productos`	Lista todos los productos
POST	`/api/productos/{id}`	Crear un nuevo producto
GET	`/api/productos/{id}`	Buscar producto por ID
PUT	`/api/productos/{id}`	Actualizar producto por ID
DELETE	`/api/productos/{id}`	Eliminar producto por ID

---

## Pruebas con Postman

1.	Usa autenticación básica con el usuario y clave.
2.	Prueba el CRUD y el endpoint `/mensaje?lang=fr`.

---

## Requisitos para ejecutar

-	Java 17
-	Maven 3.8+
-	MongoDB local (en `localhost:27017`)
---

## Cómo correr el proyecto

```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/gestion-productos-reactivo.git

# Entrar al proyecto
cd gestion-productos-reactivo

# Ejecutar el proyecto
./mvnw spring-boot:run
 





pgsql

---

## 2. Archivo `.gitignore`

```gitignore
# Compiled class file
*.class
# Maven target/
!.mvn/wrapper/maven-wrapper.jar
**/target/

# Logs
*.log

# IntelliJ IDEA
.idea/
*.iml

# Eclipse
.project
.classpath
.c9/
.settings/
# VS Code
.vscode/
# OS
.DS_Store Thumbs.db

## Ejecución de Pruebas

### Pruebas Unitarias
Para ejecutar solo las pruebas unitarias:
```bash
./mvnw test -Dtest=ProductoServiceTest
