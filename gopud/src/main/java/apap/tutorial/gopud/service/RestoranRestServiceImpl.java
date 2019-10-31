package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.RestoranDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class RestoranRestServiceImpl implements RestoranRestService{
    @Autowired
    private RestoranDB restoranDb;

    @Override
    public RestoranModel createRestoran(RestoranModel restoran) {
        return restoranDb.save(restoran);
    }

    @Override
    public List<RestoranModel> retrieveListRestoran() {
        return restoranDb.findAllByOrderByNamaAsc();
    }

    @Override
    public RestoranModel getRestoranByIdRestoran(Long idRestoran){
        Optional<RestoranModel> restoran = restoranDb.findById(idRestoran);
        if (restoran.isPresent()){
            return restoran.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public RestoranModel changeRestoran(Long idRestoran, RestoranModel restoranUpdate){
        RestoranModel restoran = getRestoranByIdRestoran(idRestoran);
        restoran.setNama(restoranUpdate.getNama());
        restoran.setAlamat(restoranUpdate.getAlamat());
        restoran.setNomorTelepon(restoranUpdate.getNomorTelepon());
        restoran.setRating(restoranUpdate.getRating());
        return restoranDb.save(restoran);
    }

    @Override
    public void deleteRestoran(Long idRestoran){
        RestoranModel restoran = getRestoranByIdRestoran(idRestoran);
        if (restoran.getListMenu().size()==0){
            restoranDb.delete(restoran);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
