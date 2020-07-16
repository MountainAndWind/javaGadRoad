package dataStructure.STU_HashMap;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @description：自己实现简单hashMap
 * @author: slfang
 * @time: 2020/3/20 21:25
 */
public class HashMapZlk <K,V>
        extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable{

    /**
     * 默认加载加载因子
     */
    static float DEFAULT_LOADFACTORY = 0.75f;

    /**
     * 默认容量值 16  链表头的个数  也可以称之为桶的个数
     */
    static int DEFAULT_INITIAL_CAPACITY =  1<<4;//16

    /**
     * size :键值对的个数
     * @param args
     */
    int size;

    /**
     * 阈值
     * @param args
     */
    int threshold;

    /**
     * 保证线程安全 涉及到快速失败
     * @param args
     */
    int modCount;

    /**
     * 桶的个数
     * @param args
     */
    int capacity;

    /**
     * 加载因子
     * @param args
     */
    float factory;

    /**
     * 最大桶容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * Entry 数组用于存储键值对
     */
    transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;
    /**
     * 空数组
     */
    static final Entry<?,?>[] EMPTY_TABLE = {};

    /**
     * 这个地方必须要写返回值，应为集成了抽象map
     * @param key
     * @param value
     * @return
     */
    public V put(K key,V value){
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);//初始化数组
        }
        if(key==null){
            return putForNullKey(value);//null key 的特殊处理
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        Entry<K,V> kvEntry = table[i];
        while (kvEntry!=null){
              if(kvEntry.hash==hash||(key==kvEntry.key&&key.equals(kvEntry.key))){
                    V oldValue = kvEntry.value;
                    kvEntry.value=value;
                    return oldValue;
             }
            kvEntry=kvEntry.next;
        }
        modCount++;
        addEntry(hash,key,value,i);
        return null;
    }


    /**
     * 获取k值
     * @return
     */
    public V get(Object key){

        if (size == 0) {
            return null;
        }


        if (key == null)
            return getForNullKey();

        int hash = hash(key);
        int index = indexFor(hash,table.length);
        Entry<K,V> kvEntry = table[index];
        while (kvEntry!=null){
            if(kvEntry.hash==hash||(kvEntry.key==key||kvEntry.key.equals(key))){
                return kvEntry.value;
            }
            kvEntry = kvEntry.next;
        }
        return null;
    }

    /**
     * 直接考，不是核心代码  可以从源码看出null key是放在第一个曹中
     */
    private V putForNullKey(V value) {
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                //e.recordAccess(this);  重写不管
                return oldValue;
            }
        }
        modCount++;
        addEntry(0, null, value, 0);
        return null;
    }


    /**
     * 顾名思义添加Entry
     */
    void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);//判断是否扩容
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    /***
     * 扩容机制
     * @param newSize
     */
    private void resize(int newSize) {
        if(newSize>=MAXIMUM_CAPACITY){
            threshold = Integer.MAX_VALUE;
            return ;
        }
        Entry<K,V>[] newTable = new Entry[newSize];
        Entry<K,V>[] currentable = table;
        for (Entry<K, V> e : currentable) {
           /* while (kvEntry.next!=null){
                int bucketIndex = indexFor(kvEntry.hash,newSize);//重新计算曹的位置
            }*/
            while(null != e) {
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash, newSize);

                //先获取已经存放到newTable中的第一个键值对，e.next 指向ta 然后下方把曹的第一个位置指向新加入的  头插法思想
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
        table=newTable;
        threshold = (int)Math.min(newSize * factory, MAXIMUM_CAPACITY + 1);//重新计算阈值
    }

    /**
     * 头插入法
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    private void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> node = table[bucketIndex];//
        Entry<K,V> data = new Entry<K,V>(hash,key,value,node);
        table[bucketIndex] = data;
        size++;
    }

    /**
     * 计算桶的位置
     * @param hash
     * @param length
     * @return
     */
    int indexFor(int hash,int length){
        return hash&(length-1);
    }

    /**
     * hash算法别看了  此处默认当做String处理使用String的hashCode
     */
    final int hash(Object k) {
        return ((String) k).hashCode();
        /*int h = hashSeed;
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);*/
    }

    /**
     * todo
     * 原来的一堆移位算法看不懂啥几把玩意，自己简化一下
     * 此方法看源码可以看出是初始化capacity,我再想一开始构造方法不是传递了capacity吗？为啥把那个参数值赋值给阈值
     * 我想了一下可能是为了复用代码吧，还没深看
     * @param tosize
     */
    private void inflateTable(int tosize) {
        capacity=tosize;
        threshold=(int) Math.min(capacity * factory, MAXIMUM_CAPACITY + 1);
        table = new Entry[capacity];
        initHashSeedAsNeeded(capacity);//hashSeed = useAltHashing给一个变量赋值，变量是与后买hash算法有关
    }

    /**
     * 看不懂此处就不copy复制没多大意义，
     * 此方法只需要主要两个：1 hashSeed 2 Holder（源码中是一个内部类） 大致看了下跟后面hash算法有关，大概是为了增加散列程度的吧
     * @param capacity
     */
    private void initHashSeedAsNeeded(int capacity) {
      /*  boolean currentAltHashing = hashSeed != 0;
        boolean useAltHashing = sun.misc.VM.isBooted() &&
                (capacity >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
        boolean switching = currentAltHashing ^ useAltHashing;
        if (switching) {
            hashSeed = useAltHashing
                    ? sun.misc.Hashing.randomHashSeed(this)
                    : 0;
        }
        return switching*/;
    }


    private V getForNullKey() {
        if (size == 0) {
            return null;
        }
        for (Entry<K,V> e = table[0]; e != null; e = e.next) {
            if (e.key == null)
                return e.value;
        }
        return null;
    }


    /**
     * entry 对象
     * @param <K>
     * @param <V>
     */
    private static class Entry<K,V> implements Map.Entry<K,V>{

        K key;
        V value;
        Entry<K,V> next;
        int hash;


        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

    }

    public HashMapZlk() {
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOADFACTORY);

    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * 此处是使用的懒加载，当第一次put的时候才会初始数组
     * @param capacity
     * @param factory
     */
    public HashMapZlk(int capacity,float factory){
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    capacity);
        if (capacity > MAXIMUM_CAPACITY)
            capacity = MAXIMUM_CAPACITY;
        if (factory <= 0 || Float.isNaN(factory))
            throw new IllegalArgumentException("Illegal load factor: " +
                    factory);
         this.factory = factory;
         threshold = capacity;//此处根据源码写的也不清楚为啥是threshold = capacity
         init();
    }

    public int size(){
        return size;
    }



    /**
     * 子类扩展
     */
    private void init() {
    }

    public static void main(String[] args) {

        /**
         * 扩容机制还没测试
         */
        HashMapZlk<String,Integer> hashMap = new HashMapZlk();
        hashMap.put("李四",1);
        hashMap.put("王五",2);
        hashMap.put("张骚男",3);
        hashMap.put("方世玉",4);
        hashMap.put("市场价",5);
        hashMap.put("开的",6);
        System.out.println(hashMap.size());
        System.out.println(hashMap.get("方世玉"));
        hashMap.put("方世玉",7);
        System.out.println(hashMap.get("方世玉"));
    }

}
