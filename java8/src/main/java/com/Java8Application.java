package com;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Java8Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Java8Application.class, args);
		Products products = readData(); 
		List<Product> listOfProducts = products.getProducts();
		listOfProducts.forEach(p -> {
			List<String> mobiles = new ArrayList<>();
			if(p.getPrice().doubleValue() == 280) {
				mobiles.add("7995952191");
				p.setMobile(mobiles);
			}else {
				mobiles.add("8335837230");
				p.setMobile(mobiles);
			}
		});
		List<Product> listProducts = listOfProducts.stream()
				.filter(p -> p.getMobile().stream().noneMatch(s -> s.startsWith("833"))).collect(Collectors.toList());
		listProducts.forEach(p -> System.out.println(p));
	}

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(Java8Application.class, args); Products products =
	 * readData(); List<Product> listOfProducts = products.getProducts();
	 * 
	 * }
	 */
	
	public static Products readData() {
		RestTemplate restTemplate = new RestTemplate();
		Products products = restTemplate.getForObject("https://dummyjson.com/products", Products.class);
		return products;
	}
	
	/*
	 * //want to print brand and price who's product price is greater than 60
		listOfProducts.stream().filter(p -> p.getPrice() > 60)
		.collect(Collectors.toList()).forEach(p -> System.out.println(p.getBrand() +" "+p.getPrice()));
	 */

/* map methos of stream
	//getting all the title from the list of products using stream object map and lambda
			listOfProducts.stream().map(p -> p.getTitle()).collect(Collectors.toList()).forEach(s -> System.out.println(s));
			//getting all the title from the list of products using stream object map and method reference
			listOfProducts.stream().map(p -> p.getTitle()).collect(Collectors.toList()).forEach(System.out::println);
			listOfProducts.stream().map(Product::getTitle).collect(Collectors.toList()).forEach(s -> System.out.println(s));
			
			//want to get image links from product list and display 
		listOfProducts.stream().map(Product::getImages).collect(Collectors.toList()).forEach(System.out::println);
		
		Optional<List<String>> findFirst = listOfProducts.stream().map(p -> p.getImages()).findFirst();
		System.out.println(findFirst.get());
	*/
	
	/*Flat map
	 * listOfProducts.stream().flatMap(p -> p.getImages().stream()).collect(Collectors.toList()).forEach(System.out::println);
	 */
	
	//want to get product image whos price is 13 first object
	//List<String> list = listOfProducts.stream().filter(p -> p.getPrice() == 13).map(p -> p.getImages()).findFirst().get();
	//System.out.println(list);
	
	/*Integer[] i = {1,2,3,4,5,6,7,8,9,10};
	 * int r = 0;
		for(Integer i1 : i) {
			r = r + i1;
		}
		System.out.println(r);
		//int val = Stream.of(i).reduce(0, (a,b) -> a+b).intValue();
		//System.out.println(val);
		 * 
		 * System.out.println(listOfProducts.stream().map(p -> p.getPrice().intValue()).reduce((p1,p2) -> p1+p2).get());
	 */
	
	//want to get product image whos price is 13
			//listOfProducts.stream().filter(p -> p.getPrice() == 13).map(p -> p.getImages()).collect(Collectors.toList()).forEach(System.out::println);
}
