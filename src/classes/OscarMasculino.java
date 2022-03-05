package classes;

public class OscarMasculino extends Oscar {
	
	public OscarMasculino(int indice, int ano, int idade, String nome, String filme) {
		super(indice, ano, idade, nome, filme);
		
	}


	@Override
	public String toString() {
		return "OscarMasculino [Indice: " + indice + ", Ano: " + ano + ", Nome do Premiado: " + nomeVencedor + ", Idade: " + idadeVencedor  + ", Filme: "
				+ filme + "]";
	}
	
	
	

}
