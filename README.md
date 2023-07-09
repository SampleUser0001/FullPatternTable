# Full Pattern Table

- [Full Pattern Table](#full-pattern-table)
  - [概要](#概要)
  - [実行](#実行)
    - [サンプル生成](#サンプル生成)

## 概要

引数のファイルの組み合わせ表を標準出力に出力する。

## 実行

``` bash
mvn clean compile exec:java -Dexec.mainClass="ittimfn.tool.fullpatterntable.App" -Dexec.args="'$file01' '$file02' ..."
```

### サンプル生成

``` bash
file01=$(pwd)/sample/items01.txt
file02=$(pwd)/sample/items02.txt
file03=$(pwd)/sample/items03.txt

mvn clean compile exec:java -Dexec.mainClass="ittimfn.tool.fullpatterntable.App" -Dexec.args="'$file01' '$file02' '$file03'"
```
