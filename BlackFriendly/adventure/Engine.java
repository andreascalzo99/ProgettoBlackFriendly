/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackFriendly.adventure;

import BlackFriendly.adventure.games.MurderInTheCastleGame;
import BlackFriendly.adventure.parser.Parser;
import BlackFriendly.adventure.parser.ParserOutput;
import BlackFriendly.adventure.type.CommandType;
import java.util.Scanner;

/**
 * ATTENZIONE: l'Engine è molto spartano, in realtà demanda la logica alla
 * classe che implementa GameDescription e si occupa di gestire I/O sul
 * terminale.
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
        System.out.println("Benvenuto nel gioco dei Black Friendly.\n Un giocatore impersona un investigatore.\n Durante la partita, dovrai raccogliere informazioni, trovare indizi e seguire diverse piste per risolvere un singolo caso, in particolare un omicidio");
        System.out.println("Prima di tutto presentiamoci. Con chi abbiamo l'onore di giocare?  ");
        Scanner scanner = new Scanner (System.in);
        String partecipante = scanner.nextLine();
        System.out.println("Molto piacere " + partecipante);
        System.out.println("================================================");
        System.out.println("Iniziamo subito!!");
        System.out.println("Detective " + partecipante + " sei stato chiamato per risolvere l'omicidio di un componente della famiglia De Santis\n"
                + "una delle famiglie più ricche del paese.\nCi troviamo all'interno della loro magnifica villa, costituita da 2 piani.\n"
                + "In particolare è stato assassinato il figlio minore, Jacob. \n"
                + "Attualmente e al momento dell'omicidio c'erano solo i 4 componenti della famiglia: la signora Maria, il signor Dylan, la figlia maggiore Filomena e il figlio minore Jacob.\n"
                + "Mettiti subito all'opera!. \nRicorda, digitando help, ti mostreremo tutti i comandi possibili.");
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
        engine.run();
    }

}
