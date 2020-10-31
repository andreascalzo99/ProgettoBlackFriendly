/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackFriendly.adventure.games;

import BlackFriendly.adventure.GameDescription;
import BlackFriendly.adventure.parser.ParserOutput;
import BlackFriendly.adventure.type.AdvObject;
import BlackFriendly.adventure.type.AdvObjectContainer;
import BlackFriendly.adventure.type.Command;
import BlackFriendly.adventure.type.CommandType;
import BlackFriendly.adventure.type.Room;
import java.io.PrintStream;
import java.util.Iterator;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che posso
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicati: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author Rigante Chiara
 * @author Ramkalawon Alessia
 * @author Scalzo Andrea
 */
public class MurderInTheCastleGame extends GameDescription {

    @Override
    public void init() throws Exception {
        //Commands
        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD", "su", "dritto", "Su", "Dritto"});
        getCommands().add(nord);
        Command iventory = new Command(CommandType.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv", "i", "I"});
        getCommands().add(iventory);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD", "giù", "Giù", "Indietro", "indietro"});
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST", "destra", "Destra", "dx", "Dx", "DX"});
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST", "sinistra", "Sinistra", "sx", "Sx", "SX"});
        getCommands().add(ovest);
        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "Rinuncia", "arrenditi", "Arrenditi", "resa", "Resa", "exit", "termina", "Termina", "rinuncia"});
        getCommands().add(end);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi", "individua", "scruta"});
        getCommands().add(look);
        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi","Raccogli","Prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"Apri"});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva", "push","Premi","Spingi","Attiva"});
        getCommands().add(push);
        Command help =new Command(CommandType.HELP, "aiuto");
        getCommands().add(help);
        help.setAlias(new String[]{"Aiuto","Help","help"});
        //Rooms 1st floor
        Room salone = new Room(0, "Salone", "Ti trovi nel salone,uno dei luoghi più frequentati della casa. \n Ci mettiamo sul divano o iniziamo a cercare? Buon Divertimento!\n"
                + "Vuoi osservare il salone o procedere in un'altra stanza?");
        salone.setLook("Sei nel salone,a est trovi la cucina, a nord trovi le scale per il piano superiore, a ovest trovi la sala da pranzo, a sud la porta d'ingresso, dove saranno gli indizi?");
        Room salaDaPranzo = new Room(1, "Sala da pranzo", "Ti trovi nella sala da pranzo del castello. Ci sono preziosi ovunque, ma nessun indizio rilevante.");
        salaDaPranzo.setLook("Non ci sono indizi rilevanti, meglio cambiare stanza!");
        Room bagno = new Room(2, "Bagno", "Ti trovi nel bagno del castello. Posto insolito per commettere un omicidio!  "
                + "C'è una grande finiestra ed una vasca da bagno lussuosa, il marmo splende come non ha mai sple-, spl- ... come mai prima d'ora!");
        bagno.setLook("Non ci sono indizi rilevanti, meglio cambiare stanza!");
        Room cucina = new Room(3, "Cucina", "Sei nella cucina del castello. Uno dei luoghi più frequentati in tutte le ore del giorno.");
        cucina.setLook("C'è un cestino accanto al lavandino.Ricorda: Un cestino, nei film contiene sempre un indizio!");
        //Rooms 2nd floor
        Room corridoio = new Room(4, "Corridio", "Sei salito al piano superiore e ti trovi nel corridoio del castello. Ci sono diverse stanze collegate tra loro.");
        corridoio.setLook("Non ci sono indizi rilevanti, meglio cambiare stanza!");
        Room stanzaJacob = new Room(5, "Stanza di Jacob, figlio dei ____", "Ti trovi nella stanza di Jacob. A sinistra scorgi la stanza di sua sorella Andrea. \n A nord trovi il bagno che cumnica a sua volta con la camera dei genitori.");
        stanzaJacob.setLook("Noti che ci sono dei fili per terra ed un cellulare sul mobile.");
        Room stanzaAndrea = new Room(6, "Stanza di Andrea", "Ti trovi nella stanza di Andrea, sorrela di Jacob. \n Questa stanza comunica unicamente con la stanza di Jacob.");
        stanzaAndrea.setLook("Non ci sono indizi rilevanti, meglio cambiare stanza!");
        Room secondoBagno = new Room(7, "Secondo Bagno", "Ti trovi nel secondo bagno del castello. Ti pareva che un castello avesse solo un bagno?");
        secondoBagno.setLook("Non ci sono indizi rilevanti, meglio cambiare stanza!");
        Room stanzaGenitori = new Room(8, "Stanza dei Genitori", "Ti trovi nella stanza dei genitori. La stanza è molto vasta, prova a cercare qualcosa!");
        stanzaGenitori.setLook("C'è una cabina-armadio che non passa affatto inosservata, c'è qualcosa di sospetto!");
        Room salaLettura = new Room(9, "Sala Lettura", "Ti trovi nella sala lettura del castello. Ci sono tomi di ogni genere, che ne diresti di leggere un libro?");
        salaLettura.setLook("C'è un libro leggermente sporgente in cima alla libbreria.");
        Room stanzaSegreta = new Room(10, "Stanza Segreta", "Hai trovato una stanza segreta. "); //INSERISCI FRASE AD EFFETTO
        stanzaSegreta.setLook("C'è una gonna con delle iniziali 'M' & 'D'");   //SUSCIETTIBILE  A MODIFICHE

        //maps 1st floor
        bagno.setSouth(salaDaPranzo);
        salaDaPranzo.setNorth(bagno);
        salaDaPranzo.setEast(salone);
        salone.setEast(cucina);
        salone.setWest(salaDaPranzo);
        salone.setNorth(corridoio);
        cucina.setWest(salone);
        //inserire scale piano di sopra
        corridoio.setEast(salaLettura);
        corridoio.setWest(stanzaJacob);
        corridoio.setSouth(salone);
        corridoio.setNorth(stanzaGenitori);
        stanzaGenitori.setWest(secondoBagno);
        stanzaGenitori.setSouth(corridoio);
        secondoBagno.setSouth(stanzaJacob);
        secondoBagno.setEast(stanzaGenitori);
        stanzaJacob.setEast(corridoio);
        stanzaJacob.setNorth(secondoBagno);
        stanzaJacob.setWest(stanzaAndrea);
        stanzaAndrea.setEast(stanzaJacob);
        salaLettura.setWest(corridoio);
        salaLettura.setEast(stanzaSegreta); // non accessibile senza interazione
        stanzaSegreta.setWest(salaLettura);
        //maps 2nd floor

        getRooms().add(bagno);
        getRooms().add(salaDaPranzo);
        getRooms().add(salone);
        getRooms().add(cucina);
        //obejcts
        AdvObject forbici = new AdvObject(1, "forbici da cucito", "Delle forbici da cucito macchiate di sangue.");
        forbici.setAlias(new String[]{"forbici", "forbice", "Forbici", "Forbice", "FORBICI", "FORBICE"});
        cucina.getObjects().add(forbici);
        AdvObjectContainer cestino = new AdvObjectContainer(2, "cestino");
        cestino.setAlias(new String[]{"spazzatura", "immondizia"});
        cestino.setOpenable(true);
        cestino.setPickupable(false);
        cestino.setOpen(false);  //è inizializzato a falso
        cucina.getObjects().add(cestino);
        AdvObject fili = new AdvObject(3, "fili da cucito", "Dei fili da cucito, un probabile indizio.");
        fili.setAlias(new String[]{"filo", "Filo", "fili", "Fili"});
        stanzaJacob.getObjects().add(fili);
        AdvObject cellulare = new AdvObject(4, "cellulare di Jacob", "Il cellulare della vittima, ci sono numerose chiamate perse della madre.");
        cellulare.setAlias(new String[]{"telefono", "telefonino", "smartphone", "cellulare", "Telefono", "Cellulare", "Telefonino"});
        stanzaJacob.getObjects().add(cellulare);
        AdvObject libro = new AdvObject(5, "Libro sporgente", "Un libro particolarmente grande, non sembra removibile, prova a schiacciarlo!");
        libro.setAlias(new String[]{"Libro", "libri", "libro"});
        libro.setPushable(true);
        libro.setPush(false);

        salaLettura.getObjects().add(libro);
        AdvObject gonna = new AdvObject(7, "Una gonna", "Una gonna con le iniziali 'M' & 'D', completamente sporca di sangue");
        gonna.setAlias(new String[]{"Gonna","gonna"});
        stanzaSegreta.getObjects().add(gonna);

        //A G G I U N G E R E   A B I T O   E   A R M A D I O
        cestino.add(forbici);  //cestino è un oggetto che contiene un altro oggetto

        //set starting room
        setCurrentRoom(salone);
    }

    int contaOggetti = 0; //variabile che conta il numero di oggetti raccolti
    
    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (null != p.getCommand().getType()) switch (p.getCommand().getType()) {
                case NORD:
                    if (getCurrentRoom().getNorth() != null) {
                        setCurrentRoom(getCurrentRoom().getNorth());
                        move = true;
                    } else {
                        noroom = true;
                    }   break;
                case SOUTH:
                    if (getCurrentRoom().getSouth() != null) {
                        setCurrentRoom(getCurrentRoom().getSouth());
                        move = true;
                    } else {
                        noroom = true;
                    }   break;
                case EAST:
                    if (getCurrentRoom().getEast() != null) {
                        setCurrentRoom(getCurrentRoom().getEast());
                        move = true;
                    } else {
                        noroom = true;
                    }   break;
                case WEST:
                    if (getCurrentRoom().getWest() != null) {
                        setCurrentRoom(getCurrentRoom().getWest());
                        move = true;
                    } else {
                        noroom = true;
                    }   break;
                case INVENTORY:
                    out.println(("Nel tuo inventario ci sono "+ contaOggetti + " oggetti"));
                    getInventory().forEach(o -> {
                        out.println(o.getName()+ ": "+ o.getDescription());
                    });
                            break;
                case LOOK_AT:
                    
                    if (getCurrentRoom().getLook() != null) {
                        out.println(getCurrentRoom().getLook());
                        /*if(p.getObject() != null){
                            out.println("Descrizione:" + p.getObject().getLook());
                        }*/ // serve per osservare gli ooggetti
                        
                    } else {
                        out.println("Non c'è niente di interessante qui.");
                    }   break;
                case PICK_UP:
                    if (p.getObject() != null) {
                        if (p.getObject().isPickupable()) {
                            getInventory().add(p.getObject());
                            getCurrentRoom().getObjects().remove(p.getObject());
                            out.println("Hai raccolto: " + p.getObject().getDescription());
                            contaOggetti++;
                            //out.println("Possiedi: " + contaOggetti + " oggetti");
                            
                        } else {
                            out.println("Non puoi raccogliere questo oggetto.");
                        }
                        java.util.Scanner scan = new java.util.Scanner(System.in);
                        out.println("Hai capito chi è il colpevole?");
                        
                        String answer = scan.next();
                            switch (answer) {
                                case "si":
                                case "Si":
                                case "SI":
                                    System.out.println("Inserisci il nome:");
                                    String nameKiller = scan.next();
                                    if (nameKiller.equals("madre")||nameKiller.equals("signora")||nameKiller.equals("Madre")) {
                                        win(out);
                     
                                    } else {
                                         out.println("Risposta Sbagliata!");
                                          if (contaOggetti == 2) {
                                            lose(out);
                                        }
                                    }       break;
                                case "no":
                                case "No":
                                case "NO":
                                    if (contaOggetti == 2) {
                                        lose(out); 
                                    } 
                                    else
                                   System.out.println("Continua a indagare!");
                                   
                             break;
                             
                                default: 
                                    System.out.println("Sei un po' confuso");
                                    break;
                            }
                       
                        out.println("ti trovi in " + getCurrentRoom().getName());

                    }
             else {
                        out.println("Non c'è niente da raccogliere qui.");
                    }   break;
                case OPEN:
                    /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                    * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                    * Questa soluzione NON va bene poiché quando un oggetto contenitore viene richiuso è complicato
                    * non rendere più disponibili gli oggetti contenuti rimuovendoli dalla stanza o dall'invetario.
                    * Trovare altra soluzione.
                    */
                    if (p.getObject() == null && p.getInvObject() == null) {
                        out.println("Non c'è niente da aprire qui.");
                    } else {
                        if (p.getObject() != null) {
                            if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                                if (p.getObject() instanceof AdvObjectContainer) {
                                    out.println("Hai aperto: " + p.getObject().getName());
                                    AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                    if (!c.getList().isEmpty()) {
                                        out.print(c.getName() + " contiene:" );
                                        Iterator<AdvObject> it = c.getList().iterator();
                                        while (it.hasNext()) {
                                            AdvObject next = it.next();
                                            getCurrentRoom().getObjects().add(next);
                                            out.print(" " + next.getName());
                                            it.remove();
                                        }
                                        out.println();
                                    }
                                } else {
                                    out.println("Hai aperto: " + p.getObject().getName());
                                    p.getObject().setOpen(true);
                                }
                            } else {
                                out.println("Non puoi aprire questo oggetto.");
                            }
                        }
                        if (p.getInvObject() != null) {
                            if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                                if (p.getInvObject() instanceof AdvObjectContainer) {
                                    AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                                    if (!c.getList().isEmpty()) {
                                        out.print(c.getName() + " contiene:");
                                        Iterator<AdvObject> it = c.getList().iterator();
                                        while (it.hasNext()) {
                                            AdvObject next = it.next();
                                            getInventory().add(next);
                                            out.print(" " + next.getName());
                                            it.remove();
                                        }
                                        out.println();
                                    }
                                } else {
                                    p.getInvObject().setOpen(true);
                                }
                                out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                            } else {
                                out.println("Non puoi aprire questo oggetto.");
                            }
                        }
                    }   break;
                case PUSH:
                    //ricerca oggetti pushabili
                    if (p.getObject() != null && p.getObject().isPushable()) {
                        out.println("Hai premuto: " + p.getObject().getName());
                        if (p.getObject().getId() == 6) {
                            secret(out);
                        }
                    } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                        out.println("Hai premuto: " + p.getInvObject().getName());
                        //  if (p.getRooms().getId() == 9) {
                        //     out.println("ciao.");
                        // }
                        if (p.getInvObject().getId() == 6) {
                            secret(out);
                        }
                    } else {
                        out.println("Non ci sono oggetti che puoi premere qui.");
                    }   break;
                case HELP:
                    //se il giocatore non sa cosa può fare
                    System.out.println("Direzioni: Nord, Sud, Est, Ovest\n"
                            + "Osserva\n"
                            + "Prendi\n"
                            + "Apri\n"
                            + "Premi\n"
                            + "Esci\n"
                            + "Inventario");
                
                default:
                    break;
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void secret(PrintStream out) {  //apertura stanza segreta
        out.println("*crick* Si apre una porta per una stanza segreta..");

    }

    private void win(PrintStream out) {
        out.println("Complimenti, hai vinto!");
        System.exit(0);
    }

    private void lose(PrintStream out) {
        out.println("Hai trovato tutti gli indizi, ma non sei riuscito/a a trovare il coplpevole. Hai perso!");
        System.exit(0);
    }
}
