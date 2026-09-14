package dev.andrei.chirila.lowroll.bill;

import dev.andrei.chirila.lowroll.account.Account;
import dev.andrei.chirila.lowroll.attachment.Attachment;
import dev.andrei.chirila.lowroll.ingestion.EmailIngestionLog;
import dev.andrei.chirila.lowroll.provider.Provider;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "currency")
    private BillCurrency currency;
    @Column(name = "issue_date")
    private LocalDateTime issueDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "paid_date")
    private LocalDateTime paidDate;
    @Column(name = "status")
    private BillStatus status;
    @Column(name = "source")
    private BillSource source;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    @Column(name = "notes")
    private String notes;
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<Attachment> attachments = new ArrayList<>();
    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    private List<EmailIngestionLog> ingestionLogs = new ArrayList<>();

    public Bill() {}

    public Long getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BillCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(BillCurrency currency) {
        this.currency = currency;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public BillSource getSource() {
        return source;
    }

    public void setSource(BillSource source) {
        this.source = source;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
        attachment.setBill(this);
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void addIngestionLog(EmailIngestionLog ingestionLog) {
        ingestionLogs.add(ingestionLog);
        ingestionLog.setBill(this);
    }

    public List<EmailIngestionLog> getIngestionLogs() {
        return ingestionLogs;
    }
}
