# Linux

## top

load average: 1min 5min 10min

uptime

## vmstat

```sh
vmstat -n 2 3
```

* procs 
  * r 运行 不能超过总核数的两倍
  * b 阻塞 
* cpu 
  * us 用户消耗CPU时间 长期大于50% 考虑优化
  * sy 系统消耗时间
  * us + sy 若大于80 则CPU不足

## free

```sh
free -m
pidstat -p pid -r internal
```

## df

```sh
df -h
```

## iostat

```sh
iostat -xdk 23
```

## ifstat

```sh
iostat 1
```

## CPU 占用过高

top获取pid

```sh
top # 获取占用进程

jps -l # Java程序定位pid
ps -ef|grep java|grep -v grep #  Java程序定位pid

ps -mp pid -o THREAD,tid,time # -m 显示所有线程 -p 进程使用的CPU时间

jstack pid | grep tid -A60 # tid为小写16进制s
```