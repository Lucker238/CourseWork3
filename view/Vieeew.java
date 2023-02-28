package view;

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
        System.out.println("Список комманд:\nCREATE - Создать игрушку\nUPDATE - Изменить вероятность выпадения игрушки\nDELETE - Удалить игрушку\nPLAY - Попробовать выйграть игрушку\nSHOW - Показать список доступных для выйгрыша игрушек\nWIN - Показать список выйгранных игрушек\nCLEAN - очистить список выйгранных игрушек\nCLOSE или EXIT - выйти из магазина");
        while(true){
            String command = prompt("Введите команду: ");
            commands = Commands.valueOf(command.toUpperCase());
            String messageForLog = command + "\n";
            if(commands == Commands.EXIT || commands == Commands.CLOSE) {
                return;
            }
            try{
                switch(commands) {
                    case NONE:
                        System.out.println("Ничего не произошло");
                        break;
                    case CREATE:
                        String name = prompt("Название игрушки: ");
                        String amount = prompt("Количество: ");
                        String chance = prompt("Процент выпадения: ");
                        int am = checkNumber(amount);
                        int ch = checkPercent(chance);
                        Toy toy = new Toy(name, am, ch);
                        controller.saveToy(toy);
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
        }
        return 0;
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
// NONE,
// CREATE,
// UPDATE,
// DELETE,
// PLAY,
// SHOW,
// WIN,
// CLOSE,
// EXIT