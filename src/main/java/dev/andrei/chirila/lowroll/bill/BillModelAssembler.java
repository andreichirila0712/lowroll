package dev.andrei.chirila.lowroll.bill;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BillModelAssembler extends RepresentationModelAssemblerSupport<Bill, BillModel> {

    public BillModelAssembler() {
        super(BillController.class, BillModel.class);
    }

    @Override
    public BillModel toModel(Bill bill) {
        BillModel resource = createResource(bill);

        resource.add(linkTo(methodOn(BillController.class).findOne(resource.id)).withSelfRel());
        return resource;
    }

    @Override
    public CollectionModel<BillModel> toCollectionModel(Iterable<? extends Bill> entities) {
        CollectionModel<BillModel> resources = super.toCollectionModel(entities);

        resources.add(linkTo(methodOn(BillController.class).findAll()).withSelfRel());

        return resources;
    }

    private BillModel createResource(Bill bill) {
       BillModel model = new BillModel();
       model.id = bill.getId();
       model.providerName = bill.getProvider().getName();
       model.amount = bill.getAmount();
       model.dueDate = bill.getDueDate();
       model.issueDate = bill.getIssueDate();
       model.status = bill.getStatus();

       return model;
    }


}
