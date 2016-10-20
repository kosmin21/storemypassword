package me.cosmin.storemypassword.Helpers;

public class CardHelper {

    public static String formatCardNumber(String cardNumber) {
        return cardNumber.substring(0,4) +
                " " + cardNumber.substring(4,8) +
                " " + cardNumber.substring(8, 12) +
                " " + cardNumber.substring(12, 16);
    }

    public static String formatSortCode(String sortCode) {
        return sortCode.substring(0,2) +
                "-" + sortCode.substring(2,4) +
                "-" + sortCode.substring(4,6);
    }

    public static String formatDate(String date) {
        return date.substring(5,7) + "/" + date.substring(0,4);
    }
}
