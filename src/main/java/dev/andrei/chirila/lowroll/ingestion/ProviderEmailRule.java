package dev.andrei.chirila.lowroll.ingestion;

import dev.andrei.chirila.lowroll.provider.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "provider_email_rules")
public class ProviderEmailRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Provider providerId;
    @Column(name = "sender_pattern")
    private String senderPattern;
    @Column(name = "requires_attachment")
    private Boolean requiresAttachment;

    public ProviderEmailRule() {}

    public Long getId() {
        return id;
    }

    public Provider getProviderId() {
        return providerId;
    }

    public void setProviderId(Provider providerId) {
        this.providerId = providerId;
    }

    public String getSenderPattern() {
        return senderPattern;
    }

    public void setSenderPattern(String senderPattern) {
        this.senderPattern = senderPattern;
    }

    public Boolean getRequiresAttachment() {
        return requiresAttachment;
    }

    public void setRequiresAttachment(Boolean requiresAttachment) {
        this.requiresAttachment = requiresAttachment;
    }
}
