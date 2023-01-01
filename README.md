# WG Sample App

## 動作要件

アプリケーションの起動にDocker/Docker Composeを利用します。

- Docker
  - <https://www.docker.com/>

### 導入すると便利なもの
  - pnpm
    - <https://pnpm.io/ja/>
    - npmでも可
  - Node.js

## 開発環境

`Java Version 17`を導入してください

IDEは好きなものを利用してください

## 利用方法

`docker compose`コマンドでビルド/起動を行います

### ビルド

```shell
docker compose build
```

### 起動

```shell
docker compose up -d
```

### 停止

```shell
docker compose down
```

### 停止 + リソースの削除

```shell
docker compose down --volumes
```

### プロセスの確認

```shell
docker compose ps
```

### サービスの起動

```shell
docker compose up {{service name}}
```

### サービスの停止

```shell
docker-compose rm -fsv {{service name}}
```