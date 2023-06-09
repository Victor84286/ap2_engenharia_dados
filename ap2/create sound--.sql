-- MySQL Script generated by MySQL Workbench
-- Fri Jun  2 08:26:14 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sound
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sound
-- -----------------------------------------------------
drop database if EXISTS sound;
CREATE SCHEMA IF NOT EXISTS `sound` DEFAULT CHARACTER SET utf8 ;
USE `sound` ;

-- -----------------------------------------------------
-- Table `sound`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Musica` (
  `titulo` VARCHAR(45) NOT NULL,
  `letra` VARCHAR(1000) NOT NULL,
  `data_lancamento` DATE NOT NULL,
  `duracao` INT NOT NULL,
  `censura` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Musica_Categoria1`
    FOREIGN KEY (`Categoria_id`)
    REFERENCES `sound`.`Categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Autor` (
  `nome` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Usuario` (
  `nome` VARCHAR(45) NOT NULL,
  `cpf` INT NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `numero_cartao` INT NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `data_criacao` DATE NOT NULL,
  `visibilidade` TINYINT NOT NULL,
  `categoria_id` INT NOT NULL,
  `usuario_cpf` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Playlist_Categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `sound`.`Categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Playlist_Usuario1`
    FOREIGN KEY (`usuario_cpf`)
    REFERENCES `sound`.`Usuario` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Produtor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Produtor` (
  `nome` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Autor_has_Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Autor_has_Musica` (
  `Autor_id` INT NOT NULL,
  `Musica_id` INT NOT NULL,
  PRIMARY KEY (`Autor_id`, `Musica_id`),
  CONSTRAINT `fk_Autor_has_Musica_Autor`
    FOREIGN KEY (`Autor_id`)
    REFERENCES `sound`.`Autor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Autor_has_Musica_Musica1`
    FOREIGN KEY (`Musica_id`)
    REFERENCES `sound`.`Musica` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Produtor_has_Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Produtor_has_Musica` (
  `Produtor_id` INT NOT NULL,
  `Musica_id` INT NOT NULL,
  PRIMARY KEY (`Produtor_id`, `Musica_id`),
  CONSTRAINT `fk_Produtor_has_Musica_Produtor1`
    FOREIGN KEY (`Produtor_id`)
    REFERENCES `sound`.`Produtor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Produtor_has_Musica_Musica1`
    FOREIGN KEY (`Musica_id`)
    REFERENCES `sound`.`Musica` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound`.`Musica_has_Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound`.`Musica_has_Playlist` (
  `Musica_id` INT NOT NULL,
  `Playlist_int` INT NOT NULL,
  PRIMARY KEY (`Musica_id`, `Playlist_int`),
  CONSTRAINT `fk_Musica_has_Playlist_Musica1`
    FOREIGN KEY (`Musica_id`)
    REFERENCES `sound`.`Musica` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Musica_has_Playlist_Playlist1`
    FOREIGN KEY (`Playlist_int`)
    REFERENCES `sound`.`Playlist` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO categoria (nome)
VALUES ('Axé'),
 ('Blues'),
 ('Country'),
 ('Eletrônica'),
 ('Forró'),
 ('Funk'),
 ('Gospel'),
 ('Hip Hop'),
 ('Jazz'),
 ('MPB'),
 ('Música clássica'),
 ('Pagode'),
 ('Pop'),
 ('Rap'),
 ('Reggae'),
 ('Rock'),
 ('Samba')