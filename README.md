# Suricato

Para subir o servidor, basta executar o arquivo Boot.java.

Se quiser inserir no banco as lousas default do projeto, rode no seu MySql as queries no arquivo lousas.sql.

# Build
mvn clean package

# Docker Compose
docker compose -p suricato-agil up -d