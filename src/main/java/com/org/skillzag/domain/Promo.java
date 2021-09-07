package com.org.skillzag.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Promo.
 */
@Entity
@Table(name = "promo")
public class Promo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "promo_code")
    private String promoCode;

    @Column(name = "valid_from")
    private Instant validFrom;

    @Column(name = "valid_to")
    private Instant validTo;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Instant creationDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public Promo cartId(Long cartId) {
        this.cartId = cartId;
        return this;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public Promo promoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public Promo validFrom(Instant validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public Promo validTo(Instant validTo) {
        this.validTo = validTo;
        return this;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Promo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Promo creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Promo)) {
            return false;
        }
        return id != null && id.equals(((Promo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Promo{" +
            "id=" + getId() +
            ", cartId=" + getCartId() +
            ", promoCode='" + getPromoCode() + "'" +
            ", validFrom='" + getValidFrom() + "'" +
            ", validTo='" + getValidTo() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
