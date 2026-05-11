package br.ifgoiano.grafo;

import java.util.List;

public class Grafo {
    public List<Vertice> Vertices;
    public List<Aresta> Arestas;
    
    public Grafo() {
    }

    public void adicionarVertice(long id, String texto) {
        Vertices.add(new Vertice(id, texto));
    }

    public void adicionarAresta(Vertice origem, Vertice destino, int peso, String texto) {
        Arestas.add(new Aresta(origem, destino, peso, texto));
    }

    public int contarLacos() {
        return 0;
    }

    public boolean ehCompleto() {
        return false;
    }

    public int grauVertice() {
        return 0;
    }

    public void mostrarCaminho() {

    }

    public void mostrarDOT() {

    }
    
}
