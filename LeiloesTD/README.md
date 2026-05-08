# LeiloesTD — Sistema de Leilões

## Pré-requisitos
- NetBeans IDE (18 ou superior)
- JDK 11+
- MySQL / XAMPP com o banco `uc11` importado
- Driver JDBC: mysql-connector-java (qualquer versão 8.x)

## Como abrir no NetBeans

1. Abra o NetBeans
2. Vá em **File > Open Project**
3. Selecione a pasta `LeiloesTD`
4. Clique em **Open Project**

## Adicionar o Driver MySQL

1. Clique com o botão direito em **Libraries** no painel do projeto
2. Selecione **Add JAR/Folder**
3. Escolha o arquivo `mysql-connector-java-x.x.x.jar`
   - Se não tiver, baixe em: https://dev.mysql.com/downloads/connector/j/
4. Clique em **OK**

## Configurar o banco de dados

Antes de rodar, importe o arquivo `uc11.sql` no phpMyAdmin ou execute:

```sql
CREATE DATABASE IF NOT EXISTS uc11;
USE uc11;
-- (execute o conteúdo do uc11.sql)
```

A conexão está configurada em `src/util/Conexao.java`:
- URL: `jdbc:mysql://localhost:3306/uc11`
- Usuário: `root`
- Senha: `` (vazia — ajuste se necessário)

## Estrutura do projeto

```
LeiloesTD/
├── src/
│   ├── Main.java               ← Ponto de entrada (abre cadastroVIEW)
│   ├── util/
│   │   └── Conexao.java        ← Conexão com o banco MySQL
│   ├── model/
│   │   └── Produto.java        ← Entidade (id, nome, valor, status)
│   ├── dao/
│   │   └── ProdutoDAO.java     ← salvar() e listarTodos()
│   └── view/
│       ├── cadastroVIEW.java   ← Tela principal de cadastro
│       └── listagemVIEW.java   ← Tela de listagem em JTable
└── nbproject/
    ├── project.xml
    └── project.properties
```

## Commits realizados (Git)

```
git commit -m "Tela cadastroVIEW definida como tela principal"
git commit -m "Botao Salvar cadastrando produto no banco de dados"
git commit -m "Mensagem de feedback exibida apos tentativa de cadastro"
git commit -m "Listagem de produtos funcionando na tela de listagem"
git commit -m "Correcao de bug em validacao do campo valor"
```
