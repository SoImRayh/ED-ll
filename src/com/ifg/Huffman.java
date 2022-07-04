package com.ifg;

import com.google.common.base.Splitter;
import com.ifg.util.Converter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class Huffman_Node {
    Character charac;
    Integer frequency;
    Huffman_Node left = null, right = null;

    Huffman_Node(Character charac, Integer frequency)
    {
        this.charac = charac;
        this.frequency = frequency;
    }

    public Huffman_Node(Character charac, Integer frequency, Huffman_Node left, Huffman_Node right)
    {
        this.charac = charac;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public Huffman_Node() {

    }
}

class Huffman {
    Huffman() throws IOException {
    }

    //função para tornar a serie string e gravar no arquivo arquivo.zin

    public static void persistirArquivo(String arvore,String string) throws IOException{

        Converter converter = new Converter();
        char[] varchar = converter.binaryStringToVarChar(string);

        File compactado = new File("arquivo.zin");

        FileWriter fileWriter = new FileWriter(compactado);
        string = new String(varchar);


        fileWriter.write(arvore +string);
        fileWriter.close();
    }



    // Huffman Tree Traversing and storing the Huffman Codes in a dictionary.
    public static void encode_huffman(Huffman_Node root_node, String str,
                                      Map<Character, String> huffman_Code)
    {
        if (root_node == null) {
            return;
        }

        // if the root node is a leaf node
        if (is_Leaf(root_node)) {
            huffman_Code.put(root_node.charac, str.length() > 0 ? str : "1");
        }

        encode_huffman(root_node.left, str + '0', huffman_Code);
        encode_huffman(root_node.right, str + '1', huffman_Code);
    }

    // Huffman Tree Traversing and decoding the encoded string
    public static int decode_huffman(Huffman_Node root_node, int index, StringBuilder sb)
    {
        if (root_node == null) {
            return index;
        }

        // if the root node is a leaf node
        if (is_Leaf(root_node))
        {
            System.out.print(root_node.charac);
            return index;
        }

        index++;

        root_node = (sb.charAt(index) == '0') ? root_node.left : root_node.right;
        index = decode_huffman(root_node, index, sb);
        return index;
    }

    // This function checks if Huffman Tree contains only one single node
    public static boolean is_Leaf(Huffman_Node root_node) {
        return root_node.left == null && root_node.right == null;
    }

    // Main Huffman tree build function
    public static void Main_Build_HuffmanTree(String text) throws IOException {
        // Base case: empty string
        if (text == null || text.length() == 0) {
            return;
        }

        //variaveis que vou ultilizar para manipular o arquivo de saida.



        // Calculand a frequencia que os caracteres se repetem e colocando em um map

        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        // priority queue to store nodes of the Huffman tree
        // the highest priority item has the lowest frequency

        PriorityQueue<Huffman_Node> prio_queue;
        prio_queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.frequency));

        // leaf node for each character, adding it to the priority queue.

        for (var entry : frequency.entrySet()) {
            prio_queue.add(new Huffman_Node(entry.getKey(), entry.getValue()));
        }

        //repeat the process till there is more than one node in the queue
        while (prio_queue.size() != 1) {
            // Then remove the two nodes with the highest priority and lowest frequency

            Huffman_Node left = prio_queue.poll();
            Huffman_Node right = prio_queue.poll();

            // Now create a new internal node with two children nodes, and the frequency will be the some of both nodes; add the new node to the priority queue.
            int sum = left.frequency + right.frequency;
            prio_queue.add(new Huffman_Node(null, sum, left, right));
        }

        Huffman_Node root_node = prio_queue.peek();

        // Huffman tree Traversing and storing the Huffman codes in a dict or map
        Map<Character, String> huffmanCode = new HashMap<>();
        encode_huffman(root_node, "", huffmanCode);

        //mostrando no console as informações

        //System.out.println("\nO HashMap do codigo de huffman e: " + huffmanCode);
        //System.out.println("\nO texto que foi enviado e: " + text);


        // display the encoded string
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }

        //System.out.println("\n\nTexto compactado: " + sb + "\nA quantidade de bits e:"+sb.length());
        //System.out.print("o texto decompactado: ");

        if (is_Leaf(root_node)) {
            // For input like a, aa, aaa, etc.
            while (root_node.frequency-- > 0) {

                //System.out.print(root_node.charac);
            }
        } else {
            // Huffman Tree traversing with decoding the encoded string
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode_huffman(root_node, index, sb);
            }
        }

        //convertendo em bits e guardando em um arquivo.
        persistirArquivo(huffmanCode.toString(),sb.toString());

    }
    public static void inserir( char caracter , String string){
        for(int i =0 ; i < string.toCharArray().length; i++){
            if(string.charAt(i+2) == 0 || string.charAt(i+2) == 1 && string.charAt(i+3) != ' '){
                Huffman_Node node = new Huffman_Node(caracter, 1);

            }
        }
    }

    public static void arvore(char[] mapa){
        String[] vetor = mapa.toString().split(";");
        for(int i =0 ; i < vetor.length ; i++){
            char caractere = vetor[i].charAt(1);
            String inserir = vetor[i];
            inserir(caractere , inserir);
        }
    }
    public static void descompactar(String string) {
        char[] mapa = string.toCharArray(), arvoremap = new char[100];
        for (int i = 0; mapa[i] == '}'; i++) {
            if (mapa[i] != '{')
                arvoremap[i] = mapa[i];
        }
        arvore(arvoremap);
    }

    // Call the Huffman code
    public static void main(String[] args){
        //====================================Compactando...
        File arquivo = new File("entrada.txt");
        try{
            Scanner scanner = new Scanner(arquivo);
            String text= "";

            while(scanner.hasNext()){
                text = text+scanner.nextLine();
            }
            Main_Build_HuffmanTree(text);

        }catch (IOException exception){
            System.out.println("nao deu para criar por algum erro");
        }

        //=====================================Descompactando...
        File descompactar = new File("arquivo.zin");

        try{
         Scanner scanner = new Scanner(descompactar);
         String string ="";

         while(scanner.hasNext()){
             string = string + scanner.nextLine();
         }
        //descompactar(string);
        }catch (IOException exception){
            System.out.println("nao deu para achar o arquivo");
        }



    }
}