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

class Variavel{
	// ATRIBUTOS:
	private String Nome;
	private double Valor;

	// CONSTRUTORES:
	public Variavel(){
		this.Nome = new String();
		this.Valor = 0.0;
	}

	// METODOS:
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