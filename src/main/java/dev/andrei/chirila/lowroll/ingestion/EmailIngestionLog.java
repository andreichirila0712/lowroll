package dev.andrei.chirila.lowroll.ingestion;

import dev.andrei.chirila.lowroll.bill.Bill;
import dev.andrei.chirila.lowroll.provider.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_ingestion_logs")
public class EmailIngestionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "gmail_message_id")
    private Long gmailMessageId;
    @Column(name = "received_at")
    private LocalDateTime receivedAt;
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    @Column(name = "status")
    private EmailIngestionLogStatus status;
    private Provider matchedProviderId;
    private Bill matchedBillId;
    @Column(name = "raw_sender")
    private String rawSender;
    @Column(name = "raw_subject")
    private String rawSubject;

    public EmailIngestionLog() {}

    public Long getId() {
        return id;
    }

    public Long getGmailMessageId() {
        return gmailMessageId;
    }

    public void setGmailMessageId(Long gmailMessageId) {
        this.gmailMessageId = gmailMessageId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public EmailIngestionLogStatus getStatus() {
        return status;
    }

    public void setStatus(EmailIngestionLogStatus status) {
        this.status = status;
    }

    public Provider getMatchedProviderId() {
        return matchedProviderId;
    }

    public void setMatchedProviderId(Provider matchedProviderId) {
        this.matchedProviderId = matchedProviderId;
    }

    public Bill getMatchedBillId() {
        return matchedBillId;
    }

    public void setMatchedBillId(Bill matchedBillId) {
        this.matchedBillId = matchedBillId;
    }

    public String getRawSender() {
        return rawSender;
    }

    public void setRawSender(String rawSender) {
        this.rawSender = rawSender;
    }

    public String getRawSubject() {
        return rawSubject;
    }

    public void setRawSubject(String rawSubject) {
        this.rawSubject = rawSubject;
    }
}
