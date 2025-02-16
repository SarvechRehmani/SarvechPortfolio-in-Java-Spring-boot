package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.FavouriteTool;
import com.portfolio.sarvech.services.FavouriteToolsService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/favourite-tool")
public class FavouriteToolController {
    private final Logger logger = LoggerFactory.getLogger(FavouriteToolController.class);

    private final FavouriteToolsService favouriteToolsService;


    public FavouriteToolController(FavouriteToolsService favouriteToolsService) {
        this.favouriteToolsService = favouriteToolsService;
    }

    @GetMapping("/add")
    public String addFavouriteTool(Model model){
        model.addAttribute("favouriteTool", new FavouriteTool());
        return "/admin/add-favourite-tool";
    }

    @PostMapping("/add")
    public String saveFavouriteTool(@Valid  FavouriteTool favouriteTool, BindingResult result, MultipartFile file, HttpSession session){
        if (result.hasErrors()){
            logger.error("Error saving favourite tool: {}", result.getAllErrors());
            session.setAttribute("message", new Message("Please fix following errors", MessageType.ERROR));
            return "/admin/add-favourite-tool";
        }
        // Log received files
        if (file == null || file.isEmpty()) {
            session.setAttribute("message",new Message("Please fix the following errors",MessageType.ERROR));
            session.setAttribute("fileMessage", "Please select logo for skill.");
            return "/admin/add-favourite-tool";
        }
        this.favouriteToolsService.saveFavouriteTool(favouriteTool, file);
        session.setAttribute("message", new Message("Favourite tool added successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editFavouriteTool(Model model, Long id, HttpSession session) {
        FavouriteTool favouriteTool = this.favouriteToolsService.findFavouriteToolById(id);
        if(favouriteTool == null){
            session.setAttribute("message", new Message("Favourite tool not found!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("favouriteTool", favouriteTool);
        return "/admin/edit-favourite-tool";
    }

    @PostMapping("/update/{id}")
    public String updateFavouriteTool(FavouriteTool favouriteTool, HttpSession session, Long id){
        FavouriteTool existingFavouriteTool = this.favouriteToolsService.findFavouriteToolById(id);
        if(existingFavouriteTool == null){
            this.logger.error("Can not update! Favourite tool not found with id: {}", id);
            session.setAttribute("message", new Message("Favourite tool not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        this.favouriteToolsService.updateFavouriteTool(favouriteTool);
        session.setAttribute("message", new Message("Favourite tool updated successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteFavouriteTool(@PathVariable long id, HttpSession session) {
        FavouriteTool favouriteTool = this.favouriteToolsService.findFavouriteToolById(id);
        if(favouriteTool == null){
            this.logger.error("Can not delete! Favourite tool not found with id: {}", id);
            session.setAttribute("message", new Message("Favourite tool not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }

        this.favouriteToolsService.deleteFavouriteTool(id);
        this.logger.info("Favourite tool deleted successfully with id: {}", id);
        session.setAttribute("message", new Message("Favourite tool deleted successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }
}
