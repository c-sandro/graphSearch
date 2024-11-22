package matrix;

import java.beans.Visibility;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class WeightedAdjacencyMatrix{

    private ArrayList<ArrayList<Integer>> matrix;
    private ArrayList<Boolean> visited;
    private ArrayList<Integer> pathWeight;
    private ArrayList<Integer> queue;
    private int nodeAmount;

    public WeightedAdjacencyMatrix(int nodeAmount){
        this.nodeAmount = nodeAmount;
        this.matrix = new ArrayList<>();

        for(int i = 0; i < nodeAmount; i++){
            this.matrix.add(new ArrayList<>());
            for(int j = 0; j < nodeAmount; j++){
                this.matrix.get(i).add(0);
            }
        }
    }

    public void addEdge(int node1, int node2, int weight){
        node1--;
        node2--;
        if(node1 > this.nodeAmount || node2 > this.nodeAmount){
            System.out.println("ERRO: nó recebido não existe \n");
            return;
        }

        if(weight > this.matrix.get(node1).get(node2) && this.matrix.get(node1).get(node2) != 0){
            return;
        }

        if(node1 == node2){
            this.matrix.get(node1).set(node2, this.matrix.get(node1).get(node2));
            return;
        }

        this.matrix.get(node1).set(node2, weight);
        this.matrix.get(node2).set(node1, weight);
    }

    public String loopDetection(){
        String output = "";

        boolean isTrue = false;
        for(int i = 0; i < this.nodeAmount; i++){
            if(this.matrix.get(i).get(i) > 0){
                output += "Loop detectado no vértice " + (i + 1) + "\n \n";
                isTrue = true;
            }
        }

        if(!isTrue){
            return "Nenhum loop detectado \n \n";
        }

        return output;
    }

    public String parallelEdgeDetection(){
        String output = "Arestas paralelas detectadas: [";
        
        boolean isTrue = false;
        for(int i = 0; i < this.nodeAmount; i++){
            if((Collections.frequency(this.matrix.get(i), 0) + Collections.frequency(this.matrix.get(i), 1)) == this.nodeAmount){
                continue;
            }

            int node1 = i + 1, node2 = 0;
            for(int j = 0; j < this.nodeAmount; j++){
                if(this.matrix.get(i).get(j) > 1){
                    node2 = j + 1;
                    break;
                }
            }
            output += "(" + node1 + ", " + node2 + "), ";
            isTrue = true;
        }

        if(!isTrue){
            return "Nenhuma aresta paralela detectada \n \n";
        }

        output = output.substring(0, output.length() - 2) + "] \n \n";
        return output;
    }

    public String nodeDegree(int nodeIndex){
        int degreeValue = 0;
        for(int node : this.matrix.get(nodeIndex)){
            degreeValue += node;
        }

        return "O grau do vértice " + (nodeIndex + 1) + " é " + degreeValue + "\n";
    }

    public String allNodesDegree(){
        String output = "";

        for(int i = 0; i < this.nodeAmount; i++){
            output += this.nodeDegree(i);
        }
        output += "\n";
        return output;
    }

    public String DIJKSTRA(int start, int end){
        if(start > nodeAmount){
            return "ERRO: nó inválido.";
        }

        String output = "O menor caminho de " + start + " até " + end + " é ";
        start--;
        end--;
        int tempS = start;
        queue = new ArrayList<>();
        queue.add(start);
        pathWeight = new ArrayList<>();
        visited = new ArrayList<>();
        for(int i = 0; i < nodeAmount; i++){
            visited.add(false);
            pathWeight.add(2147483647);
        }
        visited.set(start, true);
        pathWeight.set(start, 0);
        visited.set(end, true);

        int cont = 0;
        do{
            for(int i = 0; i < nodeAmount; i++){
                if(matrix.get(tempS).get(i) >= 1 && !visited.get(i) && i != end){
                    visited.set(i, true);
                    if(pathWeight.get(i) > pathWeight.get(tempS) + matrix.get(tempS).get(i) && start != -1){
                        pathWeight.set(i, pathWeight.get(tempS) + matrix.get(tempS).get(i));
                    }
                    queue.add(i);
                }
            }
            cont++;
            start = -1;
            if(!queue.isEmpty()){
                cont = 0;
                start = queue.getFirst();
                tempS = start;
                queue.removeFirst();
            }
        }while((!queue.isEmpty() || visited.contains(false)) && cont < 2);

        if(cont >= 2){
            return "Não há caminho direto entre os 2 nós";
        }

        for(int i = 0; i < nodeAmount; i++){
            if(matrix.get(end).get(i) >= 1){
                if(pathWeight.get(end) > pathWeight.get(i) + matrix.get(end).get(i)){
                    pathWeight.set(end, pathWeight.get(i) + matrix.get(end).get(i));
                }
            }
        }

        return output + pathWeight.get(end);
    }

    @Override
    public String toString(){
        String output = "Matriz de Adjacência com Peso: \n \n    ";

        //vértices
        for(int i = 0; i < this.nodeAmount; i++){
            output += ((i < 9) ? "  " + (i + 1)  : " " + (i + 1)) + " |";
        }
        output += "\n";

        for(int i = 0; i < this.nodeAmount; i++){
            output += ((i < 9) ? " " + (i + 1) : (i + 1)) + " |";
            for(int j = 0; j < nodeAmount; j++){
                output += ((i < 9) ? "  " + this.matrix.get(i).get(j) : " " + this.matrix.get(i).get(j)) + " |";
            }
            output += "\n";
        }
        output += "\n";

        return output + this.loopDetection() + this.allNodesDegree();
    }

}