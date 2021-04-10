package CardsWithPower;

public class Card {
    private RankPower cardRank;
    private SuitPower cardSuit;
    private int power;

    public Card(RankPower cardRank, SuitPower cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
        this.power = calculatePower();
    }
    public int calculatePower(){
        this.power = cardRank.getValue() + cardSuit.getValue();
        return this.power;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d%n",cardRank.name(), cardSuit.name(),this.power);
    }
}
