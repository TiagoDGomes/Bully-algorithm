Algoritmo de Eleição - Garcia-Molina  (bully algorithm - Garcia-Molina)
===
Trabalho para a disciplina de Sistemas Distribuídos para Web - setembro 2013

Para fazê-lo funcionar, abra várias instâncias do programa de coloque-as lado a lado.
Para cada janela aberta, identifique-a com um número de processo único. Coloque na ordem certa para poder visualizar melhor.

Inicie os processos (botão iniciar) e faça requisições (botão request) na ordem que desejar. 

Ao iniciar, o processo X solicita uma eleição para os processos maiores. Caso ninguém responder, ele se elege como coordenador e avisa os processos menores. Caso contrário, um processo Y com identificador maior irá mandar os processos menores desistirem de serem coordenadores ('alive'). O processo Y irá aguardar o processo maior responder e se não responder, se torna (ou se mantém) coordenador e avisa os menores.

O processo de eleição se repete quando há requisições de processos menores e o coordenador não está ativo (para ficar inativo, basta fechar a janela).

