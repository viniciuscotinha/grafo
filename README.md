# Projeto de Grafos em Java

Base inicial para um projeto de faculdade com:

- Java
- Maven
- JUnit 5
- Estrutura pronta para TDD

## Requisitos

- JDK 17 ou superior
- Maven 3.9 ou superior

## Como rodar os testes

```bash
mvn test
```

## Estrutura

```text
src/
  main/
    java/
      br/ufg/grafo/
  test/
    java/
      br/ufg/grafo/
```

## Sugestão de fluxo TDD

1. Escreva um teste para o comportamento do grafo.
2. Rode os testes e veja falhar.
3. Implemente o mínimo para passar.
4. Refatore mantendo os testes verdes.

## Próximos passos

- Implementar grafo direcionado ou não direcionado
- Adicionar pesos nas arestas, se o trabalho pedir
- Criar algoritmos como BFS, DFS, Dijkstra ou Prim, conforme a disciplina
