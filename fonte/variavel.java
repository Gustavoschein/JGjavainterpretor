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

	Class Variavel {
		private String Nome; //Atributo
		private double valor; //Atributo
	
		//  Métodos Construtores
	
		public Variavel (){
			this.Nome = new String();
			this.Valor = 0.0;
		}
		

		//  Métodos gets e sets.
	
		public void setNome(String s){
			this.Nome = s;
		}
		
		public String getNome(){
			return this.Nome;
		}

		public void setValor(double d){
			this.Valor = d;
		}

		public double getValor(){
			return this.Valor;
		}
	}