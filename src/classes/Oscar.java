package classes;

public  class Oscar {

	private int indice;
	private int ano;
	private int idadeVencedor;
	private String nomeVencedor, filme;
	
	
	public Oscar(int indice, int ano, int idade, String nomeVencedor, String filme) {
		super();
		this.indice = indice;
		this.ano = ano;
		this.idadeVencedor = idade;
		this.nomeVencedor = nomeVencedor;
		this.filme = filme;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getIdade() {
		return idadeVencedor;
	}
	public void setIdade(int idade) {
		this.idadeVencedor = idade;
	}
	public String getNomeVencedor() {
		return nomeVencedor;
	}
	public void setNome(String nome) {
		this.nomeVencedor = nome;
	}
	public String getFilme() {
		return filme;
	}
	public void setFilme(String filme) {
		this.filme = filme;
	}
	@Override
	public String toString() {
		return "Oscar [Indice: " + indice + ", Ano: " + ano + ", Nome Vencedor: "
				+ nomeVencedor + ", Idade Vencedor: " + idadeVencedor +  ", Filme: " + filme + "]";
	}
	
	
}
