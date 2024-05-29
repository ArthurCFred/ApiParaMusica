CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_lancamento VARCHAR(255) NOT NULL,
    artista_id INT NOT NULL,
    FOREIGN KEY (artista_id) REFERENCES artista(id)
);

CREATE TABLE musica (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    duracao INT NOT NULL,
    album_id INT NOT NULL,
    FOREIGN KEY (album_id) REFERENCES album(id)
);

INSERT INTO artista (nome, genero) VALUES ('Baco Exu do Blues', 'Rap');
INSERT INTO artista (nome, genero) VALUES ('Caetano Veloso', 'MPB');
INSERT INTO artista (nome, genero) VALUES ('Foo Fighters', 'Rock');
INSERT INTO artista (nome, genero) VALUES ('Arctic Monkeys', 'Rock');

INSERT INTO album (nome, data_lancamento, artista_id) VALUES ('Bluesman', '2018-11-23', 1);
INSERT INTO album (nome, data_lancamento, artista_id) VALUES ('Prenda Minha', '1999-04-19', 2);
INSERT INTO album (nome, data_lancamento, artista_id) VALUES ('The Colour and the Shape', '1997-05-20', 3);
INSERT INTO album (nome, data_lancamento, artista_id) VALUES ('Echoes, Silence, Patience & Grace', '2007-09-25', 3);
INSERT INTO album (nome, data_lancamento, artista_id) VALUES ('AM', '2013-09-09', 4);
INSERT INTO album (nome, data_lancamento, artista_id) VALUES ('Favourite Worst Nightmare', '2007-04-18', 4);

INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('Queima Minha Pele', 'Rap', 210, 1);
INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('Te Amo Disgraça', 'Rap', 180, 1);
INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('Sozinho', 'MPB', 220, 2);
INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('Everlong', 'Rock', 250, 3);
INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('The Pretender', 'Rock', 270, 4);
INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('Do I Wanna Know?', 'Rock', 272, 5);
INSERT INTO musica (titulo, genero, duracao, album_id) VALUES ('Do Me a Favour', 'Rock', 216, 6);