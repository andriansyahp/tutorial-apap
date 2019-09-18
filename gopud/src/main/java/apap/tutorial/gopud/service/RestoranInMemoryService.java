package apap.tutorial.gopud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import apap.tutorial.gopud.model.RestoranModel;

@Service
public class RestoranInMemoryService implements RestoranService {
	private List<RestoranModel> listRestoran;


	// Constructor 
	public RestoranInMemoryService() {
		listRestoran = new ArrayList<>();
	}
	
	@Override
	public void addRestoran(RestoranModel restoran) {
		listRestoran.add(restoran);
	}

	@Override
	public List<RestoranModel> getRestoranList() {
		return listRestoran;
	}

	@Override
	public RestoranModel getRestoranByIdRestoran(String idRestoran) {
		RestoranModel foundRestoran= null;
		for (RestoranModel restoran : listRestoran) {
            if(restoran.getIdRestoran().matches(idRestoran)){
                foundRestoran = restoran;
            }
        }
		return foundRestoran;
	}
	
	@Override
	public String updateNomorTeleponByIdRestoran(String idRestoran, Integer nomorTelepon) {
		String namaResto = "";
		for (RestoranModel restoran : listRestoran) {
            if(restoran.getIdRestoran().matches(idRestoran)){
                restoran.setNomorTelepon(nomorTelepon);
                namaResto = restoran.getNama();
            }
        }
		return namaResto;
	}
	
	@Override
	public String deleteRestoranByIdRestoran(String idRestoran) {
		String namaResto = "";
		for (RestoranModel restoran : listRestoran) {
            if(restoran.getIdRestoran().matches(idRestoran)){
                namaResto = restoran.getNama();
                listRestoran.remove(restoran);
                return namaResto;
            }
        }
		return namaResto;
	}

}
