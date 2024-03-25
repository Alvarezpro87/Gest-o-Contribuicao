# Gestão de Contribuições

Este projeto, Gestão de Contribuições, é uma API REST desenvolvida para gerenciar contribuições de contribuintes, possibilitando cálculo do tempo total de contribuição, e o valor total atualizado das contribuições com base nas aliquotas aplicáveis.

## Tecnologias Utilizadas

1. Spring Boot 3.2.4: Para construção da API REST.
2. PostgreSQL 16: Como banco de dados.
3. Docker-compose 2.25: Para containerização da aplicação e dos serviços dependentes.
4. WireMock: Para simulação de respostas de serviços externos em ambiente de desenvolvimento.
5. Postman: Para os testes da aplicação.

## Execução com Docker Compose

Utilize o seguinte comando para subir todos os serviços (aplicação, banco de dados e WireMock):

```bash
docker-compose up --build
```

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
    * GET `http://localhost:8081/contribuintes/12345678901`
    * Descrição: Retorna detalhes sobre o contribuinte TESTE;
- Dados esperados:
```bash
{
  "cpf": "12345678901",
  "categoria": "Empregado",
  "salario": 5000.00,
  "inicio_contribuicao": "01/01/2020"
}
```

- WireMock dados do contribuinte calculados
    * GET `http://localhost:8080/contribuintes/12345678901`
    * Descrição: Retorna detalhes sobre o contribuinte, incluindo tempo total
      de contribuição em meses e o total contribuído ajustado, considerando o
      salário, categoria, e variações do salário mínimo.
- Dados esperados:
```bash
{
  "{
  "cpf": "12345678901",
  "tempoContribuicaoMeses": 50,
  "categoria": "Empregado",
  "totalContribuidoAjustado": 47564.97
}
```


  