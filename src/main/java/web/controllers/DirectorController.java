package web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.dao.DirectorDb;
import web.exceptions.ParsingJsonToDaoException;
import web.model.DirectorJSON;
import web.services.DirectorService;

import java.util.List;

/**
 * Created by Rostyk on 16.06.2017.
 */
@Controller
@RequestMapping("/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    private static final Logger log = LogManager.getLogger(DirectorController.class);

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    String addOrUpdate(@RequestBody DirectorJSON directorJSON) throws ParsingJsonToDaoException {
        log.info("addOrUpdate(directorJSON=" + directorJSON + ")");


        directorService.saveOrUpdate(directorService.convertToDirectorDb(directorJSON));

        log.info("addOrUpdate() returns : OK");
        return "OK";
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable("id") String id) {
        log.info("delete(id=" + id + ")");

        directorService.delete(id);

        log.info("delete() returns : OK");
        return "OK";
    }

//    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    List<DirectorDb> getAll() {
        log.info("getAll()");

        List<DirectorDb> directorDbs = directorService.getAll();

        log.info("getAll() returns : directorsDbs.size()" + directorDbs.size());
        return directorDbs;
    }
}
