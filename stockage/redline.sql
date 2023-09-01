drop database IF EXISTS redline;

CREATE SCHEMA IF NOT EXISTS `redline`;
USE `redline` ;

CREATE TABLE `formation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `prix` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE theme (
 id int NOT NULL AUTO_INCREMENT,
 nom varchar(255) DEFAULT NULL,
 id_parent int DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE etudiant (
  id int NOT NULL AUTO_INCREMENT,
  nom varchar(255) DEFAULT NULL,
  prenom varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `professeur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `matiere` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `salle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `capacite` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_de_debut` date DEFAULT NULL,
  `date_de_fin` date DEFAULT NULL,
  `capacite` int DEFAULT NULL,
  `id_formation` int NOT NULL,
  CONSTRAINT `FK_ref_formation` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `cours` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `id_session` int NOT NULL,
  `id_professeur` int,
  `id_matiere` int,
  `id_salle` int,
  CONSTRAINT `FK_ref_prof` FOREIGN KEY (`id_professeur`) REFERENCES `professeur` (`id`),
  CONSTRAINT `FK_ref_session` FOREIGN KEY (`id_session`) REFERENCES `session` (`id`),
  CONSTRAINT `FK_ref_salle` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`),
  CONSTRAINT `FK_ref_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;



CREATE TABLE `formation_theme` (
  `id_formation` int NOT NULL,
  `id_theme` int NOT NULL,
  PRIMARY KEY (`id_formation`,`id_theme`),
  CONSTRAINT `FK_FT_formation` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id`),
  CONSTRAINT `FK_FT_theme` FOREIGN KEY (`id_theme`) REFERENCES `theme` (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `formation_matiere` (
  `id_formation` int NOT NULL,
  `id_matiere` int NOT NULL,
  PRIMARY KEY (`id_formation`,`id_matiere`),
  CONSTRAINT `FK_FM_formation` FOREIGN KEY (`id_formation`) REFERENCES `formation` (`id`),
  CONSTRAINT `FK_FM_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `session_etudiant` (
  `id_etudiant` int NOT NULL,
  `id_session` int NOT NULL,
  valide boolean DEFAULT FALSE,
  PRIMARY KEY (`id_session`,`id_etudiant`),
  CONSTRAINT `FK_SE_etudiant` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id`),
  CONSTRAINT `FK_SE_session` FOREIGN KEY (`id_session`) REFERENCES `session` (`id`)
) ENGINE=InnoDB ;

CREATE TABLE redline.user (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  admin boolean DEFAULT FALSE,
  password_digest varchar(255) DEFAULT NULL,
  UNIQUE (email),
  PRIMARY KEY (id)
) ENGINE=InnoDB ;



insert into formation(nom, description, prix) value("POEC Java", "", 5000);
insert into formation(nom, description, prix) value("Java J2EE", "", 5000);
insert into formation(nom, description, prix) value("Java Sprint", "", 5000);
insert into formation(nom, description, prix) value("C Basic", "", 5000);
insert into formation(nom, description, prix) value("C++ DEV", "", 5000);
insert into formation(nom, description, prix) value("C# .NET", "", 5000);
insert into formation(nom, description, prix) value("Ruby Basic", "", 5000);
insert into formation(nom, description, prix) value("Ruby On Rails", "", 5000);
insert into formation(nom, description, prix) value("PHP Web", "", 5000);
insert into formation(nom, description, prix) value("PHP Symphony", "", 5000);
insert into formation(nom, description, prix) value("Python", "", 5000);
insert into formation(nom, description, prix) value("AutoIT - All you need", "", 5000);
insert into formation(nom, description, prix) value("Perl Ancien", "", 5000);
insert into formation(nom, description, prix) value("XML language", "", 5000);
insert into formation(nom, description, prix) value("Database", "", 5000);
insert into formation(nom, description, prix) value("HTML", "", 5000);
insert into formation(nom, description, prix) value("Java Script", "", 5000);
insert into formation(nom, description, prix) value("CSS Boostrap", "", 5000);


insert into theme(id, nom, id_parent) value
(1, "catalogue", null),
(2, "informatique", 1),
(3, "science univers", 1),
(4, "programmation", 1),
(5, "programmation", 2),
(6, "web", 2),
(7, "réseau", 2);

insert into theme(nom, id_parent) value
("Langage compilé", 5),
("langage interprété", 5),
("Langage intermédiaire", 5);


insert into etudiant(nom, prenom) value
("PHAM", "Toan"),
("", "Romain"),
("", "Quing Yu"),
("", "Sofiane"),
("", "Stephanie"),
("", "Mylene"),
("", "Kevin"),
("", "Lucas"),
("", "Yoane"),
("", "Samuel"),
("Lacoste", "Leny"),
("", "Jennyfer"),
("", "Armerith"),
("", "Ilyas");


insert into professeur(nom, prenom) value("", "Stéphane");
insert into professeur(nom, prenom) value("", "Bruno");
insert into professeur(nom, prenom) value("", "Christophe");
insert into professeur(nom, prenom) value("", "Christian");


insert into session(date_de_debut, date_de_fin, capacite, id_formation) VALUES('2023-02-05', '2015-03-05', 15, 1);
insert into session(date_de_debut, date_de_fin, capacite, id_formation) VALUES('2023-07-05', '2015-08-05', 15, 1);
insert into session(date_de_debut, date_de_fin, capacite, id_formation) VALUES('2023-11-05', '2015-12-05', 15, 1);


insert into matiere(nom) value("Soft Skill");
insert into matiere(nom) value("Java");
insert into matiere(nom) value("Sprint");
insert into matiere(nom) value("C++");
insert into matiere(nom) value("HTML");
insert into matiere(nom) value("Java Script");
insert into matiere(nom) value("CSS");
insert into matiere(nom) value("SQL");
insert into matiere(nom) value("Angular");
insert into matiere(nom) value("Git");


insert into salle(nom, capacite) value("S1", 10);
insert into salle(nom, capacite) value("S2", 15);
insert into salle(nom, capacite) value("S3", 15);
insert into salle(nom, capacite) value("S4", 20);
insert into salle(nom, capacite) value("S5", 20);

insert into session_etudiant(id_etudiant, id_session, valide) value
(1, 1, true),
(11, 1, true),
(14, 1, true),
(2, 1, false),
(5, 1, false);

insert into cours(nom, date, id_session, id_professeur, id_matiere, id_salle) VALUES('Java Basic', '2023-11-05', 1,1,1,1);
insert into cours(nom, date, id_session, id_professeur, id_matiere, id_salle) VALUES('Database', '2015-11-08', 1,2,2,1);