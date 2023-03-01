package view;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controllers.Controller;
import model.Toy;

public class Vieeew {
    private Controller controller;

    public Vieeew(Controller controller) {
        this.controller = controller;
    }
    
    public void enterToyShop() {
        Commands commands = Commands.NONE;
        System.out.println("Список комманд:\nCREATE - Создать игрушку\nUPDATE - Изменить вероятность выпадения игрушки\nDELETE - Удалить игрушку\nPLAY - Попробовать выйграть игрушку\nSHOW - Показать список доступных для выйгрыша игрушек\nWIN - Показать список выйгранных игрушек\nHELP - Показать список доступных комманд\nCLOSE или EXIT - выйти из магазина");
        while(true){
            try{
            String command = prompt("Введите команду: ");
            commands = Commands.valueOf(command.toUpperCase());
            if(commands == Commands.EXIT || commands == Commands.CLOSE) {
                return;
            }
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели что то не то");
            }
            try{
                switch(commands) {
                    case NONE:
                        System.out.println("Ничего не произошло");
                        break;
                    case CREATE:
                        String name = prompt("Название игрушки: ");
                        String amount = prompt("Количество: ");
                        int am = checkNumber(amount);
                        String chance = prompt("Процент выпадения: ");
                        int ch = checkPercent(chance);
                        Toy toy = new Toy(name, am, ch);
                        controller.saveToy(toy);
                        System.out.println("Игрушка успешно добавлена в магазин");
                        break;
                    case UPDATE:
                        String upId = prompt("Введите ID игрушки для изменения: ");
                        controller.checkIDPresence(upId);
                        String newch = prompt("Введите новую вероятность выпадения игрушки: ");
                        int newchint = checkPercent(newch);
                        controller.changeChance(Integer.parseInt(upId), newchint);
                        System.out.println("Вероятность успешно изменена");
                        break;
                    case DELETE:
                        String delId = prompt("Введите ID игрушки для удаления: ");
                        controller.checkIDPresence(delId);
                        controller.deleteToy(Integer.parseInt(delId));
                        System.out.println("Игрушка успешно удалена из магазина");
                        break;
                    case PLAY:
                        String at = controller.attempt();
                        System.out.println(at);
                        break;
                    case SHOW:
                        List<Toy> toyList = controller.readAllToys();
                        for (Toy toy2 : toyList) {
                            System.out.println("Игрушка: "+toy2.getName()+"; В количестве: "+toy2.getAmount()+" шт; Процент выпадания: "+toy2.getChance()+"%");
                        }
                        break;
                    case WIN:
                        Map<String, Integer> out = controller.showWinPrices();
                        out.forEach((key, val) -> System.out.println("Вы выйграли " + key + " в количестве - " + val + "шт"));
                        break;
                    case HELP:
                        System.out.println("Список комманд:\nCREATE - Создать игрушку\nUPDATE - Изменить вероятность выпадения игрушки\nDELETE - Удалить игрушку\nPLAY - Попробовать выйграть игрушку\nSHOW - Показать список доступных для выйгрыша игрушек\nWIN - Показать список выйгранных игрушек\nHELP - Показать список доступных комманд\nCLOSE или EXIT - выйти из магазина");
                        break;
                    }
            } catch (Exception ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            }
    }
}
    
    
    private String prompt(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);
        return input.nextLine();
    }

    private int checkNumber(String input) throws Exception{
        try {
            int result = Integer.parseInt(input) + 1 -1;
            if (result > 0) {
                return result;
            }
            else {
                throw new Exception("Число должно быть положительным");
            }
        }
        catch (Exception ex) {
            System.out.println("Вы ввели не число!");
            throw ex;
        }
        
    }

    private int checkPercent(String input) throws Exception{
        int result = checkNumber(input);
        if (result > 0 & result <= 100) {
            return result;
        }
        else {
            throw new Exception("Число должно быть от 1 до 100");
        }
    }

}
