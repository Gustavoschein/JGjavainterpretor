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

import java.util.*;

class Interpretador{
	// ATRIBUTOS:    
	private String linhas[];
	private Variavel[] variaveis;
	public int indexVar = -1; // INDICE REFERENTE AO TOPO DO VETOR DE VARIAVEIS
	public String pilha[];
	
	// CONSTRUTORES:	
	public Interpretador(){	
		variaveis = new Variavel[200];
		for (int www = 0;www<200 ;www++ ) {
			this.variaveis[www] =  new Variavel();
		}
	}
	
	// METODOS:
	public void interpreta(String l[]){ // FUNCAO DO INTERPRETADOR:
		String [] linhas ;
		linhas = l;
		int i, j = 0; 
		
		for (i = 0 ;i <linhas.length;i++ ) {
			while(linhas[i].contains("  ") == true) { // VERIFICA SE EXISTEM DOIS ESPACOS CONTINUOS			    
		       linhas[i] = linhas[i].replaceAll("  "," "); // TOCA DOIS ESPA�OS POR UM
		    }  
		    linhas[i] = linhas[i].trim(); // REMOVE ESPA�OS DO INICIO E DO FIM		    
			pilha  = linhas[i].split(" "); // QUEBRA EM SUBSTRINGS ONDE HA UM ESPACO E JOGA PARA A PILHA						
							
			i = verificaFuncao(i, pilha);
		}	
	}	
	public int existeVariavel(String nomeVar){ // RETORNO DO INDICE
		for (int i = 0; i < variaveis.length;i++) {
			if (nomeVar.equals(variaveis[i].getNome())){			
				return i; // RETORNA O INDICE DA VARIAVEL CADO ELA EXISTA
			}
		}
		//System.out.println("ERRO 12 :Comando Invalido, a Variavel n�o foi declarada \n\n");
		return -12; // 	
	}
	public double valorVariavel(String nomeVar){ // RETORNA VALOR DA VARIAVEL CASO EXXISTA, SE NAO CONVERTE PARA DOUBLE
		for (int i = 0; i < 200;i++) {
			if (nomeVar.equals(variaveis[i].getNome())){				
				return variaveis[i].getValor(); 
			}
			
		}
		return Double.parseDouble(nomeVar); // CASO A VARIAVEL NAO EXISTA, CONVERTE PARA DOUBLE		
	}
	
	// ======================================================================================//
	
