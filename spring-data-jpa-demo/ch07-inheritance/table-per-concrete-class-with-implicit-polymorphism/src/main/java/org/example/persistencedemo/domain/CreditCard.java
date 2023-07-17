package org.example.persistencedemo.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD")
@AttributeOverride(
        name = "owner",
        column = @Column(name = "CC_OWNER")
)
public class CreditCard extends BillingDetails {

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "EXP_MONTH")
    private String expMonth;

    @Column(name = "EXP_YEAR")
    private String expYear;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }
}
