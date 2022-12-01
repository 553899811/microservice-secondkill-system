package com.newbiegroup.secondkill.manager;

import java.util.concurrent.TimeUnit;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/7/10 15:00
 */
public interface CacheManager {
    /**
     * <pre>
     * 将String值value关联到key
     *
     * 描述
     * 1、键值最大长度依赖缓存介质（memcached->redis->jimdb）
     * 2、缓存时长为介质默认缓存时长
     * 3、捕捉所有异常，异常返回false
     * </pre>
     *
     * @param key   String
     * @param value String 缓存内容
     * @return boolean
     */
    boolean set(String key, String value);

    /**
     * <pre>
     * 将Object值value关联到key
     *
     * 描述
     * 1、键值最大长度依赖缓存介质（memcached->redis->jimdb）
     * 2、缓存时长为介质默认缓存时长
     * 3、捕捉所有异常，结果以返回值为准，异常返回false
     *
     * </pre>
     *
     * @param key   String
     * @param value Object 缓存内容
     * @return boolean
     */
    boolean set(String key, Object value);

    boolean set(String key, Object value, Long time, TimeUnit unit);

    /**
     * <pre>
     * 返回key所关联的String值
     *
     * 描述
     * 1、如果key不存在那么返回特殊值null
     * 2、假如key储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
     * </pre>
     *
     * @param key String
     * @return String
     */
    String get(String key);

    /**
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * <pre>
     * 当key存在时,仍将String值value关联到key，并指定缓存时长
     *
     * 描述
     * 1、如果 key 已经存在， SETEX 命令将覆写旧值。
     * 2、这个命令类似于以下两个命令：
     * 			SET key value
     * 			EXPIRE key seconds  # 设置生存时间
     * 		   不同之处是， SETEX 是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
     * 3、捕捉所有异常，结果以返回值为准，异常返回false
     * </pre>
     *
     * @param key     String
     * @param value   String
     * @param seconds long 缓存时长,单位:秒
     * @return boolean
     */
    boolean setEx(String key, String value, long seconds);

    boolean setEx(String key, String value, long time, TimeUnit unit);

    /**
     * <pre>
     * 当key存在时,仍将Object值value关联到key，并指定缓存时长
     *
     * 描述
     * 1、如果 key 已经存在， SETEX 命令将覆写旧值。
     * 2、这个命令类似于以下两个命令：
     * 			SET key value
     * 			EXPIRE key seconds  # 设置生存时间
     * 		   不同之处是， SETEX 是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
     * 3、捕捉所有异常，异常返回false
     * </pre>
     *
     * @param key     String
     * @param value   Object
     * @param seconds long 缓存时长,单位:秒
     * @return boolean
     */
    boolean setEx(String key, Object value, long seconds);

    /**
     * <pre>
     * 当key不存时,将String值value关联到key
     *
     * 描述
     * 1、若给定的 key 已经存在，则 SETNX 不做任何动作
     * 2、捕捉所有异常，异常返回false
     * </pre>
     *
     * @param key   String
     * @param value String
     * @return boolean
     */
    boolean setNx(String key, String value);

    /**
     * <pre>
     * 当key不存时,将Object值value关联到key
     *
     * 描述
     * 1、若给定的 key 已经存在，则 SETNX 不做任何动作
     * 2、捕捉所有异常，结果以返回值为准，异常返回false
     * </pre>
     *
     * @param key   String
     * @param value Object
     * @return boolean
     */
    boolean setNx(String key, Object value);

    /**
     * <pre>
     * 向缓存的哈希表中添加value值,如果缓存的哈希表中的存在field域,则直接覆盖
     *
     * 描述
     * 1、如果哈希表中field不存在，直接设置新值
     * 2、如果哈希表中field已经存在,则旧值被新值覆盖
     * </pre>
     *
     * @param key   String
     * @param field String 域名
     * @param value String
     * @return boolean
     */
    boolean hSet(String key, String field, String value);


    /**
     * <pre>
     * 将key中储存的数字值加一
     *
     * 描述
     * 1、如果key不存在，则key的数据将被设置为0，然后加1
     * 2、如果key存在但不是数字类型，返回null
     * </pre>
     *
     * @param key String
     * @return Long
     */
    Long incr(String key,Integer amount);

    /**
     * <pre>
     * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除
     *
     * 描述
     * 1、在 Redis 中，带有生存时间的 key 被称为『易失的』(volatile)
     *  生存时间可以通过使用 DEL 命令来删除整个 key 来移除，或者被 SET 和 GETSET 命令覆写(overwrite)，这意味着，
     *  如果一个命令只是修改(alter)一个带生存时间的 key 的值而不是用一个新的 key 值来代替(replace)它的话，那么生存时间不会被改变。
     *  比如说，对一个 key 执行 INCR 命令，对一个列表进行 LPUSH 命令，或者对一个哈希表执行 HSET 命令，这类操作都不会修改 key 本身的生存时间。
     *  另一方面，如果使用 RENAME 对一个 key 进行改名，那么改名后的 key 的生存时间和改名前一样。
     *  RENAME 命令的另一种可能是，尝试将一个带生存时间的 key 改名成另一个带生存时间的 another_key ，
     *  这时旧的 another_key (以及它的生存时间)会被删除，然后旧的 key 会改名为 another_key ，因此，新的 another_key 的生存时间也和原本的 key 一样。
     *  使用 PERSIST 命令可以在不删除 key 的情况下，移除 key 的生存时间，让 key 重新成为一个『持久的』(persistent) key 。
     * 2、更新生存时间
     *  可以对一个已经带有生存时间的 key 执行 EXPIRE 命令，新指定的生存时间会取代旧的生存时间。
     * 3、过期时间的精确度
     *  在 Redis 2.4 版本中，过期时间的延迟在 1 秒钟之内 —— 也即是，就算 key 已经过期，但它还是可能在过期之后一秒钟之内被访问到，
     *  而在新的 Redis 2.6 版本中，延迟被降低到 1 毫秒之内。
     * </pre>
     *
     * @param key     String
     * @param seconds long
     * @return boolean
     */
    boolean expire(String key, long seconds);

    boolean expire(String key, long seconds, TimeUnit unit);

    /**
     * <pre>
     * 删除指定缓存
     *
     * </pre>
     *
     * @param key String
     * @return boolean
     */
    boolean del(String key);

    Long increment(String key, long var2);

    boolean hasKey(String key);
}
