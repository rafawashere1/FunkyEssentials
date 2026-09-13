# FunkyEssentials

Plugin de comandos essenciais para servidores Minecraft baseados em Paper.

## Requisitos

- Minecraft 26.2
- Paper 26.2
- Java 25

## Comandos

| Comando | Descrição | Permissão |
| --- | --- | --- |
| `/teleport <jogador>` | Teleporta você até um jogador online. | `funkyessentials.teleport` |
| `/teleport <jogador1> <jogador2>` | Teleporta o primeiro jogador até o segundo. | `funkyessentials.teleport` |
| `/gamemode <modo> [jogador]` | Altera seu modo de jogo ou o de outro jogador. | `funkyessentials.gamemode` |

Os comandos também possuem os aliases `/tp` e `/gm`.

Os modos aceitos são `survival`, `creative`, `adventure` e `spectator`, além de suas abreviações e valores numéricos.

## Instalação

1. Baixe o JAR da versão desejada.
2. Coloque o arquivo na pasta `plugins` do servidor.
3. Inicie ou reinicie o servidor.
4. Personalize as mensagens em `plugins/FunkyEssentials/messages.yml`, se desejar.

## Compilação

No Windows:

```powershell
.\gradlew.bat build
```

Em Linux ou macOS:

```bash
./gradlew build
```

O JAR será gerado em `build/libs`.

## Documentação

As alterações de cada versão estão disponíveis em [`docs/release-notes`](docs/release-notes).
