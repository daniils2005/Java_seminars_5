package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;
import lv.venta.model.ProductType;

public interface IProductRepo extends CrudRepository<Product, Long> {

	boolean existsByTitleAndPriceAndDescriptionAndProductType(String title, float price, String description,
			ProductType type);
	//TODO papildinat pec nepieciesamibas citas funkcijas

	Product findByTitleAndPriceAndDescriptionAndProductType(String title, float price, String description,
			ProductType type);
}
