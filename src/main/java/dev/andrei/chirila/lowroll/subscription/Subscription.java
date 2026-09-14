package dev.andrei.chirila.lowroll.subscription;

import dev.andrei.chirila.lowroll.bill.Bill;
import dev.andrei.chirila.lowroll.provider.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @Column(name = "name")
    private String name;
    @Column(name = "expected_amount")
    private Double expectedAmount;
    @Column(name = "currency")
    private Currency currency;
    @Column(name = "billing_cycle")
    private BillingCycle billingCycle;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "status")
    private SubscriptionStatus status;
    @Column(name = "next_expected_date")
    private LocalDate nextExpectedDate;
    @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    public Subscription() {}

    public Long getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider providerId) {
        this.provider = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BillingCycle getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(BillingCycle billingCycle) {
        this.billingCycle = billingCycle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public LocalDate getNextExpectedDate() {
        return nextExpectedDate;
    }

    public void setNextExpectedDate(LocalDate nextExpectedDate) {
        this.nextExpectedDate = nextExpectedDate;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
        bill.setSubscription(this);
    }
}
