package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

public class ToyShop {
    private Mapper mapper = new Mapper();
    private Folder folder;

    public ToyShop(Folder folder) {
        this.folder = folder;
    }

    public List<Toy> getAllToys() {
        List<String> lines = folder.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for(String line: lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    public int addToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int maxID = 0;
        for (Toy toy2 : toys) {
            int id = toy2.getId();
            if(maxID < id) {
                maxID = id;
            }
        }
        int newID = maxID + 1;
        toy.setId(newID);
        toys.add(toy);
        writeDown(toys);
        return newID;
    }

    public void changePossib(int ID, int newCh) {
        List<Toy> toys = getAllToys();
        for (Toy toy : toys) {   
            if(toy.getId() == ID) {
                toy.setChance(newCh);
                break;
            }
        }
        writeDown(toys);
    }

    public void deleteToy(int ID) {
        List<Toy> toys = getAllToys();
        for (Toy toy : toys) {   
            if(toy.getId() == ID) {
                toys.remove(toy);
                break;
            }
        }
        editToyList(toys);
    }


    private void writeDown (List<Toy> toys) {
        List<String> lines = new ArrayList<>();
        for (Toy toy : toys) {
            lines.add(mapper.map(toy));
        }
        folder.saveAllLines(lines);
    }


   private void editToyList (List<Toy> toys) {
    int id = 1;
    for (Toy toy : toys) {
        toy.setId(id);
        id += 1;
    }
    writeDown(toys);
    }

    public int attempt() {
        List<Toy> toys = getAllToys();
        for (Toy toy : toys) {
            int n = toy.getAmount();
            while(n > 0) { 
                int rnd = new Random().nextInt(99) + 1;
                if (toy.getChance() >= rnd){
                    return toy.getId();
                }
                n -= 1;
            }
        }
        return -1;
    }

    public String winToy(int ID) {
        List<Toy> toys = getAllToys();
        for (Toy toy : toys) {   
            if(toy.getId() == ID) {
                String wName = toy.getName();
                toy.setAmount(toy.getAmount() - 1);
                if (toy.getAmount() == 0) {
                    deleteToy(ID);
                }
                else {
                    writeDown(toys);
                }
                folder.saveWinToy(wName, "win.txt");
                return wName;
            }
        }
        return "None";
    }

    public Map<String, Integer> showWinToys(){
        List<String> prices = folder.showWinPrices("win.txt");
        Map<String, Integer> counter = new HashMap<>();
        for (String string : prices) {
            int newVal = counter.getOrDefault(string, 0) + 1;
            counter.put(string, newVal);
        }
        return counter;
    }
}

