package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.persistence.ProductPersistenceImpl;
import com.codegym.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    Environment env;

    @Autowired
    private ProductPersistenceImpl productPersistence;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products",productPersistence.findAll());
        return "index";
    }
    @GetMapping("/product/create")
    public String create(Model model){
        model.addAttribute("product",new Product());
        return "create";
    }
    @PostMapping("product/save")
    public String save(@ModelAttribute(value = "product") Product product, BindingResult result, RedirectAttributes redirect){
        product.setId((int)(Math.random() * 10000));

        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        // lay ten file
        MultipartFile multipartFile = product.getImages();
        String fileName = multipartFile.getOriginalFilename();


        //luu file len server
        try {
            FileCopyUtils.copy(product.getImages().getBytes(), new File(env.getProperty("file_upload") + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product productObject = new Product(product.getId(),product.getName(),product.getPrice(),
                product.getDescription(),fileName);
        productPersistence.save(productObject);
        redirect.addFlashAttribute("success","Saved product successfully!");
        return "redirect:/";
    }
    @GetMapping("product/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("product",productPersistence.findById(id));
        return "edit";
    }
    @PostMapping("/product/update")
    public String update(@ModelAttribute(value = "product") Product product, BindingResult result, RedirectAttributes redirect){

        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }

        // lay ten file
        MultipartFile multipartFile = product.getImages();
        String fileName = multipartFile.getOriginalFilename();


        // luu file len server
        try {
            FileCopyUtils.copy(product.getImages().getBytes(), new File(env.getProperty("file_upload") + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        product.setAvatar(fileName);
        productPersistence.update(product.getId(),product);
        redirect.addFlashAttribute("success", "Modified product successfully!");
        return "redirect:/";
    }


    @GetMapping("product/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("product",productPersistence.findById(id));
        return "delete";
    }
    @PostMapping("/product/remove")
    public String remove(@ModelAttribute Product product, RedirectAttributes redirect){
        productPersistence.remove(product.getId());
        redirect.addFlashAttribute("success", "Deleted product successfully!");
        return "redirect:/";
    }
    @GetMapping("/product/{id}/view")
    public String view(@PathVariable int id,Model model){
        model.addAttribute("product",productPersistence.findById(id));
        return "view";
    }
    @GetMapping("/product/search")
    public String search(@RequestParam String name, Model model){
        model.addAttribute("listResult",productPersistence.findByName(name));
        return "result";
    }


}
