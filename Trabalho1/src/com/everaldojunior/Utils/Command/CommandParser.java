package com.everaldojunior.Utils.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.everaldojunior.Utils.List.LinkedList;

public class CommandParser
{
    private Scanner fileScanner;

    public CommandParser(String path)
    {
        //Carrega o arquivo e cria o scanner
        var file = new File(path);

        try
        {
            this.fileScanner = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERRO: Não foi encontrado o arquivo no caminho: " + path);
        }
    }

    public LinkedList<BlockCommand> Parse()
    {
        var commands = new LinkedList<BlockCommand>();

        if(this.fileScanner == null)
        {
            System.out.println("ERRO: Arquivo inválido");
            return commands;
        }

        //Faz o parser dos comandos
        while (this.fileScanner.hasNextLine())
        {
            var line = this.fileScanner.nextLine();

            //Separa os comandos pelos espaços entre eles
            var tokens = line.split(" ");

            if(tokens.length == 1)
            {
                var type = tokens[0];
                BlockCommand command;

                if(type.equals("quit"))
                    command = new BlockCommand(CommandTypes.QUIT, null);
                else
                    command = new BlockCommand(CommandTypes.CREATE_BLOCKS, new int[]{Integer.parseInt(type)});

                commands.Add(command);
            }
            else if(tokens.length == 4)
            {
                var type = tokens[0];
                var subType = tokens[2];
                var args = new int[]{Integer.parseInt(tokens[1]), Integer.parseInt(tokens[3])};

                if(subType.equals("onto"))
                {
                    BlockCommand command;

                    if(type.equals("move"))
                        command = new BlockCommand(CommandTypes.MOVE_ONTO, args);
                    else
                        command = new BlockCommand(CommandTypes.PILE_ONTO, args);

                    commands.Add(command);
                }
                else
                {
                    BlockCommand command;

                    if(type.equals("move"))
                        command = new BlockCommand(CommandTypes.MOVE_OVER, args);
                    else
                        command = new BlockCommand(CommandTypes.PILE_OVER, args);

                    commands.Add(command);
                }
            }
        }

        return commands;
    }
}
