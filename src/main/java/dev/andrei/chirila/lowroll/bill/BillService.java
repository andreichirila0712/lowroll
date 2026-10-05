package dev.andrei.chirila.lowroll.bill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BillService {
    private final static Logger log = LoggerFactory.getLogger(BillService.class);
    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill getBill(Long id) {
        return this.repository.findById(id).orElseThrow(NullPointerException::new);
    }

    public List<Bill> listBills() {
        return this.repository.findAll();
    }

    public void processFile(MultipartFile file) {

    }
}
