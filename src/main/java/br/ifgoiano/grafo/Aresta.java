package br.ifgoiano.grafo;

public class Aresta {
    public Vertice origem;
    public Vertice destino;
    public String texto;
    
    public Aresta(Vertice origem, Vertice destino, String texto) {
        this.origem = origem;
        this.destino = destino;
        this.texto = texto;
    }
    
}
