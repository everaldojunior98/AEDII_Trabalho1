package com.everaldojunior.Blocks;

import com.everaldojunior.Utils.List.LinkedList;

public class TBlocos
{
    private LinkedList<Block>[] blocks;

    public TBlocos(int count)
    {
        this.blocks = new LinkedList[count];

        //Preenchendo com os blocos default
        for (var i = 0; i < count; i++)
        {
            var block = new Block(i);
            var list = new LinkedList<Block>();
            list.Add(block);

            this.blocks[i] = list;
        }
    }

    //Função para printar todos os blocos formatados na tela
    public void PrintBlocks()
    {
        for (var i = 0; i < blocks.length; i++)
        {
            System.out.print(i + ": ");
            for (var block : blocks[i])
                System.out.print(block.GetId() + " ");
            System.out.println();
        }
    }
}
