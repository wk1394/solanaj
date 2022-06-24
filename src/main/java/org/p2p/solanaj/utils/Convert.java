package org.p2p.solanaj.utils;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Description:
 * 数字货币单位精度转换工具
 * Date: 2019/4/7 16:18
 * Created by slipshield
 */
public class Convert {

    /**
     * 标准单位到指定精度单位
     *
     * @param value
     * @param decimals
     * @return
     */
    public static BigInteger toDecimal(String value, Integer decimals) {
        return toDecimal(new BigDecimal(value), decimals);
    }

    public static BigInteger toDecimal(BigDecimal value, Integer decimals) {
        return value.multiply(new BigDecimal(10).pow(decimals)).toBigInteger();
    }

    public static BigInteger toDecimal(String value, Unit unit) {
        return toDecimal(value, unit.getDecimal());
    }

    public static BigInteger toDecimal(BigDecimal value, Unit unit) {
        return toDecimal(value, unit.getDecimal());
    }

    /**
     * 指定精度到标准单位
     *
     * @param value
     * @param decimals
     * @return
     */
    public static BigDecimal fromDecimal(String value, Integer decimals) {
        return fromDecimal(new BigDecimal(value), decimals);
    }

    public static BigDecimal fromDecimal(String value, Unit unit) {
        return fromDecimal(value, unit.getDecimal());
    }

    public static BigDecimal fromDecimal(BigDecimal value, Unit unit) {
        return fromDecimal(value, unit.getDecimal());
    }

    public static BigDecimal fromDecimal(BigDecimal value, Integer decimals) {
        return value.divide(new BigDecimal(10).pow(decimals));
    }

    public static BigDecimal fromDecimal(BigInteger value, Unit unit) {
        return fromDecimal(value, unit.getDecimal());
    }

    public static BigDecimal fromDecimal(BigInteger value, Integer decimals) {
        return fromDecimal(value.toString(), decimals);
    }

    /**
     * 根据精度获取Unit对象
     *
     * @param decimal
     * @return
     */
    public static Unit getUnit(Integer decimal) {
        for (Unit u : Unit.values()) {
            if (u.getDecimal() == decimal) {
                return u;
            }
        }
        return null;
    }

    public enum Unit {
        ZERO("ZERO", 0),
        ONE("ONE", 1),
        TWO("TWO", 2),
        THREE("THREE", 3),
        FOUR("FOUR", 4),
        FIVE("FIVE", 5),
        SIX("SIX", 6),
        SEVEN("SEVEN", 7),
        EIGHT("EIGHT", 8),
        NINE("NINE", 9),
        TEN("TEN", 10),
        ELEVEN("ELEVEN", 11),
        TWELVE("TWELVE", 12),
        THIRTEEN("THIRTEEN", 13),
        FOURTEEN("FOURTEEN", 14),
        FIFTEEN("FIFTEEN", 15),
        SIXTEEN("SIXTEEN", 16),
        SEVENTEEN("SEVENTEEN", 17),
        EIGHTEEN("EIGHTEEN", 18),
        NINETEEN("NINETEEN", 19),
        TWENTY("TWENTY", 20),


        GWEI("GWEI", 9),
        ETHER("ETHER", 18);


        Unit(String name, Integer decimal) {
            this.name = name;
            this.decimal = decimal;
        }

        private String name;
        private Integer decimal;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getDecimal() {
            return decimal;
        }

        public void setDecimal(Integer decimal) {
            this.decimal = decimal;
        }


    }
}
