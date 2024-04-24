# Gestão de Contribuições

Este projeto, Gestão de Contribuições, é uma API REST desenvolvida para gerenciar contribuições de contribuintes, possibilitando cálculo do tempo total de contribuição, e o valor total atualizado das contribuições com base nas aliquotas aplicáveis.

## Tecnologias Utilizadas

1. Spring Boot 3.2.4: Para construção da API REST.
2. PostgreSQL 16: Como banco de dados.
3. Docker-compose 2.25: Para containerização da aplicação e dos serviços dependentes.
4. Postman: Para os testes da aplicação.

## Configuração das Variáveis de Ambiente
Antes de iniciar a aplicação, é necessário configurar as variáveis de ambiente para a conexão com o banco de dados e a comunicação com serviços externos. Estas configurações são definidas no arquivo .env, localizado na raiz do projeto.

### Adicione as seguintes variáveis ao arquivo .env:

    DB_HOST: Host do banco de dados (ex: db para Docker Compose).
    DB_NAME: Nome do banco de dados.
    DB_USER: Usuário do banco de dados.
    DB_PASS: Senha do banco de dados.
    APP_PORT: Porta em que a aplicação será exposta.
    DB_PORT: Porta em que o banco de dados será exposto.
    CONTRIBUINTES_API_HOST: Host do serviço de contribuintes.
    CONTRIBUINTES_API_PORT: Porta do serviço de contribuintes.

## Execução com Docker Compose

Utilize o seguinte comando na raiz  do projeto  para subir todos os serviços (aplicação, banco de dados):

```bash
docker-compose up --build
```

## Portas Utilizadas

1. Aplicaçoã: 8080
2. PostgreSQL: 5432


## Uso dos endpoints Aliquotas
As aliquotas são utilizadas para calcular o valor da contribuição com base na categoria do contribuinte e seu salário. Abaixo estão os endpoints disponíveis para gerenciar as aliquotas.

- Listar Todas as Aliquotas
    * GET `/aliquotas`
    * Descrição: Retorna todas as aliquotas cadastradas no sistema.

- Consultar por ID
    * GET `/aliquotas/{id}`
    * Descrição: Retorna os detalhes de uma aliquota específica pelo seu ID.

- Criar Nova Aliquota
    * POST `/aliquotas/`
    * Descrição: Cria uma nova aliquota no sistema. É necessário enviar no corpo da requisição os dados da aliquota a ser criada.

- Atualizar Aliquota
    * PUT `/aliquotas/{id}`
    * Descrição: Atualiza os dados de uma aliquota existente. É necessário enviar no corpo da requisição os dados atualizados da aliquota.
- Deletar Aliquota
    * Delete `/aliquotas/{id}`
    * Descrição: Remove uma aliquota do sistema pelo seu ID.

## Obter  os dados do WireMock

- WireMock consulta contribuinte.
    * GET `http://" + apiHost + ":" + apiPort + "/contribuintes/" + 99999999994`
    * Descrição: Retorna detalhes sobre o contribuinte TESTE;
- Dados esperados:
```bash
{
  "cpf": "99999999994",
  "categoria": "MEI",
  "salario": 7500.00,
  "inicio_contribuicao": "03/11/2018"
}
```

- Dados do contribuinte calculados
    * GET `http://" localhost ":" + APP_PORT +/contribuintes/consultar/99999999994`
    * Descrição: Retorna detalhes sobre o contribuinte, incluindo tempo total
      de contribuição em meses e o total contribuído ajustado, considerando o
      salário, categoria, e variações do salário mínimo.
- Dados esperados (Podem variar de acordo com o mês que a aplicação está sendo testada):
```bash
{
  "cpf": "99999999994",
  "tempoContribuicaoMeses": 65,
  "categoria": "MEI",
  "totalContribuidoAjustado": 144308.18
}
```

## Documentação da API com Swagger

A documentação da API está disponível através do Swagger UI, que fornece uma interface interativa para explorar e testar os endpoints da API.

Para acessar a documentação da API via Swagger UI, após iniciar os serviços com Docker Compose, navegue para:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Na interface do Swagger UI, você encontrará a lista de todos os endpoints disponíveis. Você também pode executar chamadas à API diretamente da interface do Swagger para testar os comportamentos. 

## Observação Importante

#### Algumas funcionalidades desta aplicação dependem diretamente da API de Contribuintes. Para garantir o funcionamento completo das chamadas disponíveis, é necessário que a API de Contribuintes esteja ativa e acessível. Certifique-se de que ela esteja rodando na sua máquina ou em um ambiente acessível pela rede antes de realizar os testes.
  