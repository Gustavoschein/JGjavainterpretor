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
	public int indexVar = -1; // Índice usado para saber o Topo do vetor das variáveis:
	public String [] pilha;	
	
	// CONSTRUTORES:	
	public Interpretador(){	
		variaveis = new Variavel[200];
		for (int www = 0;www<200 ;www++ ) {
			this.variaveis[www] =  new Variavel();
		}
	}
	
	// MÉTODOS:
	public void interpreta(String l[]){ // Função que Interpreta:
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
	
	// ======================================================================================//
	public boolean desempilha (){// faz todas as operações	
		double total = 0;
		double var1,var2;
		for (int op = 0; op < pilha.length; op++){
			//(  || ) 
			if (pilha[op].equals("+")) { // SOMA
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 + var2;
				pilha[op+1] = String.valueOf(total);// converte p/string denovo	
			
			}else if (pilha[op].equals("-")) { // SUBTRAÇÃO
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 - var2;
				pilha[op+1] = String.valueOf(total);// converte p/string denovo	
			
			}else if (pilha[op].equals("*")) { //MULTIPLICAÇÃO
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 * var2;
				pilha[op+1] = String.valueOf(total);// converte p/string denovo	
			
			}else if((pilha[op].equals("%"))){ //PEGA O RESTO
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 % var2;
				pilha[op+1] = String.valueOf(total);// converte p/string denovo	

			}else if (pilha[op].equals("/")){ //DIVISÃO
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 / var2;
				pilha[op+1] = String.valueOf(total);// converte p/string denovo	
			
			} else if (pilha[op].equals("<")) { //MENOR
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);				
				if(var1 < var2) {
					return true;
				}else{
					return false;
				}
			
			} else if (pilha[op].equals(">")) { //MAIOR
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);				
				if(var1 > var2) {
					return true;
				}else{
					return false;
				}
			
			} else if (pilha[op].equals("!=")) {// DIFERENTE
				
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				if(var1 == var2){
					return false;
				}else{
					return true;
				}
			}else if (pilha[op].equals("==")) {// IGUAL 				
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				if(var1 == var2){
					return true;
				}else{
					return false;
				}
			}else if (pilha[op].equals("++")) {// IGUAL 				
				var1 = converteDouble(pilha[op-1]);
				total = var1 + 1;
				pilha[op] = "=";// para que a verificação de atribuição localize atribuição	
			}else if (pilha[op].equals("--")) {// IGUAL 				
				var1 = converteDouble(pilha[op-1]);
				total = var1 - 1;
				pilha[op] = "=";// para que a verificação de atribuição localize atribuição	
			}
		}
		for (int j = 0; j < pilha.length ; j ++) {// verif. se ha (=) atribuição
			if (pilha[j].equals("=")) {	
				int indiVAr = existeVariavel(pilha[j-1]);// pega indice da variavel no vetor de variaveis	
				variaveis[indiVAr].setValor(total);
			}
			/* RESUMO : J VAI PARAR QUANDO TIVE ENCONTRADO =, A VARIAVEL QUE ESTA ANTES DO =  E 
						 QUEM VAI RECEBE A ATRIBUICAO, E O VALOR VAI ESTAR EM TOTAL, POR QUE I PERCOREU
						 TODA A PILHA E ESTA FORA, DAI -1. E SE NAO HOUVER IGUAL NAO ATRIBUI
			*/
		}	
		return true;
	}
}	