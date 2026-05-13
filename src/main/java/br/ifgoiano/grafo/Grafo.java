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

    public Aresta adicionarOuAtualizarAresta(Vertice origem, Vertice destino, String texto) {
        Aresta aresta = this.pegarAresta(origem, destino);
        if (aresta == null){
            aresta = new Aresta(origem, destino, texto);
            Arestas.add(aresta);
            return aresta;
        } else {
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

        // Verifica se existe os ids
        if (origem == null || destino == null) {
            System.out.println("Nao existe caminho");
            return "Nao existe caminho";
        }

        // Estrategia
        // Usamos BFS (busca em largura), ele algoritmo percorre o grafo
        // primeiro pelos vizinhos diretos da origem, depois pelos
        // vizinhos desses vizinhos e assim por diante.

        List<Vertice> visitados = new ArrayList<Vertice>();
        List<Vertice> fila = new ArrayList<Vertice>();
        List<Vertice> anteriores = new ArrayList<Vertice>();
        List<Vertice> descobertos = new ArrayList<Vertice>();

        // A fila representa os vertices que ainda precisamos processar.
        // "visitados" evita revisitar vertices e entrar em ciclos infinitos.
        // descobertos guarda cada vertice encontrado na busca
        // anteriores guarda de qual vertice ele veio

        // Adicionamos a origem, pois é o primeiro o start
        fila.add(origem);
        visitados.add(origem);
        descobertos.add(origem);
        anteriores.add(null);

        int indiceFila = 0;
        while (indiceFila < fila.size()) {
            Vertice atual = fila.get(indiceFila);
            indiceFila++;

            // Se chegamos no destino podemos parar a busca.
            if (atual == destino) {
                break;
            }

            for (int i = 0; i < Arestas.size(); i++) {
                Aresta aresta = Arestas.get(i);

                // Nesse grafo direcionado vamos seguimos apenas as arestas que saem do
                // vertice atual. Se o destino dessa aresta ainda nao foi
                // visitado ele entra na fila para ser explorado depois.
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

        // Para a reconstrucao do caminho
        // começamos no destino e vamos voltando pelos anteriores
        // armazenados em "anteriores" ate chegar na origem.
        // Como esse processo acontece de tras para frente, inserimos cada
        // vertice na posicao 0 da lista para montar o caminho na ordem correta.
        while (atual != null) {
            caminho.add(0, atual);
            int indiceAtual = descobertos.indexOf(atual);
            atual = anteriores.get(indiceAtual);
        }

        //Montagem do tetxo de saída depois de ter ajustado a ordem
        String texto = "";
        for (int i = 0; i < caminho.size(); i++) {
            texto += caminho.get(i).id;
            if (i < caminho.size() - 1) {
                texto += " -> ";
            }
        }
        System.out.println(texto);
        return texto;
    }


    // \n represnta quebra de linha
    // \ representa tab
    public String mostrarDOT() {
        String texto = "digraph Grafo {\n";

        for (int i = 0; i < Vertices.size(); i++) {
            Vertice vertice = Vertices.get(i);
            texto += "    " + vertice.id + " [label=\"" + vertice.texto + "\"];\n";
        }

        for (int i = 0; i < Arestas.size(); i++) {
            Aresta aresta = Arestas.get(i);
            texto += "    " + aresta.origem.id + " -> " + aresta.destino.id
                + " [label=\"" + aresta.texto + "\"];\n";
        }

        texto += "}";
        System.out.println(texto);
        return texto;
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
