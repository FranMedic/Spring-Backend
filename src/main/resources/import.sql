INSERT INTO `usuarios` (username, password, enabled, name, surname, email) VALUES ('andres','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq', true, "pepa", "pig", "pega@pig.com");
INSERT INTO `usuarios` (username, password, enabled, name, surname, email) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', true, "pepa", "cerda", "pega@cerda.com" );

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);


INSERT INTO regiones (id, name_region) VALUES (1, "Sudamérica");
INSERT INTO regiones (id, name_region) VALUES (2, "Norteamérica");
INSERT INTO regiones (id, name_region) VALUES (3, "Asia");
INSERT INTO regiones (id, name_region) VALUES (4, "Europa");
INSERT INTO regiones (id, name_region) VALUES (5, "Oceanía");
INSERT INTO regiones (id, name_region) VALUES (6, "África");
INSERT INTO regiones (id, name_region) VALUES (7, "Antártida");


INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(4, "Ernie", "Ern", "1991-01-21", "");
INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(1,"Guy", "Goy", "1992-02-22", "");
INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(2,"Bert", "Berto", "1993-03-23", "");
INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(3,"Gladys", "Ern", "1994-04-24", "");
INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(5,"Don", "Music", "1995-05-25", "");
INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(6,"Count", "Von Count", "1996-06-26","");
INSERT INTO clientes (region_id, name, surname, create_at, photo)VALUES(7,"Grover", "Groove", "1997-07-27", "");

INSERT INTO productos (nombre, precio, create_at)VALUES("Muñeca Barbie Malibu", 2035, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Playmobil", 3055, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Apple IPad", 100999, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Ps5", 98283, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Elden Ring", 6070, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Pendientes", 1040, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Platos chinos", 10090, NOW());
INSERT INTO productos (nombre, precio, create_at)VALUES("Guitarra Electrica", 200050, NOW());

INSERT INTO facturas(description, observation, cliente_id, create_at)VALUES("Regalos de la niña bonita", null, 1, NOW());

INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(3, 1, 1)
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(1, 1, 4)
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(3, 1, 5)


INSERT INTO facturas(description, observation, cliente_id, create_at)VALUES("Cosas de la abuela", null, 2, NOW());

INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(10, 2, 7)
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(2, 2, 6)
INSERT INTO facturas_items(cantidad, factura_id, producto_id)VALUES(1, 2, 3)
