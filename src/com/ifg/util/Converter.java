package com.ifg.util;

public class Converter {

    public Converter(){

    }

    public char[] binaryStringToVarChar(String string){
        int saida = 0, salva = 0, size = 0;

        if(string.length()%16 != 0){
             size = string.length()/16 +50;
        }else {
             size = string.length()/16+50;
        }
        char[] vector =  new char[size], varchar = string.toCharArray();

        for (int i = 0 ; i < varchar.length   ; i++ ){
            if(i%15 == 0 && i != 0 && salva< size  ){
                vector[salva] = (char) saida;
                salva++;
                saida = 0;
            }
            if(varchar[i] == '1'){
                saida = (saida << 1)| 1;
            }else if (varchar[i] == '0'){
                saida = (saida << 1);
            }
        }

        for(int i = 0 ; i < vector.length ; i++){
            if (vector[i]=='\u0000'){
                char[] suporte = new char[i];
                for (int j = 0 ; j < i;j++) {
                    suporte[j] = vector[j];
                }
                return suporte;
            }
        }
        return vector;
    }

    public String varcharToBinaryString(String string){
        String result ="";

        string.toCharArray();

        return result;
    }


}
