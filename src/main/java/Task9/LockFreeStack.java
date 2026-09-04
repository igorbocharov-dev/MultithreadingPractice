package Task9;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Реализуй неблокирующий стек push/pop через AtomicReference и CAS-цикл, без synchronized
 */

// Нашел эту реализацию на sky.pro, просто скопировал.
// Разберу потом подробно для себя, не без этого.

public class LockFreeStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    private static class Node<T> {
        final T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void push(T value) {
        Node<T> newHead;
        Node<T> oldHead;
        do {
            oldHead = head.get();
            newHead = new Node<>(value, oldHead);
        } while (!head.compareAndSet(oldHead, newHead));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.value;
    }
}
