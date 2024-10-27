import java.util.*;

class Stock {
    private String symbol;
    private String name;
    private double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Portfolio {
    private Map<String, Integer> stocks; // stock symbol -> quantity

    public Portfolio() {
        stocks = new HashMap<>();
    }

    public void buyStock(String symbol, int quantity) {
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
    }

    public void sellStock(String symbol, int quantity) {
        if (stocks.containsKey(symbol)) {
            int currentQuantity = stocks.get(symbol);
            if (currentQuantity >= quantity) {
                if (currentQuantity == quantity) {
                    stocks.remove(symbol);
                } else {
                    stocks.put(symbol, currentQuantity - quantity);
                }
            } else {
                System.out.println("Not enough shares to sell.");
            }
        } else {
            System.out.println("Stock not found in portfolio.");
        }
    }

    public void printPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class StockMarket {
    private Map<String, Stock> availableStocks;

    public StockMarket() {
        availableStocks = new HashMap<>();
        // Sample stocks
        availableStocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.00));
        availableStocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2800.00));
        availableStocks.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 3300.00));
    }

    public void displayMarketData() {
        System.out.println("Available Stocks:");
        for (Stock stock : availableStocks.values()) {
            System.out.printf("%s (%s) - $%.2f%n", stock.getName(), stock.getSymbol(), stock.getPrice());
        }
    }

    public Stock getStock(String symbol) {
        return availableStocks.get(symbol);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        Portfolio portfolio = new Portfolio();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    market.displayMarketData();
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();
                    Stock stockToBuy = market.getStock(buySymbol);
                    if (stockToBuy != null) {
                        portfolio.buyStock(buySymbol, buyQuantity);
                        System.out.printf("Bought %d shares of %s.%n", buyQuantity, stockToBuy.getName());
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();
                    portfolio.sellStock(sellSymbol, sellQuantity);
                    break;
                case 4:
                    portfolio.printPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
