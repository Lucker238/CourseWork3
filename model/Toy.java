package model;
/**
 * Toy
 */
public class Toy {
    private int id;
    private String name;
    private int amount;
    private int chance;

    public Toy(String name, int amount, int chance) {
        this.name = name;
        this.amount = amount;
        this.chance = chance;
    }


    public Toy(int id, String name, int amount, int chance) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.chance = chance;
    }

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getChance() {
        return chance;
    }
    public void setChance(int chance) {
        this.chance = chance;
    }
    
}