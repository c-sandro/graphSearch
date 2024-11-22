import java.sql.SQLOutput;
import java.util.Scanner;

import matrix.AdjacencyMatrix;
import matrix.WeightedAdjacencyMatrix;

public class Main{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String input = "";

        AdjacencyMatrix matrix = null;
        WeightedAdjacencyMatrix weightMatrix = null;


        while(!input.equals("P")){
            menu();
            input = scan.nextLine();
            switch(input){
                case "Q":
                {
                    int newQnt;
                    while(true){
                        System.out.println("Digite a quantidade de vértices: ");
                        try{
                            newQnt = Integer.parseInt(scan.nextLine());
                            if(newQnt < 1){
                                System.out.println("\n ERRO: Quantidade inválida. \n");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    matrix = new AdjacencyMatrix(newQnt);
                }
                break;

                case "W":
                {
                    if(matrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    int newN1, newN2;
                    while(true){
                        System.out.println("Digite os vértices 1 e 2, separados por um enter, respectivamente: ");
                        try{
                            newN1 = Integer.parseInt(scan.nextLine());
                            newN2 = Integer.parseInt(scan.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    matrix.addEdge(newN1, newN2);
                }
                break;

                case "E":
                    if(matrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    System.out.println(matrix.toString());
                break;

                case "R":
                {
                    if (matrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    int tempStart;
                    while(true){
                        System.out.println("Digite o vértice inicial: ");
                        try{
                            tempStart = Integer.parseInt(scan.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    System.out.println(matrix.BFS(tempStart));
                }
                break;

                case "T":
                {
                    if (matrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    int tempStart;
                    while(true){
                        System.out.println("Digite o vértice inicial: ");
                        try{
                            tempStart = Integer.parseInt(scan.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    System.out.println(matrix.DFS(tempStart));
                }
                break;

                case "Y":
                {
                    int newQnt;
                    while(true){
                        System.out.println("Digite a quantidade de vértices: ");
                        try{
                            newQnt = Integer.parseInt(scan.nextLine());
                            if(newQnt < 1){
                                System.out.println("\n ERRO: Quantidade inválida. \n");
                                continue;
                            }
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    weightMatrix = new WeightedAdjacencyMatrix(newQnt);
                }
                break;

                case "U":
                {
                    if(weightMatrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    int newN1, newN2, newWeight;
                    while(true){
                        System.out.println("Digite os vértices 1 e 2 e o peso, separados por um enter, respectivamente: ");
                        try{
                            newN1 = Integer.parseInt(scan.nextLine());
                            newN2 = Integer.parseInt(scan.nextLine());
                            newWeight = Integer.parseInt(scan.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    weightMatrix.addEdge(newN1, newN2, newWeight);
                }
                break;

                case "I":
                    if(weightMatrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    System.out.println(weightMatrix.toString());
                break;

                case "O":
                {
                    if (weightMatrix == null){
                        System.out.println("\n ERRO: Matriz não foi criada ainda. \n");
                        break;
                    }
                    int tempStart, tempEnd;
                    while(true){
                        System.out.println("Digite os vértices inicial e final, separados por um enter, respectivamente: ");
                        try{
                            tempStart = Integer.parseInt(scan.nextLine());
                            tempEnd = Integer.parseInt(scan.nextLine());
                            break;
                        }catch(Exception e){
                            System.out.println("\n ERRO: Entrada inválida. \n");
                        }
                    }
                    System.out.println(weightMatrix.DIJKSTRA(tempStart, tempEnd));
                }
                break;

                case "P":
                    System.out.println("Finalizando...");
                break;
            
                default:
                    System.out.println("\n ERRO: Comando inválido. \n");
                break;
            }
        }

        scan.close();
    }

    public static void menu(){
        System.out.println(" -- MENU -- ");
        System.out.println("Q: criar matriz de adjacencia sem peso");
        System.out.println("W: adicionar aresta na matriz de adjacencia sem peso");
        System.out.println("E: checar propriedades da matriz de adjacencia sem peso");
        System.out.println("R: fazer busca BFS");
        System.out.println("T: fazer busca DFS");
        System.out.println("Y: criar matriz de adjacencia com peso");
        System.out.println("U: adicionar aresta na matriz de adjacencia com peso");
        System.out.println("I: checar propriedades da matriz de adjacencia com peso");
        System.out.println("O: fazer busca DIJKSTRA");
        System.out.println("P: sair");
        System.out.println("Digite seu comando: ");
    }

}
