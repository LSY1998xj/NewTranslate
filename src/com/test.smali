.class public Lcom/test;
.super Ljava/lang/Object;
.source "test.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a()V
    .registers 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 12
    new-instance v0, Lcom/smaliDetail/SmaliFileInfo;

    const-string v1, "./"

    invoke-direct {v0, v1}, Lcom/smaliDetail/SmaliFileInfo;-><init>(Ljava/lang/String;)V

    .line 13
    .local v0, "smaliFileInfo":Lcom/smaliDetail/SmaliFileInfo;
    return-void
.end method
