package com.unab.Calificadoor.Service;
import com.unab.Calificadoor.Models.Transaccion;
import com.unab.Calificadoor.Dao.TransaccionDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;
@Service
public class NotasService {
    @Autowired
    private NotasDao NotasDao;

    @Transactional(readOnly=false)
    public Notas save(Notas notas) {
        return notasDao.save(notas);
    }

    @Transactional(readOnly=false)
    public void delete(Integer id) {
        notasDao.deleteById(id);;
    }

    @Transactional(readOnly=true)
    public Notas findById(Integer id) {
        return notasDao.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Notas> findByAll() {
        return (List<Notas>) notasDao.findAll();
    }

    @Transactional(readOnly=true)
    public List<Notas> consulta_notas(String idEstu) {
        return (List<Notas>) notasDao.consulta_notas(idEstu);
    }

    @Transactional(readOnly=false)
    public void cear_notas(String idEstu, Double valor_transaccion, String tipo) {
        notasDao.crear_notas(idEstu, valor_transaccion, tipo);
    }

}
