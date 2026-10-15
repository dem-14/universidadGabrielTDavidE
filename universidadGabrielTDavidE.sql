Create DATABASE universidadGabrielTDavidE_db;
USE universidadGabrielTDavidE_db;

Create TABLE profesor(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
apellidos VARCHAR(150) NOT NULL,
direccion VARCHAR(200)
);

Create TABLE materia (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(150) NOT NULL,
creditos INT NOT NULL,
horas_clase INT NULL,
id_profesor BIGINT NOT NULL
);

