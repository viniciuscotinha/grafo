package br.ifgoiano.grafo;

public class Aresta {
    public Vertice origem;
    public Vertice destino;
    public int peso;
    public String texto;
    
    public Aresta(Vertice origem, Vertice destino, int peso, String texto) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.texto = texto;
    }
    
}
