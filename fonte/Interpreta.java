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

	import java.util.*; 

class Interpretador {
	// ATRIBUTOS:    
	private String linhas[];
	private Variavel[] variaveis;
	public int indexVar = -1; //indíce p/ saber topo da Vetor de Variaveis
	public String [] pilha;	
	
	// CONSTRUTORES:		
	
	public Interpretador(){	
			
		variaveis = new Variavel[200];
		for (int www = 0;www<200 ;www++ ) {
			this.variaveis[www] =  new Variavel();
		}
	}
	
	// MÉTODOS:
	public void interpreta(String l[]){// funcao interpreta
		String [] linhas ;
		linhas = l;
		int i, j = 0; 
		
		for (i = 0 ;i <linhas.length;i++ ) {
			while(linhas[i].contains("  ") == true) {// verifica se ha dois espaços continuos			    
		       linhas[i] = linhas[i].replaceAll("  "," ");// troca dois espaços por um
		    }  
		    linhas[i] = linhas[i].trim(); // remove os espaços inico e fim		    
			pilha  = linhas[i].split(" ");// quebra em  substrings onde ha espaço e coloca numa pilha						
							
			i = verificaFuncao(i, pilha);
		}	
	}	
	public int existeVariavel(String nomeVar){ // retorna o indice 
		for (int i = 0; i < variaveis.length;i++) {
			if (nomeVar.equals(variaveis[i].getName())){			
				return i; // return indice variavel se existe a varival  passada por parametro
			}
		}
		//System.out.println("ERRO 12 :Comando Invalido, a Variavel não foi declarada \n\n");
		return -12; // 	
	}
	public double valorVariavel(String nomeVar){//retorna val variavel caso exista se nao converte em double
		for (int i = 0; i < 200;i++) {
			if (nomeVar.equals(variaveis[i].getName())){				
				return variaveis[i].getValor(); 
			}
			
		}
		return Double.parseDouble(nomeVar);// caso variavel nao exista converte double		
	}
}	