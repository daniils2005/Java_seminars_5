package lv.venta;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.model.MyAuthority;
import lv.venta.model.MyUser;
import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.repo.IMyAuthorityRepo;
import lv.venta.repo.IMyUserRepo;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class JavaSeminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaSeminar5Application.class, args);
	}
	
	@Bean
	public CommandLineRunner testRepo(IProductRepo prodRepo, IMyAuthorityRepo authRepo,
			IMyUserRepo userRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Product prod1 = new Product("Abols", 0.99f, 5, "Garsigs", ProductType.fruit);
				Product prod2 = new Product("Burkans", 0.49f, 2, "Oranzs", ProductType.vegetable);
				Product prod3 = new Product("Apelsins", 1.99f, 3, "Suligs", ProductType.fruit);
				
				prodRepo.save(prod1);
				prodRepo.save(prod2);
				prodRepo.save(prod3);
				
				
				MyAuthority auth1 = new MyAuthority("ADMIN");
				MyAuthority auth2 = new MyAuthority("USER");
				authRepo.saveAll(Arrays.asList(auth1, auth2));
				
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				
				MyUser u1 = new MyUser("karina", encoder.encode("123"), auth2);
				MyUser u2 = new MyUser("janis", encoder.encode("321"), auth2);
				MyUser u3 = new MyUser("admin", encoder.encode("987"), auth1);
				
				userRepo.saveAll(Arrays.asList(u1,u2,u3));
				
			}
		};
		
	}
	
	

}
