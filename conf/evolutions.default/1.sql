# --- !Ups

use emei;
create table IF NOT EXISTS `nivel` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nivel` varchar(255) not null
);

create table IF NOT EXISTS `familia` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `descripcion` varchar(255) not null,
  `observaciones` varchar(255) not null

);

create table IF NOT EXISTS `tipo_contacto` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `descripcion` varchar(255) not null
);

create table IF NOT EXISTS `contacto` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `descripcion` varchar(255) not null,
  `tipo_contacto` integer  not null,
  FOREIGN KEY (tipo_contacto) REFERENCES tipo_contacto (ID)

);

create table IF NOT EXISTS `grado` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `grado` varchar(255) not null
  );


create table IF NOT EXISTS `nivel_grado` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `nivel` integer not null,
  `grado` integer not null,
  FOREIGN KEY (nivel) REFERENCES nivel (ID),
  FOREIGN KEY (grado) REFERENCES grado (ID)
);


create table IF NOT EXISTS `alumnos` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `legajo` varchar(255) not null,
  `nombres` varchar(255) not null,
  `apellidos` varchar(255) not null,
  `nivel_grado` integer not null,
  `familia` integer  not null,
    FOREIGN KEY (familia) REFERENCES familia (ID),
    FOREIGN KEY (nivel_grado) REFERENCES nivel_grado (ID)
);


create table IF NOT EXISTS `anio` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `anio` integer not null
);

create table IF NOT EXISTS `mes` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `mes` integer not null
);


create table IF NOT EXISTS `tipo_pago` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `descripcion` varchar(255) not null
);

create table IF NOT EXISTS `cuota_base` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `valor` double not null,
  `anio` integer not null,
  `mes` integer  not null,
  FOREIGN KEY (anio) REFERENCES anio (ID),
  FOREIGN KEY (mes) REFERENCES mes (ID)
);

create table IF NOT EXISTS `cuota` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `anio` integer not null,
  `mes` integer  not null,
  `cuota_base` integer not null,
  FOREIGN KEY (anio) REFERENCES anio (ID),
  FOREIGN KEY (mes) REFERENCES mes (ID),
  FOREIGN KEY (cuota_base) REFERENCES cuota_base (ID)


);

create  table IF NOT EXISTS `pago` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `recibo` varchar(255) not null,
  `descuento_aplicado` double null ,
  `tipo_pago` integer not null,
  `familia` integer not null,
  `cuota` integer not null,
   FOREIGN KEY (tipo_pago) REFERENCES tipo_pago (ID),
   FOREIGN KEY (familia) REFERENCES familia (ID),
   FOREIGN KEY (cuota) REFERENCES cuota (ID)
);