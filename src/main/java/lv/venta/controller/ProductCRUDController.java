package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {

	@Autowired
	private IProductCRUDService prodService;
	
	@GetMapping("/all") //localhost:8080/product/crud/all
	public String getAllProducts(Model model) {
		try {
			model.addAttribute("package", prodService.retrieveAll());
			return "show-all-products-page"; //tiks paradita show-all-products-page.html lapa
		} catch(Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
