package com.devsuperior.uri2621;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projection.ProductMinProjection;
import com.devsuperior.uri2621.repositories.ProductRepository;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2621Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<ProductMinProjection>list = repository.search1(10, 20, "p");
		List<ProductMinDTO> result = list.stream().map(x-> new ProductMinDTO(x)).collect(Collectors.toList());

		System.out.println("\n RESULTADO SQL RAÍZ");
		for (ProductMinDTO obj : result) {
			System.out.println(obj.getName());			
		}


		System.out.println("\n RESULTADO JPQL");
		List<ProductMinDTO>list2 = repository.search2(10, 20, "P");

		for (ProductMinDTO obj : list2) {
			System.out.println(obj.getName());			
		}
	}
}
