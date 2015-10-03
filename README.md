Algoritmo de Eleição (bully algorithm - Garcia-Molina)
===

Esta é uma aplicação de demonstração do algoritmo de eleição (bully algorithm - Garcia-Molina). Foi um trabalho desenvolvido para a disciplina de Sistemas Distribuídos para Web (IFSP Câmpus São João da Boa Vista) em setembro 2013.

Para fazê-lo funcionar, abra várias instâncias do programa e coloque-as lado a lado.
Para cada janela aberta, identifique-a com um número de processo único. Coloque-as na ordem certa para poder visualizar melhor.

Inicie os processos (botão iniciar) e faça requisições (botão request) na ordem que desejar. 

Ao iniciar, o processo X solicita uma eleição para os processos maiores. Caso ninguém responda, X se elege como coordenador e avisa os processos menores. Caso contrário, um processo Y com identificador maior irá mandar os processos menores desistirem de serem coordenadores ('alive'). O processo Y irá aguardar o processo maior responder e, se ninguém responder, se torna (ou se mantém) coordenador, e avisa os menores.

O processo de eleição se repete quando há requisições de processos menores e o coordenador não está ativo (para ficar inativo, basta fechar a janela).

