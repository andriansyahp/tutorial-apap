package apap.tutorial.gopud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController {
	@Autowired
	private RestoranService restoranService;
	
	//URL mapping add
	@RequestMapping("/restoran/add")
	public String add(
			// Request Parameter untuk di-pass
			@RequestParam(value="idRestoran", required=true) String idRestoran,
			@RequestParam(value="nama", required=true) String nama,
			@RequestParam(value="alamat", required=true) String alamat,
			@RequestParam(value="nomorTelepon", required=true) Integer nomorTelepon,
			Model model){

		// Membuat objek RestoranModel
		RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);
		
		// Memanggil service addRestoran
		restoranService.addRestoran(restoran);
		
		// Add variabel nama restoran ke "namaResto" untuk di-render
		model.addAttribute("namaResto", nama);
		
		// Return view template
		return "add-restoran";
	}
	
	//URL mapping view dengan Request Param
	@RequestMapping("/restoran/view")
	public String viewWithRequestParam(
			// Request Parameter untuk di-pass
			@RequestParam(value="idRestoran") String idRestoran, Model model){
		
		if(idRestoran != null && idRestoran != "") {
			// Mengambil objek RestoranModel yang dituju 
			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
			if(restoran != null) {
				// Add model restoran ke "resto" untuk di-render
				model.addAttribute("resto", restoran);
			}
		} else {
			model.addAttribute("resto", null);
		}
		return "view-restoran";
	}
	
	//URL mapping view dengan Path Variable
	@RequestMapping("/restoran/view/id-restoran/{idRestoran}")
	public String viewWithPathVariable(
			@PathVariable(value="idRestoran") String idRestoran, Model model) {

		if(idRestoran != null && idRestoran != "") {
			// Mengambil objek RestoranModel yang dituju 
			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
			if(restoran != null) {
				// Add model restoran ke "resto" untuk di-render
				model.addAttribute("resto", restoran);
			}
		} else {
			model.addAttribute("resto", null);
		}
		return "view-restoran";
	}
	
	//URL mapping update nomorTelepon Restoran dengan Path Variable
	@RequestMapping("/restoran/update/id-restoran/{idResto}/nomor-telepon/{nomorTeleponResto}")
	public String updateNomorTelepon(
			@PathVariable(value="idResto") String idRestoran,
			@PathVariable(value="nomorTeleponResto") String nomorTelepon,
			Model model){

		// Konversi String ke Integer
		Integer newNomorTelepon = Integer.valueOf(nomorTelepon);
		if(idRestoran != null && idRestoran != "") {
			// Meng-update nomor Telepon
			String namaRestoran = restoranService.updateNomorTeleponByIdRestoran(idRestoran, newNomorTelepon);
			if(namaRestoran != "") {
				model.addAttribute("namaResto", namaRestoran);
			}
		} else {
			model.addAttribute("namaResto", null);
		}
//		String namaRestoran = restoranService.updateNomorTeleponByIdRestoran(idRestoran, newNomorTelepon);

//		model.addAttribute("namaResto", namaRestoran);
			
		// Return view template
		return "update-nomor-telepon-restoran";
	}
	
	//URL mapping delete Restoran dengan Path Variable
	@RequestMapping("/restoran/delete/id/{idResto}")
	public String deleteRestoranByIdRestoran(
			@PathVariable(value="idResto") String idRestoran, Model model) {

		if(idRestoran != null && idRestoran != "") {
			// Mengambil nama dari objek RestoranModel yang ingin dihapus 
			String namaRestoran = restoranService.deleteRestoranByIdRestoran(idRestoran);
			if(namaRestoran != "") {
				model.addAttribute("namaResto", namaRestoran);
			}
		} else {
			model.addAttribute("namaResto", null);
		}
		return "delete-restoran";
	}
	
	//URL mapping viewAll
	@RequestMapping("/restoran/viewall")
	public String viewall (Model model){
		
		// Mengambil semua objek RestoranModel yang ada 
		List<RestoranModel> listRestoran = restoranService.getRestoranList();
			
		// Add model restoran ke "resto" untuk di-render
		model.addAttribute("restoList", listRestoran);
			
		// Return view template
		return "viewall-restoran";
	}
}
