public class StockService {

    private int quantity;

    private static StockService stockService;

    private StockService(){}

    //* Thread Safe
    public static StockService getInstance(){
        if (stockService == null){
            synchronized (StockService.class){
                if (stockService == null)
                    stockService = new StockService();
            }
        }
        return stockService;
    }

    public synchronized void increment(int amount){
        quantity += amount;
    }

    public synchronized void decrement(int amount){
        if (quantity >= amount)
            quantity -= amount;
        else
            quantity = 0;
    }

    public synchronized int getQuantity(){
        return quantity;
    }

}
