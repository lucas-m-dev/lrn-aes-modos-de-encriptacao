from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
from hashlib import sha256
import base64


def key_normalize(key) -> None:
# digest() tranforma o resultado em bytes
    return sha256(key.encode()).digest()[:16]    

def encrypt_ecb(key: str, plaintext: str) -> str:
# encode() transforma em bytes
    cipher = AES.new(key, AES.MODE_ECB)                     # cifrador de ECB
    padded_text = pad(plaintext.encode(), AES.block_size)   # text para bytes e adiciona pad
    encrypted_bytes = cipher.encrypt(padded_text)           # encripta bytes
    encrypted_b64 = base64.b64encode(encrypted_bytes)       # melhor para armazenamento - base64
    return encrypted_b64.decode()                           # melhor para transmissão de dados

def decrypt_ecb(key: str, encrypted_b64: str) -> str:
    cipher = AES.new(key, AES.MODE_ECB)                             # cifrador de ECB
    encrypted_bytes = base64.b64decode(encrypted_b64.encode())      # remover base64
    decrypted_bytes = cipher.decrypt(encrypted_bytes)               # bytes decriptados
    unpadded_text = unpad(decrypted_bytes, AES.block_size)          # pad removido
    return unpadded_text.decode()                                   # devolve texto normal


if __name__ == '__main__':
    print('\nENCRIPTADOR DE AES')
    print('Insira o texto a encriptar e a chave')
    plaintext = input('\tText: ')
    key = input('\tKey: ')

    key16 = key_normalize(key)
    encrypted_b64 = encrypted = encrypt_ecb(key16, plaintext)
    decrypted_text = decrypted = decrypt_ecb(key16, encrypted_b64)

    print("\nTexto original:\t\t\t\t", plaintext)
    print("Texto encriptado (base64):\t", encrypted_b64)
    print("Texto desencriptado:\t\t", decrypted_text, end='\n\n')