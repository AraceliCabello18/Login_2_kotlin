create database gatos;
use gatos;
CREATE TABLE `t_usuario` (
    `id_usuarios` int(11) NOT NULL AUTO_INCREMENT,
    `nombre` varchar(245) NOT NULL,
    `apPaterno` varchar(245) NOT NULL,
    `apMaterno` varchar(245) NOT NULL,
    `edad` varchar(245) NOT NULL,
    `genero` varchar(245) NOT NULL,
    `correo` varchar(245) NOT NULL,
    `contrasena` varchar(245) NOT NULL,
    `fechaNacimiento` varchar(245) NOT NULL,
    PRIMARY KEY (`id_usuarios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;