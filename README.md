# 📚 LiterAlura - Catálogo de Livros e Autores

Projeto desenvolvido em Java 17/21 com foco em persistência de dados e consumo de API, integrando os conceitos de Spring Data JPA e PostgreSQL. O projeto faz parte do desafio do Oracle Next Education (ONE) em parceria com a Alura.

# 🚀 Funcionalidades
Busca por Título: Consulta livros na API externa Gutendex.

Persistência Inteligente: Salva livros e seus respectivos autores automaticamente no banco de dados.

Listagem Completa: Exibe todos os livros e autores já pesquisados e armazenados.

Filtro de Autores Vivos: Consulta autores que estavam vivos em um ano específico fornecido pelo usuário.

Estatísticas por Idioma: Filtra e conta a quantidade de livros em idiomas específicos (PT, EN, ES, FR).

Tratamento de Dados: Conversão de JSON para objetos Java utilizando a biblioteca Jackson.

# 🧠 Conceitos Aplicados
Spring Data JPA: Criação de repositórios e consultas derivadas (Derived Queries).

Queries Personalizadas: Uso da anotação @Query (JPQL) para filtros complexos de datas.

Mapeamento Objeto-Relacional (ORM): Relacionamento @ManyToOne e @OneToMany com persistência em cascata.

Consumo de API: Uso de HttpClient e HttpRequest para integração com serviços externos.

Arquitetura Clean: Organização do projeto em camadas de serviço, modelo e repositório.


Shutterstock
🛠 Tecnologias Utilizadas
Java 17/21+

Spring Boot 3.x

Spring Data JPA

PostgreSQL (Banco de dados relacional)

Jackson (Manipulação de JSON)

Maven (Gerenciador de dependências)

IntelliJ IDEA

# 📂 Estrutura do Projeto
Plaintext
com.alura.literalura
├── model           # Entidades JPA e Records (DTOs)
├── repository      # Interfaces de acesso ao banco (Spring Data)
├── service         # Consumo da API e conversão de dados
├── principal       # Classe de interação com o usuário (Menu)
└── LiteraluraApp   # Classe de inicialização (main)

# ⚙️ Como rodar o projeto
Clone este repositório.

Certifique-se de ter o PostgreSQL instalado.

Configure seu usuário e senha do banco no arquivo src/main/resources/application.properties.

Execute a classe LiteraluraApplication no seu IDE.

Desenvolvido por Rodrigo Gonçalves (rogondev)
