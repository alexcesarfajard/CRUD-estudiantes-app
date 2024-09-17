CREATE SCHEMA estudiantes;

CREATE TABLE `estudiante` (
  `id_estudiante` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_estudiante`));

-- Read (Leer)
SELECT * FROM estudiante;

-- Create (Insertar)
INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES ("Alex", "Cesar", "88776655", "alex@mail.com");
INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES ("Joselyn", "Rivera", "88665544", "joselyn@mail.com");
INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES ("Mario", "Gomez", "77665533", "mario@mail.com");
INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES ("Henry", "Narvaez", "66554433", "henry@mail.com");

-- Update (Modificar)
UPDATE estudiante SET nombre = "Rafael", apellido = "Rivera" WHERE id_estudiante = 3;

-- Delete (Eliminar)
DELETE FROM estudiante WHERE id_estudiante = 4;
