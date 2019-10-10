package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDB menuDb;

    @Override
    public void addMenu(MenuModel menu){
        menuDb.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(Long idRestoran) {
        return menuDb.findByRestoranIdRestoran(idRestoran);
    }

    @Override
    public List<MenuModel> getListMenuOrderByHargaAsc(Long idRestoran) {
        return menuDb.findByRestoranIdRestoranOrderByHarga(idRestoran);
    }

    @Override
    public MenuModel changeMenu(MenuModel menu) {
        try {
            // mengambil object restoran yang ingin diubah
            MenuModel targetMenu = menuDb.findById(menu.getId()).get();
            targetMenu.setNama(menu.getNama());
            targetMenu.setHarga(menu.getHarga());
            targetMenu.setDurasiMasak(menu.getDurasiMasak());
            targetMenu.setDeskripsi(menu.getDeskripsi());
            menuDb.save(targetMenu);
            return targetMenu;
        } catch (NullPointerException nullException) {
            throw nullException;
        }
    }

    @Override
    public void deleteMenu(MenuModel menu) {
        menuDb.delete(menu);
    }

}
