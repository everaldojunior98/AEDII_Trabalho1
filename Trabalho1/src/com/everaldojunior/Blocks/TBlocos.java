package com.everaldojunior.Blocks;

import com.everaldojunior.Utils.Command.BlockCommand;
import com.everaldojunior.Utils.List.LinkedList;

public class TBlocos
{
    private LinkedList<Block>[] blocks;

    //Função para printar todos os blocos formatados na tela
    public void PrintBlocks()
    {
        if(blocks == null)
            return;

        for (var i = 0; i < blocks.length; i++)
        {
            System.out.print(i + ": ");
            for (var block : blocks[i])
                System.out.print(block.GetId() + " ");
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
            var block = new Block(i);
            var list = new LinkedList<Block>();
            list.Add(block);

            this.blocks[i] = list;
        }
    }

    //move o bloco a para cima do bloco b retornando eventuais blocos que já
    //estiverem sobre a ou b para as suas posições originais.
    private void MoveOnto(int a, int b)
    {
        if(!CanExecuteCommand(a, b))
            return;

        var stackedOnA = new LinkedList<Block>();
        var stackedOnB = new LinkedList<Block>();

        Block blockA = null;
        int blockAPosition = 0;

        int blockBPosition = 0;

        //Pega quais blocos estão em cima de A e B
        for (var i = 0; i < blocks.length; i++)
        {
            var foundA = false;
            var foundB = false;
            for (var block : this.blocks[i])
            {
                //Armazena os blocos que estão em cima de A ou B
                if(foundA)
                    stackedOnA.Add(block);
                else if(foundB)
                    stackedOnB.Add(block);

                if(block.GetId() == a)
                {
                    blockA = block;
                    blockAPosition = i;
                    foundA = true;
                }
                else if(block.GetId() == b)
                {
                    blockBPosition = i;
                    foundB = true;
                }
            }
        }

        //Reverte a lista para remover do ultimo até chegar no bloco A
        stackedOnA.Reverse();
        for (Block block : stackedOnA)
            ReturnToDefaultPosition(block);

        //Reverte a lista para remover do ultimo até chegar no bloco B
        stackedOnB.Reverse();
        for (Block block : stackedOnB)
            ReturnToDefaultPosition(block);

        //Desempilha o bloco A da posição que ele tava e empilha em cima de B
        this.blocks[blockAPosition].Remove(blockA);
        this.blocks[blockBPosition].Add(blockA);
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

    //Checa as seguintes condições:
    //Comandos onde a = b ou onde a e b estejam no mesmo monte, devem ser ignorados.
    private boolean CanExecuteCommand(int a, int b)
    {
        if(a == b)
            return false;

        for (var i = 0; i < blocks.length; i++)
        {
            var foundA = false;
            var foundB = false;

            for (var block : this.blocks[i])
            {
                if(block.GetId() == a)
                    foundA = true;
                else if(block.GetId() == b)
                    foundB = true;

                if(foundA && foundB)
                    return false;
            }
        }

        return true;
    }

    private void ReturnToDefaultPosition(Block blockToReturn)
    {
        //Procura pelo bloco, remove e adiciona na posição inicial
        for (var i = 0; i < blocks.length; i++)
        {
            //Procura pelo bloco e remove
            for (var block : this.blocks[i])
                if(block.GetId() == blockToReturn.GetId())
                    blocks[i].Remove(block);

            //Adiciona o bloco na posição inicial
            if(i == blockToReturn.GetId())
                blocks[blockToReturn.GetId()].Add(blockToReturn);
        }
    }
}