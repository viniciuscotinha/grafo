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
      br/ifgoiano/grafo/
  test/
    java/
      br/ifgoiano/grafo/
```

## Regras do projeto

- O grafo e direcionado.
- Um laco conta como grau 1.
- Arestas paralelas so sao consideradas quando nao estao no mesmo sentido.
- A identificacao dos vertices e feita por `id`.
- Nao e permitido repetir `id` de vertice; vertices com o mesmo `id` devem ser considerados o mesmo vertice.

## Sugestao de fluxo TDD

1. Escreva um teste para o comportamento do grafo.
2. Rode os testes e veja falhar.
3. Implemente o minimo para passar.
4. Refatore mantendo os testes verdes.

## Proximos passos

- Implementar as regras de grafo direcionado
- Adicionar pesos nas arestas, se o trabalho pedir
- Criar algoritmos como BFS, DFS, Dijkstra ou Prim, conforme a disciplina
