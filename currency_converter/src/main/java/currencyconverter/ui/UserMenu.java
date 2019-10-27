package currencyconverter.ui;

import currencyconverter.provider.NDataProvider;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public abstract class UserMenu {

    public static void printMenu() throws IOException, SAXException, ParserConfigurationException {
        NDataProvider provider = new NDataProvider();

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the currency code you have");
            String fromCurrentyCode = scan.nextLine();
            System.out.println("Enter the currency code you want to exchange for");
            String toCurrencyCode = scan.nextLine();
            System.out.println("How much currency you have");
            String value = scan.nextLine();

            Optional<BigDecimal> result = UserCalculator.calc(fromCurrentyCode, toCurrencyCode, value, provider);

            if(result.isPresent()) {
                System.out.println("You would receive " + result.get().toString() + " " + toCurrencyCode);
            } else {
                System.out.println("Doesn't exist currency");
            }
        }


    }

}
