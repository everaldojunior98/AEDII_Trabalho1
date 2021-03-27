package com.everaldojunior.Blocks;

import com.everaldojunior.Utils.List.LinkedList;

public class BlockInfo
{
    private Block block;
    private int currentPosition;
    private LinkedList<Block> stacked;

    public BlockInfo(Block block, int currentPosition, LinkedList<Block> stacked)
    {
        this.block = block;
        this.currentPosition = currentPosition;
        this.stacked = stacked;
    }

    public Block GetBlock()
    {
        return this.block;
    }

    public int GetCurrentPosition()
    {
        return this.currentPosition;
    }

    public LinkedList<Block> GetStackedBlocks()
    {
        return this.stacked;
    }
}