# serieslist-spring
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/gabrielreisresende/serieslist-spring/blob/main/LICENSE)

## Descrição
Este projeto é um CRUD bem estruturado para gerenciar séries. O projeto é estrutrado utilizando o padrão de camadas
Repository, Service e Controller. Além disso, foi desenvolvido utilizando boas práticas como a utilização do padrão DTO.

## Tecnologias utilizadas:
- Spring Boot 3
- Maven
- Spring Data JPA
- Banco de dados H2

## Conceitos abordados:
- Padrão Rest para API web
- Database seeding
- Entidades e ORM
- Padrão de camadas
- Controller, service, repository
- Padrão DTO
- Consultas SQL no Spring Data JPA

## Endpoints:
Este projeto oferece alguns endpoints. Você pode testá-los usando um cliente REST, como, por exemplo, o Postman. Aqui estão alguns exemplos de endpoints disponíveis:
- `GET /series`: retorna todos as series do banco de dados.
- `GET /series/{id}`: retorna uma sérias específica com o ID fornecido.
- `POST /series`: permite inserir uma nova série e seus dados no banco de dados.
- `PUT /series/{id}`: permite atualizar os dados de uma série existente no banco de dados.
- `DELETE /series/{id}`:  permite deletar uma série existente no banco de dados.
