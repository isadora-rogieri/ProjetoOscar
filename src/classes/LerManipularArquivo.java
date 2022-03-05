package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LerManipularArquivo {
	public static List<Oscar> criandoNovoOscar(String filename) {
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			List<String> linhas = ((stream.skip(1)).collect(Collectors.toList()));
			return  converterEmOscar(linhas);
			//oscarM.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	private static  List<Oscar> converterEmOscar(List<String> linhas) {
		List<Oscar> resultado = new ArrayList<>();
		for (String linha : linhas) {
			String[] campos = linha.split(";");
			Oscar novoOscar = new Oscar(Integer.parseInt(campos[0]),Integer.parseInt(campos[1]), 
					Integer.parseInt(campos[2]),
					campos[3], campos[4]);
			resultado.add(novoOscar);
			
		}
		return resultado;
	}

}
