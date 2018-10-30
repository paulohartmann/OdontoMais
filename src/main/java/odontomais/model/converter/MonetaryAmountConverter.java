package odontomais.model.converter;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryQuery;
import java.math.BigDecimal;
import java.util.Optional;

/*
 * Author: phlab
 * Date: 16/10/18
 */
public class MonetaryAmountConverter {

    private static final CurrencyUnit CURRENCY = Monetary.getCurrency("BRL");

    private static final MonetaryQuery<BigDecimal> EXTRACT_BIG_DECIMAL = (m) -> m.getNumber()
            .numberValue(BigDecimal.class);

    public BigDecimal convertToBigDecimal(MonetaryAmount attribute) {
        return Optional.ofNullable(attribute).orElse(FastMoney
                .zero(CURRENCY))
                .query(EXTRACT_BIG_DECIMAL);
    }

    public static MonetaryAmount convertToMonetaryAmount(BigDecimal dbData) {
        return Money.of(dbData, CURRENCY);
    }
}
