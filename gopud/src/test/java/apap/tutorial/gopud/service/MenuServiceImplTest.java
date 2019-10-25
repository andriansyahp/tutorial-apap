package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
	@InjectMocks
	MenuService menuService = new MenuServiceImpl();
	@Mock
	MenuDB menuDb;

    private RestoranModel generateDummyRestoranModel(int count) {
        RestoranModel dummyRestoranModel = new RestoranModel();
        dummyRestoranModel.setNama("dummy no. " + count);
        dummyRestoranModel.setAlamat("alamat dummy no. " + count);
        dummyRestoranModel.setIdRestoran((long)count);
        dummyRestoranModel.setNomorTelepon(14000);
        dummyRestoranModel.setListMenu(new ArrayList<>());
        return dummyRestoranModel;
    }

    private MenuModel generateDummyMenu(int count, RestoranModel restoranModel) {
        MenuModel menuModel = new MenuModel();
        menuModel.setRestoran(restoranModel);
        menuModel.setNama("dummy no. " + count);
        menuModel.setHarga(BigInteger.valueOf(count));
        menuModel.setDurasiMasak(count);
        menuModel.setDeskripsi("deskripsi dummy no. " + count);
        return menuModel;
    }

	@Test
	public void whenAddValidMenuItShouldCallsMenuRepositorySave() {
		MenuModel newMenu = new MenuModel();
		newMenu.setNama("Sate");
		newMenu.setHarga(BigInteger.valueOf(10000));
		newMenu.setDurasiMasak(600);
		newMenu.setDeskripsi("Sate Pacil Murah Enak");
		menuService.addMenu(newMenu);
		verify(menuDb, times(1)).save(newMenu);
	}

	@Test
	public void whenFindAllMenuByIdRestoranCalledItShouldReturnsAllMenuInThatRestaurant() {
		List<MenuModel> allMenuInRestaurant = new ArrayList<>();
		RestoranModel dummyRestoran = generateDummyRestoranModel(1);
		for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
			allMenuInRestaurant.add(generateDummyMenu(loopTimes, dummyRestoran));
		}
		when (menuService.findAllMenuByIdRestoran(1L)).thenReturn(allMenuInRestaurant);

		List<MenuModel> dataFromServiceCall = menuService.findAllMenuByIdRestoran(1L);
		assertEquals(3, dataFromServiceCall.size());

		verify(menuDb, times(1)).findByRestoranIdRestoran(1L);
	}

	@Test
	public void whenGetListMenuOrderByHargaAscCalledItShouldReturnsCorrectDataOrder() {
        List<MenuModel> allMenuInRestaurant = new ArrayList<>();
        RestoranModel dummyRestoran = generateDummyRestoranModel(1);
        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            allMenuInRestaurant.add(generateDummyMenu(loopTimes, dummyRestoran));
        }
        allMenuInRestaurant.sort(Comparator.comparing(MenuModel::getHarga));
        when (menuService.getListMenuOrderByHargaAsc(1L)).thenReturn(allMenuInRestaurant);

        List<MenuModel> dataFromServiceCall = menuService.getListMenuOrderByHargaAsc(1L);
        assertEquals(3, dataFromServiceCall.size());

        verify(menuDb, times(1)).findByRestoranIdRestoranOrderByHarga(1L);
	}

	@Test
	public void whenChangeMenuCalledAndMenuNotNullItShouldChangesMenuData() {
        MenuModel changedMenu = new MenuModel();
        changedMenu.setNama("Tongseng");
        changedMenu.setHarga(BigInteger.valueOf(13000));
        changedMenu.setDurasiMasak(600);
        changedMenu.setDeskripsi("Tongseng Pacil Enak");
        changedMenu.setId(1L);


        when(menuDb.findById(1L)).thenReturn(Optional.of(changedMenu));
        when(menuService.changeMenu(changedMenu)).thenReturn(changedMenu);

        MenuModel dataFromServiceCall = menuService.changeMenu(changedMenu);
        assertEquals("Tongseng", dataFromServiceCall.getNama());
        assertEquals(BigInteger.valueOf(13000), dataFromServiceCall.getHarga());
        assertEquals(Integer.valueOf(600), dataFromServiceCall.getDurasiMasak());
        assertEquals("Tongseng Pacil Enak", dataFromServiceCall.getDeskripsi());
	}

    @Test(expected=NullPointerException.class)
    public void whenChangeMenuCalledAndMenuIsNullItShouldThrowsNullException() {
        //MenuModel changedData = generateDummyMenu(1, generateDummyRestoranModel(1));
        MenuModel changedData = new MenuModel();
        changedData.setId(1L);

        when(menuDb.findById(1L)).thenReturn(Optional.of(changedData));
        when(menuDb.save(changedData)).thenThrow(NullPointerException.class);

        MenuModel menu = menuService.changeMenu(changedData);
        assertNull(menu);
    }

    @Test
    public void whenDeleteMenuCalledItShouldDeletesMenuData() {
        RestoranModel dummyRestoran = generateDummyRestoranModel(1);
        MenuModel dummyMenu = generateDummyMenu(100, dummyRestoran);

        menuService.addMenu(dummyMenu);
        menuService.deleteMenu(dummyMenu);

        verify(menuDb, times(1)).delete(dummyMenu);
    }
}
