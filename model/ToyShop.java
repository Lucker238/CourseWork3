package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void deleteToy(Toy toy) {
        List<Toy> toys = getAllToys();
        Toy toyForDel = toys.stream().filter(i -> i.getId() == toy.getId()).findFirst().get();
        toys.remove(toyForDel);
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

            while(toy.getAmount()>0) { 
                int rnd = new Random().nextInt(99) + 1;
                if (toy.getChance() <= rnd){
                    return toy.getId();
                }
                toy.setAmount(toy.getAmount()-1);
            }
        }
        return -1;
    }

    private void winToy(Toy toy) {
        List<Toy> toys = getAllToys();
        Toy winingToy = toys.stream().filter(i -> i.getId() == toy.getId()).findFirst().get();
        winingToy.setAmount(winingToy.getAmount() - 1);
        if (winingToy.getAmount() == 0) {
            deleteToy(toy);
        }
        else {
            writeDown(toys);
        }
    }

}

