# AES Playground - ECB, CBC, OFB & CFB em Python e Java

Este repositório contém implementações práticas do algoritmo AES (Advanced Encryption Standard) em vários modos de operação (ECB, CBC, OFB, CFB), usando as linguagens Python e Java. Os scripts cobrem desde abordagens manuais até o uso de bibliotecas como `PyCryptodome` e `javax.crypto`.

> **Nota:** Este projeto é apenas para fins educacionais.

<br>

## Índice
- [Scripts Disponíveis](#-scripts-disponíveis)
- [Como Funciona?](#️-como-funciona)
- [Exemplo de saída](#exemplo-de-saída)
- [Como Usar?](#-como-usar)
- [Parâmetros](#️-parâmetros)
- [Contribuição](#-contribuição)
- [Licença](#-licença)
<br><br>

## Scripts Disponíveis

### `aes_ecb_python.py` (ECB)

- **Linguagem:** Python  
- **Modo:** ECB  
- **Descrição:** Recebe texto e chave como entrada, normaliza a chave com SHA-256 e realiza a encriptação/decriptação na mesma execução.  
- **Exemplo:**  
  Entrada: `"Criptografia AES"`  
  → Saída: Texto cifrado em base64 + texto restaurado.

### `AesEcbJava.java` (Main + AESCipher.java)

- **Linguagem:** Java  
- **Modo:** ECB  
- **Descrição:** Permite ao usuário escolher entre cifrar ou decifrar um texto em tempo real com uma chave fornecida. Loop interativo até o usuário encerrar.  
- **Exemplo:**  
  Escolha → `"Encrypt"` → Entrada: `"Texto"`  
  → Saída: Encrypted + opção de reiniciar.

### `AesModesJava.java` (CBC, OFB, CFB)

- **Linguagem:** Java  
- **Modos:** CBC, OFB, CFB  
- **Descrição:** Mostra o mesmo texto sendo cifrado e decifrado com uma chave pré-definida nos três modos, exibindo os resultados em Base64.  
- **Exemplo:**  
  Texto original: `"ESTAMOS NA AULA DE CRIPTOGRAFIA"`  
  → Criptograma (Base64)  
  → Texto restaurado
<br><br>

## Como Funciona?
Cada script segue estes passos básicos:
1. **Input:** Solicita texto e chave (ou usa valores fixos).
2. **Key Normalization:** Chave é ajustada via SHA-256 (ou usada diretamente, conforme o modo).
3. **Encrypt:** Utiliza a cifra AES no modo especificado.
4. **Output:** Mostra texto cifrado (em Base64) e o texto restaurado após decriptação.
<br><br>

## Exemplo de saída
```
===Modo CBC===
Criptograma 		Wh485OT2H9fYjdRgeUEf8gi6WN4D3PChTLZBIk9eLsg=
Texto Decifrado 	ESTAMOS NA AULA DE CRIPTOGRAFIA
===Modo OFB===
...
```
<br>

## Como Usar?

### 1. Clone o repositório:
```bash
git clone https://github.com/SEU_USUARIO/aes-playground.git
```
### 2. Acesse a pasta:
	cd aes-playground
### 3. Execute o script desejado:
* **Python**: <br> `python aes_ecb_python.py`

* **Java** (após compilar): <br> `javac Main.java` <br> `java Main`
    
```
⚠️ Certifique-se de ter as dependências necessárias:
```
* Python: pycryptodome
* Java: Nenhuma lib externa, usa javax.crypto
<br><br>

## Parâmetros
* `text`: Texto a ser cifrado ou decifrado.
* `key`: Chave de encriptação, que será normalizada com SHA-256 se necessário.
>   💡 A maioria dos scripts trata a chave automaticamente e aceita qualquer string.

<br>

## Contribuição
Sinta-se à vontade para contribuir! 
Abra um pull request ou crie um issue para sugerir melhorias, novos modos ou novas linguagens.
<br><br>

## Licença
Este projeto está licenciado sob a **MIT License**. 
Veja `LICENSE` para mais detalhes.