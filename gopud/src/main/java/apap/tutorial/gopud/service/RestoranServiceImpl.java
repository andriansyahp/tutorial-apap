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
public class RestoranServiceImpl implements RestoranService{
    @Autowired
    private RestoranDB restoranDb;

    @Override
    public void addRestoran(RestoranModel restoran){
        restoranDb.save(restoran);
    }

    @Override
    public List<RestoranModel> getRestoranList(){
        return restoranDb.findAllByOrderByNamaAsc();
    }

    @Override
    public RestoranModel getRestoranByIdRestoran(Long idRestoran) {
        try{
            Optional<RestoranModel> restoran = restoranDb.findByIdRestoran(idRestoran);
            return restoran.get();
        }catch (NoSuchElementException e) {
            throw e;
        }
    }

    @Override
    public RestoranModel changeRestoran(RestoranModel restoranModel){
        // Mengambil objek restoran yang ingin diubah
        RestoranModel targetrestoran = restoranDb.findById(restoranModel.getIdRestoran()).get();

        try{
            targetrestoran.setNama(restoranModel.getNama());
            targetrestoran.setAlamat(restoranModel.getAlamat());
            targetrestoran.setNomorTelepon(restoranModel.getNomorTelepon());
            restoranDb.save(targetrestoran);
            return targetrestoran;
        } catch (NullPointerException nullException) {
            return null;
        }
    }

    @Override
    public void deleteRestoran(Long idRestoran) {
        RestoranModel restoran = getRestoranByIdRestoran(idRestoran);
        if(restoran.getListMenu().size()==0){
            restoranDb.delete(restoran);
        }else{
            UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();
            throw unsupportedOperationException;
        }
    }
}
