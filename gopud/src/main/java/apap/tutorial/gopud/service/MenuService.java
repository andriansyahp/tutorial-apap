package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.util.List;

public interface MenuService {
    void addMenu(MenuModel menu);

    List<MenuModel> findAllMenuByIdRestoran(Long idRestoran);

    MenuModel findById(Long idMenu);

    MenuModel changeRestoran(MenuModel restoran);

    void deleteMenu(MenuModel menu);
}
