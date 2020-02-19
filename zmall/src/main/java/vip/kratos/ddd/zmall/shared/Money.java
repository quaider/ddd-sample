package vip.kratos.ddd.zmall.shared;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

@Value
public final class Money implements Serializable {
    // Currency.getInstance("CNY");
    public static final Currency DEFAULT_CURRENCY = Currency.getInstance(Locale.CHINA);
    public static final Money ZERO = new Money();

    // 面值
    private BigDecimal denomination;
    // 货币编号
    private String currencyCode;

    public Money() {
        this(0, DEFAULT_CURRENCY);
    }

    public Money(BigDecimal denomination, Currency currency) {
        this(denomination, currency.getCurrencyCode());
    }

    private Money(BigDecimal denomination, String currencyCode) {
        this.denomination = denomination.setScale(2, RoundingMode.HALF_EVEN);
        this.currencyCode = currencyCode;
    }

    public Money(BigDecimal denomination) {
        this(denomination, DEFAULT_CURRENCY);
        if (denomination.doubleValue() < 0) throw new IllegalArgumentException("");
    }

    public Money(double denomination, Currency currency) {
        this(new BigDecimal(denomination), currency.getCurrencyCode());
    }

    public Money(double denomination, String currencyCode) {
        this(new BigDecimal(denomination), currencyCode);
    }

    public Money(double denomination) {
        this(denomination, DEFAULT_CURRENCY);
        if (denomination < 0) throw new IllegalArgumentException("");
    }

    public Money multiplyBy(double multiplier) {
        return multiplyBy(parseDoubleToBC(multiplier));
    }

    public Money multiplyBy(BigDecimal multiplier) {
        return new Money(denomination.multiply(multiplier), currencyCode);
    }

    public Money add(double money) {
        return add(BigDecimal.valueOf(money));
    }

    public Money add(BigDecimal money) {
        return add(new Money(money));
    }

    public Money add(Money money) {
        if (!compatibleCurrency(money)) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        return new Money(denomination.add(money.denomination), determineCurrencyCode(money));
    }

    public Money subtract(double money) {
        return subtract(BigDecimal.valueOf(money));
    }

    public Money subtract(BigDecimal money) {
        return subtract(new Money(money));
    }

    public Money subtract(Money money) {
        if (!compatibleCurrency(money))
            throw new IllegalArgumentException("Currency mismatch");

        return new Money(denomination.subtract(money.denomination), determineCurrencyCode(money));
    }

    public Currency getCurrency() {
        return Currency.getInstance(currencyCode);
    }

    public boolean gt(Money other) {
        return denomination.compareTo(other.denomination) > 0;
    }

    public boolean lt(Money other) {
        return denomination.compareTo(other.denomination) < 0;
    }

    public boolean lte(Money other) {
        return denomination.compareTo(other.denomination) <= 0;
    }

    private boolean compatibleCurrency(Money money) {
        return isZero(denomination) || isZero(money.denomination) || currencyCode.equals(money.getCurrencyCode());
    }

    private boolean isZero(BigDecimal testedValue) {
        return BigDecimal.ZERO.compareTo(testedValue) == 0;
    }

    private Currency determineCurrencyCode(Money otherMoney) {
        String resultingCurrentCode = isZero(denomination) ? otherMoney.currencyCode : currencyCode;
        return Currency.getInstance(resultingCurrentCode);
    }

    private BigDecimal parseDoubleToBC(double value) {
        return new BigDecimal(String.valueOf(value));
    }
}
