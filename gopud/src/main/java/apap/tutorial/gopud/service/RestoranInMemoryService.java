package apap.tutorial.gopud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public Optional<RestoranModel> getRestoranByIdRestoran(Long idRestoran) {
		RestoranModel foundRestoran= null;
		for (RestoranModel restoran : listRestoran) {
            if(restoran.getIdRestoran() == idRestoran){
                foundRestoran = restoran;
            }
        }
		return Optional.ofNullable(foundRestoran);
	}

	@Override
	public RestoranModel changeRestoran(RestoranModel restoranModel) {
		return null;
	}
}
