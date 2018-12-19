redis和memcached的区别

存储格式：
Redis和Memcache都是将数据存放在内存中，都是内存数据库。不过memcache还可用于缓存其他东西，例如图片、视频等等

数据类型：
Redis不仅仅支持简单的k/v类型的数据，同时还提供list，set，hash等数据结构的存储

Redis支持数据的备份，即master-slave模式的数据备份

内存使用效率：
使用简单的key-value存储的话，memcached的内存利用率会更高一点，如果redis采用hash结构来做key-value存储，由于其组合式的压缩，内存的利用率更高

性能对比：
由于redis只使用单核，而memcached使用多核，所以平均在每一个核上redis在存储小数据时比memcached性能更高，而在100Ks=以上的时候memcached性能要高于red

虚拟内存：
Redis当物理内存用完时，可以将一些很久没用到的value 交换到磁盘

内存管理机制的不同：
在redis中,并不是所有的数据都一一直存储在内存中的，这是和memcached相比最大的一个区别，Redis只会缓存所有的key端的信息，如果redis发现内存的使用量超过某一个值，将触发swap的操作，redis根据相应的表达式计算出那些key对应value需要swap到磁盘，然后再将这些这些key对应的value持久化到磁盘中，同时再内存清除。同时由于redis将内存中的数据swap到磁盘的时候，提供服务的主线程和进行swap操作的子进程会共享这部分内存，所以如果更新需要swap的数据，redis将阻塞这个操作，直到子线程完成swap操作后才可以进行修改

过期策略：
memcache在set时就指定，例如set key1 0 0 8,即永不过期。Redis可以通过例如expire 设定，例如expire name 10；

分布式：
设定memcache集群，利用magent做一主多从;redis可以做一主多从。都可以一主一从

数据持久化的支持：
虽然redis是基于内存的存储系统，但是他本身是支持内存数据的持久化，而且主要提供两种主要的持久化策略，RDB快照和AOF日志，而memcached是不支持数据持久化的操作的。

存储数据安全：
memcache挂掉后，数据没了；redis可以定期保存到磁盘（持久化）

灾难恢复：
memcache挂掉后，数据不可恢复; redis数据丢失后可以通过aof恢复

应用场景：
Redis出来作为NoSQL数据库使用外，还能用做消息队列、数据堆栈和数据缓存等；Memcached适合于缓存SQL语句、数据集、用户临时性数据、延迟查询数据和session等。