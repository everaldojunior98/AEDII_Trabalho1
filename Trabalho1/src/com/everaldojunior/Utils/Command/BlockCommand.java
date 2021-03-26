package com.everaldojunior.Utils.Command;

public class BlockCommand
{
    private CommandTypes commandType;
    private String[] arguments;

    public BlockCommand(CommandTypes type, String[] args)
    {
        this.commandType = type;
        this.arguments = args;
    }

    public CommandTypes GetType()
    {
        return this.commandType;
    }

    public String[] GetArguments()
    {
        return this.arguments;
    }
}
