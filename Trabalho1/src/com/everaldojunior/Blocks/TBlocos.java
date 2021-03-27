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
        //Verifica se pode executar o comando, caso não apenas ignora
        if(!CanExecuteCommand(a, b))
            return;

        //Pega as informações do bloco A
        var blockAInfo = GetBlockInfo(a);
        Block blockA = blockAInfo.GetBlock();
        int blockAPosition = blockAInfo.GetCurrentPosition();
        var stackedOnA = blockAInfo.GetStackedBlocks();

        //Pega as informações do bloco B
        var blockBInfo = GetBlockInfo(b);
        int blockBPosition = blockBInfo.GetCurrentPosition();
        var stackedOnB = blockBInfo.GetStackedBlocks();

        //Retorna os blocos empilhados em cima do A para a posição inicial
        ReturnToDefaultPosition(stackedOnA);

        //Retorna os blocos empilhados em cima do B para a posição inicial
        ReturnToDefaultPosition(stackedOnB);

        //Desempilha o bloco A da posição que ele tava e empilha em cima de B
        this.blocks[blockAPosition].Remove(blockA);
        this.blocks[blockBPosition].Add(blockA);
    }

    //Coloca o bloco a no topo do monte onde está o bloco b retornando eventuais
    //blocos que já estiverem sobre a às suas posições originais.
    private void MoveOver(int a, int b)
    {
        //Verifica se pode executar o comando, caso não apenas ignora
        if(!CanExecuteCommand(a, b))
            return;

        //Pega as informações do bloco A
        var blockAInfo = GetBlockInfo(a);
        Block blockA = blockAInfo.GetBlock();
        int blockAPosition = blockAInfo.GetCurrentPosition();
        var stackedOnA = blockAInfo.GetStackedBlocks();

        //Pega as informações do bloco B
        var blockBInfo = GetBlockInfo(b);
        int blockBPosition = blockBInfo.GetCurrentPosition();

        //Retorna os blocos empilhados em cima do A para a posição inicial
        ReturnToDefaultPosition(stackedOnA);

        //Desempilha o bloco A da posição que ele tava e empilha em cima de B
        this.blocks[blockAPosition].Remove(blockA);
        this.blocks[blockBPosition].Add(blockA);
    }

    //coloca o bloco a juntamente com todos os blocos que estiverem sobre ele
    //em cima do bloco b, retornando eventuais blocos que já estiverem sobre b as suas posições
    //originais.
    private void PileOnto(int a, int b)
    {
        //Verifica se pode executar o comando, caso não apenas ignora
        if(!CanExecuteCommand(a, b))
            return;
        //Pega as informações do bloco A
        var blockAInfo = GetBlockInfo(a);
        Block blockA = blockAInfo.GetBlock();
        int blockAPosition = blockAInfo.GetCurrentPosition();
        var stackedOnA = blockAInfo.GetStackedBlocks();

        //Pega as informações do bloco B
        var blockBInfo = GetBlockInfo(b);
        int blockBPosition = blockBInfo.GetCurrentPosition();
        var stackedOnB = blockBInfo.GetStackedBlocks();

        //Retorna os blocos empilhados em cima do B para a posição inicial
        ReturnToDefaultPosition(stackedOnB);

        //Desempilha o bloco A da posição que ele tava e empilha em cima de B
        this.blocks[blockAPosition].Remove(blockA);
        this.blocks[blockBPosition].Add(blockA);

        //Desempilha todos os blocos que esta em A e empilha em B
        for (var block : stackedOnA)
        {
            this.blocks[blockAPosition].Remove(block);
            this.blocks[blockBPosition].Add(block);
        }
    }

    //coloca o bloco a juntamente com todos os blocos que estiverem sobre ele
    //sobre o monte que contém o bloco b.
    private void PileOver(int a, int b)
    {
        //Verifica se pode executar o comando, caso não apenas ignora
        if(!CanExecuteCommand(a, b))
            return;
        //Pega as informações do bloco A
        var blockAInfo = GetBlockInfo(a);
        Block blockA = blockAInfo.GetBlock();
        int blockAPosition = blockAInfo.GetCurrentPosition();
        var stackedOnA = blockAInfo.GetStackedBlocks();

        //Pega as informações do bloco B
        var blockBInfo = GetBlockInfo(b);
        int blockBPosition = blockBInfo.GetCurrentPosition();

        //Desempilha o bloco A da posição que ele tava e empilha em cima de B
        this.blocks[blockAPosition].Remove(blockA);
        this.blocks[blockBPosition].Add(blockA);

        //Desempilha todos os blocos que esta em A e empilha em B
        for (var block : stackedOnA)
        {
            this.blocks[blockAPosition].Remove(block);
            this.blocks[blockBPosition].Add(block);
        }
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

    private void ReturnToDefaultPosition(LinkedList<Block> returnList)
    {
        //Reverte a lista para remover do ultimo até chegar no bloco A
        returnList.Reverse();

        for (var blockToReturn : returnList)
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

    //Pega o bloco, sua posição e quem está empilhado em cima. Baseado no ID do bloco
    private BlockInfo GetBlockInfo(int id)
    {
        var stacked = new LinkedList<Block>();
        Block block = null;
        int blockPosition = 0;

        //Pega quais blocos estão em cima de A
        for (var i = 0; i < blocks.length; i++)
        {
            var found = false;
            for (var b : this.blocks[i])
            {
                //Armazena os blocos que estão em cima de A ou B
                if(found)
                    stacked.Add(b);

                if(b.GetId() == id)
                {
                    block = b;
                    blockPosition = i;
                    found = true;
                }
            }
        }

        return new BlockInfo(block, blockPosition, stacked);
    }
}