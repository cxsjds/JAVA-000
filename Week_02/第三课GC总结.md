第三课作业以及总结

   作业1 使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。
   
   预先通过javac GCLogAnalysis.java 和javaGCLogAnalysis命令编译该java文件。
   
串行GC：
   1.java -XX:+UseSerialGC -Xms512m -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
   2.年轻代垃圾回收：Serial收集器是一个单线程的收集器，即在它进行垃圾收集时，其他工作线程要暂停，直到收集结束。年轻代采用复制算法，即将内存分为两部分，每次使用一部分，当该内存快满时，就复制对象到另一个内存中，再将该内存清空。没有多余开销，单线程收集效率较高。
   3.老年代垃圾回收：Serial Old是它的老年代收集，采用标记-整理算法，即在标记阶段，该算法也将所有对象标记为存活和死亡两种状态；不同的是，在第二个阶段，该算法并没有直接对死亡的对象进行清理，而是将所有存活的对象整理一下，放到另一处空间，然后把剩下的所有对象全部清除。耗时，收集效率不高。
   
并行GC：
   1.java -XX:+UseParallelGC -Xms512m -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
   2.年轻代：Parallel Scavenge收集器是一个新生代收集器，它也是使用复制算法的收集器，又是并行的多线程收集器。特点在于是一个高吞吐量收集器，提高CPU的利用效率。
   3.老年代：Parallel Old是老年代版本，多线程，采用标记-整理算法。特点在于和Parallel Scavenge收集器联合使用适应吞吐量高和CPU资源敏感的场合。
   
CMS GC：
   1.java -XX:+UseConcMarSweepGC -Xms512m -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
   2.CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。年轻代采用复制算法，老年代采用标记-清除算法，即为每个对象存储一个标记位，记录对象的状态（活着或是死亡）。 分为两个阶段，一个是标记阶段，这个阶段内，为每个对象更新标记位，检查对象是否死亡；第二个阶段是清除阶段，该阶段对死亡的对象进行清除，执行 GC 操作。一般步骤为：
   a.初始标记
   b.并发标记
   c.重新标记
   d.并发清除
   常常用于低时延的场景中，但会产生浮动碎片和大量空间碎片。
   
G1 GC：
   1.java -XX:+UseG1GC -Xms512m -Xms512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
   2.G1（Garbage-First）是一款面向服务端应用的垃圾收集器。它能够适应于并行和并发的环境，同时能够分代收集。同时能够空间整合，G1不会出现空间碎片，更核心在于G1除了能进一步降低时延外，还能够进行停顿时间的预测。采用标记-整理-复制算法。执行步骤为：
   a. 初始标记
   b. 并发标记
   c. 最终标记
   d. 筛选回收
   适用于内存较大的场景。

作业2 使用压测工具（wrk或sb）, 演练gateway-server-0.0.1-SNAPSHOT.jar示例。
得出猜想：内存占用不大，GC能够应对当前场景时，除了串行不适应与web场景，其余GC差距不是很大。

