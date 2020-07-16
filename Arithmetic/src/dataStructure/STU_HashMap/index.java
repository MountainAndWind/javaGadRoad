package dataStructure.STU_HashMap;

import java.util.*;

/**
 *
 * 学习作业：实现一个简单的HashMap&散列表
 * @description:介绍
 *
 * 三点散列函数设计的基本要求：
 *     1、散列函数计算得到的散列值是一个非负整数；
 *     2、如果 key1 = key2，那 hash(key1) == hash(key2)；
 *     3、如果 key1 ≠ key2，那 hash(key1) ≠ hash(key2)。
 *    第三点理解起来可能会有问题，我着重说一下。这个要求看起来合情合理，但是在真实的情况下，要想找到一个不同的 key 对应的散列值都不一样的散列函数，
 *    几乎是不可能的。即便像业界著名的MD5、SHA、CRC等哈希算法，也无法完全避免这种散列冲突。而且，因为数组的存储空间有限，也会加大散列冲突的概率。
 *
 *
 * 散列冲突的处理
 *    开放寻址法（open addressing）和链表法（chaining）。  优缺点[需要拉很多链表。散列表中的数据都存储在数组中，可以有效地利用 CPU 缓存加快查询速度,序列化起来比较简单]
 *
 *    开发寻址法：  优缺点【链表法对内存的利用率比开放寻址法要高，不是连续的，所以对 CPU 缓存是不友好的】
 *        1、线性探测（当我们往散列表中插入数据时，如果某个数据经过散列函数散列之后，存储位置已经被占用了，我们就从当前位置开始，依次往后查找，看是否有空闲位置，直到找到为止。）
 *        2、二次探测（所谓二次探测，跟线性探测很像，线性探测每次探测的步长是 1，那它探测的下标序列就是 hash(key)+0，hash(key)+1，hash(key)+2……而二次探测探测的步长就变成了原来的“二次方”，也就是说，它探测的下标序列就是 hash(key)+0，hash(key)+12，hash(key)+22……）
 *        3、双重散列（双重散列，意思就是不仅要使用一个散列函数。我们使用一组散列函数 hash1(key)，hash2(key)，hash3(key)……我们先用第一个散列函数，如果计算得到的存储位置已经被占用，再用第二个散列函数，依次类推，直到找到空闲的存储位置。）
 *
 *    总结：当数据量比较小、装载因子小的时候，适合采用开放寻址法。这也是 Java 中的ThreadLocalMap使用开放寻址法解决散列冲突的原因。
 *          我总结一下，基于链表的散列冲突处理方法比较适合存储大对象、大数据量的散列表，而且，比起开放寻址法，它更加灵活，支持更多的优化策略，比如用红黑树代替链表。
 *
 *
 *   链表法：hashMap1.7实现
 *
 *   散列的表的优化在于减少hash冲突，使数据均匀的分布。以及扩容机制的优化。
 *
 *
 *   如何避免低效地扩容？
 *       为了解决一次性扩容耗时过多的情况，我们可以将扩容操作穿插在插入操作的过程中，
 *       分批完成。当装载因子触达阈值之后，我们只申请新空间，但并不将老的数据搬移到新散列表中。
 *       当有新数据要插入时，我们将新数据插入新散列表中，并且从老的散列表中拿出一个数据放入到新散列表。
 *       每次插入一个数据到散列表，我们都重复上面的过程。经过多次插入操作之后，老的散列表中的数据就一点一
 *       点全部搬移到新散列表中了。这样没有了集中的一次性数据搬移，插入操作就都变得很快了。
 *
 * HashMap
 *    插入一个数据，最好情况下，不需要扩容，最好时间复杂度是 O(1)。最坏情况下，散列表装载因子过高，启动扩容，我们需要重新申请内存空间，
 *    重新计算哈希位置，并且搬移数据，所以时间复杂度是 O(n)。用摊还分析法，均摊情况下，时间复杂度接近最好情况，就是 O(1)。
 *
 * 基于版本1.7学习  散列链表(数组+链表) 键值对
 * 2、table声明为局部变量 为了安全
 * 3、i = (n - 1) & hash  计算数组索引值  jdk7是 int index=hash & (tab.length-1)
 *    此处为什么采用&运算，且（n要减1）？
 *        HashMap 的初始长度为 16，且每次扩容都必须以 2 的倍数（2^n）扩充。因为在 HashMap 中，采用按位与运算（&）代替取模运算（&），当 b = 2^n 时，a % b = a & (b - 1) 。
 *
 *       【运算规则：0^0=0；  0^1=1；  1^0=1；   1^1=0；：参加运算的两个对象，如果两个位为“异”（值不同），则该位结果为1，否则为0。
 *                                                                   0&0=0；0&1=0；1&0=0；1&1=1即：两个同时为1，结果为1，否则为0 】
 *       hash值得计算 （ 1.8 return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); 1.7  return h ^ (h >>> 7) ^ (h >>> 4);）
 *      & 位运算  都为1才为1 与运算取小范围
 *
 *      hash函数解决高位相同地位不同&运算出的结果相同。
 *
 * 4、数组 Entry (1.7) 其中存储key value hash 以及执政next
 *        node(1.8)
 * 5、put操作e.hash == hash && ((k = e.key) == key || key.equals(k)) key 相同会覆盖
 * 6、put方法时候，扩容机制：resize(2 * table.length);  entry需要重新hash
 *                 加入值得时候  table[bucketIndex] = new Entry<>(hash, key, value, e); 会让新加入值放在链表的第一个位置，然后设置其next指针指向之前第一个位置的值
 * 7、remove方法if (prev == e)
                    table[i] = next;
                 else
                    prev.next = next;

   扩展：LRUCache算法
   linkedhashMap  双列回环循环链表
   1、private final boolean accessOrder; 访问排序设置值，访问时会将数据放到队尾
   2、private transient Entry<K,V> header;  头结点
   3、重写HashMap中的addEntry方法
          addEntry方法中Entry<K,V> eldest = header.after;证明名了回环
          eldest为对头（用来移除，出队）  header为对尾（入队）
          removeEldestEntry 需要子类重写
 * @author: slfang
 * @time: 2020/3/14 16:54
 */
