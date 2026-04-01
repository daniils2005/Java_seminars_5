package lv.venta.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simple")
public class SimpleController {

	@GetMapping("/page") //localhost:8080/simple/page
	public String getShowPage() {
		System.out.println("Mans pirmais kontrolieris ir izsaukts");
		return "show-page";//tiks parādīta show-page.html lapa ieks web parlūka
	}
	
	@GetMapping("/data") //localhost:8080/simple/data
	public String getDataInPage(Model model) {
		System.out.println("Izpildas datu kontrolieris");
		Random rand = new Random();
		String data = "@Daniils " + rand.nextInt(2010, 2026);
		model.addAttribute("package", data);
		return "show-data-page"; //tiks paradita show-data-page.html lapa
	}
	
}
