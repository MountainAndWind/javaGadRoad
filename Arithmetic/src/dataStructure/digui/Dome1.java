package dataStructure.digui;

/**
 * @description:  递归
 * 递归需要满足的三个条件刚刚这个例子是非常典型的递归，那究竟什么样的问题可以用递归来解决呢？
 *     我总结了三个条件，只要同时满足以下三个条件，就可以用递归来解决。
 *     1. 一个问题的解可以分解为几个子问题的解
 *     2. 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
 *     3. 存在递归终止条件
 *     写递归代码最关键的是写出递推公式，找到终止条件
 *     一个问题只需要分解为一个子问题”，我们很容易能够想清楚“递“和”归”的每一个步骤，所以写起来、理解起来都不难。
 *          当我们面对的是一个问题要分解为多个子问题的情况，递归代码就没那么好理解了
 *
 *     如果一个问题 A 可以分解为若干子问题 B、C、D，你可以假设子问题 B、C、D 已经解决，在此基础上思考如何解决问题 A。而且，你只需要思考问题
 *        A 与子问题 B、C、D 两层之间的关系即可，不需要一层一层往下思考子问题与子子问题，子子问题与子子子问题之间的关系。屏蔽掉递归细节，这样子
 *        理解起来就简单多了。
 *
 *     编写递归代码的关键是，只要遇到递归，我们就把它抽象成一个递推公式，不用想一层层的调用关系，不要试图用人脑去分解递归的每个步骤。
 *
 *     注：
 *         1、递归代码要警惕堆栈溢出
 *            我在“栈”那一节讲过，函数调用会使用栈来保存临时变量。每调用一个函数，都会将临时变量封装为栈帧压入内存栈，等函数执行完成返回时，才出栈。
 *            系统栈或者虚拟机栈空间一般都不大。如果递归求解的数据规模很大，调用层次很深，一直压入栈，就会有堆栈溢出的风险。
 *            限制递归调用的最大深度的方式来解决这个问题 ，递归调用超过一定深度（比如 1000）之后，我们就不继续往下再递归了，直接返回报错。
 *         2、递归代码要警惕重复计算
 *            公式为： f(n-1) + f(n-2);出口条件为if (n == 1) return 1;if (n == 2) return 2;就回导致可能存在对各重复计算
 *            为了避免重复计算，我们可以通过一个数据结构（比如散列表）来保存已经求解过的 f(k)。当递归调用到 f(k) 时，先看下是否已经求解过了。
 *            如果是，则直接从散列表中取值返回，不需要重复计算，这样就能避免刚讲的问题了。
 *         3、递归调用一次就会在内存栈中保存一次现场数据，所以在分析递归代码空间复杂度时需要额外考虑这部分的开销。
 *
 *     todo
 *     调试递归:
 *         1.打印日志发现，递归值。
 *         2.结合条件断点进行调试。
 *
 * @author: slfang
 * @time: 2020/7/2 15:50
 */
public class Dome1 {

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
        }
    }
}
