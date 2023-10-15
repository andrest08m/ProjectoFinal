<h1>Sistema de Manejo de Vuelos</h1>
Este sistema de manejo de vuelos está diseñado como una aplicación Spring Boot que utiliza Spring Security JWT para la autenticación y autorización, incluye Swagger para documentar la API y sigue una arquitectura monolítica.

<h3>Arquitectura Monolítica</h3>
Este sistema sigue un enfoque de arquitectura monolítica, lo que significa que todos los componentes de la aplicación están integrados en un solo programa. En este contexto, tenemos:

<b>Controladores:</b> Estos gestionan las solicitudes HTTP entrantes y definen las rutas y métodos que la aplicación puede manejar. En este caso, gestionan las operaciones relacionadas con los vuelos, como buscar vuelos disponibles, reservar vuelos, etc.

<b>Servicios:</b> Los servicios son responsables de la lógica de negocio. Realizan operaciones como validar reservas, calcular tarifas, etc.

<b>Repositorios:</b> Se encargan de interactuar con la base de datos para almacenar y recuperar datos relacionados con vuelos, como detalles de vuelos, reservas y usuarios.

<b>Spring Security JWT:</b> Se utiliza para gestionar la autenticación y autorización de usuarios. Las solicitudes a las rutas protegidas deben incluir un token JWT válido en el encabezado de la solicitud.

<h3>Spring Security JWT</h3>
Spring Security JWT se encarga de asegurar que solo los usuarios autorizados puedan acceder a ciertas rutas y funciones del sistema. Cuando un usuario se autentica con éxito, se le proporciona un token JWT (JSON Web Token) que debe incluirse en las solicitudes subsiguientes. Este token contiene información sobre el usuario y sus permisos, y se verifica en el lado del servidor antes de que se permita el acceso a recursos protegidos.

<h3>Swagger</h3>
Swagger se utiliza para documentar la API REST expuesta por la aplicación. Proporciona una interfaz interactiva que permite a los desarrolladores, testers y otros usuarios explorar y entender cómo interactuar con la API. Puedes acceder a la documentación de Swagger visitando la URL http://localhost:8080/swagger-ui.html una vez que la aplicación esté en ejecución.

<h3>Funcionalidades Principales</h3>
<b>Búsqueda de vuelos:</b> Los usuarios pueden buscar vuelos disponibles según diversos criterios, como origen, destino, fecha de salida, fecha de regreso, etc.

<b>Reserva de vuelos:</b> Los usuarios autenticados pueden reservar vuelos disponibles. Spring Security JWT garantiza que solo los usuarios autorizados puedan realizar reservas.

<b>Administración de vuelos:</b> Los administradores pueden agregar, modificar o eliminar vuelos y gestionar la información relacionada con los vuelos.

Este sistema proporciona una solución completa para la gestión de vuelos y garantiza la seguridad de los datos y las operaciones mediante Spring Security JWT. Puedes explorar y probar las funcionalidades a través de la documentación de Swagger y personalizar la aplicación según tus necesidades específicas.
