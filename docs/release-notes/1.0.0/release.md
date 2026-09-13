# Versão 1.0.0

Primeira versão do FunkyEssentials, um plugin de comandos essenciais para servidores Minecraft. Esta versão oferece teleporte entre jogadores, alteração de modo de jogo e mensagens configuráveis em português.

## Comandos

- `/teleport <jogador>` ou `/tp <jogador>`: teleporta você até um jogador online.
- `/teleport <jogador1> <jogador2>` ou `/tp <jogador1> <jogador2>`: teleporta o primeiro jogador até o segundo.
- `/gamemode <modo> [jogador]` ou `/gm <modo> [jogador]`: altera seu modo de jogo ou o de outro jogador online.

Os comandos também podem ser executados pelo console. Para teleporte, é necessário informar os dois jogadores; para modo de jogo, é necessário informar o jogador que será alterado.

### Modos de jogo

| Modo | Valores aceitos |
| --- | --- |
| Survival | `survival`, `s` ou `0` |
| Creative | `creative`, `c` ou `1` |
| Adventure | `adventure`, `a` ou `2` |
| Spectator | `spectator`, `sp` ou `3` |

## Permissões

- `funkyessentials.teleport`: permite usar os comandos de teleporte.
- `funkyessentials.gamemode`: permite alterar o próprio modo de jogo e o de outros jogadores.
- `funkyessentials.admin`: reúne as permissões administrativas do plugin.

Por padrão, os comandos de teleporte e modo de jogo estão disponíveis para operadores do servidor.

## Mensagens

- Mensagens em português, personalizáveis pelo arquivo `messages.yml`.
- Orientações de uso e avisos para falta de permissão, jogadores offline e modos de jogo inválidos.
- Mensagens de confirmação para teleporte e alteração de modo de jogo.
