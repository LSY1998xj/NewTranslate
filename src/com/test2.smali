.class public Lcom/test2;
.super Ljava/lang/Object;
.source "test2.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 6
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static main([Ljava/lang/String;)V
    .registers 2
    .param p0, "args"    # [Ljava/lang/String;

    .prologue
    .line 8
    const-string v0, "qwe"

    invoke-static {v0}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 9
    return-void
.end method
