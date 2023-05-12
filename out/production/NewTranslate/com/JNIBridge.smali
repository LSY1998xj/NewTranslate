.class public Lcom/JNIBridge;
.super Ljava/lang/Object;
.source "JNIBridge.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/JNIBridge$a;
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static native delete(J)V
.end method

.method static disableInterfaceProxy(Ljava/lang/Object;)V
    .registers 2
    .param p0, "obj"    # Ljava/lang/Object;

    .prologue
    .line 80
    if-eqz p0, :cond_b

    .line 81
    invoke-static {p0}, Ljava/lang/reflect/Proxy;->getInvocationHandler(Ljava/lang/Object;)Ljava/lang/reflect/InvocationHandler;

    move-result-object v0

    check-cast v0, Lcom/JNIBridge$a;

    invoke-virtual {v0}, Lcom/JNIBridge$a;->a()V

    .line 83
    :cond_b
    return-void
.end method

.method static native invoke(JLjava/lang/Class;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
.end method

.method static newInterfaceProxy(J[Ljava/lang/Class;)Ljava/lang/Object;
    .registers 5
    .param p0, "j"    # J
    .param p2, "clsArr"    # [Ljava/lang/Class;

    .prologue
    .line 88
    const-string v0, "JNIBridge.smali.newInterfaceProxy\u8c03\u7528"

    invoke-static {v0}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 89
    const-class v0, Lcom/JNIBridge;

    invoke-virtual {v0}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v0

    new-instance v1, Lcom/JNIBridge$a;

    invoke-direct {v1, p0, p1}, Lcom/JNIBridge$a;-><init>(J)V

    invoke-static {v0, p2, v1}, Ljava/lang/reflect/Proxy;->newProxyInstance(Ljava/lang/ClassLoader;[Ljava/lang/Class;Ljava/lang/reflect/InvocationHandler;)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method
