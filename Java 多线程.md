# Java 多线程

## volatile

轻量级同步机制，保证可见性、不保证原子性、禁止指令重排

### 可见性

### 禁止指令重排

通过内存屏障

DCL 双端检锁机制 + volatile 确保 new 不发生指令重排



## JMM Java内存模型

本地内存保存主内存中共享变量的副本，变量操作在本地内存中操作，操作完成后会写回主内存，本地内存无法共享，线程通信必须通过主内存。

### 可见性

数据

### 原子性



### 有序性

指令重排，单线程下确保代码执行结果和代码顺序执行一致。

重排会考虑数据的依赖性。

## 保障线程安全

synchronize和volatile可以保证可见性

volatile保证有序性，禁止指令重排

## CAS 比较交换

CAS是CPU指令原语，只能保证一个变量的原子性

底层基于自旋锁实现，自旋在高并发下可能给CPU带来很大开销

使用Unsafe类的native方法实现

可能存在ABA问题，通过版本校验规避， AtomicStampedReference<V>

## 集合并发修改错误



```java
Collections.synchronizedList(new ArrayList<>());
Collections.synchronizedSet(new HashSet<>());

import java.util.concurrent.CopyOnWriteArrayList;
new CopyOnWriteArrayList<>();
```



## Java 锁

### 公平锁

ReentrantLock 默认非公平，非公平会先抢占再排队，吞吐量大。

公平锁会先查询等待队列

### 可重入锁 递归锁

ReentrantLock 和 synchronize关键字 都可视为 可重入锁

获得锁的线程可以进入同一个锁的代码

最大作用是避免死锁









t.me/guomotaotu





## 线程池

### Runnable Callable<T>

Callable<T> 带返回值和异常，可以用FutureTask类包装Callable<T>。

FutureTask一个实例只能运行一次。

### 池化技术

避免创建线程的额外消耗

###  ThreadPoolExecutor

* Executors.newCachedThreadPool(); 短期异步多个
* Executors.newSingleThreadExecutor();  单任务
* Executors.newFixedThreadPool(int nThread);  固定池，长期任务

#### int corePoolSize

核心常驻线程数

#### int maximumPoolSize

最大扩容线程数

#### long keepAliveTime

非核心线程存活时间

#### TimeUnit unit

存活时间单位

#### BlockingQueue<Runnable> workQueue

等待执行任务阻塞队列

#### ThreadFactory threadFactory

线程工厂

#### RejectedExecutionHandler handler

* AbortPolicy 抛出异常
* DiscardPolicy 丢弃任务
* DiscardOldestPolicy poll阻塞队列任务，重新添加
* CallerRunsPolicy 调用者运行

### 线程数量

IO密集：2n或 n/1-阻塞系数 0.8~0.9

## 死锁

### 原因

争抢资源互相等待，若无外力干涉，无限等待

## 排查

```sh
jps -l
jstack pid
```



