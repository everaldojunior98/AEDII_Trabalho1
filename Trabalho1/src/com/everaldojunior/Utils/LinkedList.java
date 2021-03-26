package com.everaldojunior.Utils;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>
{
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int length;

    public LinkedList()
    {
        this.firstNode = null;
        this.lastNode = null;
        this.length = 0;
    }

    public int GetLength()
    {
        return this.length;
    }

    public Node<T> GetFirstNode()
    {
        return this.firstNode;
    }

    public void Add(T item)
    {
        var newNode = new Node<>(item, null);

        //Checa se a lista está vazia, caso esteja preenche o primeiro node
        if (this.firstNode == null)
        {
            this.firstNode = newNode;
        }
        else
        {
            this.lastNode.UpdateNextNode(newNode);
        }

        //Atualiza o final da lista e incrementa o comprimento
        this.lastNode = newNode;
        this.length++;
    }

    public void Remove(T item)
    {
        if(item == null)
            return;

        var currentNode = this.firstNode;
        Node<T> lastNode = null;

        //Percorre todos os elementos da lista e deleta o primeiro que combinar
        while (currentNode != null)
        {
            //TODO Checar se a verificação direta pega todos os casos ou trocar por Equals
            if(currentNode.GetItem() == item)
            {
                var nextNode = currentNode.GetNextNode();
                //Checa se existe um node anterior e se tiver ele já atualiza pro proximo node
                if(lastNode != null)
                    lastNode.UpdateNextNode(nextNode);

                //Verificacoes do head da lista
                if(currentNode == this.firstNode)
                    this.firstNode = nextNode;
                if(currentNode == this.lastNode)
                    this.lastNode = lastNode;

                length--;
                break;
            }

            lastNode = currentNode;
            currentNode = currentNode.GetNextNode();
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        //Iterador pra conseguir usar o for e não ter que fazer o while sempre
        return new LinkedListIterator<>(this);
    }
}