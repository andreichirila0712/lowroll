package dev.andrei.chirila.lowroll.bill;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BillModel extends RepresentationModel<BillModel> {
    Long id;
    String providerName;
    Double amount;
    BillCurrency currency;
    LocalDateTime issueDate;
    LocalDate dueDate;
    BillStatus status;
}
