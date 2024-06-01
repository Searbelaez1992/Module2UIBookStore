package com.example.BookStore.controller;

import com.example.BookStore.models.BookStore;
import com.example.BookStore.models.Product;
import com.example.BookStore.service.BookStoreService;
import com.example.BookStore.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class BookStoreWebController {

    private final BookStoreService bookStoreService;

    private final ProductService productService;

    public BookStoreWebController(BookStoreService bookStoreService, ProductService productService) {
        this.bookStoreService = bookStoreService;
        this.productService = productService;
    }

    @RequestMapping("/bookStore")
    public String getBookStores(Model model, @Param("keyWord1") String keyWord1, @Param("keyWord2") String keyWord2, @Param("keyWord3") String keyWord3, @Param("keyWord4") String keyWord4, @Param("keyWord5") String keyWord5, @Param("keyWord6") String keyWord6) {

        model.addAttribute("listBookStore",bookStoreService.getBookStores(keyWord1,keyWord2,keyWord3, keyWord4, keyWord5,keyWord6));
        model.addAttribute("keyWord1",keyWord1);
        model.addAttribute("keyWord2",keyWord2);
        model.addAttribute("keyWord3",keyWord3);
        model.addAttribute("keyWord4",keyWord4);
        model.addAttribute("keyWord5",keyWord5);
        model.addAttribute("keyWord6",keyWord6);

        return "bookStore";
    }
    @RequestMapping("/newBookStore")
    public String showFormNewBookStore(Model model){
        List<Product> listOfProducts = productService.getProducts(null,null,null,null);
        BookStore bookStore = new BookStore();
        model.addAttribute("BookStore", bookStore);
        model.addAttribute("listOfProducts", listOfProducts);
        return "new_bookStore";
    }

    @RequestMapping(value = "/saveBookStore", method = RequestMethod.POST)
    public String saveBookStore(@ModelAttribute("BookStore") BookStore bookStore){
        bookStoreService.save(bookStore);
        return "redirect:/bookStore";
    }

    @RequestMapping("/editBookStore/{id}")
    public ModelAndView showFormUpdateBookStore(@PathVariable(name="id") Long id){
        ModelAndView model = new ModelAndView("edit_bookStore");
        BookStore bookStore = bookStoreService.findBookStoreById(id);
        model.addObject("BookStore", bookStore);
        return model;
    }

    @RequestMapping(value = "/updateBookStore", method = RequestMethod.POST)
    public String UpdateBookStore(@ModelAttribute("BookStore") BookStore bookStore){
        bookStoreService.updateBookStore(bookStore.getId(),bookStore);

        return "redirect:/bookStore";
    }

    @RequestMapping("/deleteBookStore/{id}")
    public String deletBookStore(@PathVariable(name="id") Long id){
        bookStoreService.deleteBookStore(id);
        return "redirect:/bookStore";
    }
}
