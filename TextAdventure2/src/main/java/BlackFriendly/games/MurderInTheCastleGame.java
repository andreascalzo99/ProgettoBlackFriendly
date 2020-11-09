/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackFriendly.games;

import BlackFriendly.adventure.Art;
import BlackFriendly.adventure.GameDescription;
import BlackFriendly.parser.ParserOutput;
import BlackFriendly.type.AdvObject;
import BlackFriendly.type.AdvObjectContainer;
import BlackFriendly.type.Command;
import BlackFriendly.type.CommandType;
import BlackFriendly.type.Room;
import java.io.PrintStream;
import java.util.Iterator;

/**
 * Murder in the Castle contiene l'implementazione dei comandi e delle direzioni possibili all'utente.<br>
 * E' la classe che implementa la logica di gioco e come interagiscono i comandi dell'utente con il gioco.<br>
 * Estende la classe GameDescription e ne implementa i suoi metodi astratti.
 * @author Rigante Chiara
 * @author Ramkalawon Alessia
 * @author Scalzo Andrea
 */
public class MurderInTheCastleGame extends GameDescription {

    Art art = new Art();

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
        pickup.setAlias(new String[]{"prendi", "Raccogli", "Prendi"});
        getCommands().add(pickup);
        Command open = new Command(CommandType.OPEN, "apri");
        open.setAlias(new String[]{"Apri"});
        getCommands().add(open);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva", "push", "Premi", "Spingi", "Attiva", "riponi", "Riponi"});
        getCommands().add(push);
        Command help = new Command(CommandType.HELP, "aiuto");
        getCommands().add(help);
        help.setAlias(new String[]{"Aiuto", "Help", "help"});
        Command map = new Command(CommandType.MAP, "mappa");
        map.setAlias(new String[]{"map", "Map", "Mappa"});
        getCommands().add(map);
        //Rooms 1st floor
        Room salone = new Room(0, "Salone", "Ti trovi nel salone,uno dei luoghi più frequentati della casa."
                + "Vuoi osservare il salone o procedere in un'altra stanza?");
        salone.setLook("Come in tutti i saloni, ci sono una serie di divani, un enorme tappeto al centro e una parete attrezzata con una tv 60''. In un angolo c'è un tavolino con sopra degli alcolici.\n A est trovi la cucina, a nord trovi le scale per il piano superiore, a ovest trovi la sala da pranzo, a sud la porta d'ingresso.\nVuoi riposarti sul divano o inizi a cercare? Buon Divertimento!");
        Room salaDaPranzo = new Room(1, "Sala da pranzo", "Ti trovi nella sala da pranzo del castello.\n Vuoi accomodarti e aspettare che qualcuno ti porti del cibo? Ma non pensarci proprio! L'assassino potrebbe uccidere ancora!");
        salaDaPranzo.setLook("Un imponente tavolo rettangolare al centro della stanza, con 4 sedie. Un lampadario stile barocco che pende dal soffitto. Attorno  vari mobili color legno.\nSembra essere tutto perfettamente al posto, cerca altrove!\n A nord c'è il bagno");
        Room bagno = new Room(2, "Bagno", "Sei nel bagno del castello.\n Digitando osserva potrai immaginare un bagno mai visto prima d'ora!");
        bagno.setLook("C'è una grande finiestra ed una vasca da bagno lussuosa, il marmo splende come non ha mai sple-, spl- ... come mai prima d'ora!\n Un lavandino con sopra un enorme specchio tutto color oro, peccato non si possa prendere...\n");
        Room cucina = new Room(3, "Cucina", "Sei nella cucina del castello. Uno dei luoghi più frequentati in tutte le ore del giorno. A chi non piace mangiare alla fine?...Guardati attorno");
        cucina.setLook("La cucina è costituita da una serie di pensili, un piano da lavoro con lavabo e piano cottura. \nC'è un set di coltelli accanto al piano cottura, ma non sembra mancanrne qualcuno.\nInoltre piccolo tavolo dove pranzano gli inservienti.\nC'è un cestino accanto al lavandino.\nRicorda: Un cestino, nei film contiene sempre un indizio!");
        //Rooms 2nd floor
        Room corridoio = new Room(4, "Corridoio", "Sei salito al piano superiore e ti trovi nel corridoio del castello. Ci sono diverse stanze collegate tra loro.");
        corridoio.setLook("Un piccolo quadrato con 3 direzioni possibili: nord Stanza dei genitori, est Sala lettura, ovest scena del crimine.\nQuale sarà la prossima mossa?");
        Room stanzaJacob = new Room(5, "Stanza di Jacob", "Ti trovi nella stanza di Jacob, ovvero la scena del crimine. Il cadavere del giovane è stato portato via, ma puoi solamente sapere che aveva una ferita profonda al collo.");
        stanzaJacob.setLook("Al centro della stanza, un letto tutto ricoperto di sangue. C'è un cellulare sulla scrivania ...\nAspetta aspetta: ci sono dei fili per terra... Vorranno dirti qualcosa?Che ne dici di prenderli?\nA sinistra si trova la stanza di sua sorella Filomena.\nA nord trovi il bagno.");
        Room stanzaFilomena = new Room(6, "Stanza di Filomena", "Ti trovi nella stanza di Filomena, sorrela di Jacob. \n La solita stanza da 'femmine' con colore predominante il rosa. ");
        stanzaFilomena.setLook("Un letto con coperte e cuscini rosa, un armadio bianco con attaccate delle immagini di alcuni attori.\n Sulla scrivania ci sono dei libri... Sarà una ragazza studiosa! Però c'è anche una rivista sul cucito...mmm");
        Room secondoBagno = new Room(7, "Secondo Bagno", "Ti trovi nel secondo bagno del castello. Ti pareva che un castello avesse solo un bagno?");
        secondoBagno.setLook("E' molto meglio l'altro, anzi molto meglio quello di casa tua.. E' un modo per farti sentire ricco ed importante.\nNon ci sono indizi rilevanti, meglio cambiare stanza! A est trovi la stanza dei genirori");
        Room stanzaGenitori = new Room(8, "Stanza dei Genitori", "Ti trovi nella stanza dei genitori. La stanza è molto grande, prova a cercare qualcosa!");
        stanzaGenitori.setLook("Un letto a baldacchino al centro, con 2 comodini a destra e sinistra.\nDi fronte c'è una cabina-armadio che non passa affatto inosservata, c'è qualcosa di sospetto!");
        Room salaLettura = new Room(9, "Sala Lettura", "Ti trovi nella sala lettura del castello. Ci sono tomi di ogni genere, che ne diresti di leggere un libro?");
        salaLettura.setLook("Ma è il castello dei tavoli enormi? Un altro tavolo immenso fatto di legno massiccio, con attorno milioni di libri catalogati in diversi scaffali...\nFai attenzione! C'è categoria 'cucito'.  Qualcuno sarà veramente appassionato!\n Attento che un libro sporge, rimettilo bene prima che cada.");
        salaLettura.setVisible(false);
        Room stanzaSegreta = new Room(10, "Stanza Segreta", "Non basta l'immensità del castello e il mistero che s'infittisce... Anche la stanza segreta!"); //INSERISCI FRASE AD EFFETTO
        stanzaSegreta.setLook("Una stanza dall'aspetto abbastanza macabro, con una misera luce...\nMa.. ma.. c'è una gonna con delle iniziali 'M' & 'D' ed è pure sporca di sangue. Non sarà mica la gonna che mancava al tailleur?\nPrendila e inizia a ricomporre i pezzi!");
        stanzaSegreta.setVisible(false);
        //maps 1st floor
        bagno.setSouth(salaDaPranzo);
        salaDaPranzo.setNorth(bagno);
        salaDaPranzo.setEast(salone);
        salone.setEast(cucina);
        salone.setWest(salaDaPranzo);
        salone.setNorth(corridoio);
        cucina.setWest(salone);

