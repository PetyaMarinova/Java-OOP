package workingWithAbstraction.greedyTimes;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentTotalQuantity;
    private long gold;
    private Map<String, Long> gem;
    private Map<String, Long> cash;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.gem = new HashMap<>();
        this.cash = new HashMap<>();
    }

    public void addGold(long amount) {
        if (currentTotalQuantity + amount <= capacity) {
            this.gold += amount;
            this.currentTotalQuantity += amount;
        }
    }

    public void addGem(String nameItem, long amount) {
        if (currentTotalQuantity + amount <= this.capacity && getGemAmount() + amount <= this.gold) {
            this.gem.putIfAbsent(nameItem, (long) 0);
            this.gem.put(nameItem, this.gem.get(nameItem) + amount);
            currentTotalQuantity += amount;
        }
    }

    public void addCash(String currencyName, long amount) {
        if (currentTotalQuantity + amount <= this.capacity && getCashAmount() + amount <= getGemAmount()) {
            this.cash.putIfAbsent(currencyName, (long) 0);
            this.cash.put(currencyName, this.cash.get(currencyName) + amount);
            currentTotalQuantity += amount;
        }
    }

    public long getGemAmount() {
        return this.gem.values().stream().mapToLong(e -> e).sum();
    }

    public long getCashAmount() {
        return this.cash.values().stream().mapToLong(e->e).sum();
    }

    public void printResult(){
        StringBuilder sb = new StringBuilder();
        if (this.gold >= 0){
            sb.append(String.format("<Gold> $%d",this.gold)).append(System.lineSeparator());
            sb.append(String.format("##Gold - %d",this.gold)).append(System.lineSeparator());
        }
        if (!this.gem.isEmpty()){
            sb.append(String.format("<Gem> $%d",getGemAmount())).append(System.lineSeparator());
            this.gem.entrySet().stream().sorted((g1, g2) -> {
                int result = g2.getKey().compareTo(g1.getKey());
                if (result == 0){
                    result = g1.getValue().compareTo(g2.getValue());
                }
                return result;
            }).forEach(e -> sb.append(String.format("##%s - %d",e.getKey(),e.getValue())).append(System.lineSeparator()));
        }
        if (!this.cash.isEmpty()){
            sb.append(String.format("<Cash> $%d",getCashAmount())).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted((c1, c2) -> {
                int result = c2.getKey().compareTo(c1.getKey());
                if (result == 0){
                    result = c1.getValue().compareTo(c2.getValue());
                }
                return result;
            }).forEach(e -> sb.append(String.format("##%s - %d",e.getKey(),e.getValue())).append(System.lineSeparator()));
        }
        System.out.println(sb.toString().trim());
    }

}
