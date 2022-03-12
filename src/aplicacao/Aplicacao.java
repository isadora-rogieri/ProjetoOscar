package aplicacao;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import classes.LerManipularArquivo;
import classes.Oscar;

public class Aplicacao {

	private static List<Oscar> oscarM;
	private static List<Oscar> oscarF;

	public static void main(String[] args) {
		Aplicacao app = new Aplicacao();

		oscarM = LerManipularArquivo.criandoNovoOscar("oscar_male.csv");
		oscarF = LerManipularArquivo.criandoNovoOscar("oscar_female.csv");
		app.encontreAtorMaisJovem();
		app.encontreAtrizMaisPremiada();
		app.encontreAtrizMaisPremiadacomFiltro();
		app.encontreVencedor();
		app.pesquisaAtoreAtriz();
	}

	private void encontreAtorMaisJovem() {
		System.out.println("#1 - Ator mais Jovem: ");
		this.oscarM.stream()
		.min(Comparator.comparingInt(Oscar::getIdade))
		.ifPresent(c -> System.out.println(c.getNomeVencedor() + " -  Foi premiado aos " + c.getIdade() + " anos por " + c.getFilme()));

	}
	private void encontreAtrizMaisPremiada() {
		System.out.println("#2 - Atriz Mais premiada: ");
		Map<String, Long> nomes = this.oscarF.stream()
				.map(Oscar::getNomeVencedor)		  
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		nomes.entrySet().stream()
		.max(Comparator.comparingLong(Entry::getValue))
		.ifPresent(c -> System.out.println(c.getKey() + " - Foi premiada " + c.getValue() + " vezes."));
	}
	//Atriz mais premiada entre 20 e 30 anos
	private void encontreAtrizMaisPremiadacomFiltro() {
		System.out.println("#3 - Atriz entre 20 a 30 anos mais premiada: ");
		Map<String, Long>  nomes = this.oscarF.stream()				
				.filter(item -> item.getIdade()>=20 && item.getIdade() <=30)
				.map(Oscar::getNomeVencedor)
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		nomes.entrySet().stream()
		.max(Comparator.comparingLong(Entry::getValue))
		.ifPresent(c -> System.out.println(c.getKey() + " - Foi premiada " + c.getValue() + " vezes."));
	}
	//Vencedores com mais de um Oscar
	private void encontreVencedor() {		
		System.out.println("#4 - Ator e Atriz que ganhou mais de um Oscar: ");
		List<Oscar> listas = Stream.concat(this.oscarM.stream(),this.oscarF.stream()).collect(Collectors.toList());
		Map<String, Long> nomes = listas.stream()
				.map(Oscar::getNomeVencedor)		  
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		nomes.entrySet().stream()
		.filter(e -> e.getValue()>1)
		.forEach(System.out::println);
	}

	private void pesquisaAtoreAtriz() {		
		System.out.println("#5 - Entre com nome de um Ator ou Atriz  ");
		List<Oscar> listas = Stream.concat(this.oscarM.stream(),this.oscarF.stream()).collect(Collectors.toList());
		Scanner sc = new Scanner(System.in);
		String nome = sc.nextLine();
		List<Oscar> busca = listas.stream()
				.filter((c) -> c.getNomeVencedor().toLowerCase().contains(nome.toLowerCase()))
				.collect(Collectors.toList());

		//Validando se o nome está na Lista
		if(busca.isEmpty()) {
			System.out.println("Essa pessoa não está lista!");			
		}else {
			busca.stream()
			.collect(Collectors.groupingBy(Oscar::getNomeVencedor))
			.forEach((premiado, oscar) -> {
				System.out.printf("\nNome: %s (Ganhou: %d)\n", premiado, oscar.size());
				oscar.forEach((c) -> System.out.println(" Ano: " + c.getAno() 
				+ " Idade: "+  c.getIdade()+ 
				" Filme:" + c.getFilme()));
			});		

		}

	}


}







