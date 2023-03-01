package controllers;

import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.Other;

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
        toyshop.changePossib(ID, newCh);
    }

    public void deleteToy(int ID) {
        toyshop.deleteToy(ID);
    }

    public String attempt() {
        int result = toyshop.attempt();
        String output = "";
        if (result == -1) {
            output += "К сожалению вы ничего не выйграли";
        }
        else {
            output += "Поздравляем, вы выйграли - " + toyshop.winToy(result);
        }
        return output;
    }

    public void checkIDPresence (String id) throws Exception{
        List<Toy> toys = toyshop.getAllToys();
        int intid = Integer.parseInt(id);
        for (Toy toy : toys) {
            if(toy.getId() == intid){
                return;
            }
        }
        throw new Exception("Нет такого ID!");
    }

    public Map<String, Integer> showWinPrices() {
        Map<String, Integer> result = toyshop.showWinToys();
        return result;
    }

}



