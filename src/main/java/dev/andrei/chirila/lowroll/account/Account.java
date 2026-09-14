package dev.andrei.chirila.lowroll.account;

import dev.andrei.chirila.lowroll.bill.Bill;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "bank_name")
    private String bankName;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    public Account() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
        bill.setAccount(this);
    }
}