        //maps 2nd floor
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
        stanzaJacob.setWest(stanzaFilomena);
        stanzaFilomena.setEast(stanzaJacob);
        salaLettura.setWest(corridoio);
        salaLettura.setEast(stanzaSegreta);
        stanzaSegreta.setWest(salaLettura);

        getRooms().add(bagno);
        getRooms().add(salaDaPranzo);
        getRooms().add(salone);
        getRooms().add(cucina);
        getRooms().add(stanzaGenitori);
        getRooms().add(stanzaSegreta);
        getRooms().add(salaLettura);

        //obejcts
        AdvObject forbici = new AdvObject(1, "Forbici da cucito", "Delle forbici da cucito macchiate di sangue. Sarà forse l'arma del delitto...");
        forbici.setAlias(new String[]{"forbici", "forbice", "Forbici", "Forbice", "FORBICI", "FORBICE"});
        cucina.getObjects().add(forbici);

        AdvObjectContainer cestino = new AdvObjectContainer(2, "Cestino", "Ci sono un mucchio di schifezze, ma c'è un fazzoletto con un aspetto sospetto");
        cestino.setAlias(new String[]{"spazzatura", "immondizia", "cestino"});
        cestino.setOpenable(true);
        cestino.setPickupable(false);
        cestino.setOpen(false);
        cucina.getObjects().add(cestino);

