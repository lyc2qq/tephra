package org.lpw.tephra.atomic;

/**
 * 可关闭事务。
 *
 * @auth lpw
 */
public interface Closable {
    /**
     * 关闭。
     */
    void close();
}
