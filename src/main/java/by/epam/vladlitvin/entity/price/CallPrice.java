package by.epam.vladlitvin.entity.price;

import by.epam.vladlitvin.exception.ValueLessZeroException;

import java.math.BigDecimal;
import java.math.MathContext;

import static by.epam.vladlitvin.action.PriceRounder.roundBySix;

/**
 * Created by vlad_ on 3/25/2017.
 */
public class CallPrice {
    private BigDecimal withinNetwork;
    private BigDecimal otherNetwork;
    private BigDecimal otherCountry;

    public CallPrice(BigDecimal withinNetwork,
                     BigDecimal otherNetwork,
                     BigDecimal otherCountry) throws ValueLessZeroException {

        if ((withinNetwork.compareTo(new BigDecimal("0")) >= 0) &&
                (otherNetwork.compareTo(new BigDecimal("0")) >= 0) &&
                (otherCountry.compareTo(new BigDecimal("0")) >= 0)) {
            this.withinNetwork = roundBySix(withinNetwork);
            this.otherNetwork = roundBySix(otherNetwork); // так можно делать?
            this.otherCountry = roundBySix(otherCountry);
        } else {
            throw new ValueLessZeroException();
        }

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
        return "CallPrice{" +
                "withinNetwork=" + withinNetwork +
                ", otherNetwork=" + otherNetwork +
                ", otherCountry=" + otherCountry +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CallPrice)) {
            return false;
        }
        CallPrice callPrice = (CallPrice) o;

        if (withinNetwork != null ?
                !withinNetwork.equals(callPrice.withinNetwork) :
                callPrice.withinNetwork != null) {
            return false;
        }
        if (otherNetwork != null ?
                !otherNetwork.equals(callPrice.otherNetwork) :
                callPrice.otherNetwork != null) {
            return false;
        }
        return (otherCountry != null ?
                otherCountry.equals(callPrice.otherCountry) :
                callPrice.otherCountry == null);
    }

    @Override
    public int hashCode() {
        int result = withinNetwork != null ? withinNetwork.hashCode() : 0;
        result = 31 * result + (otherNetwork != null ?
                otherNetwork.hashCode() : 0);
        result = 31 * result + (otherCountry != null ?
                otherCountry.hashCode() : 0);
        return result;
    }
}
