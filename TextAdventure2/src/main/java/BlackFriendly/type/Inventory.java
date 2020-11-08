/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackFriendly.type;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Inventory è la classe che contiene la struttura dati di tipo lista,ovvero collezione di oggetti, utilizzata per registare gli oggetti posseduti nell'inventario
 * @author Rigante Chiara
 * @author Ramkalawon Alessia
 * @author Scalzo Andrea
 */
public class Inventory {
    
    private List<AdvObject> list = new ArrayList<>();

    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    public void add(AdvObject o) {
        list.add(o);
    }

    public void remove(AdvObject o) {
        list.remove(o);
    }
}
