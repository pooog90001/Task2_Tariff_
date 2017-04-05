package by.epam.vladlitvin.entity.price;

import by.epam.vladlitvin.exception.ValueLessZeroException;

import java.math.BigDecimal;
import java.math.MathContext;

import static by.epam.vladlitvin.action.PriceRounder.roundBySix;

/**
 * Created by vlad_ on 3/25/2017.
 */
public class SMSPrice {
    private BigDecimal withinNetwork;
    private BigDecimal otherNetwork;
    private BigDecimal otherCountry;

    public SMSPrice(BigDecimal withinNetwork,
                    BigDecimal otherNetwork,
                    BigDecimal otherCountry) {

        this.withinNetwork = roundBySix(withinNetwork);
        this.otherNetwork = roundBySix(otherNetwork);
        this.otherCountry = roundBySix(otherCountry);

    }

    public BigDecimal getWithinNetwork() {
        return withinNetwork;
    }

    public BigDecimal getOtherNetwork() {
        return otherNetwork;
    }

    public BigDecimal getOtherCountry() {
        return otherCountry;
    }

    @Override
    public String toString() {
        return "SMSPrice {\n" +
                " withinNetwork = " + withinNetwork +
                "ruble,\n otherNetwork = " + otherNetwork +
                "ruble,\n otherCountry = " + otherCountry +
                "ruble\n }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SMSPrice)) {
            return false;
        }

        SMSPrice smsPrice = (SMSPrice) o;

        if (withinNetwork != null ?
                !withinNetwork.equals(smsPrice.withinNetwork) :
                smsPrice.withinNetwork != null) {
            return false;
        }
        if (otherNetwork != null ?
                !otherNetwork.equals(smsPrice.otherNetwork) :
                smsPrice.otherNetwork != null) {
            return false;
        }
        return otherCountry != null ?
                otherCountry.equals(smsPrice.otherCountry) :
                smsPrice.otherCountry == null;
    }

    @Override
    public int hashCode() {
        int result = withinNetwork != null ?
                withinNetwork.hashCode() : 0;
        result = 31 * result + (otherNetwork != null ?
                otherNetwork.hashCode() : 0);
        result = 31 * result + (otherCountry != null ?
                otherCountry.hashCode() : 0);
        return result;
    }
}
