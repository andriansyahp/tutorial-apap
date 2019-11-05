package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.util.List;

public interface MenuRestService {
    MenuModel createMenu(MenuModel restoran);

    List<MenuModel> retrieveListMenu();

    MenuModel getMenuByIdMenu(Long idRestoran);

    MenuModel changeMenu(Long idRestoran, MenuModel restoranUpdate);

    void deleteMenu(Long idMenu);
}
