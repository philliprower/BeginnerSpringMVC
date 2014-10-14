package com.packt.webstore.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@RequestMapping("/all")
	public ModelAndView allProducts() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}
	
	@RequestMapping("/{category}")
	public String getProductsByCategory(@PathVariable("category") String productCategory, Model model) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}
	
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String,List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
	
	@RequestMapping("/{category}/{price}")
	public String getProductsByPrice(@PathVariable("category") String productCategory, @MatrixVariable(pathVar="price") Map<String,List<String>> filterParams, Model model, @RequestParam String manufacturer) {
		List<Product> products = new ArrayList<>();
		
		List<Product> pm = productService.getProductsByManufacturer(manufacturer);
		List<Product> pc = productService.getProductsByCategory(productCategory);
		Set<Product> intersection = new HashSet<>(pm);
		List<Product> intermediate = new ArrayList<>();
		for (Product product : pc) {
			if (intersection.contains(product)) {
				intermediate.add(product);
			}
		}
		BigDecimal low = new BigDecimal(filterParams.get("low").get(0));
		BigDecimal high = new BigDecimal(filterParams.get("high").get(0));
		for (Product product : intermediate) {
			if (low.compareTo(product.getUnitPrice()) <= 0 && high.compareTo(product.getUnitPrice()) >= 0) {
				products.add(product);
			}
		}		
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct) {
	   return "addProduct";
	}
	   
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product productToBeAdded, ModelMap map, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
	   	productService.addProduct(productToBeAdded);
		return "redirect:/products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock", "condition");
	}

}
