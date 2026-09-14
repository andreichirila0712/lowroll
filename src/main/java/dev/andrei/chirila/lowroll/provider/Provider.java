package dev.andrei.chirila.lowroll.provider;

import dev.andrei.chirila.lowroll.bill.Bill;
import dev.andrei.chirila.lowroll.category.Category;
import dev.andrei.chirila.lowroll.ingestion.EmailIngestionLog;
import dev.andrei.chirila.lowroll.ingestion.ProviderEmailRule;
import dev.andrei.chirila.lowroll.subscription.Subscription;
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

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private ProviderType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<Subscription> subscriptions = new ArrayList<>();
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<EmailIngestionLog> ingestionLogs = new ArrayList<>();
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    private List<ProviderEmailRule> emailRules = new ArrayList<>();

    public Provider() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProviderType getType() {
        return type;
    }

    public void setType(ProviderType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
        bill.setProvider(this);
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
        subscription.setProvider(this);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void addIngestionLog(EmailIngestionLog ingestionLog) {
        ingestionLogs.add(ingestionLog);
        ingestionLog.setProvider(this);
    }

    public List<EmailIngestionLog> getIngestionLogs() {
        return ingestionLogs;
    }

    public void addEmailRule(ProviderEmailRule emailRule) {
        emailRules.add(emailRule);
        emailRule.setProvider(this);
    }

    public List<ProviderEmailRule> getEmailRules() {
        return emailRules;
    }
}
