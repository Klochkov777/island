package priv.klochkov.island;

import priv.klochkov.island.controller.Controller;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Controller controller = new Controller(3,3);
        controller.start();
    }
}
