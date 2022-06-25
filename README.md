<h1>Algoritmo de Ruffman para a compactaçao de dados.</h1>

<h2>objetivo do trabalho:</h2>

1. Criar um componente de codificação Huffman, que poderá utilizar somente memória RAM e não gravará os dados compactados em Disco. Este componente deverá mostrar a tabela de probabilidade, o número de bits originais e o número de bits após a codificação. 4 pts

2. Melhorar o item 1 para que funcione com um arquivo de texto como entrada. 1 pt

3. Melhorar o item 2 para que após a codificação do arquivo, o mesmo possa ser gravado no disco com a extensão .zin. Mostre que o arquivo realmente está menor que o original. 3 pts.

DICA 1: aqui você terá que trabalhar com um componente feito por você para manipular os bytes/bits de forma que consigam representar a codificação de Huffman e não mais a codificação tradicional.

DICA 2: utilize alguma informação extra, preâmbulo, no arquivo .zin para indicar qual foi o dicionário utilizado. E explique porque este dicionário inviabiliza o uso de Huffman para arquivos pequenos (menores que 1Kb).

4. Melhorar o item 3, para consiga abrir o arquivo do item 3, ler o preâmbulo e descompactar o arquivo. 2 pts.

Objetivo secundário: Medir a taxa de compactação entre os dois arquivos via programa.

Ex: file.txt → 1024Kb → sem compactação

file.zin → 512Kb → compactado

taxa de compactação de 50%.

<h2> Minhas observaçoes:</h2>

<p>Implementarei o trabalho usando a linguagem java pelo fato de ela ter muitos metodos para trabalhar com dados do tipo
Strig</p>
