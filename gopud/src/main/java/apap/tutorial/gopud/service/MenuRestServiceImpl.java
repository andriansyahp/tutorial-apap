package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MenuRestServiceImpl implements MenuRestService{
    @Autowired
    private MenuDB menuDb;

    @Override
    public MenuModel createMenu(MenuModel menu) {
        return menuDb.save(menu);
    }

    @Override
    public List<MenuModel> retrieveListMenu() {
        return menuDb.findAll();
    }

    @Override
    public MenuModel getMenuByIdMenu(Long idMenu){
        Optional<MenuModel> menu = menuDb.findById(idMenu);
        if (menu.isPresent()){
            return menu.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public MenuModel changeMenu(Long idMenu, MenuModel menuUpdate){
        MenuModel menu = getMenuByIdMenu(idMenu);
        menu.setNama(menuUpdate.getNama());
        menu.setHarga(menuUpdate.getHarga());
        menu.setDurasiMasak(menuUpdate.getDurasiMasak());
        menu.setDeskripsi(menuUpdate.getDeskripsi());
        return menuDb.save(menu);
    }

    @Override
    public void deleteMenu(Long idMenu){
        MenuModel menu = getMenuByIdMenu(idMenu);
        menuDb.delete(menu);
    }
}
