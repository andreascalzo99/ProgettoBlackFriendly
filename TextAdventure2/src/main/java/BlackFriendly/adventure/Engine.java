/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BlackFriendly.adventure;

import BlackFriendly.games.MurderInTheCastleGame;
import BlackFriendly.parser.Parser;
import BlackFriendly.parser.ParserOutput;
import BlackFriendly.type.CommandType;
import java.util.Scanner;


/**
 * L'Engine contiene il main e il metodo run che serve a gestire il caricamento dell'entità del gioco.<br>
 * Contiene un metodo per chiudere il gioco.<br>
 * Si occupa della gestione delle risposte ai comandi inseriti dall'utente.
 *
 * @author Rigante Chiara
 * @author Scalzo Andrea
 * @author Ramkalawon Alessia
 */
public class Engine {

    private final GameDescription game;

    private final Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        parser = new Parser();
    }

    public void run() {
        
        System.out.println("Benvenuto nel gioco Murder in the Castle.\n Un giocatore impersona un investigatore.\n Durante la partita, dovrai raccogliere informazioni, trovare indizi e seguire diverse piste per risolvere un singolo caso, in particolare un omicidio");
        System.out.println("Prima di tutto presentiamoci. Con chi abbiamo l'onore di giocare?  ");
        Scanner scanner = new Scanner (System.in);
        String partecipante = scanner.nextLine();
        System.out.println("Molto piacere " + partecipante);
        System.out.println("================================================");
        System.out.println("Iniziamo subito!!"); 
        System.out.println("Detective " + partecipante + " sei stato chiamato per risolvere l'omicidio di un componente della famiglia De Santis\n"
                + "una delle famiglie più ricche del paese.\nCi troviamo all'interno del loro magnifico castello, costituito da 2 piani.\n"
                + "La vicenda inizia con il ritrovamento del cadavere del giovane Jacob all'interno della sua camera.\nSulla scena del crimine non c'è neanche l'arma del delitto.\n"
                + "Attualmente e al momento dell'omicidio c'erano solo i 4 componenti della famiglia: la signora Maria, il signor Dylan, la figlia maggiore Filomena e il figlio minore Jacob.\n"
                + "Mettiti subito all'opera!. Dovrai trovare l'arma del delitto e chi lo ha ucciso.\nRicorda, digitando help, ti mostreremo tutti i comandi possibili.");
        //System.out.println("Parte della casa" + game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Alla Prossima!\n Grazie di aver giocato con noi.\n -Chiara,Alessia,Andrea");
                break;
            } else {
                game.nextMove(p, System.out);
                System.out.println("================================================");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new MurderInTheCastleGame());
        Art art = new Art();
        art.writeCastle();
        engine.run();

    }

}
