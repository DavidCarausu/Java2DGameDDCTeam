package Main;

import PaooGame.Menu.MenuPrincipal;

import java.awt.*;

public class Main
{
     public static void main(String[] args) {
     //odata ce apelezi constructorul jframe_init iti face un frame global "frame"

        // Gamestate.state= Gamestate.PLAYING;
         GameWindow w=new GameWindow();

         MenuPrincipal p=new MenuPrincipal(w);
         w.AddMenuPanel(p);
         w.CarePanelPeFrame(false);
         //Game g=new Game(w);

          }
}