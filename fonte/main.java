/*
~#################################################################################~
	Autores do código: 		Gustavo André Schein, João Marcos Campagnolo.
	E-mail do autor: 		gustavoschein@hotmail.com, jota.campagnolo@gmail.com.
	Propósito do programa:	O programa simula um interpretador JAVA de uma
							linguagem de programação inventada pelos autores,
							a qual recebe o código a ser interpretado através 
							de um arquivo de texto, que será redirecionado como
							entrada do programa.
	Link do código fonte:	https://github.com/Gustavoschein/JGjavainterpretor
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
		String linhas[] = new String[2000]; // Máximo de linhas: 2000.
		f = new File(args[0]);
		s = new Scanner(f);
		b = new Interpretador();
		int i = 0;
		while(s.hasNext()) {
			linhas[i] = s.nextLine();
			i++;
		}
		i = 0;
		while(i < 2000){ // deixa todas as posições com nada caso a linha seja NULL.
			if (linhas[i] == null) {
				linhas[i] = "@";
			}
			i++;
		}		
		b.interpreta(linhas);  
	}
}