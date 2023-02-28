import javax.swing.text.View;

import controllers.Controller;
import model.Folder;
import model.ToyShop;
import view.Vieeew;

public class Main {
    public static void main(String[] args) {
    Folder folder = new Folder("ToyShop.txt");
    ToyShop toyshop = new ToyShop(folder);
    Controller controller = new Controller(toyshop);
    Vieeew vi = new Vieeew(controller);
    vi.enterToyShop();
    }
}
