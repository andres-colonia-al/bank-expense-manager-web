# Proyecto Frontend Web - Gestión de Gastos Menores para un Banco

Este proyecto frontend web consume APIs RESTful de un backend desarrollado con Java y Spring Boot. 
Utiliza JSP para renderizar vistas dinámicas y Bootstrap para estilizar la interfaz de usuario.

## Descripción

El sistema permite la gestión de gastos menores por parte de usuarios con diferentes roles:

1. Usuario: Puede registrar, visualizar y eliminar sus propios gastos.
2. Admin: Tiene acceso a un panel administrativo completo donde puede:
  - Visualizar todas las transacciones.
  - Filtrar transacciones por categoría y departamento.
  - Gestionar usuarios (crear, modificar, eliminar) -> futura implementación ya que los endpoints ya se crearon en el backend

## Tecnologías Utilizadas

- Java 21
- Spring Boot
- JSP (JavaServer Pages)
- Bootstrap 5 (para estilos y componentes responsive)
- Maven (para gestión de dependencias)
- Tomcat (como servidor de aplicaciones)

## Estructura del Proyecto

El proyecto cuenta con cuatro vistas principales:

### Login:

- Permite a los usuarios autenticarse en la aplicación.
- La autenticación se realiza consultando al backend a través de un controlador REST.

### NavBar (Barra de navegación):

- Compartida por las vistas de Dashboard Admin y Dashboard Usuario.
- Muestra las opciones de navegación según el rol autenticado.

### Dashboard Usuario:

- Permite al usuario registrar, visualizar y eliminar sus gastos personales.
- El acceso a esta vista es restringido a usuarios con rol USER.

### Dashboard Admin:

- Visualizar todas las transacciones de todos los usuarios.
- Permite al administrador realizar filtros para las transacciones por:
  1. Categoría
  2. Departamento
- Gestionar usuarios (crear, modificar, eliminar). -> futura implementación ya que los endpoints ya se crearon en el backend
- El acceso a esta vista es restringido a usuarios con rol ADMIN.

## Estructura de Archivos

```
├── src/
│   ├── main/
│   │   ├── java/
|   |   |   ├── com.acolonia.spring.frontend
|   |   |   |   ├─── controller (Peticiones al Backend)
|   |   |   |   ├─── model (Dto's y enums)
|   |   |   |   ├─── BankExpenseManagerFrontendApplication
|   |   |   |   ├─── ServletInitializer 
│   │   ├── resources/
│   │   ├── webapp/
│   │   │   ├── WEB-INF/
│   │   │   │   ├── views/
│   │   │   │   │    ├─── admin
│   │   │   │   │    │      ├── dashboard.jsp
│   │   │   │   │    ├─── auth
│   │   │   │   │    │      ├── login.jsp
│   │   │   │   │    ├─── common
│   │   │   │   │    │      ├── navbar.jsp
│   │   │   │   │    ├─── user
│   │   │   │   │    │      ├── dashboard.jsp
├── README.md
```

## Autenticación

El sistema utiliza autenticación basada en roles (USER y ADMIN). Los roles se manejan desde el backend y determinan el acceso a las vistas y funcionalidades de la aplicación.

## Instalación y Configuración

### Opción 1: Ejecutar con Maven Wrapper (./mvnw)

1. Clonar el repositorio.
2. Configurar las variables de entorno en application.properties del backend.
   ```
   https://github.com/andres-colonia-al/bank-expense-manager-backend/blob/master/README.md#configuraci%C3%B3n-de-applicationproperties)
   ```
3. Ejecutar el proyecto con:
  ```
  ./mvnw spring-boot:run
  ```
4. Acceder a la aplicación desde:
  ```
  http://localhost:8081/auth/login
  ```
5. Ingreso a la aplicación web
   Utilice las siguientes credenciales para ingresar a la web, tenga en cuenta que se deberon poblar los datos inciales descritos en documento README del Backend.
  ```
  Username: andrescolonia / Password: 1234 [ADMIN]
  Username: felipealdana / Password: 1234 [USER]
  ```

### Opción 2: Despliegue con Tomcat

1. Clonar el repositorio.
2. Configurar las variables de entorno en application.properties del backend.
3. Generar el archivo .war utilizando Maven con:
```
mvn clean package
```
4. Copiar el archivo .war generado en la carpeta target y desplazarlo a la carpeta webapps del servidor Apache Tomcat
5. Iniciar el servidor Tomcat con:
```
# En Windows
C:\Program Files\Apache Software Foundation\Tomcat\bin\startup.bat

# En Linux o MacOS
./startup.sh
```
6. Acceder a la aplicación desde:
```
http://localhost:8081/auth/login
```
7. Ingreso a la aplicación web
   Utilice las siguientes credenciales para ingresar a la web, tenga en cuenta que se deberon poblar los datos inciales descritos en documento README del Backend.
  ```
  Username: andrescolonia / Password: 1234 [ADMIN]
  Username: felipealdana / Password: 1234 [USER]
  ```

## Notas Importantes

- Este frontend consume un backend desarrollado en Java con Spring Boot.
- La autenticación y las peticiones se maneja mediante consultas al backend a través de controladores en Java.
- Las vistas están renderizadas con JSP y estilizadas con Bootstrap.

## Autor
Andrés Felipe Colonia Aldana.
