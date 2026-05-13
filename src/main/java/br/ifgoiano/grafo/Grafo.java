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
            if (aresta.origem == vertice && aresta.destino == vertice) {
                grau++;
            } else if (aresta.origem == vertice || aresta.destino == vertice) {
                grau++;
            }
        }
        return grau;
    }

    public int grauEntrada(long id) {
        Vertice vertice = this.pegarVertice(id);
        if (vertice == null) {
            return 0;
        }

        int grau = 0;
        for (int i = 0; i < Arestas.size(); i++) {
            if (Arestas.get(i).destino == vertice) {
                grau++;
            }
        }
        return grau;
    }

    public int grauSaida(long id) {
        Vertice vertice = this.pegarVertice(id);
        if (vertice == null) {
            return 0;
        }

        int grau = 0;
        for (int i = 0; i < Arestas.size(); i++) {
            if (Arestas.get(i).origem == vertice) {
                grau++;
            }
        }
        return grau;
    }

    public String mostrarCaminho(long idOrigem, long idDestino) {
        Vertice origem = this.pegarVertice(idOrigem);
        Vertice destino = this.pegarVertice(idDestino);
        if (origem == null || destino == null) {
            System.out.println("Nao existe caminho");
            return "Nao existe caminho";
        }

        List<Vertice> visitados = new ArrayList<Vertice>();
        List<Vertice> fila = new ArrayList<Vertice>();
        List<Vertice> anteriores = new ArrayList<Vertice>();
        List<Vertice> descobertos = new ArrayList<Vertice>();

        fila.add(origem);
        visitados.add(origem);
        descobertos.add(origem);
        anteriores.add(null);

        int indiceFila = 0;
        while (indiceFila < fila.size()) {
            Vertice atual = fila.get(indiceFila);
            indiceFila++;

            if (atual == destino) {
                break;
            }

            for (int i = 0; i < Arestas.size(); i++) {
                Aresta aresta = Arestas.get(i);
                if (aresta.origem == atual && !visitados.contains(aresta.destino)) {
                    visitados.add(aresta.destino);
                    fila.add(aresta.destino);
                    descobertos.add(aresta.destino);
                    anteriores.add(atual);
                }
            }
        }

        if (!visitados.contains(destino)) {
            System.out.println("Nao existe caminho");
            return "Nao existe caminho";
        }

        List<Vertice> caminho = new ArrayList<Vertice>();
        Vertice atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            int indiceAtual = descobertos.indexOf(atual);
            atual = anteriores.get(indiceAtual);
        }

        String texto = "";
        for (int i = 0; i < caminho.size(); i++) {
            texto += caminho.get(i).id;
            if (i < caminho.size() - 1) {
                Aresta aresta = this.pegarAresta(caminho.get(i), caminho.get(i + 1));
                texto += " -" + aresta.peso + "-> ";
            }
        }
        System.out.println(texto);
        return texto;
    }

    public String mostrarCaminhoMenorPeso(long idOrigem, long idDestino) {
        Vertice origem = this.pegarVertice(idOrigem);
        Vertice destino = this.pegarVertice(idDestino);
        if (origem == null || destino == null) {
            System.out.println("Nao existe caminho");
            return "Nao existe caminho";
        }

        List<Vertice> verticesAbertos = new ArrayList<Vertice>();
        List<Vertice> verticesConhecidos = new ArrayList<Vertice>();
        List<Integer> distancias = new ArrayList<Integer>();
        List<Vertice> anteriores = new ArrayList<Vertice>();

        for (int i = 0; i < Vertices.size(); i++) {
            Vertice vertice = Vertices.get(i);
            verticesAbertos.add(vertice);
            verticesConhecidos.add(vertice);
            if (vertice == origem) {
                distancias.add(0);
            } else {
                distancias.add(Integer.MAX_VALUE);
            }
            anteriores.add(null);
        }

        while (!verticesAbertos.isEmpty()) {
            Vertice atual = null;
            int menorDistancia = Integer.MAX_VALUE;

            for (int i = 0; i < verticesAbertos.size(); i++) {
                Vertice candidato = verticesAbertos.get(i);
                int indiceCandidato = verticesConhecidos.indexOf(candidato);
                int distanciaCandidata = distancias.get(indiceCandidato);
                if (distanciaCandidata < menorDistancia) {
                    menorDistancia = distanciaCandidata;
                    atual = candidato;
                }
            }

            if (atual == null || menorDistancia == Integer.MAX_VALUE) {
                break;
            }

            verticesAbertos.remove(atual);
            if (atual == destino) {
                break;
            }

            for (int i = 0; i < Arestas.size(); i++) {
                Aresta aresta = Arestas.get(i);
                if (aresta.origem == atual) {
                    int indiceVizinho = verticesConhecidos.indexOf(aresta.destino);
                    int novaDistancia = menorDistancia + aresta.peso;
                    if (novaDistancia < distancias.get(indiceVizinho)) {
                        distancias.set(indiceVizinho, novaDistancia);
                        anteriores.set(indiceVizinho, atual);
                    }
                }
            }
        }

        int indiceDestino = verticesConhecidos.indexOf(destino);
        if (indiceDestino < 0 || distancias.get(indiceDestino) == Integer.MAX_VALUE) {
            System.out.println("Nao existe caminho");
            return "Nao existe caminho";
        }

        List<Vertice> caminho = new ArrayList<Vertice>();
        Vertice atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            int indiceAtual = verticesConhecidos.indexOf(atual);
            atual = anteriores.get(indiceAtual);
        }

        String texto = "";
        for (int i = 0; i < caminho.size(); i++) {
            texto += caminho.get(i).id;
            if (i < caminho.size() - 1) {
                Aresta aresta = this.pegarAresta(caminho.get(i), caminho.get(i + 1));
                texto += " -" + aresta.peso + "-> ";
            }
        }
        System.out.println(texto);
        return texto;
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
