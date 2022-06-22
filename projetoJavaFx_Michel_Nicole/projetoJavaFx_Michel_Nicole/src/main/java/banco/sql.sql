/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  myhea
 * Created: 31 de mai. de 2022
 */

CREATE DATABASE BID_JOGADORES;
USE BID_JOGADORES;


CREATE TABLE NACIONALIDADE (
	siglaPais char(3) not null PRIMARY KEY,
	nomePais varchar (30) not null,
	continente varchar (30) not null);

CREATE TABLE CLUBE (
	siglaClube char(3) not null PRIMARY KEY,
	nomeClube varchar (30) not null,
	paisOrigem char (3) not null DEFAULT 'BRA',
	estado char(2) not null DEFAULT 'SP',
	FOREIGN KEY(paisOrigem) REFERENCES NACIONALIDADE(siglaPais));

CREATE TABLE JOGADOR (
	idJog int not null PRIMARY KEY AUTO_INCREMENT,
	nomeJog varchar(30) not null,
	dataNasc DATE not null,
	posicao char (3) not null DEFAULT 'ATA',
	clubeJog char(3),
	paisOrigem char (3) not null DEFAULT 'BRA',
	FOREIGN KEY(clubeJog) REFERENCES CLUBE(siglaClube),
	FOREIGN KEY(paisOrigem) REFERENCES NACIONALIDADE(siglaPais));

	
	
INSERT INTO NACIONALIDADE (siglaPais, nomePais,continente) VALUES ('BRA','Brasil','America do Sul'),
																  ('ARG','Argentina','America do Sul'),
																  ('COL','Colombia','America do Sul'),
																  ('ESP','Espanha','Europa'),
																  ('ITA','Italia','Europa'),
																  ('JAP','Japao','Asia');
INSERT INTO CLUBE VALUES ('SAN','Santos','BRA','SP'),
						 ('COR','Corinthians','BRA',DEFAULT),
						 ('FLA','Flamengo','BRA','RJ'),
						 ('PAL','Palmeiras','BRA','SP'),
						 ('SAO','Sao Paulo','BRA','SP'),
						 ('CAM','Atletico Mineiro','BRA','MG'),
						 ('GRE','Gremio',DEFAULT,'RS');
						 
INSERT INTO JOGADOR (nomeJog, dataNasc, posicao, clubeJog, paisOrigem) VALUES ('Cassio','1990-05-20','GOL','COR',DEFAULT),
																			 ('Hulk','1986-07-24',DEFAULT,'CAM','BRA'),
																			 ('Calleri','1993-03-18','ATA','SAO','ARG'),
																			 ('Ricardo Goulart','1992-02-01','MEI','SAN','BRA');

																 
																  



