/*
~#################################################################################~
	Autores do c�digo: 		Gustavo Andr� Schein, Jo�o Marcos Campagnolo.
	E-mail do autor: 		gustavoschein@hotmail.com, jota.campagnolo@gmail.com.
	Prop�sito do programa:	O programa simula um interpretador JAVA de uma
							linguagem de programa��o inventada pelos autores,
							a qual recebe o c�digo a ser interpretado atrav�s 
							de um arquivo de texto, que ser� redirecionado como
							entrada do programa.
	Link do c�digo fonte:	https://github.com/Gustavoschein/JGjavainterpretor
~#################################################################################~
*/

import java.util.Scanner;
import java.io.File;
import java.awt.*;

class Main {
	public static void main(String []args) throws Exception{
		File f;
		Scanner s;
		Interpretador b;
		String linhas[] = new String[2000]; // MAXIMO DE LINHAS: 2000.
		f = new File(args[0]);
		s = new Scanner(f);
		b = new Interpretador();
		int i = 0;
		while(s.hasNext()){
			linhas[i] = s.nextLine();
			i++;
		}
		i = 0;
		while(i < 2000){ // DEIXA TODAS AS POCICOES COM ANDA CASO A LINHA SEJA NULL.
			if (linhas[i] == null) {
				linhas[i] = "@";
			}
			i++;
		}		
		b.interpreta(linhas);  
	}
}