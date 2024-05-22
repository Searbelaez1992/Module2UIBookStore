package com.example.BookStore.controller;

import com.example.BookStore.models.Product;

import com.example.BookStore.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/productsM")
    public String getProductsM(Model model, @Param("keyWord1") String keyWord1, @Param("keyWord2") String keyWord2, @Param("keyWord3") String keyWord3, @Param("keyWord4") String keyWord4){

        model.addAttribute("productList",productService.getProducts(keyWord1,keyWord2,keyWord3, keyWord4));
        model.addAttribute("keyWord1",keyWord1);
        model.addAttribute("keyWord2",keyWord2);
        model.addAttribute("keyWord3",keyWord3);
        model.addAttribute("keyWord4",keyWord4);

        return "productsM";
    }

    @RequestMapping("/productsC")
    public String getProductsC(Model model){

        model.addAttribute("productList",productService.getProducts(null,null,null,null));

        return "productsC";
    }

    @RequestMapping("/newProduct")
    public String showFormNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("Products", product);
        return "new_product";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("Product") Product product){
        productService.addProduct(product);
        return "redirect:/productsM";
    }

    @RequestMapping("/editProduct/{id}")
    public ModelAndView showFormUpdateProduct(@PathVariable(name="id") Long id){
        ModelAndView model = new ModelAndView("edit_product");
        Product product = productService.findProductById(id);
        model.addObject("Products", product);
        return model;
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String UpdateProduct(@ModelAttribute("Products") Product product){

        productService.updateProduct(product.getId(),product);
        return "redirect:/productsM";
    }

    @RequestMapping("/deleteProduct/{id}")
    public String deletProduct(@PathVariable(name="id") Long id){
        productService.deleteProduct(id);
        return "redirect:/productsM";
    }
}
