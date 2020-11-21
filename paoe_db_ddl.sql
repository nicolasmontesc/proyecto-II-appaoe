-- MySQL Workbench Synchronization
-- Generated: 2020-09-05 21:14
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Juan Nicolas

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `paoe_db` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `paoe_db`.`paciente` (
  `documento` INT(12) NOT NULL,
  `tipo_consultante` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` INT(12) NOT NULL,
  `localidad` VARCHAR(45) NOT NULL,
  `edad` INT(3) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `financiacion` VARCHAR(45) NULL DEFAULT NULL,
  `codigo` VARCHAR(45) NULL DEFAULT NULL,
  `semestre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`documento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `paoe_db`.`programa` (
  `nom_programa` VARCHAR(45) NOT NULL,
  `modalidad` VARCHAR(45) NOT NULL,
  `telefono` INT(12) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `nom_director` VARCHAR(45) NOT NULL,
  `facultad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nom_programa`),
  INDEX `fk_programa_facultad_idx` (`facultad` ASC),
  CONSTRAINT `fk_programa_facultad`
    FOREIGN KEY (`facultad`)
    REFERENCES `paoe_db`.`facultad` (`nom_facultad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `paoe_db`.`facultad` (
  `nom_facultad` VARCHAR(45) NOT NULL,
  `telefono` INT(12) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `nom_decano` VARCHAR(45) NOT NULL,
  `psicologo` INT(12) NOT NULL,
  PRIMARY KEY (`nom_facultad`),
  INDEX `fk_facultad_psicologo_idx` (`psicologo` ASC),
  CONSTRAINT `fk_facultad_psicologo`
    FOREIGN KEY (`psicologo`)
    REFERENCES `paoe_db`.`psicologo` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `paoe_db`.`psicologo` (
  `cedula` INT(12) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `edad` INT(3) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(128) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `paoe_db`.`asesoria` (
  `num_asesoria` INT(6) NOT NULL,
  `fecha_hora` DATETIME NOT NULL,
  `tipo_acompanamiento` VARCHAR(45) NOT NULL,
  `acceso_servicio` VARCHAR(45) NOT NULL,
  `remitido_por` VARCHAR(45) NOT NULL,
  `problematica_ref_trab` VARCHAR(45) NOT NULL,
  `acciones_realizadas` VARCHAR(45) NOT NULL,
  `asistencia` VARCHAR(45) NOT NULL,
  `culminacion` VARCHAR(45) NOT NULL,
  `remitido_a` VARCHAR(45) NOT NULL,
  `observaciones` VARCHAR(45) NOT NULL,
  `psicologo` INT(12) NOT NULL,
  PRIMARY KEY (`num_asesoria`),
  INDEX `fk_asesoria_psicologo_idx` (`psicologo` ASC),
  CONSTRAINT `fk_asesoria_psicologo`
    FOREIGN KEY (`psicologo`)
    REFERENCES `paoe_db`.`psicologo` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `paoe_db`.`historial_paciente` (
  `paciente` INT(12) NOT NULL,
  `asesoria` INT(6) NOT NULL,
  PRIMARY KEY (`paciente`, `asesoria`),
  INDEX `fk_historial_paciente_asesoria_idx` (`asesoria` ASC),
  INDEX `fk_historial_paciente_paciente_idx` (`paciente` ASC),
  CONSTRAINT `fk_historial_paciente_paciente`
    FOREIGN KEY (`paciente`)
    REFERENCES `paoe_db`.`paciente` (`documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historial_paciente_asesoria`
    FOREIGN KEY (`asesoria`)
    REFERENCES `paoe_db`.`asesoria` (`num_asesoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `paoe_db`.`programas_estudiante` (
  `paciente` INT(12) NOT NULL,
  `programa` VARCHAR(45) NOT NULL,
  `ingreso` DATE NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `egreso` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`paciente`, `programa`),
  INDEX `fk_programas_estudiante_programa_idx` (`programa` ASC),
  INDEX `fk_programas_estudiante_paciente_idx` (`paciente` ASC),
  CONSTRAINT `fk_programas_estudiante_paciente`
    FOREIGN KEY (`paciente`)
    REFERENCES `paoe_db`.`paciente` (`documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_programas_estudiante_programa`
    FOREIGN KEY (`programa`)
    REFERENCES `paoe_db`.`programa` (`nom_programa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
