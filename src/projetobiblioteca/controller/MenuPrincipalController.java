
package projetobiblioteca.controller;

import projetobiblioteca.controller.helper.MenuPrincipalHelper;
/**
 *
 * @author Lucas
 */
public class MenuPrincipalController {
    
    private final MenuPrincipalHelper helper;

    public MenuPrincipalController() {
        this.helper = new MenuPrincipalHelper();
    }

    public MenuPrincipalHelper getHelper() {
        return helper;
    }
}
