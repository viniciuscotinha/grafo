package br.ifgoiano.grafo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

class GrafoTest {

    @Test
    @DisplayName("Adicionar um vertice")
    void deveAdicionarUmVertice() {
        Grafo g = new Grafo();
        g.adicionarVertice(1, "texto1");
    }

}
