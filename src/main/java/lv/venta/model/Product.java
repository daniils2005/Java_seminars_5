package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "ProductTable")
@Entity
public class Product {
	//1.mainigie
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Id")
	@Id // ka primara atslega
	@GeneratedValue(strategy = GenerationType.AUTO) //tiks glabata DB automatiski pec auto increment algoritma
	private long id;
	
	@Column(name = "Title")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z]{1}[a-z ]{2,30}")
	//@Size(min  3, max = 31)
	private String title;
	
	@Column(name = "Price")
	@Min(0)
	@Max(1000)
	private float price;
	
	@Column(name = "Quantity")
	@Min(0)
	@Max(100)
	private int quantity;
	
	
	@Column(name = "Description")
	@NotNull(message = "Aprakstam jabut realam") //var padot message kuru izprinte, ja validacija neizdodas
	@Pattern(regexp = "[A-Za-z1-9]{0,400}", message = "Var saturet tikai burtus un ciparus") //vairs neradisies automatiski generetais teksts, ja notiek validacijas kluda
	private String description;
	
	@Column(name = "ProductType")
	@NotNull
	private ProductType productType;

	//2.getters - nak no lombok bibliotekas
	//3.setters - nak no lombok bibliotekas
	//4.abi konstruktori - bez argumenta konstruktors nak no lombok bibliotekas
	public Product(String inputTitle, float inputPrice, int inputQuantity, String inputDescription, ProductType inputProductType) {
		setTitle(inputTitle);
		setDescription(inputDescription);
		setPrice(inputPrice);
		setQuantity(inputQuantity);
		setProductType(inputProductType);
	}
	//5. toString - nak no lombok bibliotekas
	//6. parejas funkcijas
}
