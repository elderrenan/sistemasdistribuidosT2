CREATE TABLE hospedagem
(
  codigo serial NOT NULL,
  nome varchar(100),
  cidade varchar(100),
  estado varchar(100),
  numquartosdisponiveis integer,
  valordiaria double precision,
  periodoinicio varchar(10),
  periodofim varchar(10),
  CONSTRAINT hospedagem_pkey PRIMARY KEY (codigo)
),

CREATE TABLE passagens
(
  codigo serial NOT NULL,
  estadoorigem varchar(100),
  cidadeorigem varchar(100),
  estadodestino varchar(100),
  cidadedestino varchar(100),
  compania varchar(100),
  datainicio varchar(10),
  datafim varchar(10),
  quantpassagemdisponivel integer,
  valor double precision,
  CONSTRAINT passagens_pkey PRIMARY KEY (codigo)
),

CREATE TABLE pacotes
(
  codigo serial NOT NULL,
  codhospedagen integer NOT NULL,
  codpassagemida integer NOT NULL,
  codpassagemvolta integer NOT NULL,
  valorpacote double precision,
  'vagasdisponíveis' integer,
  datapartida varchar(10),
  dataretorno varchar(10),
  estadoorigem varchar(100),
  cidadeorigem varchar(100),
  estadodestio varchar(100),
  cidadedestino varchar(100),
  CONSTRAINT pacotes_pkey PRIMARY KEY (codigo),
  CONSTRAINT pacotes_codhospedagen_fkey FOREIGN KEY (codhospedagen)
      REFERENCES hospedagem (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pacotes_codpassagemida_fkey FOREIGN KEY (codpassagemida)
      REFERENCES passagens (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT pacotes_codpassagemvolta_fkey FOREIGN KEY (codpassagemvolta)
      REFERENCES passagens (codigo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
),




INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Bom sono','São Paulo','São Paulo',50,98.9,'01/03/2014','31/10/2014'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Slaviero','Copacabana','Rio de Janeiro',102,325,'01/12/2014','31/01/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Solar do Sol','Recife','Pernambuco',15,98,'01/02/2015','31/10/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Burbom','Manaus','Amazonas',20,96.9,'01/02/2015','31/12/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Bc Paradise Hostel','Balneário Camburiú','Santa Catarina',50,65,'14/02/2015','19/02/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Hotel Bella Camboriú','Balneário Camburiú','Santa Catarina',100,172,'01/03/2015','31/03/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Hotel Bella Camboriú','Balneário Camburiú','Santa Catarina',100,1000,'30/12/2014','10/01/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Hotel Bella Camboriú','Balneário Camburiú','Santa Catarina',100,200,'10/01/2015','31/02/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Slaviero','Curitiba','Paraná',56,167.6,'01/01/2014','31/09/2015'),
INSERT INTO hospedagem(
            nome, cidade, estado, numquartosdisponiveis, valordiaria, 
            periodoinicio, periodofim)
    VALUES ('Burbom','Curitiba','Paraná',194,198.5,'01/01/2014','31/12/2014'),


INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Paraná','Curitiba','Balneário Camburiú','Santa Catarina','TAM','10/01/2015','31/02/2015',100,110),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('São Paulo','São Paulo','Paraná','Curitiba','TAM','01/01/2014','31/12/2014',100,99.9),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Paraná','Curitiba','São Paulo','São Paulo','TAM','01/01/2014','31/12/2014',100,110),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('São Paulo','São Paulo','Paraná','Curitiba','TAM','15/02/2015','31/09/2015',100,89.9),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Paraná','Curitiba','Copacabana','Rio de Janeiro','TAM','01/12/2014','31/01/2015',100,200),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Paraná','Curitiba','Recife','Pernambuco','TAM','01/02/2015','31/10/2015',100,168.5),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Recife','Pernambuco','Manaus','Amazonas','TAM','01/02/2015','31/12/2015',100,168.5),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('São Paulo','São Paulo','Balneário Camburiú','Santa Catarina','TAM','14/02/2015','19/02/2015',100,135),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Paraná','Curitiba','Balneário Camburiú','Santa Catarina','TAM','01/03/2015','31/03/2015',100,69.9),
INSERT INTO passagens(
            estadoorigem, cidadeorigem, estadodestino, cidadedestino, 
            compania, datainicio, datafim, quantpassagemdisponivel, valor)
    VALUES ('Manaus','Amazonas','Balneário Camburiú','Santa Catarina','TAM','30/12/2014','10/01/2015',100,800),



INSERT INTO pacotes(
            codhospedagen, codpassagemida, codpassagemvolta, valorpacote, 
            "vagasdisponíveis", datapartida, dataretorno, estadoorigem, cidadeorigem, 
            estadodestio, cidadedestino)
    VALUES (1,1,2,436.59,20,'01/01/2014','31/12/2014','São Paulo','São Paulo','Paraná','Curitiba');