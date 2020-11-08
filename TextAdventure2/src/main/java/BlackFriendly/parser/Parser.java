/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackFriendly.parser;

import BlackFriendly.type.AdvObject;
import BlackFriendly.type.Command;
import java.util.List;

/**
 * Il parser si occupa di determinare la struttura e la correttezza dell’input analizzando i comandi e i potenziali oggetti inseriti nella frase eliminando le informazioni superflue.<br>
 * E' implementato in modo indipendete dalla lingua, in quanto implementato per riconoscere frasi semplici senza l'utilizzo di articoli o preposizioni.<br>
 * Verifica la correttezza dell'input inserito dall'utente, costuituito da uno o più parole, riferendosi ad un comando, oppure comando e oggetto.
 * 
 * @author Rigante Chiara
 * @author Ramkalawon Alessia
 * @author Scalzo Andrea
 * 
 */
public class Parser {

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkForObject(String token, List<AdvObject> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).getName().equals(token) || obejcts.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        String cmd = command.toLowerCase().trim();
        String[] tokens = cmd.split("\\s+");
        if (tokens.length > 0) {
            int ic = checkForCommand(tokens[0], commands);
            if (ic > -1) {
                if (tokens.length > 1) {
                    int io = checkForObject(tokens[1], objects);
                    int ioinv = -1;
                    if (io < 0 && tokens.length > 2) {
                        io = checkForObject(tokens[2], objects);
                    }
                    if (io < 0) {
                        ioinv = checkForObject(tokens[1], inventory);
                        if (ioinv < 0 && tokens.length > 2) {
                            ioinv = checkForObject(tokens[2], inventory);
                        }
                    }
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
