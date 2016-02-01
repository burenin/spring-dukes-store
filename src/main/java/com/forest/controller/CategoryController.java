package com.forest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forest.service.ICategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private ICategoryService						categoryService;
	
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("items", categoryService.getItems());
        return "product/listCategory";
    }
	
	@RequestMapping(value = "admin/category/list", method = RequestMethod.GET)
    public String adminCategoryList(Model model) {
        model.addAttribute("items", categoryService.getItems());
        return "category/list";
    }
	
	@RequestMapping(value = "admin/category/create", method = RequestMethod.GET)
    public String adminCategoryCreate(Model model) {
        model.addAttribute("items", categoryService.getItems());
        return "product/listCategory";
    }
	
}
