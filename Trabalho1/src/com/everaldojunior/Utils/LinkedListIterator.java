package com.everaldojunior.Utils;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T>
{
    private Node<T> currentNode;

    public LinkedListIterator(LinkedList<T> list)
    {
        this.currentNode = list.GetFirstNode();
    }

    @Override
    public boolean hasNext()
    {
        return this.currentNode != null;
    }

    @Override
    public T next()
    {
        var item = this.currentNode.GetItem();
        this.currentNode = this.currentNode.GetNextNode();
        return item;
    }
}