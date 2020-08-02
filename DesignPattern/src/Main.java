public class Main {

    public static void main(String[] args) {

        StockService producer = StockService.getInstance();
        producer.increment(50);
        System.out.println(producer.getQuantity());

        StockService consumer = StockService.getInstance();
        consumer.decrement(10);
        System.out.println(consumer.getQuantity());
        consumer.decrement(50);
        System.out.println(consumer.getQuantity());

    }

}



