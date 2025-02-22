package apap.tutorial.gopud.controller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;

    @Qualifier("restoranServiceImpl")
    @Autowired
    RestoranService restoranService;

    @RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addProductFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model) {
        MenuModel menu = new MenuModel();
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
        List<MenuModel> menuList = new ArrayList<>();

        menuList.add(new MenuModel());
        restoran.setListMenu(menuList);

//        menu.setRestoran(restoran);

        model.addAttribute("resto", restoran);

        return "form-add-menu";
    }

    @RequestMapping(value = "/menu/add/{restoranId}", params= {"addRow"}, method=RequestMethod.POST)
    private String addRow(@ModelAttribute RestoranModel restoran, Model model) {

        if (restoran.getListMenu() == null || restoran.getListMenu().size() == 0) {
            restoran.setListMenu(new ArrayList<>());
        }
        restoran.getListMenu().add(new MenuModel());
        model.addAttribute("resto", restoran);

        return "form-add-menu";
    }

    @RequestMapping(value = "/menu/add/{restoranId}", params= {"deleteRow"}, method=RequestMethod.POST)
    private String deleteRow(@ModelAttribute RestoranModel restoran, final HttpServletRequest req, Model model) {
        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        restoran.getListMenu().remove(rowId.intValue());

        model.addAttribute("resto", restoran);
        return "form-add-menu";
    }

    @RequestMapping(value = "menu/add/{idRestoran}",params = {"save"}, method = RequestMethod.POST)
    private String addProductSubmit(@ModelAttribute RestoranModel restoran, Model model){
        RestoranModel curr = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran());
        List<MenuModel> menus = restoran.getListMenu();
        for(int i=0; i<menus.size(); i++) {
            menus.get(i).setRestoran(curr);
            menuService.addMenu(menus.get(i));
        }

        return "add-menu";
    }

    @RequestMapping(value="menu/change/{idMenu}", method = RequestMethod.GET)
    public String changeMenuFormPage(@PathVariable Long idMenu, Model model) {
        //Mengambil existing data restoran
        try{
            for(MenuModel menu : menuService.getListMenuOrderByHargaAsc(idMenu)){
                if(menu.getId() == idMenu){
                    model.addAttribute("menu", menu);;
                }
            }
            return "form-change-menu";
        }catch (NoSuchElementException e){
            model.addAttribute("idMenu", idMenu);
            return "change-menu-error";
        }
    }

    @RequestMapping(value="menu/change/{idMenu}", method = RequestMethod.POST)
    public String changeMenuFormSubmit(@PathVariable Long idMenu, @ModelAttribute MenuModel menu, Model model) {
        try{
            MenuModel newMenuData = menuService.changeMenu(menu);
            model.addAttribute("menu", newMenuData);
        }
        catch (NoSuchElementException e){
            model.addAttribute("idMenu", idMenu);
            return "change-menu-error";
        }

        return "change-menu";
    }

    @RequestMapping(value="menu/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute RestoranModel restoran, Model model){
        for (MenuModel menu : restoran.getListMenu()) {
            menuService.deleteMenu(menu);
        }
        return "delete-menu";
    }
}
