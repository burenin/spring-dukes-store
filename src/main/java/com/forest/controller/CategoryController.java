package com.forest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forest.entity.Category;
import com.forest.service.ICategoryService;
import com.forest.util.AbstractPaginationHelper;

@Controller
public class CategoryController {
	
	private AbstractPaginationHelper<Category> 		pagination;
	
	@Autowired
	private ICategoryService						categoryService;
	
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "product/listCategory";
    }
	
	public AbstractPaginationHelper<Category> getPagination() {
		if (pagination == null) {
            pagination = new AbstractPaginationHelper<Category>(AbstractPaginationHelper.DEFAULT_SIZE) {

                @Override
                public int getItemsCount() {
                    return categoryService.count();
                }

                @Override
                public List<Category> createPageDataModel() {
                    return categoryService.findRange(new int[]{getPageFirstItem(), 
                        getPageFirstItem() + getPageSize()});
                }
            };
        }
        return pagination;
	}
	
	public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

}