        AdvObjectContainer fazzoletto = new AdvObjectContainer(3, "Fazzoletto sporco", "Fazzoletto sporco con qualcosa di duro all'interno");
        fazzoletto.setAlias(new String[]{"fazzoletto"});
        fazzoletto.setOpenable(true);
        fazzoletto.setOpen(false);
        cucina.getObjects().add(fazzoletto);
        cestino.add(fazzoletto);
        fazzoletto.add(forbici);

        AdvObject fili = new AdvObject(4, "Fili da cucito", "Dei fili da cucito, un probabile indizio.");
        fili.setAlias(new String[]{"filo", "Filo", "fili", "Fili"});
        stanzaJacob.getObjects().add(fili);

        AdvObject cellulare = new AdvObject(5, "Cellulare di Jacob", "Il cellulare della vittima, ci sono numerose chiamate perse della madre.\nSo che stai pensando: 'Cosa c'è di strano', ma pensaci...");
        cellulare.setAlias(new String[]{"telefono", "telefonino", "smartphone", "cellulare", "Telefono", "Cellulare", "Telefonino"});
        stanzaJacob.getObjects().add(cellulare);

        AdvObject libro = new AdvObject(6, "Libro sporgente", "Un libro particolarmente grande, con scritto 'Impara a cucire con java', mettilo al posto va'... ");
        libro.setAlias(new String[]{"libro sporgente", "libri", "libro"});
        libro.setPushable(true);
        libro.setPush(false);
        libro.setPickupable(false);
        salaLettura.getObjects().add(libro);

        AdvObject gonna = new AdvObject(7, "Una gonna", "Una gonna con le iniziali 'M' & 'D', completamente sporca di sangue");
        gonna.setAlias(new String[]{"Gonna", "gonna"});
        stanzaSegreta.getObjects().add(gonna);

        AdvObjectContainer armadio = new AdvObjectContainer(8, "Armadio", "Ci sono tutti i vestiti costosissimi dei padroni di casa...\nMa attenzione...");
        armadio.setAlias(new String[]{"armadio", "mobile", "cabina-armadio"});
        armadio.setOpenable(true);
        armadio.setPickupable(false);
        armadio.setOpen(false);
        stanzaGenitori.getObjects().add(armadio);

        AdvObject abito = new AdvObject(9, "Tailleur", "tailleur con la gonna mancante");
        abito.setAlias(new String[]{"tailleur", "Abito", "giacca", "abito"});
        stanzaGenitori.getObjects().add(abito);
        armadio.add(abito);

