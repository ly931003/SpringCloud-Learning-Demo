JVM GC



可达性分析，GC Root

* 虚拟机栈中引用的对象
* 方法区静态属性引用的对象
* 方法区常量引用对象
* 本地方法栈JNI引用的对象



jps -l 查看Java线程

jinfo -flag arg pid 查看JVM参数

jinfo -flags pid 查看所有参数

-XX参数

-X参数

java -XX:+PrintFlagsInitial 初始参数

java -XX:+PrintFlagsFinal 修改参数 := 为修改过的

java -XX:+PrintCommandLineFlags 

-Xms -Xmx 元空间以外的堆空间 Eden(Minor GC) S0 S1 Old Memory(Major GC)

-Xmn 年轻代空间 Eden(Minor GC) S0 S1 

-XX:MetaspaceSize 元空间大小

-XX:PrintGCDetails GC细节展示

-XX:SurvivorRatio Eden比例 8:1:1  -XX:SurvivorRatio=4 Eden:S0:S1 = 4:1:1

-XX:NewRatio 老年代比例 2:1  -XX:NewRatio=4 New:Old = 4:1

-XX:MaxTenuringThreshold 垃圾最大年龄 15  -XX:NewRatio=4 New:Old = 4:1



## 强软弱虚引用

软引用 空间不足回收

弱引用 GC回收 WeakHashMap

虚引用 和引用队列联合使用，用于对象回收的监控



## OOM

StackOverflowError 深度递归

OutOfMemoryError: java heap space 对象过多内存过少

OutOfMemoryError: GC overhead limit exceeded 98%时间回收不到2%，多次

OutOfMemoryError: Direct buffer memory NIO分配本地内存，占满内存  -XX:MaxDirectMemorySize=5m

OutOfMemoryError: Unable to create new native thread 线程过多，实在不行修改系统配置 ulimit -u

OutOfMemoryError: Metaspace 创建过多类，代理类



## GC器

### Serial 串行垃圾回收

单线程回收，暂停用户线程，不适合做服务器

### Parallel 并行垃圾回收 Java 8 默认

多线程回收，暂停用户线程，并行回收较快，适合弱交互的服务器。

单核情况下线程切换更慢

stop the world STW 全停

### CMS 并发标记清除

并发处理，多个短暂停，垃圾回收同时可运行，响应时间有保障

### G1

分割堆内存成不同区域并发收集

### UseSerialGC UseParallelGC UseConcMarkSweepGC UseParNewGC UseParallelOldGC UseG1GC

### UseSerialGC

使用 Serial + Serial Old，DefNew + Tenured 新生代使用复制算法，老年代使用标记整理算法 ，均为串行

### UseParNewGC 过时

使用 ParNew + Serial Old， ParNew + Tenured 新生代使用并行复制算法，老年代使用标记整理算法

-XX:ParallelGCThreads 并行线程数，默认CPU核数

### UseParallelGC UseParallelOldGC 

使用 Parallel Scavenge + Serial Old， PSYoungGen + Tenured 新生代使用并行复制算法，老年代使用标记整理算法

Parallel Scavenge 俗称吞吐量优先收集器，后台运算，可自适应调节停顿时间

-MaxGCPauseMillis GC停顿时间

-XX:ParallelGCThreads 并行线程数，默认CPU核数

JDK 1.6 使用 UseParallelGC 为 Parallel Scavenge + Serial Old，之后为 Parallel Scavenge + Parallel Old PSYoungGen + ParOldGen

### UseConcMarkSweepGC 

使用 ParNew + CMS + Serial Old(CMS后备)

初始标记 并发标记 重新标记 并发清除 并发标记和并发清除耗时较长但是和用户线程并发操作

停顿低 并发执行CPU压力大，标记清除产生碎片

## 垃圾收集器选择

* 单CPU

  -XX:+UseSerialGC

* 多CPU，需要最大吞吐，后台计算

  -XX:+UseParallelGC  -XX:+UseParallelOldGC  

* 多CPU，低停顿时间，互联网

  -XX:+UseParNewGC -XX:+UseConcMarkSweepGC 

## G1垃圾收集器

高CPU、高内存，停顿时间端，吞吐量高

区域化内存划片Region，每个区域2~32MB，最多2048块

-XX:+UseG1GC

-XX:G1HeapRegionSize=

-XX:MaxGCPauseMillis=

-XX:InitiatingHeapOccupancyPercent=

-XX:ConcGCThreads=

-XX:G1ReservePercent=

### 回收步骤

针对Eden进行收集，Eden耗尽触发，小区域收集和形成连续内存块

Eden区移动S区，S区满了升级为Old区，S区移动到新的位置，部分升级为Old

初始标记 并发标记 最终标记 筛选回收

### CMS相比的优势

G1没有碎片，可以精确控制停顿

### 微服务SpringBoot调优

java -server -Xms1024m -Xmm1024m -XX:+UseG1GC -jar
