-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sound--
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sound--
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sound--` DEFAULT CHARACTER SET utf8 ;
USE `sound--` ;

-- -----------------------------------------------------
-- Table `sound--`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Categoria` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Musica` (
  `id` INT NOT NULL,
  `título` VARCHAR(45) NOT NULL,
  `letra` VARCHAR(45) NOT NULL,
  `data de lançamento` DATE NOT NULL,
  `duração` INT NOT NULL,
  `censura` VARCHAR(45) NOT NULL,
  `Categoria_id` INT NOT NULL,
  PRIMARY KEY (`Categoria_id`, `id`),
  INDEX `fk_Musica_Categoria1_idx` (`Categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_Musica_Categoria1`
    FOREIGN KEY (`Categoria_id`)
    REFERENCES `sound--`.`Categoria` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Autor` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Usuario` (
  `cpf` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Playlist` (
  `id` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `visibilidade` VARCHAR(45) NOT NULL,
  `Categoria_id` INT NOT NULL,
  `Usuario_cpf` INT NOT NULL,
  PRIMARY KEY (`id`, `Categoria_id`, `Usuario_cpf`),
  INDEX `fk_Playlist_Categoria1_idx` (`Categoria_id` ASC) VISIBLE,
  INDEX `fk_Playlist_Usuario1_idx` (`Usuario_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Playlist_Categoria1`
    FOREIGN KEY (`Categoria_id`)
    REFERENCES `sound--`.`Categoria` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Playlist_Usuario1`
    FOREIGN KEY (`Usuario_cpf`)
    REFERENCES `sound--`.`Usuario` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Produtor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Produtor` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Autor_has_Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Autor_has_Musica` (
  `Autor_id` INT NOT NULL,
  `Musica_Categoria_id` INT NOT NULL,
  `Musica_id` INT NOT NULL,
  PRIMARY KEY (`Autor_id`, `Musica_Categoria_id`, `Musica_id`),
  INDEX `fk_Autor_has_Musica_Musica1_idx` (`Musica_Categoria_id` ASC, `Musica_id` ASC) VISIBLE,
  INDEX `fk_Autor_has_Musica_Autor1_idx` (`Autor_id` ASC) VISIBLE,
  CONSTRAINT `fk_Autor_has_Musica_Autor1`
    FOREIGN KEY (`Autor_id`)
    REFERENCES `sound--`.`Autor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Autor_has_Musica_Musica1`
    FOREIGN KEY (`Musica_Categoria_id` , `Musica_id`)
    REFERENCES `sound--`.`Musica` (`Categoria_id` , `id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Produtor_has_Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Produtor_has_Musica` (
  `Produtor_id` INT NOT NULL,
  `Musica_Categoria_id` INT NOT NULL,
  `Musica_id` INT NOT NULL,
  PRIMARY KEY (`Produtor_id`, `Musica_Categoria_id`, `Musica_id`),
  INDEX `fk_Produtor_has_Musica_Musica1_idx` (`Musica_Categoria_id` ASC, `Musica_id` ASC) VISIBLE,
  INDEX `fk_Produtor_has_Musica_Produtor1_idx` (`Produtor_id` ASC) VISIBLE,
  CONSTRAINT `fk_Produtor_has_Musica_Produtor1`
    FOREIGN KEY (`Produtor_id`)
    REFERENCES `sound--`.`Produtor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Produtor_has_Musica_Musica1`
    FOREIGN KEY (`Musica_Categoria_id` , `Musica_id`)
    REFERENCES `sound--`.`Musica` (`Categoria_id` , `id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sound--`.`Playlist_has_Musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sound--`.`Playlist_has_Musica` (
  `Playlist_id` INT NOT NULL,
  `Playlist_Categoria_id` INT NOT NULL,
  `Playlist_Usuario_cpf` INT NOT NULL,
  `Musica_Categoria_id` INT NOT NULL,
  `Musica_id` INT NOT NULL,
  PRIMARY KEY (`Playlist_id`, `Playlist_Categoria_id`, `Playlist_Usuario_cpf`, `Musica_Categoria_id`, `Musica_id`),
  INDEX `fk_Playlist_has_Musica_Musica1_idx` (`Musica_Categoria_id` ASC, `Musica_id` ASC) VISIBLE,
  INDEX `fk_Playlist_has_Musica_Playlist1_idx` (`Playlist_id` ASC, `Playlist_Categoria_id` ASC, `Playlist_Usuario_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Playlist_has_Musica_Playlist1`
    FOREIGN KEY (`Playlist_id` , `Playlist_Categoria_id` , `Playlist_Usuario_cpf`)
    REFERENCES `sound--`.`Playlist` (`id` , `Categoria_id` , `Usuario_cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Playlist_has_Musica_Musica1`
    FOREIGN KEY (`Musica_Categoria_id` , `Musica_id`)
    REFERENCES `sound--`.`Musica` (`Categoria_id` , `id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
