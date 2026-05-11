package br.ifgoiano.grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    public List<Vertice> Vertices;
    public List<Aresta> Arestas;
    
    public Grafo() {
        this.Arestas = new ArrayList<Aresta>();
        this.Vertices = new ArrayList<Vertice>();
    }

    public Vertice adicionarVertice(long id, String texto) {
        Vertice vertice = this.pegarVertice(id);
        if(vertice == null) {
            vertice = new Vertice(id, texto);
            Vertices.add(vertice);
            return vertice;
        } else {
            return vertice;
        }
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

    public Vertice pegarVertice(long id) {
        for(int i = 0; i < Vertices.size(); i++) {
            if (id == Vertices.get(i).id) {
                return Vertices.get(i);
            }
        }
        return null;
    }
}
