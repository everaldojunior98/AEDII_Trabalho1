package com.everaldojunior.Utils.List;

public class Node<T>
{
    private T item;
    private Node nextNode;

    public Node(T item, Node next)
    {
        this.item = item;
        this.nextNode = next;
    }

    public T GetItem()
    {
        return this.item;
    }

    public Node<T> GetNextNode()
    {
        return this.nextNode;
    }

    public void UpdateNextNode(Node next)
    {
        this.nextNode = next;
    }
}