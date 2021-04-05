# ALGORITMOS E ESTRUTURAS DE DADOS II - TRABALHO 1
Trabalho desenvolvido na disciplina de algoritmos e estruturas de dados II, da [Faculdade UCL](https://www.ucl.br/). Projeto desenvolvido baseado no mundo de [blocos](https://onlinejudge.org/external/1/101.pdf).

#### Documentação
##### Objetivo
Implementar  um  mundo  de  [blocos](https://onlinejudge.org/external/1/101.pdf)  simples  sob  certas  regras  e  restrições. Respondendo  a um  conjunto limitado  de  comandosdados  pelo usuário,  simulando um braço robótico.

##### Funcionamento geral
O mundo de blocos possui um conjunto limitado de comandos(ondecomandos coma  =  b ou onde  a  e  b  estejam  no  mesmo monte,  devem  ser  ignorados),  sendo eles:
1. Move a onto b: move o bloco a para cima do bloco b retornando eventuais blocos que já estiverem sobre a ou b para as suas posições originais.
2. Move a over b:Coloca o bloco a no topo do monte onde está o bloco b retornando  eventuais  blocos  que  já  estiverem  sobre  a  às  suas  posições originais.
3. Pile  a  onto  b:coloca  o  bloco  a  juntamente  com  todos  os  blocos  que estiverem sobre ele em cima do bloco b, retornando eventuais blocos que já estiverem sobre b as suas posições originais.
4. Pile  a  over  b:coloca  o  bloco  a  juntamente  com  todos  os  blocos  que estiverem sobre ele sobre o monte que contém o bloco b.
5. Quit:termina a execução.

O funcionamento do programa é baseado em duas classes principais, TBlocos e CommandParser, sendo a primeira responsável por gerenciar e executar todas as manipulações dos blocos e a segunda encarregada de traduzir os comandos dados pelo usuário para o padrão dos blocos. Abaixo o digrama para representar o fluxo dos dados.

<img src="https://i.ibb.co/44yp530/fluxo.png" alt="" data-canonical-src="https://i.ibb.co/44yp530/fluxo.png" height="250" />
