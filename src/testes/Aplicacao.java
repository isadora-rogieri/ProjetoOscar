package testes;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import classes.Oscar;
import classes.OscarFeminino;
import classes.OscarMasculino;

public class Aplicacao {

	private static List<OscarMasculino> oscarM;
	private static List<OscarFeminino> oscarF;

	public static void main(String[] args) {
		Aplicacao app = new Aplicacao();

		app.criandoNovoOscarMale();
		app.criandoNovoOscarFemale();
		app.findAtorMaisJovem();
		app.findAtrizMaisPremiada();
		app.findAtrizMaisPremiadacomFiltro();
		app.agrupandoListas();
		app.pesquisaAtoreAtriz();
	}

	private void findAtorMaisJovem() {
		System.out.println("#1 - Ator mais Jovem: ");
		this.oscarM.stream()
		.min(Comparator.comparingInt(OscarMasculino::getIdade))
		.ifPresent(c -> System.out.println(c.getNome() + " -  Foi premiado aos " + c.getIdade() + " anos por " + c.getFilme()));

	}

	private void findAtrizMaisPremiada() {
		System.out.println("#2 - Atriz Mais premiada: ");
		Map<String, Long> nomes = this.oscarF.stream()
				.map(OscarFeminino::getNome)		  
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		nomes.entrySet().stream()
		.max(Comparator.comparingLong(Entry::getValue))
		.ifPresent(c -> System.out.println(c.getKey() + " - Foi premiada " + c.getValue() + " vezes."));
	}
	
	private void findAtrizMaisPremiadacomFiltro() {
		System.out.println("#3 - Atriz entre 20 a 30 anos mais premiada: ");
		Map<String, Long>  nomes = this.oscarF.stream()				
				.filter(item -> item.getIdade()>=20 && item.getIdade() <=30)
				.map(OscarFeminino::getNome)
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		nomes.entrySet().stream()
		.max(Comparator.comparingLong(Entry::getValue))
		.ifPresent(c -> System.out.println(c.getKey() + " - Foi premiada " + c.getValue() + " vezes."));
	}

	private void agrupandoListas() {		
		System.out.println("#4 - Ator e Atriz que ganhou mais de um Oscar: ");
		List<Oscar> listas = Stream.concat(this.oscarM.stream(),this.oscarF.stream()).collect(Collectors.toList());
		Map<String, Long> nomes = listas.stream()
				.map(Oscar::getNome)		  
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
		listas.stream()
		.filter((c) -> c.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList())
		.forEach(System.out::println);
	}

	private void criandoNovoOscarMale() {
		try (Stream<String> stream = Files.lines(Paths.get("oscar_male.csv"))) {
			List<String> linhas = ((stream.skip(1)).collect(Collectors.toList()));
			List<OscarMasculino> oscarM = converterEmOscar(linhas);
			//oscarM.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static  List<OscarMasculino> converterEmOscar(List<String> linhas) {
		List<OscarMasculino> resultado = new ArrayList<>();//diamond operator
		for (String linha : linhas) {
			String[] campos = linha.split(";");
			OscarMasculino novoOscar = new OscarMasculino(Integer.parseInt(campos[0]),Integer.parseInt(campos[1]), 
					Integer.parseInt(campos[2]),
					campos[3], campos[4]);
			resultado.add(novoOscar);
			oscarM = resultado;

		}
		return resultado;
	}

	private void criandoNovoOscarFemale() {
		try (Stream<String> stream = Files.lines(Paths.get("oscar_female.csv"))) {
			List<String> linhas = ((stream.skip(1)).collect(Collectors.toList()));
			List<OscarFeminino> oscarF = converterEmOscarF(linhas);
			//oscarF.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<OscarFeminino> converterEmOscarF(List<String> linhas) {
		List<OscarFeminino> resultado = new ArrayList<>();//diamond operator
		for (String linha : linhas) {
			String[] campos = linha.split(";");
			OscarFeminino novoOscar = new OscarFeminino(Integer.parseInt(campos[0]),Integer.parseInt(campos[1]), 
					Integer.parseInt(campos[2]),
					campos[3], campos[4]);
			resultado.add(novoOscar);
			oscarF = resultado;
		}
		return resultado;
	}
}







