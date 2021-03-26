package com.everaldojunior;

import com.everaldojunior.Blocks.TBlocos;
import com.everaldojunior.Utils.Command.BlockCommand;
import com.everaldojunior.Utils.Command.CommandParser;

public class Main
{
    public static void main(String[] args)
    {
        var blocks = new TBlocos(5);
        blocks.PrintBlocks();

        var parser = new CommandParser("src/input.txt");
        var commands = parser.Parse();
    }
}