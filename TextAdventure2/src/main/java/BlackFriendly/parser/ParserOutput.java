/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackFriendly.parser;

import BlackFriendly.type.AdvObject;
import BlackFriendly.type.Command;

/**
 * La classe parserOutput contiene l'implementazione per gestire una struttura dati che contiene i dati dell’input utente tokenizzati e strutturati.
 * 
 * @author Rigante Chiara
 * @author Ramkalawon Alessia
 * @author Scalzo Andrea
 */
public class ParserOutput {

    private Command command;

    private AdvObject object;
    
    private AdvObject invObject;

    public ParserOutput(Command command, AdvObject object) {
        this.command = command;
        this.object = object;
    }

    public ParserOutput(Command command, AdvObject object, AdvObject invObejct) {
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public AdvObject getObject() {
        return object;
    }

    public void setObject(AdvObject object) {
        this.object = object;
    }

    public AdvObject getInvObject() {
        return invObject;
    }

    public void setInvObject(AdvObject invObject) {
        this.invObject = invObject;
    }

}
