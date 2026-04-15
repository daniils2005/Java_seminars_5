package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.model.ProductType;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;

@Service
public class ProductCRUDServiceImpl implements IProductCRUDService {

	@Autowired
	private IProductRepo prodRepo;
	
	@Override
	public void create(String title, float price, int quantity, String description, ProductType type) throws Exception {
		if(title == null || !title.matches("[A-Z]{1}[a-z ]{2,30}") || price < 0 || price > 1000 || quantity < 0 || quantity > 100 || description == null || !description.matches("[A-Za-z1-9]{0,400}") || type == null) {
			throw new Exception("Kads no ievades argumentiem nav atbilstoss");
		}
		//parbaudam, vai tads produkts jau eksiste, ja ta, tad papildinam krajumus
		if(prodRepo.existsByTitleAndPriceAndDescriptionAndProductType(title, price, description, type)) {
			Product productFromDB = prodRepo.findByTitleAndPriceAndDescriptionAndProductType(title, price, description, type);
			
			int newQuantity = productFromDB.getQuantity() + quantity;
			productFromDB.setQuantity(newQuantity);
			prodRepo.save(productFromDB); //izpildas UPDATE vaicajums
		
		} else {
			Product newProduct = new Product(title, price, quantity, description, type);
			prodRepo.save(newProduct); //izpildas INSERT INTO vaicajums
		}
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(prodRepo.count() == 0) {
			throw new Exception("produktu tabula DB ir tuksa");
		}
		
		ArrayList<Product> allProductsFromDB = (ArrayList<Product>)prodRepo.findAll();
		return allProductsFromDB;
	}

	@Override
	public Product retrieveById(long id) throws Exception {
		if(id <= 0) {
			throw new Exception("id nevar but negativs vai 0");
		}
		if(!prodRepo.existsById(id)) {
			throw new Exception("neeksiste produkts ar id " + id);
		}
	
		return prodRepo.findById(id).get();
	}

	@Override
	public void updateById(long id, String title, float price, int quantity, String description, ProductType type) throws Exception {
		Product productForUpdating = prodRepo.findByTitleAndPriceAndDescriptionAndProductType(title, price, description, type);
		if(title == null || !title.matches("[A-Z]{1}[a-z ]{2,30}") || price < 0 || price > 1000 || quantity < 0 || quantity > 100 || description == null || !description.matches("[A-Za-z1-9]{0,400}") || type == null) {
			throw new Exception("Kads no ievades argumentiem nav atbilstoss");
		}
		productForUpdating.setDescription(description);
		productForUpdating.setPrice(price);
		productForUpdating.setProductType(type);
		productForUpdating.setQuantity(quantity);
		productForUpdating.setTitle(title);
		prodRepo.save(productForUpdating);
	}

	@Override
	public void deleteById(long id) throws Exception {
		if(id <= 0) {
			throw new Exception("id nevar but negativs vai 0");
		}
		if(!prodRepo.existsById(id)) {
			throw new Exception("neeksiste produkts ar id " + id);
		}
		prodRepo.deleteById(id);
	}

}
