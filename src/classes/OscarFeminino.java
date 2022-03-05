package classes;

public class OscarFeminino extends Oscar {

	public OscarFeminino(int indice, int ano, int idade, String nome, String filme) {
		super(indice, ano, idade, nome, filme);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "OscarFeminino [Indice: " + indice + ", Ano: " + ano +", Nome do Premiado: " + nomeVencedor  +", Idade: " + idadeVencedor + ",+  Filme: "
				+ filme + "]";
	}
	
	
}
