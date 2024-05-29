# ApiParaMusica
API REST para um Sistema de Cadastro de Músicas

## Pré-requisitos para usar a ApiParaMusica com o Eclipse:
### 1. Software:

Eclipse IDE: Baixe e instale a versão mais recente do Eclipse para seu sistema operacional (https://www.eclipse.org/downloads/).

Git: Instale o Git para clonar o repositório da ApiParaMusica. Você pode encontrar instruções de instalação em [https://git-scm.com/book/en/v2/Getting-Started-Installing-Git].

Java Development Kit (JDK): Instale o JDK para executar a aplicação Java. Você pode encontrar o download em [https://www.oracle.com/java/technologies/downloads/].

Maven: Instale o Maven para gerenciar as dependências do projeto. Você pode encontrar instruções de instalação em [https://maven.apache.org/download.cgi].

Banco de dados: Configure um banco de dados MySQL. Você pode encontrar instruções de instalação e configuração em [https://dev.mysql.com/].

### 2. Passos no Eclipse:

2.1 Clonando o repositório:

Abra o Eclipse e vá em File > New > Project.
Selecione Git na categoria Use an existing Git repository.
Clique em Next e insira a URL do repositório: https://github.com/ArthurCFred/ApiParaMusica.
Clique em Next e selecione a pasta local onde deseja clonar o projeto.
Clique em Finish.

2.2 Importando o projeto Maven:

No menu do Eclipse, vá em File > Import.
Na caixa de pesquisa, digite Maven Projects e selecione-o.
Clique em Next e marque o projeto ApiParaMusica.
Clique em Finish.

2.3 Configurando o banco de dados:

No Eclipse, abra o arquivo application.properties localizado na pasta src/main/resources.
Edite as propriedades spring.datasource.url, spring.datasource.username e spring.datasource.password com as credenciais de acesso ao seu banco de dados MySQL.
Observação: Substitua localhost pelo nome ou endereço IP do seu servidor MySQL.

2.4 Criando o banco de dados (apiparamusica_database):

Execute o script criar-banco-de-dados.sql localizado na pasta src/main/resources para criar as tabelas do banco de dados.
Observação: Certifique-se de ter os privilégios de acesso ao banco de dados para executar o script.
Observação: O script irá criar um banco de dados chamado apiparamusica_database. Certifique-se de que este nome esteja de acordo com o que você definiu na etapa 2.3.

2.5 Executando a aplicação:

No Eclipse, abra o editor de Java e encontre a classe br.org.serratec.apiparamusica.Application.
Clique com o botão direito do mouse na classe e selecione Run As > Java Application.
Observações:

Certifique-se de que todas as variáveis de ambiente do Java e Maven estejam configuradas corretamente.
Se você encontrar algum erro durante a execução, verifique os logs do console do Eclipse.
Você pode ajustar as configurações da aplicação no arquivo application.properties de acordo com suas necessidades.
Recursos adicionais:

Documentação da ApiParaMusica: https://github.com/topics/music-api
Tutoriais do Eclipse: https://www.eclipse.org/
Documentação do Maven: https://maven.apache.org/guides/index.html
Documentação do MySQL: https://dev.mysql.com/

## Endpoints da API

Artistas
GET /api/artistas: Listar todos os artistas.
GET /api/artistas/{id}: Obter um artista por ID.
GET /api/artistas/buscar-por-genero?genero={genero}: Buscar artistas por gênero.
POST /api/artistas: Criar um novo artista.
PUT /api/artistas/{id}: Atualizar um artista.
DELETE /api/artistas/{id}: Excluir um artista.
Álbuns
GET /api/albuns: Listar todos os álbuns.
GET /api/albuns/{id}: Obter um álbum por ID.
GET /api/albuns/buscar-por-artista?nomeArtista={nomeArtista}: Buscar álbuns por nome do artista.
POST /api/albuns: Criar um novo álbum.
PUT /api/albuns/{id}: Atualizar um álbum.
DELETE /api/albuns/{id}: Excluir um álbum.
Músicas
GET /api/musicas: Listar todas as músicas.
GET /api/musicas/{id}: Obter uma música por ID.
GET /api/musicas/buscar-por-titulo?titulo={titulo}: Buscar músicas por título.
GET /api/musicas/buscar-por-genero?genero={genero}: Buscar músicas por gênero.

## A aplicação ja esta com algumas injeções de banco da dado para averiguação.

http://localhost:8080/api/musicas

http://localhost:8080/api/artistas

http://localhost:8080/api/artistas

http://localhost:8080/api/albuns/buscar-por-artista?nomeArtista=Baco Exu do Blues

http://localhost:8080/api/artistas/buscar-por-genero?genero=Rap

http://localhost:8080/api/musicas/buscar-por-album?album=Bluesman

http://localhost:8080/api/musicas/buscar-por-titulo?titulo=Sozinho
