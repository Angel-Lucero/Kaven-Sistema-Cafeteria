Kaven - Sistema de manejo de cafetería
Ete proyecto es una aplicación Java para la administración de una cafetería, que incluye funciones de CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar empleados, estudiantes, usuarios, productos, pedidos y entregas.

Requisitos:

Java: 21
Maven: 3.5.5
MySQl: 8.0 CE
Spring Boot: para el desarrollo de la API

Funcionalidades

El sistema proporciona las siguientes características:


- Empleados:
  - Crear, leer, actualizar y eliminar empleados.
  - Gestionar roles y permisos de los empleados.
  
- Estudiantes:
  - Registrar estudiantes.
  - Consultar información de los estudiantes.
  
- Usuarios:
  - Gestión de usuarios para el acceso al sistema.
  - CRUD para la creación y edición de usuarios.
  
- Productos:
  - Agregar, actualizar, eliminar y listar productos.
  - Los productos incluyen alimentos y bebidas disponibles en la cafetería.

- Pedidos:
  - Crear y consultar pedidos realizados por los usuarios.
  - Verificar el estado de los pedidos (pendientes, entregados, etc.).
  
- Entregas:
  - Registrar las entregas realizadas.
  - Ver el historial de entregas.

Empleados:

Crear, leer, actualizar y eliminar empleados.
Gestionar roles y permisos de los empleados.
Estudiantes:

Registrar estudiantes.
Consultar información de los estudiantes.
Usuarios:

Gestión de usuarios para el acceso al sistema.
CRUD para la creación y edición de usuarios.
Productos:

Agregar, actualizar, eliminar y listar productos.
Los productos incluyen alimentos y bebidas disponibles en la cafetería.
Pedidos:

Crear y consultar pedidos realizados por los usuarios.
Verificar el estado de los pedidos (pendientes, entregados, etc.).
Entregas:

Registrar las entregas realizadas.
Ver el historial de entregas.

Instalación

Para comenzar con el proyecto, sigue estos pasos:


1. Clona el repositorio:
    ```bash
    git clone https://github.com/Angel-Lucero/Kaven-Sistema-Cafeteria.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd nombre-repositorio
    ```

3. Instala las dependencias del proyecto usando Maven:
    ```bash
    mvn install
    ```

4. Si tienes configurada una base de datos PostgreSQL o cualquier otro motor, 
asegúrate de tener las credenciales y la URL configuradas en el archivo 
`application.properties`.

5. Para ejecutar la aplicación, usa el siguiente comando:
    ```bash
    mvn spring-boot:run
    ```

Uso

Una vez que la aplicación esté corriendo, puedes interactuar con la API usando herramientas 
como.

Endpoints principales:

- Empleados:
  - `GET/empleados` - Lista todos los empleados.
  - `POST/empleados` - Crea un nuevo empleado.
  - `PUT/empleados/{id}` - Actualiza un empleado existente.
  - `DELETE/empleados/{id}` - Elimina un empleado.
  - `localhost:8092/cafeteria-sistema/api/v1/empleados/[id]` - direccion

- Estudiantes:
  - `GET/estudiantes` - Lista todos los estudiantes.
  - `POST/estudiantes` - Registra un nuevo estudiante.
  - `PUT/estudiantes` - Actualiza un empleado existente.
  - `DELETE/estudiantes` - Elimina un empleado.
  - `localhost:8092/cafeteria-sistema/api/v1/estudiantes/[id]` - direccion

- Usuarios:
  - `GET/usuarios` - Lista todos los Usuarios.
  - `POST/usuarios` - Registra un nuevo Usuarios.
  - `PUT/usuarios` - Actualiza un empleado Usuarios
  - `DELETE`- Elimina un usua
  - `localhost:8092/cafeteria-sistema/api/v1/estudiantes/[id]` - direccion

- Productos:
  - `GET/productos` - Lista todos los productos.
  - `POST/productos` - Crea un nuevo producto.
  - `PUT/productos/{id}` - Actualiza un producto existente.
  - `DELETE/productos/{id}` - Elimina un producto.
  - `localhost:8092/cafeteria-sistema/api/v1/estudiantes[id]` - direccion

