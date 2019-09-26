package apap.tutorial.gopud.controller;

import java.util.List;
import java.util.Optional;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController {
	@Qualifier("restoranServiceImpl")
	@Autowired
	private RestoranService restoranService;

	@Autowired
	private MenuService menuService;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	//URL mapping yang digunakan untuk mengakses halaman add restoran
	@RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
	public String addRestoranFormPage(Model model){
		RestoranModel newRestoran = new RestoranModel();
		model.addAttribute("restoran", newRestoran);
		return "form-add-restoran";
	}

	//URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add restoran
	@RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
	public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model){
		restoranService.addRestoran(restoran);
		model.addAttribute("namaResto", restoran.getNama());
		return "add-restoran";
	}
	
	//URL mapping view
	@RequestMapping(value = "/restoran/view", method = RequestMethod.GET)
	public String viewWithRequestParam(
			// Request Parameter untuk di-pass
			@RequestParam(value="idRestoran") Long idRestoran, Model model){
		
//		if(idRestoran != null && idRestoran != "") {
			// Mengambil objek RestoranModel yang dituju 
//			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
//			if(restoran != null) {
				// Add model restoran ke "resto" untuk di-render
//				model.addAttribute("resto", restoran);
//			}
//		} else {
//			model.addAttribute("resto", null);
//		}
		// Mengambil objek RestoranModel yang dituju
		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();

		// Add model restoran ke "resto" untuk di-render
		model.addAttribute("resto", restoran);

		List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(restoran.getIdRestoran());
		model.addAttribute("menuList", menuList);

		return "view-restoran";
	}

	// API yang digunakan untuk menuju halaman form change restoran
	@RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.GET)
	public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model){
		// Mengambil existing data restoran
		RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
		model.addAttribute("restoran", existingRestoran);
		return "form-change-restoran";
	}

	// API yang digunakan untuk submit form change restoran
	@RequestMapping(value = "restoran/change/{idRestoran}", method = RequestMethod.POST)
	public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model){
		RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
		model.addAttribute("restoran", newRestoranData);
		return "change-restoran";
	}
	
//	//URL mapping view dengan Path Variable
//	@RequestMapping("/restoran/view/id-restoran/{idRestoran}")
//	public String viewWithPathVariable(
//			@PathVariable(value="idRestoran") String idRestoran, Model model) {
//
//		if(idRestoran != null && idRestoran != "") {
//			// Mengambil objek RestoranModel yang dituju
//			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//			if(restoran != null) {
//				// Add model restoran ke "resto" untuk di-render
//				model.addAttribute("resto", restoran);
//			}
//		} else {
//			model.addAttribute("resto", null);
//		}
//		return "view-restoran";
//	}
	
	//URL mapping update nomorTelepon Restoran dengan Path Variable
//	@RequestMapping("/restoran/update/id-restoran/{idResto}/nomor-telepon/{nomorTeleponResto}")
//	public String updateNomorTelepon(
//			@PathVariable(value="idResto") String idRestoran,
//			@PathVariable(value="nomorTeleponResto") String nomorTelepon,
//			Model model){
//
//		// Konversi String ke Integer
//		Integer newNomorTelepon = Integer.valueOf(nomorTelepon);
//		if(idRestoran != null && idRestoran != "") {
//			// Meng-update nomor Telepon
//			String namaRestoran = restoranService.updateNomorTeleponByIdRestoran(idRestoran, newNomorTelepon);
//			if(namaRestoran != "") {
//				model.addAttribute("namaResto", namaRestoran);
//			}
//		} else {
//			model.addAttribute("namaResto", null);
//		}
//		String namaRestoran = restoranService.updateNomorTeleponByIdRestoran(idRestoran, newNomorTelepon);

//		model.addAttribute("namaResto", namaRestoran);
			
		// Return view template
//		return "update-nomor-telepon-restoran";
	//}
	
	//URL mapping delete Restoran dengan Path Variable
//	@RequestMapping("/restoran/delete/id/{idResto}")
//	public String deleteRestoranByIdRestoran(
//			@PathVariable(value="idResto") String idRestoran, Model model) {
//
//		if(idRestoran != null && idRestoran != "") {
//			// Mengambil nama dari objek RestoranModel yang ingin dihapus
//			String namaRestoran = restoranService.deleteRestoranByIdRestoran(idRestoran);
//			if(namaRestoran != "") {
//				model.addAttribute("namaResto", namaRestoran);
//			}
//		} else {
//			model.addAttribute("namaResto", null);
//		}
//		return "delete-restoran";
//	}
//
//	//URL mapping viewAll
//	@RequestMapping("/restoran/viewall")
//	public String viewall (Model model){
//
//		// Mengambil semua objek RestoranModel yang ada
//		List<RestoranModel> listRestoran = restoranService.getRestoranList();
//
//		// Add model restoran ke "resto" untuk di-render
//		model.addAttribute("restoList", listRestoran);
//
//		// Return view template
//		return "viewall-restoran";
//	}
}
