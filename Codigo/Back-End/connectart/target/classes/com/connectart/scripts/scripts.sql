


/*CREATE TABLE connectart.cliente(
    cliente_id SERIAL PRIMARY KEY,
    cliente_nome VARCHAR(32) NOT NULL,
    cliente_email VARCHAR(64) NOT NULL,
    cliente_senha VARCHAR(16) NOT NULL
);

CREATE TABLE connectart.artista(
    artista_id SERIAL PRIMARY KEY,
    artista_nome VARCHAR(32) NOT NULL,
    artista_email VARCHAR(64) NOT NULL,
    artista_senha VARCHAR(16) NOT NULL,
    artista_endereco VARCHAR(64),
    artista_telefone VARCHAR(11)
);

CREATE TABLE connectart.avalartista (
    aval_artista_id SERIAL PRIMARY KEY,
    aval_artista_pontuacao INTEGER CHECK (aval_artista_pontuacao BETWEEN 1 AND 5),
    aval_artista_comentario TEXT CHECK (char_length(aval_artista_comentario) <= 300),
    aval_artista_cliente INTEGER NOT NULL,
    CONSTRAINT fk_cliente
      FOREIGN KEY (aval_artista_cliente)
      REFERENCES connectart.cliente(cliente_id)
);

CREATE TABLE connectart.avalcliente (
    aval_cliente_id SERIAL PRIMARY KEY,
    aval_cliente_pontuacao INTEGER CHECK (aval_cliente_pontuacao BETWEEN 1 AND 5),
    aval_cliente_comentario TEXT CHECK (char_length(aval_cliente_comentario) <= 300),
    aval_cliente_artista INTEGER NOT NULL,
    CONSTRAINT fk_artista
      FOREIGN KEY (aval_cliente_artista)
      REFERENCES connectart.artista(artista_id)
);