- Pedidos:
  - `GET/pedidos` - Lista todos los Pedidos.
  - `POST/pedidos` - Registra un Pedidos estudiante.
  - `PUT/pedidos` - Actualiza un Pedidos existente.
  - `DELETE/pedidos` - Elimina un Pedidos.
  - `localhost:8092/cafeteria-sistema/api/v1/estudiantes/[id]` - direccion

- Entregas:
  - `GET/entregas` - Lista todos los Entregas.
  - `POST/entregas` - Registra un nuevo Entregas.
  - `PUT/entregas` - Actualiza un empleado Entregas.
  - `DELETE/entregas` - Elimina un Entregas.
  - `localhost:8090/cafeteria-sistema/api/v1/estudiantes/[id]` - direccion


Clona el repositorio:

git clone https://github.com/Angel-Lucero/Kaven-Sistema-Cafeteria.git
Navega al directorio del proyecto:

cd nombre-repositorio
Instala las dependencias del proyecto usando Maven:

mvn install
Si tienes configurada una base de datos PostgreSQL o cualquier otro motor, asegúrate de tener las credenciales y la URL configuradas en el archivo application.properties.

Para ejecutar la aplicación, usa el siguiente comando:

mvn spring-boot:run
Uso

Una vez que la aplicación esté corriendo, puedes interactuar con la API usando herramientas como.

Endpoints principales:

Empleados:

GET/empleados - Lista todos los empleados.
POST/empleados - Crea un nuevo empleado.
PUT/empleados/{id} - Actualiza un empleado existente.
DELETE/empleados/{id} - Elimina un empleado.
localhost:8092/cafeteria-sistema/api/v1/empleados/[id] - direccion
Estudiantes:

GET/estudiantes - Lista todos los estudiantes.
POST/estudiantes - Registra un nuevo estudiante.
PUT/estudiantes - Actualiza un empleado existente.
DELETE/estudiantes - Elimina un empleado.
localhost:8092/cafeteria-sistema/api/v1/estudiantes/[id] - direccion
Usuarios:

GET/usuarios - Lista todos los Usuarios.
POST/usuarios - Registra un nuevo Usuarios.
PUT/usuarios - Actualiza un empleado Usuarios
DELETE- Elimina un usua
localhost:8092/cafeteria-sistema/api/v1/estudiantes/[id] - direccion
Productos:

GET/productos - Lista todos los productos.
POST/productos - Crea un nuevo producto.
PUT/productos/{id} - Actualiza un producto existente.
DELETE/productos/{id} - Elimina un producto.
localhost:8092/cafeteria-sistema/api/v1/estudiantes[id] - direccion
Pedidos:

GET/pedidos - Lista todos los Pedidos.
POST/pedidos - Registra un Pedidos estudiante.
PUT/pedidos - Actualiza un Pedidos existente.
DELETE/pedidos - Elimina un Pedidos.
localhost:8092/cafeteria-sistema/api/v1/estudiantes/[id] - direccion
Entregas:

GET/entregas - Lista todos los Entregas.
POST/entregas - Registra un nuevo Entregas.
PUT/entregas - Actualiza un empleado Entregas.
DELETE/entregas - Elimina un Entregas.
localhost:8090/cafeteria-sistema/api/v1/estudiantes/[id] - direccion
Contribución

Si deseas contribuir al proyecto, sigue estos pasos:


1. Haz un clone del repositorio.
2. Crea una nueva rama para tu característica (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Sube tu rama (`git push origin feature/nueva-caracteristica`).
5. Crea un Pull Request.


Desarrollado por Kaven S.A. Si tienes alguna pregunta, no dudes en contactarnos


Haz un clone del repositorio.
Crea una nueva rama para tu característica (git checkout -b feature/nueva-caracteristica).
Realiza tus cambios y haz commit (git commit -am 'Añadir nueva funcionalidad').
Sube tu rama (git push origin feature/nueva-caracteristica).
Crea un Pull Request.
Desarrollado por Kaven S.A. Si tienes alguna pregunta, no dudes en contactarnos
se agregaron vista para cada tabla para sgregar, eliminar, esitar y buscar

