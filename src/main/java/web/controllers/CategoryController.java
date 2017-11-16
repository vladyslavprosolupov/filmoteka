package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.dao.CategoryDb;
import web.exceptions.ParsingJsonToDaoException;
import web.model.CategoryJSON;
import web.model.FilmJSONIndex;
import web.services.CategoryService;
import web.services.FilmCategoryService;

import java.util.List;

/**
 * Created by Rostyk on 16.06.2017.
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    FilmCategoryService filmCategoryService;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody String addOrUpdateCategory(@RequestBody CategoryJSON categoryJSON){
        try {
            categoryService.saveOrUpdate(categoryService.convertToCategoryDb(categoryJSON));
        } catch (ParsingJsonToDaoException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public @ResponseBody String deleteCategory(@PathVariable("id") String id){
        categoryService.deleteCategory(id);
        return "OK";
    }

    @RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
    public @ResponseBody List<FilmJSONIndex> getFilmsForCategory(@PathVariable("id") String id){
        return filmCategoryService.getAllFilmsForCategory(id);
    }
//    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<CategoryDb> getAll(){
        return categoryService.getAllCategories();
    }
}
