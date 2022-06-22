CREATE DATABASE BID_JOGADORES;
USE BID_JOGADORES;


CREATE TABLE NACIONALIDADE (
	siglaPais char(3) PRIMARY KEY,
	nomePais varchar(30) not null,
	continente varchar(30) not null);

CREATE TABLE CLUBE (
	siglaClube char(3) PRIMARY KEY,
	nomeClube varchar(30) not null,
	estado char(3) not null,
	paisOrigem char(3) not null default 'BRA',
	FOREIGN KEY(paisOrigem) REFERENCES NACIONALIDADE(siglaPais));

CREATE TABLE JOGADOR (
	idJog int PRIMARY KEY AUTO_INCREMENT,
	nomeJog varchar(30) not null,
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

																  
INSERT INTO CLUBE VALUES ('SAN','Santos','SP','BRA'),
						 ('COR','Corinthians','SP','BRA'),
						 ('FLA','Flamengo','RJ','BRA'),
						 ('PAL','Palmeiras','SP','BRA'),
						 ('SAO','Sao Paulo','SP','BRA'),
						 ('CAM','Atletico Mineiro','MG','BRA'),
						 ('GRE','Gremio','RS','BRA');
					
INSERT INTO JOGADOR (nomeJog, posicao, clubeJog, paisOrigem) VALUES ('Cassio','GOL','COR','BRA'),
                                                                            ('Hulk','ATA','CAM','BRA'),
                                                                            ('Calleri','ATA','SAO','ARG'),
                                                                            ('Ricardo Goulart','MEI','SAN','BRA');