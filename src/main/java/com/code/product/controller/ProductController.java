package com.code.product.controller;

import com.code.product.model.Category;
import com.code.product.model.Product;
import com.code.product.service.CategoryService;
import com.code.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {

    private static String stringSave = "";

    @Autowired
    private ProductService productService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("products")
    public ModelAndView showListForm(@PageableDefault(5) Pageable pageable, @RequestParam("s") Optional<String> s){
        Page<Product> products;
        stringSave = s.toString().substring(9,s.toString().length()-1);
        if (stringSave.equals("empt")) stringSave = "";
        if (!stringSave.equals("")){
            products = productService.findAllByProduct_nameEquals(stringSave, pageable);
        }else{
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        modelAndView.addObject("stringSave", stringSave);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", product);
        modelAndView.addObject("mess", "Created!");
        return modelAndView;

    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView =  new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit-product/{id}")
    public ModelAndView editProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        productService.save(product);
        modelAndView.addObject("product", product);
        modelAndView.addObject("mess", "Edited!");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("product/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product){
        productService.remove(product.getId());
        return "redirect:products";
    }
}
