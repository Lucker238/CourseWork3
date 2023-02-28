package controllers;

import java.util.List;

import model.Toy;
import model.ToyShop;

public class Controller {
    private ToyShop toyshop;

    public Controller(ToyShop toyshop) {
        this.toyshop = toyshop;
    }
    
    public void saveToy(Toy toy) {
        toyshop.addToy(toy);
    }

    public List<Toy> readAllToys() {
        List<Toy> toys = toyshop.getAllToys();
        return toys;
    }

    public void changeChance(int ID, int newCh) throws Exception{
        checkIDPresence(ID);
        List<Toy> toys = toyshop.getAllToys();
        for (Toy toy : toys) {   
            if(toy.getId() == ID) {
                toy.setChance(newCh);
                break;
            }
        }
    }

    public void checkIDPresence (int id) throws Exception{
        List<Toy> toys = toyshop.getAllToys();
        for (Toy toy : toys) {
            if(toy.getId() == id){
                return;
            }
        }
        throw new Exception("Нет такого ID!");
    }

}



