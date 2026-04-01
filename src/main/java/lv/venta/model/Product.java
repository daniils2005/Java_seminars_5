package lv.venta.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {
	//1.mainigie
	@Setter(value = AccessLevel.NONE)
	private long id;
	private String title;
	private float price;
	private int quantity;
	private String description;
	private ProductType productType;
	private static long counter = 0;
	//2.getters - nak no lombok bibliotekas
	//3.setters - nak no lombok bibliotekas
	//4.abi konstruktori - bez argumenta konstruktors nak no lombok bibliotekas
	public Product(String inputTitle, float inputPrice, int inputQuantity, String inputDescription, ProductType inputProductType) {
		setTitle(inputTitle);
		setDescription(inputDescription);
		setPrice(inputPrice);
		setQuantity(inputQuantity);
		setProductType(inputProductType);
		id = counter++;
	}
	//5. toString - nak no lombok bibliotekas
	//6. parejas funkcijas
}
