package br.ifgoiano.grafo;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        Aresta a1 = g.adicionarOuAtualizarAresta(v1, v2, "texto1");
        assertAll(
            () -> assertEquals(a1, g.Arestas.get(0)),
            () -> assertEquals(v1, g.Arestas.get(0).origem),
            () -> assertEquals(v2, g.Arestas.get(0).destino),
            () -> assertEquals("texto1", g.Arestas.get(0).texto)
        );
    }

    @Test
    @DisplayName("Adicionar uma aresta e adicionar outro de mesmo jeito")
    void deveAdicionarMesmoAresta() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Aresta a1 = g.adicionarOuAtualizarAresta(v1, v2, "texto1");
        Aresta a2 = g.adicionarOuAtualizarAresta(v1, v2, "texto2");
        assertAll(
            () -> assertEquals(a1, a2),
            () -> assertEquals("texto2", g.Arestas.get(0).texto)
        );
    }

    @Test
    @DisplayName("Contar zero lacos")
    void deveContarZeroLacos() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");

        assertEquals(0, g.contarLacos());
    }

    @Test
    @DisplayName("Contar um laco")
    void deveContarUmLaco() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        g.adicionarOuAtualizarAresta(v1, v1, "a1");
        g.adicionarOuAtualizarAresta(v1, v2, "a2");

        assertEquals(1, g.contarLacos());
    }

    @Test
    @DisplayName("Contar tres lacos")
    void deveContarTresLacos() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v1, "a1");
        g.adicionarOuAtualizarAresta(v2, v2, "a2");
        g.adicionarOuAtualizarAresta(v3, v3, "a3");

        assertEquals(3, g.contarLacos());
    }

    @Test
    @DisplayName("Grafo vazio e completo")
    void grafoVazioNaoECompleto() {
        Grafo g = new Grafo();

        assertTrue(g.ehCompleto());
    }

    @Test
    @DisplayName("Grafo com um vertice e completo")
    void grafoComUmVerticeECompleto() {
        Grafo g = new Grafo();
        g.adicionarOuAtualizarVertice(1, "texto1");

        assertTrue(g.ehCompleto());
    }

    @Test
    @DisplayName("Dois vertices sem aresta nao formam grafo completo")
    void doisVerticesSemArestaNaoFormamGrafoCompleto() {
        Grafo g = new Grafo();
        g.adicionarOuAtualizarVertice(1, "texto1");
        g.adicionarOuAtualizarVertice(2, "texto2");

        assertFalse(g.ehCompleto());
    }

    @Test
    @DisplayName("Dois vertices com uma direcao so nao formam grafo completo")
    void doisVerticesComUmaDirecaoSoNaoFormamGrafoCompleto() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");

        assertFalse(g.ehCompleto());
    }

    @Test
    @DisplayName("Dois vertices com duas direcoes formam grafo completo")
    void doisVerticesComDuasDirecoesFormamGrafoCompleto() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v2, v1, "a2");

        assertTrue(g.ehCompleto());
    }

    @Test
    @DisplayName("Tres vertices faltando uma aresta nao formam grafo completo")
    void tresVerticesFaltandoUmaArestaNaoFormamGrafoCompleto() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v2, v1, "a2");
        g.adicionarOuAtualizarAresta(v1, v3, "a3");
        g.adicionarOuAtualizarAresta(v3, v1, "a4");
        g.adicionarOuAtualizarAresta(v2, v3, "a5");

        assertFalse(g.ehCompleto());
    }

    @Test
    @DisplayName("Tres vertices com lacos mas sem ligacoes completas nao formam grafo completo")
    void tresVerticesComLacosMasSemLigacoesCompletasNaoFormamGrafoCompleto() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v1, "a1");
        g.adicionarOuAtualizarAresta(v2, v2, "a2");
        g.adicionarOuAtualizarAresta(v3, v3, "a3");

        assertFalse(g.ehCompleto());
    }

    @Test
    @DisplayName("Tres vertices com todas as ligacoes entre diferentes formam grafo completo")
    void tresVerticesComTodasAsLigacoesEntreDiferentesFormamGrafoCompleto() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v2, v1, "a2");
        g.adicionarOuAtualizarAresta(v1, v3, "a3");
        g.adicionarOuAtualizarAresta(v3, v1, "a4");
        g.adicionarOuAtualizarAresta(v2, v3, "a5");
        g.adicionarOuAtualizarAresta(v3, v2, "a6");

        assertTrue(g.ehCompleto());
    }

    @Test
    @DisplayName("Arestas repetidas nao compensam aresta faltando no grafo completo")
    void arestasRepetidasNaoCompensamArestaFaltandoNoGrafoCompleto() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v1, v2, "a1 atualizado");
        g.adicionarOuAtualizarAresta(v2, v1, "a2");
        g.adicionarOuAtualizarAresta(v1, v3, "a3");
        g.adicionarOuAtualizarAresta(v3, v1, "a4");

        assertFalse(g.ehCompleto());
    }

    @Test
    @DisplayName("Grau de vertice soma arestas de entrada e saida")
    void grauDoVerticeSomaEntradasESaidas() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v2, v1, "a2");
        g.adicionarOuAtualizarAresta(v3, v1, "a3");

        assertEquals(3, g.grauVertice(1));
    }

    @Test
    @DisplayName("Grau de entrada conta arestas que chegam ao vertice")
    void grauDeEntradaContaArestasQueChegamAoVertice() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v2, v1, "a1");
        g.adicionarOuAtualizarAresta(v3, v1, "a2");
        g.adicionarOuAtualizarAresta(v1, v2, "a3");

        assertEquals(2, g.grauEntrada(1));
    }

    @Test
    @DisplayName("Grau de saida conta arestas que saem do vertice")
    void grauDeSaidaContaArestasQueSaemDoVertice() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v1, v3, "a2");
        g.adicionarOuAtualizarAresta(v2, v1, "a3");

        assertEquals(2, g.grauSaida(1));
    }

    @Test
    @DisplayName("Laco conta como grau um no grau total")
    void lacoContaComoGrauUmNoGrauTotal() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        g.adicionarOuAtualizarAresta(v1, v1, "a1");
        g.adicionarOuAtualizarAresta(v2, v1, "a2");

        assertEquals(2, g.grauVertice(1));
    }

    @Test
    @DisplayName("Laco conta na entrada e na saida")
    void lacoContaNaEntradaENaSaida() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        g.adicionarOuAtualizarAresta(v1, v1, "a1");

        assertAll(
            () -> assertEquals(1, g.grauEntrada(1)),
            () -> assertEquals(1, g.grauSaida(1)),
            () -> assertEquals(1, g.grauVertice(1))
        );
    }

    @Test
    @DisplayName("Vertice inexistente tem grau zero")
    void verticeInexistenteTemGrauZero() {
        Grafo g = new Grafo();

        assertAll(
            () -> assertEquals(0, g.grauVertice(99)),
            () -> assertEquals(0, g.grauEntrada(99)),
            () -> assertEquals(0, g.grauSaida(99))
        );
    }

    @Test
    @DisplayName("Vertice pode ter grau maior que tres")
    void verticePodeTerGrauMaiorQueTres() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        Vertice v4 = g.adicionarOuAtualizarVertice(4, "texto4");
        Vertice v5 = g.adicionarOuAtualizarVertice(5, "texto5");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v3, v1, "a2");
        g.adicionarOuAtualizarAresta(v1, v4, "a3");
        g.adicionarOuAtualizarAresta(v5, v1, "a4");

        assertEquals(4, g.grauVertice(1));
    }

    @Test
    @DisplayName("Mostrar caminho exibe sequencia entre origem e destino")
    void mostrarCaminhoExibeSequenciaEntreOrigemEDestino() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        Vertice v4 = g.adicionarOuAtualizarVertice(4, "texto4");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");
        g.adicionarOuAtualizarAresta(v2, v3, "a2");
        g.adicionarOuAtualizarAresta(v3, v4, "a3");

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            g.mostrarCaminho(1, 4);
        } finally {
            System.setOut(original);
        }

        assertEquals("1 -> 2 -> 3 -> 4", saida.toString().trim());
    }

    @Test
    @DisplayName("Mostrar caminho avisa quando nao existe rota")
    void mostrarCaminhoAvisaQuandoNaoExisteRota() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "texto1");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "texto2");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "texto3");
        g.adicionarOuAtualizarAresta(v1, v2, "a1");

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(saida));

        try {
            g.mostrarCaminho(1, 3);
        } finally {
            System.setOut(original);
        }

        assertEquals("Nao existe caminho", saida.toString().trim());
    }

    @Test
    @DisplayName("Mostrar DOT gera representacao do grafo direcionado")
    void mostrarDOTGeraRepresentacaoDoGrafoDirecionado() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "Casa");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "Escola");
        g.adicionarOuAtualizarAresta(v1, v2, "caminho");

        String dot = g.mostrarDOT();

        assertEquals(
            "digraph Grafo {\n" +
            "    1 [label=\"Casa\"];\n" +
            "    2 [label=\"Escola\"];\n" +
            "    1 -> 2 [label=\"caminho\"];\n" +
            "}",
            dot
        );
    }

    @Test
    @DisplayName("Mostrar DOT funciona com grafo vazio")
    void mostrarDOTFuncionaComGrafoVazio() {
        Grafo g = new Grafo();

        assertEquals(
            "digraph Grafo {\n" +
            "}",
            g.mostrarDOT()
        );
    }

    @Test
    @DisplayName("Mostrar DOT representa arestas nos dois sentidos")
    void mostrarDOTRepresentaArestasNosDoisSentidos() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "A");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "B");
        g.adicionarOuAtualizarAresta(v1, v2, "ida");
        g.adicionarOuAtualizarAresta(v2, v1, "volta");

        assertEquals(
            "digraph Grafo {\n" +
            "    1 [label=\"A\"];\n" +
            "    2 [label=\"B\"];\n" +
            "    1 -> 2 [label=\"ida\"];\n" +
            "    2 -> 1 [label=\"volta\"];\n" +
            "}",
            g.mostrarDOT()
        );
    }

    @Test
    @DisplayName("Mostrar DOT representa laco")
    void mostrarDOTRepresentaLaco() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "Central");
        g.adicionarOuAtualizarAresta(v1, v1, "auto");

        assertEquals(
            "digraph Grafo {\n" +
            "    1 [label=\"Central\"];\n" +
            "    1 -> 1 [label=\"auto\"];\n" +
            "}",
            g.mostrarDOT()
        );
    }

    @Test
    @DisplayName("Mostrar DOT representa varios vertices e arestas")
    void mostrarDOTRepresentaVariosVerticesEArestas() {
        Grafo g = new Grafo();
        Vertice v1 = g.adicionarOuAtualizarVertice(1, "Inicio");
        Vertice v2 = g.adicionarOuAtualizarVertice(2, "Meio");
        Vertice v3 = g.adicionarOuAtualizarVertice(3, "Fim");
        g.adicionarOuAtualizarAresta(v1, v2, "passo1");
        g.adicionarOuAtualizarAresta(v2, v3, "passo2");
        g.adicionarOuAtualizarAresta(v1, v3, "atalho");

        assertEquals(
            "digraph Grafo {\n" +
            "    1 [label=\"Inicio\"];\n" +
            "    2 [label=\"Meio\"];\n" +
            "    3 [label=\"Fim\"];\n" +
            "    1 -> 2 [label=\"passo1\"];\n" +
            "    2 -> 3 [label=\"passo2\"];\n" +
            "    1 -> 3 [label=\"atalho\"];\n" +
            "}",
            g.mostrarDOT()
        );
    }

}
