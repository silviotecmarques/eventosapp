# eventosapp

# I) Instale o banco de dados MySQL com Docker

1) Baixe a imagem do MySQL:

sudo docker pull mysql/mysql-server

2) Execute um container definindo uma senha root desejada:

sudo docker run --name meu-mysql-container  \
-p 3306:3306 -p 33060:33060  \
-e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD='michelli14'   \
-d mysql/mysql-server:latest

3) Caso precise entrar no container:

sudo docker exec -it meu-mysql-container mysql -uroot -pmichelli14

4) Caso o container já tenha sido criado e não está UP:

4.1) Primeiro obtem o container id:
sudo docker ps

4.2) Iniciei o Container
sudo docker start CONTAINER_ID