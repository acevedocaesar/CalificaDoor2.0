package com.unab.Calificadoor.Service;
import com.unab.Calificadoor.Models.Cuenta;
import com.unab.Calificadoor.Dao.CuentaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalificadoorService {
    @Autowired
    private CalificadoorDao calificadoorDao;
    
    @Transactional(readOnly=false)
    public Calificadoor save(Calificadoor calificadoor) {
        return calificadoorDao.save(calificadoor);
    }
    @Transactional(readOnly=false)
    public void delete(String id) {
        calificadoorDao.deleteById(id);;
    }
    @Transactional(readOnly=true)
    public Calificadoor findById(String id) {
       return calificadoorDao.findById(id).orElse(null);
    }
    @Transactional(readOnly=true)
    public List<Calificadoor> findByAll() {
        return (List<Calificadoor>) calificadoorDao.findAll();
    }
    @Transactional(readOnly=true)
    public List<Calificadoor> consulta_calificadoor(String idc) {
        return (List<Calificadoor>) calificadoorDao.consulta_calificadoor(idc);
    }

    @Transactional(readOnly=false)
    public void deposito(String idEstu,Double valor_deposito) {
        calificadoorDao.deposito(idEstu, valor_deposito);
    }

    @Transactional(readOnly=false)
    public void retiro(String idEstu,Double valor_retiro) {
        calificadoorDao.retiro(idEstu, valor_retiro);
    }
}
