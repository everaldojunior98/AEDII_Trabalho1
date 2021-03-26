package com.everaldojunior.Blocks;

import com.everaldojunior.Utils.Command.BlockCommand;
import com.everaldojunior.Utils.List.LinkedList;

public class TBlocos
{
    private LinkedList<Integer>[] blocks;

    //Função para printar todos os blocos formatados na tela
    public void PrintBlocks()
    {
        if(blocks == null)
            return;

        for (var i = 0; i < blocks.length; i++)
        {
            System.out.print(i + ": ");
            for (var block : blocks[i])
                System.out.print(block + " ");
            System.out.println();
        }
    }

    //Função para ler os comandos e executar os movimentos
    public void ExecuteCommands(LinkedList<BlockCommand> commands)
    {
        for (var command : commands)
        {
            var args = command.GetArguments();
            switch (command.GetType())
            {
                case CREATE_BLOCKS:
                    CreateBlocks(args[0]);
                    break;
                case MOVE_ONTO:
                    MoveOnto(args[0], args[1]);
                    break;
                case MOVE_OVER:
                    MoveOver(args[0], args[1]);
                    break;
                case PILE_ONTO:
                    PileOnto(args[0], args[1]);
                    break;
                case PILE_OVER:
                    PileOver(args[0], args[1]);
                    break;
                case QUIT:
                    return;
            }
        }
    }

    //Comandos
    //Cria o array e preenche as listas
    private void CreateBlocks(int count)
    {
        this.blocks = new LinkedList[count];

        //Preenchendo com os blocos default
        for (var i = 0; i < count; i++)
        {
            var list = new LinkedList<Integer>();
            list.Add(i);

            this.blocks[i] = list;
        }
    }

    //move o bloco a para cima do bloco b retornando eventuais blocos que já
    //estiverem sobre a ou b para as suas posições originais.
    private void MoveOnto(int a, int b)
    {

    }

    //Coloca o bloco a no topo do monte onde está o bloco b retornando eventuais
    //blocos que já estiverem sobre a às suas posições originais.
    private void MoveOver(int a, int b)
    {

    }

    //coloca o bloco a juntamente com todos os blocos que estiverem sobre ele
    //em cima do bloco b, retornando eventuais blocos que já estiverem sobre b as suas posições
    //originais.
    private void PileOnto(int a, int b)
    {

    }

    //coloca o bloco a juntamente com todos os blocos que estiverem sobre ele
    //sobre o monte que contém o bloco b.
    private void PileOver(int a, int b)
    {

    }
}
