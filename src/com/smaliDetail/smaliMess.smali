.class public Lcom/smaliMess;
.super Ljava/lang/Object;
.source "smaliMess.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static methodName(Ljava/lang/String;)V
    .registers 2
    .param p0, "str"    # Ljava/lang/String;

    .prologue
    .line 5
    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {v0, p0}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 6
    return-void
.end method
