package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.FavouriteTool;
import com.portfolio.sarvech.services.FavouriteToolsService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/save")
    public String saveFavouriteTool(FavouriteTool favouriteTool, HttpSession session){
        this.favouriteToolsService.saveFavouriteTool(favouriteTool);
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
        this.favouriteToolsService.saveFavouriteTool(favouriteTool);
        session.setAttribute("message", new Message("Favourite tool updated successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }

}
