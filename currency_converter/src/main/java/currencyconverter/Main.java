package currencyconverter;

import currencyconverter.ui.UserMenu;

public class Main {

    public static void main(String[] args) {
        try {
            UserMenu.printMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
