package dev.andrei.chirila.lowroll.ingestion;

import dev.andrei.chirila.lowroll.provider.Provider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "provider_email_rules")
public class ProviderEmailRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @Column(name = "sender_pattern")
    private String senderPattern;
    @Column(name = "requires_attachment")
    private Boolean requiresAttachment;

    public ProviderEmailRule() {}

    public Long getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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
