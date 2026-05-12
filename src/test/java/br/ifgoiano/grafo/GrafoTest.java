package br.ifgoiano.grafo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

class GrafoTest {

    @Test
    @DisplayName("Adicionar um vertice")
    void deveAdicionarUmVertice() {
        Grafo g = new Grafo();
        g.adicionarOuAtualizarVertice(1, "texto1");
        assertEquals(g.Vertices.get(0).id, 1);
    }

    @Test
    @DisplayName("Adicionar um vertice e adicionar outro de mesmo id")
    void deveAdicionarMesmoVertice() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(1, "texto2");
        assertAll(
            () -> assertEquals(v1, v2),
            () -> assertEquals("texto2", v1.texto)
        );
    }
    @Test
    @DisplayName("Adicionar uma aresta")
    void deveAdicionarUmAresta() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Aresta a1 = g.adicionarOuAtualizarAresta(v1, v2, 5, "texto1");
        assertAll(
            () -> assertEquals(a1, g.Arestas.get(0)),
            () -> assertEquals(v1, g.Arestas.get(0).origem),
            () -> assertEquals(v2, g.Arestas.get(0).destino),
            () -> assertEquals(5, g.Arestas.get(0).peso),
            () -> assertEquals("texto1", g.Arestas.get(0).texto)
        );
    }

    @Test
    @DisplayName("Adicionar uma aresta e adicionar outro de mesmo jeito")
    void deveAdicionarMesmoAresta() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Aresta a1 = g.adicionarOuAtualizarAresta(v1, v2, 5, "texto1");
        Aresta a2 = g.adicionarOuAtualizarAresta(v1, v2, 6, "texto2");
        assertAll(
            () -> assertEquals(a1, a2),
            () -> assertEquals(6, g.Arestas.get(0).peso),
            () -> assertEquals("texto2", g.Arestas.get(0).texto)
        );
    }
}
