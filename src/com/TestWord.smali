.class public Lcom/TestWord;
.super Ljava/lang/Object;
.source "TestWord.java"


# static fields
.field public static final location:Ljava/lang/String; = "src\\Path.txt"

.field public static final path1:Ljava/lang/String;

.field public static final path2:Ljava/lang/String;

.field public static final path3:Ljava/lang/String;

.field public static final path4:Ljava/lang/String;

.field public static final path5:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .registers 3

    .prologue
    .line 16
    const/4 v1, 0x1

    :try_start_1
    const-string v2, "src\\Path.txt"

    invoke-static {v1, v2}, Lcom/FileOperation;->getLineStr(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    sput-object v1, Lcom/TestWord;->path1:Ljava/lang/String;

    .line 17
    const/4 v1, 0x2

    const-string v2, "src\\Path.txt"

    invoke-static {v1, v2}, Lcom/FileOperation;->getLineStr(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    sput-object v1, Lcom/TestWord;->path2:Ljava/lang/String;

    .line 18
    const/4 v1, 0x3

    const-string v2, "src\\Path.txt"

    invoke-static {v1, v2}, Lcom/FileOperation;->getLineStr(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    sput-object v1, Lcom/TestWord;->path3:Ljava/lang/String;

    .line 19
    const/4 v1, 0x4

    const-string v2, "src\\Path.txt"

    invoke-static {v1, v2}, Lcom/FileOperation;->getLineStr(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    sput-object v1, Lcom/TestWord;->path4:Ljava/lang/String;

    .line 20
    const/4 v1, 0x5

    const-string v2, "src\\Path.txt"

    invoke-static {v1, v2}, Lcom/FileOperation;->getLineStr(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    sput-object v1, Lcom/TestWord;->path5:Ljava/lang/String;
    :try_end_2d
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_2d} :catch_2e

    .line 24
    return-void

    .line 21
    :catch_2e
    move-exception v0

    .line 22
    .local v0, "e":Ljava/io/IOException;
    new-instance v1, Ljava/lang/RuntimeException;

    invoke-direct {v1, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v1
.end method

.method public constructor <init>()V
    .registers 1

    .prologue
    .line 6
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getInputNum(Ljava/lang/String;)[Ljava/lang/String;
    .registers 6
    .param p0, "str"    # Ljava/lang/String;

    .prologue
    .line 64
    sget-object v2, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {v2, p0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 65
    new-instance v1, Ljava/util/Scanner;

    sget-object v2, Ljava/lang/System;->in:Ljava/io/InputStream;

    invoke-direct {v1, v2}, Ljava/util/Scanner;-><init>(Ljava/io/InputStream;)V

    .line 66
    .local v1, "scanner":Ljava/util/Scanner;
    invoke-virtual {v1}, Ljava/util/Scanner;->nextLine()Ljava/lang/String;

    move-result-object v0

    .line 67
    .local v0, "line":Ljava/lang/String;
    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/String;

    const/4 v3, 0x0

    invoke-static {v0}, Lcom/TextOperation;->totalStrBytes(Ljava/lang/String;)I

    move-result v4

    invoke-static {v4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v2, v3

    const/4 v3, 0x1

    aput-object v0, v2, v3

    return-object v2
.end method

.method public static main([Ljava/lang/String;)V
    .registers 13
    .param p0, "args"    # [Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v11, 0x0

    const/4 v10, 0x1

    .line 27
    sget-object v7, Lcom/TestWord;->path2:Ljava/lang/String;

    invoke-static {v7}, Lcom/TextOperation;->totalFileBytes(Ljava/lang/String;)I

    move-result v0

    .line 28
    .local v0, "allBytes":I
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "\u5df2\u8f7d\u5165\u6587\u4ef6\uff0c\u603b\u5b57\u8282\u6570\u4e3a:  "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 29
    new-instance v6, Lcom/TotalFileBytesCompare;

    sget-object v7, Lcom/TestWord;->path2:Ljava/lang/String;

    invoke-direct {v6, v7, v0}, Lcom/TotalFileBytesCompare;-><init>(Ljava/lang/String;I)V

    .line 30
    .local v6, "totalFileBytesCompare":Lcom/TotalFileBytesCompare;
    new-instance v3, Lcom/SaveFile;

    invoke-direct {v3}, Lcom/SaveFile;-><init>()V

    .line 31
    .local v3, "saveFile":Lcom/SaveFile;
    invoke-virtual {v3}, Lcom/SaveFile;->start()V

    .line 32
    invoke-virtual {v6}, Lcom/TotalFileBytesCompare;->start()V

    .line 34
    :cond_32
    const-string v7, "====================\u8bf7\u8f93\u5165\u9700\u7ffb\u8bd1\u5185\u5bb9===================="

    invoke-static {v7}, Lcom/TestWord;->getInputNum(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    .line 35
    .local v2, "result":[Ljava/lang/String;
    aget-object v7, v2, v11

    invoke-static {v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v4

    .line 36
    .local v4, "total":I
    aget-object v7, v2, v10

    sget-object v8, Lcom/TestWord;->path2:Ljava/lang/String;

    sget-object v9, Lcom/TestWord;->path3:Ljava/lang/String;

    invoke-static {v7, v8, v9}, Lcom/TextOperation;->checkEnWords(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v7

    if-nez v7, :cond_5d

    .line 37
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v8, "\u6587\u4ef6\u4e2d\u4e0d\u5b58\u5728\u6b64\u53e5\uff01\u8bf7\u91cd\u65b0\u8f93\u5165"

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 56
    :goto_51
    iget-boolean v7, v6, Lcom/TotalFileBytesCompare;->isSame:Z

    if-nez v7, :cond_32

    .line 57
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v8, "\u6587\u4ef6\u5b57\u8282\u6570\u6709\u8bef\uff01\u8bf7\u6392\u67e5"

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 61
    return-void

    .line 39
    :cond_5d
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "\u8f93\u5165\u5185\u5bb9\u5b57\u8282\u957f\u5ea6\u4e3a:"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v9, ",\u6362\u7b97\u6210\u6c49\u5b57\uff1a"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    div-int/lit8 v9, v4, 0x3

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v9, "+"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    rem-int/lit8 v9, v4, 0x3

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 41
    :goto_8d
    const-string v7, "====================\u8bf7\u8f93\u5165\u7ffb\u8bd1\u7ed3\u679c===================="

    invoke-static {v7}, Lcom/TestWord;->getInputNum(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    .line 42
    aget-object v7, v2, v11

    invoke-static {v7}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v5

    .line 43
    .local v5, "total2":I
    if-gt v5, v4, :cond_cb

    aget-object v7, v2, v10

    sget-object v8, Lcom/TestWord;->path1:Ljava/lang/String;

    invoke-static {v7, v8}, Lcom/TextOperation;->checkWords(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_cb

    .line 44
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v8, "====================\u68c0\u6d4b\u901a\u8fc7====================="

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 45
    aget-object v7, v2, v10

    invoke-static {v5, v4, v7}, Lcom/TextOperation;->addChar(IILjava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 47
    .local v1, "array":Ljava/lang/String;
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v9, "                 "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_51

    .line 50
    .end local v1    # "array":Ljava/lang/String;
    :cond_cb
    sget-object v7, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "\u5185\u5bb9\u6709\u8bef\uff01\u8d85\u51fa"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    sub-int v9, v5, v4

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v9, "\u5b57\u8282 \u8bf7\u91cd\u65b0\u8f93\u5165"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_8d
.end method
