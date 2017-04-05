package by.epam.vladlitvin.entity.price;

import java.math.BigDecimal;

import static by.epam.vladlitvin.action.PriceRounder.roundBySix;

/**
 * Created by vlad_ on 3/25/2017.
 */
public class InternetPrice {
    private BigDecimal internet;

    public InternetPrice(BigDecimal internet) {
        this.internet = roundBySix(internet);
    }

    public BigDecimal getInternet() {
        return internet;
    }


    @Override
    public String toString() {
        return "InternetPrice {\n" +
                " internet = " + internet +
                "ruble \n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InternetPrice)) {
            return false;
        }

        InternetPrice that = (InternetPrice) o;

        return (internet != null ?
                internet.equals(that.internet) :
                that.internet == null);
    }

    @Override
    public int hashCode() {
        return internet != null ? internet.hashCode() : 0;
    }
}
