package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class JavaSeminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaSeminar5Application.class, args);
	}
	
	@Bean
	public CommandLineRunner testRepo(IProductRepo prodRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Product prod1 = new Product("Abols", 0.99f, 5, "Garsigs", ProductType.fruit);
				Product prod2 = new Product("Banans", 0.66f, 12, "Dzeltens", ProductType.fruit);
				Product prod3 = new Product("Skapis", 55.99f, 3, "Liels", ProductType.furniture);
				
				prodRepo.save(prod1);
				prodRepo.save(prod2);
				prodRepo.save(prod3);
				
			}
		};
	}
}
