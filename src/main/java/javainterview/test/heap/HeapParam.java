package javainterview.test.heap;

/**
 *
 * Java调优工具：VisualVM
 * -Xms 660m -Xmx 600m 初始对空间内存  堆的最大内存
 *
 * -XX:NewRadio   :   设置新生代与老年代比例。默认是2，即1:2
 * -XX：SurvivorRadtio   :   设置新生代中Eden区与Survivor区的比例 默认值是8，及8:1:1
 * -XX:-UserAdaptiveSizePolicy   :   关闭自适应的内存分配策略
 * -XX:+PrintGCDetails   :   显示垃圾回收细节
 * -XX:MaxTenuringTheshold=   :   在Servivor区多少次后进入老年代，默认15
 *         但有可能不超过15就会进入老年代（YGC/MinorGC后，Eden+Survivor区(from)存活的对象超过了Survivor区(to区)的大小，超过部分进入老年代
 *         也有可能一开始直接进入老年代(大对象)
 *         也有可能Servivor区中相同年龄的所有对象大小总和大于Servivor区的一半，年龄大于等于该对象直接进入老年区(动态对象年龄判断)
 *
 * Minor GC / Young GC   :   只是新生代的垃圾收集 （只有Eden区满了会触发，Survivor不会触发）
 *          Minor GC会引发STW(Stop-The-World机制),暂停其他用户线程(产生对象)，直到GC结束
 * Major GC / Old GC   :   只是老年代的垃圾收集
 *         目前只有CMS GC垃圾收集器会有单独收集老年代行为
 * Full GC   :   整个堆和方法区(Java8改为元空间)的垃圾回收
 *
 * -XX:HandlePromotionFailure=true   空间分配担保策略   （JDK6之后弃用，之后默认是true）
 *          Minor GC前，JVM检查老年代最大可用的连续空间是否大于新生代所有对象的总空间
 *              若大于，则此次Minor GC是安全的
 *              若小于
 *                  如果为true，则检查老年代最大可用连续空间是否大于历次晋升到老年代的对象的平均大小
 *                      若大于，尝试进行Minor GC，但Minor GC仍有风险
 *                      若小于，则进行Full GC
 *                  如果为false，则进行Full GC
 * 老年代最大可用的连续空间大于新生代所有对象的总空间   或   老年代最大可用连续空间大于历次晋升到老年代的对象的平均大小      Minor GC
 *
 * Java8之后永久代变成了元空间
 *      其中intern字符串的缓存和静态变量以前在永久代，更新后并不是分配到元空间
 *      而是进行了堆上分配
 */
public class HeapParam {
}
