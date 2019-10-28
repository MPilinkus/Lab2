package edu.ktu.ds.lab2.utils;

import java.util.Comparator;

/**
 * Klasė paveldi klasę BstSet ir perdengia add metodą iteracine realizacija
 *
 * @param <E>
 * @author darius
 */
public class BstSetIterative<E extends Comparable<E>> extends BstSet<E> implements SortedSet<E> {

    public BstSetIterative() {
        super();
    }

    public BstSetIterative(Comparator<? super E> c) {
        super(c);
    }

    /**
     * Aibė papildoma nauju elementu. Papildymas atliekamas iteracijos į gylį
     *
     * @param element
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }

        if (root == null) {
            root = new BstNode<>(element);
        } else {
            BstNode<E> current = root;
            BstNode<E> parent = null;
            while (current != null) {
                parent = current;
                int cmp = c.compare(element, current.element);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    return;
                }
            }

            int cmp = c.compare(element, parent.element);
            if (cmp < 0) {
                parent.left = new BstNode<>(element);
            } else if (cmp > 0) {
                parent.right = new BstNode<>(element);
            }
        }
        size++;
    }
}
