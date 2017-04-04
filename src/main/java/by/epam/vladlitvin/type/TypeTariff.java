package by.epam.vladlitvin.type;

/**
 * Created by vlad_ on 3/25/2017.
 */
public enum TypeTariff {
    FOR_CALLS,
    FOR_INTERNET,
    FOR_SMS,;

    @Override
    public String toString() {
        return this.name();
    }
}
