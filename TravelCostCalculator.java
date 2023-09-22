
//EG.2020.3891

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;






public class TravelCostCalculator {
    static Map<String, Double> a = new HashMap<>();
    static Map<String, Double> b = new HashMap<>();
    static Map<String, Double> c = new HashMap<>();

    static void l1(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i; 
        while ((i = reader.readLine()) != null) {
            String[] p = i.split(",");
            a.put(p[0].toUpperCase(), Double.parseDouble(p[1]));
        }
    }

    static void l2(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i;
        while ((i = reader.readLine()) != null) {
            String[] p = i.split(",");
            b.put(p[0].toUpperCase(), Double.parseDouble(p[1]));
        }
    }

    static void l3(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i;
        while ((i = reader.readLine()) != null) {
            String[] p = i.split(",");
            c.put(p[0].toUpperCase(), Double.parseDouble(p[1]));
        }
    }

    public static void main(String[] args) {
        try {
            l1("data/hotel_rates.csv");
            l2("data/exchange_rates.csv");
            l3("data/flight_costs.csv");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your destination: ");//asking the user to enter the destination
            String destination = reader.readLine().toUpperCase();//getting the entered value

            double flightCost;//variable to store flight cost
            double hotelCost; //variable to store hotel cost

             flightCost = c.getOrDefault(destination, 0.0);//to get flight cost
             hotelCost = a.getOrDefault(destination, 0.0);//to get hotel cost



            System.out.print("Enter your stay duration in days: ");

            int stayDuration;//variable to store duration that we stay in a place

            stayDuration = Integer.parseInt(reader.readLine());
            hotelCost *= stayDuration;

            double totalCostInUsd;//variable to store total cost in usd

            totalCostInUsd = flightCost + hotelCost;// getting sum of the all costs

            System.out.printf("Flight cost: USD %.2f\n", flightCost);//outputting flight cost in usd
            System.out.printf("Hotel cost (%d days): USD %.2f\n", stayDuration, hotelCost);//outputting hotel and stay duration cost in usd
            System.out.printf("Total: USD %.2f\n", totalCostInUsd);//outputting overall cost in usd

            String[] availableCurrencies = b.keySet().toArray(new String[0]);
            System.out.print("Select your currency for final price estimation(" + String.join(", ", available_currencies) + "): ");
            String selected_currency = reader.readLine();

            double final_price_local_currency = totalCostInUsd * b.get(selected_currency);

            System.out.printf("Total in %s: %.2f\n", selected_currency, final_price_local_currency);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
