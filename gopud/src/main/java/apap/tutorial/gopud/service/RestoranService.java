package apap.tutorial.gopud.service;

import java.util.List;
import java.util.Optional;

import apap.tutorial.gopud.model.RestoranModel;

public interface RestoranService {
	// Method untuk menambah restoran
	void addRestoran(RestoranModel restoran);
	
	// Method untuk mendapatkan semua data Restoran yang tersimpan 
	List<RestoranModel> getRestoranList();
	
	// Method untuk mendapatkan data sebuah Restoran berdasarkan idRestoran
	RestoranModel getRestoranByIdRestoran(String idRestoran);
	
	// Method untuk mengubah (update) nomor telepon Restoran berdasarkan idRestoran
	String updateNomorTeleponByIdRestoran(String idRestoran, Integer nomorTelepon);
	
	// Method untuk menghapus restoran
	String deleteRestoranByIdRestoran(String idRestoran);
}
