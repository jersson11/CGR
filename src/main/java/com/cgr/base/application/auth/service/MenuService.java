package com.cgr.base.application.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cgr.base.infrastructure.persistence.entity.Menu.Menu;
import com.cgr.base.infrastructure.persistence.repository.menu.MenuRepositoryJpa;

@Service
public class MenuService {

    private final MenuRepositoryJpa menuRepositoryJpa;

    public MenuService(MenuRepositoryJpa menuRepositoryJpa) {
        this.menuRepositoryJpa = menuRepositoryJpa;
    }

    public List<Menu> getAllMenus() {
        return menuRepositoryJpa.findAll();
    }

    public Map<String, Object> getMenus() {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("MenuRepositoryJpa bean: " + menuRepositoryJpa);
            List<Menu> menus = menuRepositoryJpa.findAll();
            response.put("menus", menus);
            response.put("message", "Menus retrieved successfully");
            response.put("statusCode", 200);
            response.put("status", "success");
            return response;
        } catch (Exception e) {
            response.put("errormsj", e.getMessage());
            response.put("message", "Error retrieving menus");
            response.put("statusCode", 500);
            response.put("status", "error");
            return response;
        }
    }

}
