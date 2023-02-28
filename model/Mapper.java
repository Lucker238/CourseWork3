package model;

public class Mapper {
    public String map (Toy toy) {
        return String.format("%d,%s,%d,%d", toy.getId(), toy.getName(), toy.getAmount(), toy.getChance());
    }

    public Toy map(String str) {
        String[] strings = str.split(",");
        return new Toy(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3]));
    }
}