        AdvObject rivista = new AdvObject(10, "Rivista", "Una comune rivista di cucito... forse seguirà le orme della madre...");
        rivista.setAlias(new String[]{"rivista", "rivista da cucito", "Rivista da cucito"});
        stanzaFilomena.getObjects().add(rivista);

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
            if (null != p.getCommand().getType()) {
                switch (p.getCommand().getType()) {
                    case NORD:
                        if (getCurrentRoom().getNorth() != null) {
                            setCurrentRoom(getCurrentRoom().getNorth());
                            move = true;
                        } else {
                            noroom = true;
                        }
                        break;
                    case SOUTH:
                        if (getCurrentRoom().getSouth() != null) {
                            setCurrentRoom(getCurrentRoom().getSouth());
                            move = true;
                        } else {
                            noroom = true;
                        }
                        break;
                    case EAST:
                        if (getCurrentRoom().getEast() != null) {
                            if (getCurrentRoom().isVisible()) {
                                setCurrentRoom(getCurrentRoom().getEast());
                                move = true;
                            } else {
                                System.out.println("Da quella parte non puoi andarci c'è un muro!");
                            }
                        } else {
                            noroom = true;
                        }
                        break;
                    case WEST:
                        if (getCurrentRoom().getWest() != null) {
                            setCurrentRoom(getCurrentRoom().getWest());
                            move = true;
                        } else {
                            noroom = true;
                        }
                        break;
                    case INVENTORY:
                        out.println(("Nel tuo inventario ci sono " + contaOggetti + " oggetti"));
                        getInventory().forEach(o -> {
                            out.println("- " + o.getName());
                        });
                        break;
                    case LOOK_AT:

                        if (getCurrentRoom().getLook() != null) {

                            out.println(getCurrentRoom().getLook());
                            if (getCurrentRoom().getId() == 9) {
                                art.writeBook();
                            }
                            else if (getCurrentRoom().getId() == 3) {
                                art.writeTrash();
                            }
                            else if (getCurrentRoom().getId() == 8) {
                                art.writeWardrobe();
                            }

                        } else {
                            out.println("Non c'è niente di interessante qui.");
                        }
                        break;
                    case PICK_UP:
                        if (p.getObject() != null) {
                            if (p.getObject().isPickupable()) {
                                getInventory().add(p.getObject());
                                getCurrentRoom().getObjects().remove(p.getObject());
                                out.println("Hai raccolto: " + p.getObject().getDescription());
                                switch (p.getObject().getId()) {
                                    case 1:
                                        art.writeScisors();
                                        break;
                                    case 3:
                                        art.writeTissue();
                                        break;
                                    case 4:
                                        art.writeWires();
                                        break;
                                    case 5:
                                        art.writeMobile();
                                        break;
                                    case 7:
                                        art.writeSkirt();
                                        break;
                                    case 9:
                                        art.writeTailleur();
                                        break;
                                    case 10:
                                        art.writeMegazine();
                                        break;
                                    default:
                                        break;
                                }
                                contaOggetti++;
                                p.getObject().setPickupable(false);//oggetto non più raccoglibile
                                System.out.println(contaOggetti);
                                java.util.Scanner scan = new java.util.Scanner(System.in);
                                out.println("Hai capito chi è il colpevole?");

                                String answer = scan.next();
                                switch (answer) {
                                    case "si":
                                    case "Si":
                                    case "SI":
                                        System.out.println("Inserisci il nome:");
                                        String nameKiller = scan.next();
                                        if (nameKiller.equals("madre") || nameKiller.equals("signora") || nameKiller.equals("Maria") || nameKiller.equals("maria")) {
                                            win(out);

                                        } else {
                                            out.println("Risposta Sbagliata!");
                                            if (contaOggetti == 7) {  //massimo oggetti raccoglibili
                                                lose(out);
                                            }
                                        }
                                        break;
                                    case "no":
                                    case "No":
                                    case "NO":
                                        if (contaOggetti == 7) {
                                            lose(out);
                                        } else {
                                            System.out.println("Continua a indagare!");
                                        }

                                        break;

                                    default:
                                        System.out.println("Sei un po' confuso");
                                        break;
                                }
                            } else {
                                out.println("Non puoi raccogliere questo oggetto.");
                            }

                            out.println("================================================");
                            out.println("Posizione attuale: " + getCurrentRoom().getName());

                        } else {
                            out.println("Non c'è niente da raccogliere qui.");
                        }
                        break;
                    case OPEN:

                        if (p.getObject() == null && p.getInvObject() == null) {
                            out.println("Non c'è niente da aprire qui.");
                        } else {
                            if (p.getObject() != null) {
                                if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                                    if (p.getObject() instanceof AdvObjectContainer) {
                                        AdvObjectContainer c = (AdvObjectContainer) p.getObject();
                                        if (!c.getList().isEmpty()) {
                                            out.println("Hai aperto: " + p.getObject().getDescription());
                                            Iterator<AdvObject> it = c.getList().iterator();
                                            while (it.hasNext()) {
                                                AdvObject oggetto = it.next();
                                                getCurrentRoom().getObjects().add(oggetto);
                                                if (oggetto.isPickupable() == false) { //se l'ggetto è stato pickato puoi rimuoverlo
                                                    it.remove();
                                                    getCurrentRoom().getObjects().remove(oggetto);
                                                    System.out.println(p.getObject().getName() + " non ha nulla di raccoglibile\n Hai già raccolto tutto");
                                                    p.getObject().setOpenable(false);
                                                } else if (oggetto.isPickupable() == true) {
                                                    out.print(c.getName() + " contiene: ");
                                                    out.print(" " + oggetto.getDescription() + " ");
                                                    System.out.println("\nOggetto raccoglibile: " + oggetto.getName());

                                                }
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
                                            out.print(c.getName() + " contiene: ");
                                            Iterator<AdvObject> it = c.getList().iterator();
                                            while (it.hasNext()) {
                                                AdvObject next = it.next();
                                                getInventory().add(next);
                                                out.print(" " + next.getDescription());
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
                        }
                        break;
                    case PUSH:
                        //ricerca oggetti pushabili
                        if (p.getObject() != null && p.getObject().isPushable()) {
                            out.println("Hai premuto: " + p.getObject().getName());
                            if (p.getObject().getId() == 6 && getCurrentRoom().getId() == 9) {
                                secret(out);
                                System.out.println("Chissà cosa sarà nascosto dentro...\n Che ne dici di entrare?\n La camera si trova a destra");
                                getCurrentRoom().setVisible(true); //nel caso l'utente volesse andarci autonomamente
                            }
                        } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                            out.println("Hai premuto: " + p.getInvObject().getName());

                        } else {
                            out.println("Non ci sono oggetti che puoi premere qui.");
                        }
                        break;
                    case MAP:
                        art.writeMap();
                        out.println("Posizione attuale: " + getCurrentRoom().getName());
                        break;
                    case HELP:
                        //se il giocatore non sa cosa può fare
                        System.out.println("Direzioni: Nord, Sud, Est, Ovest\n"
                                + "Osserva\n"
                                + "Prendi\n"
                                + "Apri\n"
                                + "Premi\n"
                                + "Mappa\n"
                                + "Esci\n"
                                + "Inventario");

                    default:
                        break;
                }
            }
            if (noroom) {
                out.println("Da quella parte non puoi andarci c'è un muro!");
            } else if (move) {
                out.println("Posizione attuale: " + getCurrentRoom().getName() + " \n" + getCurrentRoom().getDescription());
            }
        }
    }

    private void secret(PrintStream out) {  //apertura stanza segreta
        out.println("*crick* Si apre una porta per una stanza segreta..");

    }

    private void win(PrintStream out) {
        out.println("Complimenti, hai vinto!\n"
                + "Con questa avventura vogliamo comunicare a tutti i ragazzi della nostra età\n"
                + "che i nostri genitori sono esauriti e possono impazzire,\n"
                + "quindi se non vuoi fare la fine di Jacob,\n"
                + "ti conviene rispondere al cellulare!");
        System.exit(0);
    }

    private void lose(PrintStream out) {
        out.println("Hai trovato tutti gli indizi, ma non sei riuscito/a a trovare il coplpevole. Hai perso!");
        System.exit(0);
    }
}
