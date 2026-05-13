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

    public Vertice adicionarOuAtualizarVertice(long id, String texto) {
        Vertice vertice = this.pegarVertice(id);
        if(vertice == null) {
            vertice = new Vertice(id, texto);
            Vertices.add(vertice);
            return vertice;
        } else {
            vertice.texto = texto;
            return vertice;
        }
    }

    public Aresta adicionarOuAtualizarAresta(Vertice origem, Vertice destino, int peso, String texto) {
        Aresta aresta = this.pegarAresta(origem, destino);
        if (aresta == null){
            aresta = new Aresta(origem, destino, peso, texto);
            Arestas.add(aresta);
            return aresta;
        } else {
            aresta.peso = peso;
            aresta.texto = texto;
            return aresta;
        }
    }

    
    public int contarLacos() {
        int total = 0;
        for (int i = 0; i < Arestas.size(); i++) {
            if (Arestas.get(i).origem == Arestas.get(i).destino) {
                total++;
            }
        }
        return total;
    }

    public boolean ehCompleto() {
        for (int i = 0; i < Vertices.size(); i++) {
            for (int j = 0; j < Vertices.size(); j++) {
                if (i != j) {
                    Aresta aresta = pegarAresta(Vertices.get(i), Vertices.get(j));
                    if (aresta == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public int grauVertice(long id) {
        Vertice vertice = this.pegarVertice(id);
        if (vertice == null) {
            return 0;
        }

        int grau = 0;
        for (int i = 0; i < Arestas.size(); i++) {
            Aresta aresta = Arestas.get(i);
            if (aresta.origem == vertice || aresta.destino == vertice) {
                grau++;
            }
        }
        return grau;
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
    private Aresta pegarAresta(Vertice origem, Vertice destino) {
        for (int i = 0; i < Arestas.size(); i++) {
            if (origem == Arestas.get(i).origem && destino == Arestas.get(i).destino) {
                return Arestas.get(i);
            }
        }
        return null;
    }
}
