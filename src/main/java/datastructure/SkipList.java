package datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<K extends Comparable<K>, V> implements Iterable<K> {
    private int listsize;
    private double pb;
    protected static final Random randomGen = new Random();
    protected static final double DEFAULT_PB = 0.5;
    private NodeKeyValue<K, V> head;
    public SkipList() {
        this(DEFAULT_PB);
    }
    public SkipList(double pb) {
        this.head = new NodeKeyValue<K, V>(null, null, 0);
        this.pb = pb;
        this.listsize = 0;
    }
    public V get(K key) {
        checkKeyValid(key);
        NodeKeyValue<K, V> listnode = findNode(key);
        if (listnode.getKey().compareTo(key) == 0)
            return listnode.getValue();
        else
            return null;
    }
    public void add(K key, V value) {
        checkKeyValid(key);
        NodeKeyValue<K, V> listnode = findNode(key);
        if (listnode.getKey() != null && listnode.getKey().compareTo(key) == 0) {
            listnode.setValue(value);
            return;
        }
        NodeKeyValue<K, V> newlistNode = new NodeKeyValue<K, V>(key, value, listnode.getLevel());
        horizontalInsertList(listnode, newlistNode);
        int curLevel = listnode.getLevel();
        int headlistLevel = head.getLevel();
        while (isBuildLevel()) {
            if (curLevel >= headlistLevel) {
                NodeKeyValue<K, V> newHeadEle = new NodeKeyValue<K, V>(null, null, headlistLevel + 1);
                verticalLink(newHeadEle, head);
                head = newHeadEle;
                headlistLevel = head.getLevel();
            }
            while (listnode.getUp() == null) {
                listnode = listnode.getPrevious();
            }
            listnode = listnode.getUp();
            NodeKeyValue<K, V> tmp = new NodeKeyValue<K, V>(key, value, listnode.getLevel());
            horizontalInsertList(listnode, tmp);
            verticalLink(tmp, newlistNode);
            newlistNode = tmp;
            curLevel++;
        }
        listsize++;
    }
    public void remove(K key) {
        checkKeyValid(key);
        NodeKeyValue<K, V> listnode = findNode(key);
        if (listnode == null || listnode.getKey().compareTo(key) != 0)
            throw new NoSuchElementException("Key does not exist!");
        while (listnode.getDownList() != null)
            listnode = listnode.getDownList();
        NodeKeyValue<K, V> previous = null;
        NodeKeyValue<K, V> next = null;
        for (; listnode != null; listnode = listnode.getUp()) {
            previous = listnode.getPrevious();
            next = listnode.getNext();
            if (previous != null)
                previous.setNext(next);
            if (next != null)
                next.setPreviousVal(previous);
        }
        while (head.getNext() == null && head.getDownList() != null) {
            head = head.getDownList();
            head.setUp(null);
        }
        listsize--;
    }
    public boolean contains(K key) {
        return get(key) != null;
    }
    public int listsize() {
        return listsize;
    }
    public boolean empty() {
        return listsize == 0;
    }
    protected NodeKeyValue<K, V> findNode(K key) {
        NodeKeyValue<K, V> listnode = head;
        NodeKeyValue<K, V> next = null;
        NodeKeyValue<K, V> down = null;
        K nodeKey = null;
        while (true) {
            next = listnode.getNext();
            while (next != null && lessThanEqual(next.getKey(), key)) {
                listnode = next;
                next = listnode.getNext();
            }
            nodeKey = listnode.getKey();
            if (nodeKey != null && nodeKey.compareTo(key) == 0)
                break;
            down = listnode.getDownList();
            if (down != null) {
                listnode = down;
            } else {
                break;
            }
        }
        return listnode;
    }
    protected void checkKeyValid(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key must be not null!");
    }
    protected boolean lessThanEqual(K a, K b) {
        return a.compareTo(b) <= 0;
    }
    protected boolean isBuildLevel() {
        return randomGen.nextDouble() < pb;
    }
    protected void horizontalInsertList(NodeKeyValue<K, V> a, NodeKeyValue<K, V> b) {
        b.setPreviousVal(a);
        b.setNext(a.getNext());
        if (a.getNext() != null)
            a.getNext().setPreviousVal(b);
        a.setNext(b);
    }
    protected void verticalLink(NodeKeyValue<K, V> a, NodeKeyValue<K, V> b) {
        a.setDown(b);
        b.setUp(a);
    }
    @Override
    public String toString() {
        StringBuilder stringbuild = new StringBuilder();
        NodeKeyValue<K, V> listnode = head;
        while (listnode.getDownList() != null)
            listnode = listnode.getDownList();
        while (listnode.getPrevious() != null)
            listnode = listnode.getPrevious();
        if (listnode.getNext() != null)
            listnode = listnode.getNext();
        while (listnode != null) {
            stringbuild.append(listnode.toString()).append("\n");
            listnode = listnode.getNext();
        }
        return stringbuild.toString();
    }
    @Override
    public Iterator<K> iterator() {
        return new SkipListIterator<K, V>(head);
    }
    protected static class SkipListIterator<K extends Comparable<K>, V> implements Iterator<K> {
        private NodeKeyValue<K, V> listnode;
        public SkipListIterator(NodeKeyValue<K, V> listnode) {
            while (listnode.getDownList() != null)
                listnode = listnode.getDownList();
            while (listnode.getPrevious() != null)
                listnode = listnode.getPrevious();
            if (listnode.getNext() != null)
                listnode = listnode.getNext();
            this.listnode = listnode;
        }
        @Override
        public boolean hasNext() {
            return this.listnode != null;
        }
        @Override
        public K next() {
            K result = listnode.getKey();
            listnode = listnode.getNext();
            return result;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    protected static class NodeKeyValue<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private int skiplevel;
        private NodeKeyValue<K, V> up, down, next, previous;
        public NodeKeyValue(K key, V value, int skiplevel) {
            this.key = key;
            this.value = value;
            this.skiplevel = skiplevel;
        }
        @Override
        public String toString() {
            StringBuilder stringbuild = new StringBuilder();
            stringbuild.append("Node[")
                    .append("key:");
            if (this.key == null)
                stringbuild.append("None");
            else
                stringbuild.append(this.key.toString());
            stringbuild.append(", value:");
            if (this.value == null)
                stringbuild.append("None");
            else
                stringbuild.append(this.value.toString());
            stringbuild.append("]");
            return stringbuild.toString();
        }
        public K getKey() {
            return key;
        }
        public void setKey(K key) {
            this.key = key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public int getLevel() {
            return skiplevel;
        }
        public void setLevel(int skiplevel) {
            this.skiplevel = skiplevel;
        }
        public NodeKeyValue<K, V> getUp() {
            return up;
        }
        public void setUp(NodeKeyValue<K, V> up) {
            this.up = up;
        }
        public NodeKeyValue<K, V> getDownList() {
            return down;
        }
        public void setDown(NodeKeyValue<K, V> down) {
            this.down = down;
        }
        public NodeKeyValue<K, V> getNext() {
            return next;
        }
        public void setNext(NodeKeyValue<K, V> next) {
            this.next = next;
        }
        public NodeKeyValue<K, V> getPrevious() {
            return previous;
        }
        public void setPreviousVal(NodeKeyValue<K, V> previous) {
            this.previous = previous;
        }
    }
    public static void main(String[] args) {
        SkipList<Integer, String> skip = new SkipList<>();
        for (int i = 20; i < 35; i++) {
            skip.add(i, String.valueOf(i));
        }
        System.out.println(skip);
        assert skip.listsize() == 10;
        int count = 0;
        for (Integer i : skip)
            assert i.equals(count++);
        skip.remove(23);
        System.out.println(skip);
        skip.remove(25);
        skip.remove(33);
        skip.remove(30);
        System.out.println(skip);
        skip.remove(28);
        skip.add(25, "25");
        System.out.println(skip);
        assert skip.listsize() == 0;
        assert skip.empty();
    }
}