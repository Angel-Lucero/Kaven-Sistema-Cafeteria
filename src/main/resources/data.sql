-- Insertar usuarios administradores
INSERT IGNORE INTO usuarios (id, correo, contrasena, tipo_usuario) VALUES
(1, 'admin@cafeteria.edu', 'Admin123!', 'ADMIN'),
(2, 'supervisor@cafeteria.edu', 'Super2025!', 'ADMIN'),
(3, 'director@cafeteria.edu', 'Director25!', 'ADMIN');

-- Insertar usuarios estudiantes
INSERT IGNORE INTO usuarios (id, correo, contrasena, tipo_usuario) VALUES
(4, 'juan.perez@estudiante.edu', 'JuanP2025!', 'STUDENT'),
(5, 'maria.gomez@estudiante.edu', 'MariaG25!', 'STUDENT');
-- ... resto de usuarios



INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (1, 'Juan Pérez', 'juan.perez@correo.com', 'Zona 1, Ciudad de Guatemala', '5023456789');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (2, 'María Gómez', 'maria.gomez@correo.com', 'Zona 10, Ciudad de Guatemala', '5029876543');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (3, 'Carlos López', 'carlos.lopez@correo.com', 'Zona 7, Mixco', '5024561237');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (4, 'Ana Morales', 'ana.morales@correo.com', 'Zona 5, Villa Nueva', '5023217894');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (5, 'Luis Castillo', 'luis.castillo@correo.com', 'Zona 18, Ciudad de Guatemala', '5027418529');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (6, 'Sofía Méndez', 'sofia.mendez@correo.com', 'Amatitlán', '5029632581');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (7, 'Ricardo Ramírez', 'ricardo.ramirez@correo.com', 'Zona 12, Ciudad de Guatemala', '5021597534');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (8, 'Paola Torres', 'paola.torres@correo.com', 'Zona 2, Santa Catarina Pinula', '5028523697');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (9, 'Andrés Reyes', 'andres.reyes@correo.com', 'San Miguel Petapa', '5023692587');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (10, 'Fernanda Álvarez', 'fernanda.alvarez@correo.com', 'Zona 13, Ciudad de Guatemala', '5027531598');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (11, 'José García', 'jose.garcia@correo.com', 'Zona 21, Ciudad de Guatemala', '5029517536');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (12, 'Claudia Sánchez', 'claudia.sanchez@correo.com', 'Mixco', '5023571594');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (13, 'Víctor Hernández', 'victor.hernandez@correo.com', 'Villa Canales', '5022587419');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (14, 'Alejandra Ruiz', 'alejandra.ruiz@correo.com', 'Zona 16, Ciudad de Guatemala', '5026547891');

INSERT IGNORE INTO estudiantes (id, nombre, correo, carrera, telefono)
VALUES (15, 'Martín Cabrera', 'martin.cabrera@correo.com', 'Zona 8, Ciudad de Guatemala', '5021478529');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (1, 'Carlos Pérez', 'Cocinero', 'Mañana');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (2, 'Lucía Gómez', 'Repartidora', 'Tarde');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (3, 'Ana López', 'Gerente', 'Mañana');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (4, 'Juan Martínez', 'Cocinero', 'Tarde');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (5, 'Sofía Ruiz', 'Repartidora', 'Mañana');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (6, 'Ricardo Torres', 'Administrador', 'Tarde');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (7, 'Paola Hernández', 'Cocinera', 'Mañana');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (8, 'Víctor Ramírez', 'Repartidor', 'Noche');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (9, 'Martín Cabrera', 'Gerente', 'Tarde');

INSERT IGNORE INTO empleados (id, nombre, rol, turno)
VALUES (10, 'Claudia Sánchez', 'Repartidora', 'Mañana');

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (1, 'Salchipapas', 'SALCHIPAPA', 25.00, 50);

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (2, 'Torta de chocolate', 'CAKE', 35.00, 20);

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (3, 'Combo Pizza', 'PIZZA_COMBO', 100.00, 30);

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (4, 'Botella de agua pura', 'AGUAPURA_BOTTLE', 10.00, 100);

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (5, 'Refresco Pepsi', 'PEPSI', 15.00, 80);

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (6, 'Café Espresso', 'COFFEE', 20.00, 60);

INSERT IGNORE INTO productos (id, nombre, tipo, precio, disponibilidad)
VALUES (7, 'Galletas de chocolate', 'COOKIE', 5.00, 200);

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (1, 1, '2025-09-19 10:30:00', 40.00, 'PENDIENTE');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (2, 2, '2025-09-19 11:00:00', 120.00, 'EN_PROCESO');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (3, 3, '2025-09-19 12:15:00', 50.00, 'ENTREGADO');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (4, 4, '2025-09-19 13:30:00', 25.00, 'CANCELADO');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (5, 5, '2025-09-19 14:00:00', 30.00, 'PENDIENTE');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (6, 6, '2025-09-19 15:00:00', 150.00, 'EN_PROCESO');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (7, 7, '2025-09-19 16:10:00', 70.00, 'PENDIENTE');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (8, 8, '2025-09-19 17:00:00', 90.00, 'ENTREGADO');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (9, 9, '2025-09-19 18:30:00', 55.00, 'CANCELADO');

INSERT IGNORE INTO pedidos (id, id_estudiante, fecha_pedido, total, estado)
VALUES (10, 10, '2025-09-19 19:45:00', 75.00, 'EN_PROCESO');