package dev.andrei.chirila.lowroll.bill;

import dev.andrei.chirila.lowroll.security.SecureMultipartFile;
import dev.andrei.chirila.lowroll.validation.ValidFileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {
    private static final Logger log = LoggerFactory.getLogger(BillController.class);
    private final BillModelAssembler assembler;
    private final BillService service;

    public BillController(BillModelAssembler assembler, BillService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@ValidFileType @SecureMultipartFile MultipartFile file) {
        log.info("File received. Attempting upload...");
        this.service.processFile(file);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillModel> findOne(@PathVariable Long id) {
        Bill bill = service.getBill(id);

        return ResponseEntity.ok(assembler.toModel(bill));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<BillModel>> findAll() {
        List<Bill> bills = service.listBills();

        return ResponseEntity.ok(assembler.toCollectionModel(bills));
    }
}
