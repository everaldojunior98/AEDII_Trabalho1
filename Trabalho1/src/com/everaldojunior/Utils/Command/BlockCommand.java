package com.everaldojunior.Utils.Command;

public class BlockCommand
{
    private CommandTypes commandType;
    private int[] arguments;

    public BlockCommand(CommandTypes type, int[] args)
    {
        this.commandType = type;
        this.arguments = args;
    }

    public CommandTypes GetType()
    {
        return this.commandType;
    }

    public int[] GetArguments()
    {
        return this.arguments;
    }
}
