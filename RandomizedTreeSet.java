import java.util.ArrayList;
import java.util.Collections;
public class RandomizedTreeSet<K> extends TreeSet<K> {
    @Override
    public void insertList(ArrayList<K> list){
        ArrayList<K> listCopy = new ArrayList<K>();
        for (K key : list) listCopy.add(key);
        Collections.shuffle(listCopy);
        for (K key : listCopy) put(key);
    }
}
