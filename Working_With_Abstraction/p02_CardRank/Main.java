package CardRank;

public class Main {
    public static void main(String[] args) {
        CardRank[] cardRanks = CardRank.values();
        System.out.println("Card Ranks:");
        for (CardRank cardRank : cardRanks) {
            int ordinal = cardRank.ordinal();
            String name = cardRank.name();
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    ordinal, name);
        }
    }
}
