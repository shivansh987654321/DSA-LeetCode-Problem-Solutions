class LRUCache {
    class Node{
        int key , value;
        Node prev , next;
        Node(int k , int v){
            key = k;
            value = v;
        }
    }
    HashMap<Integer ,Node> map;
    Node head;
    Node tail;
    int cap;
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    void addToFront(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node temp = map.get(key);
        removeNode(temp);
        addToFront(temp);
        return temp.value;
    }
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            temp.value = value;
            removeNode(temp);
            addToFront(temp);
        }else{
            if(map.size() == cap){
                Node temp = tail.prev;
                removeNode(temp);
                map.remove(temp.key);
            }
            Node newnode = new Node(key , value);
            addToFront(newnode);
            map.put(key , newnode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */