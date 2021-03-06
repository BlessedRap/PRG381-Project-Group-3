package implementation.layer;
import java.util.ArrayList;
import java.util.Scanner;

import implementation.layer.MenuItem.MenuItemReturnValue;

/**
 * Menu object for the CLI
 */
public class Menu extends ArrayList<MenuItem> {
    private static final long serialVersionUID = 1L;
    
    String title;

    /**
     * Construct Menu
     * @param title Title of menu
     */
    public Menu(String title) {
        this.title = title;
    }

    private void display() {
        System.out.println(this.title);
        int i = 0;
        for (MenuItem menuItem : this) {
            System.out.println(String.format("\t%d. %s", i++, menuItem));
        }
    }


    /**
     * Present the menu to the user
     * @return the last MenuItemReturnValue that was not CONTINUE
     */
    public MenuItemReturnValue present() {
        int selectedItem = -1;
        MenuItemReturnValue returnValue;
        do {
            do {
                selectedItem = getUserSelection();
            } while (selectedItem < 0 || selectedItem >= this.size());
            returnValue = this.get(selectedItem).selected();   
        } 
        while (returnValue == MenuItemReturnValue.CONTINUE);

        return returnValue;
    }

    private int getUserSelection() {
        while (true) {
            try {
                @SuppressWarnings("resource")
                Scanner sc = new Scanner(System.in); 
                display();
                System.out.print(": ");
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid selection. Try again.");
            }
            
        }
    }
}