public class index {
    /*从集成关系可以看到，HashMap继承AbstractMap LinkedHashMap继承于HashMap 而TreeMap 是实现SortMap和实现AbstractMap*/
    /*Fail-Fast 机制Fail-Fast 机制
    *     java.util.HashMap 不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了 map，那么将抛出 ConcurrentModificationException，这就是所谓 fail-fast 策略。
    *     这一策略在源码中的实现是通过 modCount 域，modCount 顾名思义就是修改次数，对 HashMap 内容（当然不仅仅是 HashMap 才会有，其他例如 ArrayList 也会）的修改都将
    *     增加这个值（大家可以再回头看一下其源码，在很多操作中都有 modCount++ 这句），那么在迭代器初始化过程中会将这个值赋给迭代器的 expectedModCount。
    *     注意到 modCount 声明为 volatile，保证线程之间修改的可见性。
    *     注意:迭代器的快速失败行为不能得到保证，一般来说，存在非同步的并发修改时，不可能作出任何坚决的保证。快速失败迭代器尽最大努力抛出 ConcurrentModificationException。
    *     因此，编写依赖于此异常的程序的做法是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。
    *     解决方案：
    *         在上文中也提到，fail-fast 机制，是一种错误检测机制。它只能被用来检测错误，因为 JDK 并不保证 fail-fast机制一定会发生。若在多线程环境下使用 fail-fast 机制的集合，
    *         建议使用“java.util.concurrent 包下的类”去取代“java.util 包下的类”
    * */
    public static void main(String[] args) {

        /*散列链表(数组+链表) 键值对
        *1、特性hashMap允许使用 null 值和 null 键 初始化并未初始table而是使用懒加载方式，当执行到put方法时才会去初始化table
        * 2、内部常量属性：
        *     modCount：修改次数，因为HashMap是线程不安全，如果在迭代的过程中HashMap被其他线程修改了,modCount
        * 的数值就会发生变化, 这个时候expectedModCount和ModCount不相等,迭代器就会抛出ConcurrentModificationException()异常。
        *     loadFactory：加载因子，0.75（负载因子 loadFactor 衡量的是一个散列表的空间的使用程度，负载因子越大表示散列表的装填程度越高，反之愈小。对于使用链表法的散列表来说，
        * 查找一个元素的平均时间是 O(1+a)，因此如果负载因子越大，对空间的利用更充分，然而后果是查找效率的降低；如果负载因子太小，那么散列表的数据将过于稀疏，
        * 对空间造成严重浪费。）
        *     threshold（阀值）：capacity * loadFactory，当size > threshold时，会扩容。
        * 3、put方法:
        *     存在putForNullKey（）方法是存放null key的，他会存放在table[0]的位置
        *     初始化并未初始table而是使用懒加载方式，当执行到put方法时才会去初始化table
        *     int hash = hash(key)（hash方法中的运算增加散列）;int i = indexFor(hash, table.length);  h & (length-1);
        *（使用第一次hash出来的值与数组长度进行&[都为1为1其他为0]与运算，获得较小值范围）
        *     addEntry方法
        *          判断resize条件(size >= threshold) && (null != table[bucketIndex])
        *               transfer（）方法中循环遍历数组，数组中循环遍历链表。核心逻辑 e.next = newTable[i];newTable[i] = e;采用头插入法
        *     createEntry（）采用头插入法
        * 4、Entry 基本存储元素 其成员属性有key,value,hash，以及next索引
        * 5、hashMap的遍历
        *     高效处理：Iteratoriter=map.entrySet().iterator()  低效遍历Iteratoriter=map.keySet().iterator()
        * */
        HashMap hasMap = new HashMap();

        /* 1、介绍：
              LinkedHashMap 是 HashMap 的一个子类，它保留插入的顺序，如果需要输出的顺序和输入时的相同，那么就选用 LinkedHashMap。
        *底层使用哈希表与双向链表来保存所有元素
        *     LinkedHashMap 实现与 HashMap 的不同之处在于，LinkedHashMap 维护着一个运行于所有条目的双重链(双列回环循环链表)
        *接列表。此链接列表定义了迭代顺序，该迭代顺序可以是插入顺序或者是访问顺序。
        *    根据链表中元素的顺序可以分为：按插入顺序的链表，和按访问顺序(调用 get 方法)的链表。默认是按插入顺序
        *排序，如果指定按访问顺序排序，那么调用get方法后，会将这次访问的元素移至链表尾部，不断访问可以形成按访问顺序排序的
        *链表。设置（private final boolean accessOrder; 访问排序设置值，访问时会将数据放到队尾）
        *  2、实现：
        *     （1）它重新定义了数组中保存的元素 Entry在原有的基础上增加了before 与 after两个引用
        *     （2）private final boolean accessOrder; 【 如果为true，则按照访问顺序；如果为false，则按照插入顺序。】
        *     （3）private transient Entry<K,V> header;【双向链表的表头元素。】
        *     （4）该哈希映射的迭代顺序就是最后访问其条目的顺序，这种映射很适合构建 LRU 缓存。LinkedHashMap 提供了
        *            removeEldestEntry(Map.Entry<K,V> eldest) 方法。该方法可以提供在每次添加新条目时移除最旧条目的实
        *            现程序，默认返回 false，这样，此映射的行为将类似于正常映射，即永远不能移除最旧的元素。
        *     （5）扩展： LRU 缓存
        *                实现原理：a linkedList 是它本身已经实现了按照访问顺序的存储，也就是说，最近读取的会放在最前面，最最
                                 不常读取的会放在最后（当然，它也可以实现按照插入顺序存储）。
                                  b LinkedHashMap 本身有一个方法用
                                    于判断是否需要移除最不常读取的数，但是，原始方法默认不需要移除（这是，LinkedHashMap 相当于一个lin
                                    kedlist），所以，我们需要 override 这样一个方法，使得当缓存里存放的数据个数超过规定个数后，就把最不常
                                    用的移除掉。
        *
        * */
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TreeMap treeMap = new TreeMap();



        //testMapTraversed();
        testFangwenOrder();
    }

    /**
     * map遍历实践  测试LinkedhashMap是插入排序
     */
    private static void testMapTraversed() {
        HashMap map = new HashMap();
        map.put("张三",1);
        map.put("李四",2);
        map.put("王五",3);
        map.put("找钱",4);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry next =  (Map.Entry)iterator.next();
            System.out.println(next.getKey()+"--"+next.getValue());
        }
        LinkedHashMap map2 = new LinkedHashMap();
        map2.put("张三",1);
        map2.put("李四",2);
        map2.put("王五",3);
        map2.put("找钱",4);
        Iterator iterator2 = map2.entrySet().iterator();
        while (iterator2.hasNext()){
            Map.Entry next =  (Map.Entry)iterator2.next();
            System.out.println(next.getKey()+"--"+next.getValue());
        }
    }

    /**
     * 测试LinkedhashMap的访问排序,访问的放到队尾
     */
    static void testFangwenOrder(){
        Map<String, String> map = new LinkedHashMap<String, String>
                (16,0.75f,true);
        map.put("apple", "苹果"); map.put("watermelon", "西瓜");
        map.put("banana", "香蕉"); map.put("peach", "桃子");
        map.get("banana"); map.get("apple");
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }
}