	public boolean desempilha (){ // FUN��O QUE REALIZA TODAS AS OPERA��ES:
		double total = 0;
		double var1,var2;
		for (int op = 0; op < pilha.length; op++){
			//(  || ) 
			if (pilha[op].equals("+")) { // SOMA
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 + var2;
				pilha[op+1] = String.valueOf(total); // RECONVERTE PARA STRING	
			
			}else if (pilha[op].equals("-")) { // SUBTRA��O
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 - var2;
				pilha[op+1] = String.valueOf(total); // RECONVERTE PARA STRING	
			
			}else if (pilha[op].equals("*")) { // MULTIPLICA��O
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 * var2;
				pilha[op+1] = String.valueOf(total); // RECONVERTE PARA STRING	
			
			}else if((pilha[op].equals("%"))){ // PEGA O RESTO
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 % var2;
				pilha[op+1] = String.valueOf(total); // RECONVERTE PARA STRING	

			}else if (pilha[op].equals("/")){ // DIVIS�O
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				total = var1 / var2;
				pilha[op+1] = String.valueOf(total); // RECONVERTE PARA STRING	
			
			} else if (pilha[op].equals("<")) { // MENOR
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);				
				if(var1 < var2) {
					return true;
				}else{
					return false;
				}
			
			} else if (pilha[op].equals(">")) { // MAIOR
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);				
				if(var1 > var2) {
					return true;
				}else{
					return false;
				}
			
			} else if (pilha[op].equals("!=")) { // DIFERENTE
				
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				if(var1 == var2){
					return false;
				}else{
					return true;
				}
			}else if (pilha[op].equals("==")) { // IGUAL 				
				var1 = converteDouble(pilha[op-1]);
				var2 = converteDouble(pilha[op+1]);
				if(var1 == var2){
					return true;
				}
				else{
					return false;
				}
			}else if (pilha[op].equals("++")) { // INCREMENTO 				
				var1 = converteDouble(pilha[op-1]);
				total = var1 + 1;
				pilha[op] = "="; // PARA QUE A VERIFICA��O DE ATRIBUI��O LOCALIZE ATRIBUI��O	
			}else if (pilha[op].equals("--")) { // DECREMENTO			
				var1 = converteDouble(pilha[op-1]);
				total = var1 - 1;
				pilha[op] = "="; // PARA QUE A VERIFICA��O DE ATRIBUI��O LOCALIZE ATRIBUI��O	
			}
		}
		for (int j = 0; j < pilha.length ; j ++) { // VERIFICA SE HA (=) ATRIBUICAO
			if (pilha[j].equals("=")) {	
				int indiVAr = existeVariavel(pilha[j-1]); // PEGA O INDICE DA VARIAVEL NO VETOR DE VARIAVEIS	
				variaveis[indiVAr].setValor(total);
			}
			/* RESUMO : J VAI PARAR QUANDO TIVE ENCONTRADO =, A VARIAVEL QUE ESTA ANTES DO =  E 
						 QUEM VAI RECEBE A ATRIBUICAO, E O VALOR VAI ESTAR EM TOTAL, POR QUE I PERCOREU
						 TODA A PILHA E ESTA FORA, DAI -1. E SE NAO HOUVER IGUAL NAO ATRIBUI
			*/
		}	
		return true;
	}
	public void DEC(String nomeVar){
		indexVar++; // INDICE ESTATICO PARA ATRIBUI��O NA PROXIMA POSI��O
		variaveis[indexVar].setNome(nomeVar);
		//variaveis[indexVar].setValor(33);		
		//imprimeVariaveis();	
	}
	
	public void imprimeVariaveis(){ // IMPRIME TODO O VETOR DE VARIAVEIS
	 	for (int i = 0 ;i <10; i++ ) {
	 		System.out.print("Var = "+ variaveis[i].getNome());
	 		System.out.println("Valor"+variaveis[i].getValor());
	 	}
	}
	
	public int IFF(int i, String []linhas){
		while(!(pilha[0].equals("#ENDIFF"))){ // ENQUANTO N�O ACHAR O FIM DO IFF
			i++;
			if (pilha[0].equals("#ENDIFF")){
				return i+1;
			}
			else{
				while(linhas[i].contains("  ") == true){ // VERIFICA SE HA DOIS ESPA�OS CONTINUOS			    
					linhas[i] = linhas[i].replaceAll("  "," "); // TROCA DOIS ESPA�OS POR UM
				}  
				if(linhas[i].charAt(0) != '@'){ // QUANDO A LINHA N�O TIVER NADA
					linhas[i] = linhas[i].trim(); // REMOVE OS ESPA�OS DO INICIO E DO FIM
				}
				pilha  = linhas[i].split(" "); // QUEBRA EM SUBSTRINGS ONDE HA ESPACO E COLOCA NUMA PILHA						
			}
		}
		return i + 1;
	}
	
	public double converteDouble(String nomeVar){
		int ind;
		ind = existeVariavel(nomeVar);
		if (ind >= 0) {
			return variaveis[ind].getValor();
		}else {
			return Double.parseDouble(nomeVar);	
		}		
	}
	
	public void OUT(String []pilhaOut){
		String nomeVar = new String();
		for (int i = 1; i < pilhaOut.length ;i++ ) { // POSI��O [0] � #OUT
			if (pilhaOut[i].charAt(0) == '$'){
				//System.out.println(variaveis[0].getNome());	
				nomeVar = pilhaOut[i].substring(1,pilhaOut[i].length());
				System.out.print(variaveis[existeVariavel(nomeVar)].getValor()+" ");
			}else{
				System.out.print(pilhaOut[i]+" ");
			}
		}
		System.out.print("\n");

	}
	
	public void REP(){
		// WHILE COM RECURS�O, PASSANDO A LINHA ONDE ESTAVA
	}
	
	public int verificaFuncao(int i, String[] pilha){
		boolean verifica;
		String nomeVar = new String(); // ARMAZENA O NOME NAS LINHAS
		boolean deuCerto = true;
		
		// ============================== VERIFICA AS FUN��ES ==================================
		
		if (pilha[0].equals("#DEC")) { // DECLARA UMA VARIAVEL 
			nomeVar = pilha[1]; // PEGA O VALOR NA VARIAVEL
			DEC(nomeVar);
			deuCerto = desempilha(); // DEU CERTO, N�O FAZ NADA, PARA RECEBER RETORNO TRUE
		

		}else if (pilha[0].equals("#OUT")) {
			OUT(pilha); // PASSA A LINHA QUE ESTA SENDO EXECUTADA
			deuCerto = desempilha(); // DEU CERTO, N�O FAZ NADA, PARA RECEBER RETORNO TRUE	
		

		}else if (pilha[0].equals("#IFF")){	// CHAMA FUNCAO IFF PARA ACHAR O END IFF			
			
			verifica = desempilha(); // VERIFICA IFF
			if (verifica == false){
				i = IFF(i,linhas); // PROCURA O FIM
				i =i-1;
			}
			
		}else if(pilha[0].equals("#REP")){
			/*
			verifica = desempilha(); //verifica IFF
			System.out.println(verifica);
			if (verifica = false){
				i = IFF(i);
				System.out.println("\n\n DEU FALSO \n\n");
			}
			*/
		}else{
			desempilha();
		}
		return i ;
	}	
}