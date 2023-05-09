.class Lcom/JNIBridge$a;
.super Ljava/lang/Object;
.source "JNIBridge.java"

# interfaces
.implements Ljava/lang/reflect/InvocationHandler;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/JNIBridge;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field private a:Ljava/lang/Object;

.field private b:J

.field private f0c:Ljava/lang/reflect/Constructor;


# direct methods
.method public constructor <init>(J)V
    .registers 8
    .param p1, "j"    # J

    .prologue
    const/4 v1, 0x0

    .line 17
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 11
    new-array v1, v1, [Ljava/lang/Object;

    iput-object v1, p0, Lcom/JNIBridge$a;->a:Ljava/lang/Object;

    .line 18
    const-string v1, "JNIBridge$a.smali.<init>\u8c03\u7528"

    invoke-static {v1}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 19
    iput-wide p1, p0, Lcom/JNIBridge$a;->b:J

    .line 21
    :try_start_f
    const-class v1, Ljava/lang/invoke/MethodHandles$Lookup;

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Class;

    const/4 v3, 0x0

    const-class v4, Ljava/lang/Class;

    aput-object v4, v2, v3

    const/4 v3, 0x1

    sget-object v4, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    aput-object v4, v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/Class;->getDeclaredConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;

    move-result-object v1

    iput-object v1, p0, Lcom/JNIBridge$a;->f0c:Ljava/lang/reflect/Constructor;

    .line 22
    iget-object v1, p0, Lcom/JNIBridge$a;->f0c:Ljava/lang/reflect/Constructor;

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Ljava/lang/reflect/Constructor;->setAccessible(Z)V
    :try_end_2a
    .catch Ljava/lang/NoClassDefFoundError; {:try_start_f .. :try_end_2a} :catch_2b
    .catch Ljava/lang/NoSuchMethodException; {:try_start_f .. :try_end_2a} :catch_30

    .line 26
    :goto_2a
    return-void

    .line 23
    :catch_2b
    move-exception v0

    .line 24
    .local v0, "unused":Ljava/lang/Throwable;
    :goto_2c
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/JNIBridge$a;->f0c:Ljava/lang/reflect/Constructor;

    goto :goto_2a

    .line 23
    .end local v0    # "unused":Ljava/lang/Throwable;
    :catch_30
    move-exception v0

    goto :goto_2c
.end method

.method private a(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
    .registers 9
    .param p1, "obj"    # Ljava/lang/Object;
    .param p2, "method"    # Ljava/lang/reflect/Method;
    .param p3, "objArr"    # [Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    const/4 v4, 0x2

    const/4 v3, 0x0

    .line 29
    const-string v1, "JNIBridge$a.smali.a\u8c03\u7528"

    invoke-static {v1}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 30
    if-nez p3, :cond_b

    .line 31
    new-array p3, v3, [Ljava/lang/Object;

    .line 33
    :cond_b
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v0

    .line 34
    .local v0, "declaringClass":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    iget-object v1, p0, Lcom/JNIBridge$a;->f0c:Ljava/lang/reflect/Constructor;

    new-array v2, v4, [Ljava/lang/Object;

    aput-object v0, v2, v3

    const/4 v3, 0x1

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/reflect/Constructor;->newInstance([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/invoke/MethodHandles$Lookup;

    invoke-virtual {v1, v0}, Ljava/lang/invoke/MethodHandles$Lookup;->in(Ljava/lang/Class;)Ljava/lang/invoke/MethodHandles$Lookup;

    move-result-object v1

    invoke-virtual {v1, p2, v0}, Ljava/lang/invoke/MethodHandles$Lookup;->unreflectSpecial(Ljava/lang/reflect/Method;Ljava/lang/Class;)Ljava/lang/invoke/MethodHandle;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/invoke/MethodHandle;->bindTo(Ljava/lang/Object;)Ljava/lang/invoke/MethodHandle;

    move-result-object v1

    invoke-virtual {v1, p3}, Ljava/lang/invoke/MethodHandle;->invokeWithArguments([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    return-object v1
.end method


# virtual methods
.method public final a()V
    .registers 5

    .prologue
    .line 38
    const-string v0, "JNIBridge$a.smali.a\u8c03\u7528"

    invoke-static {v0}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 39
    iget-object v1, p0, Lcom/JNIBridge$a;->a:Ljava/lang/Object;

    monitor-enter v1

    .line 40
    const-wide/16 v2, 0x0

    :try_start_a
    iput-wide v2, p0, Lcom/JNIBridge$a;->b:J

    .line 41
    monitor-exit v1

    .line 42
    return-void

    .line 41
    :catchall_e
    move-exception v0

    monitor-exit v1
    :try_end_10
    .catchall {:try_start_a .. :try_end_10} :catchall_e

    throw v0
.end method

.method public final finalize()V
    .registers 7

    .prologue
    .line 46
    const-string v0, "JNIBridge$a.smali.finalize\u8c03\u7528"

    invoke-static {v0}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 47
    iget-object v1, p0, Lcom/JNIBridge$a;->a:Ljava/lang/Object;

    monitor-enter v1

    .line 48
    :try_start_8
    iget-wide v2, p0, Lcom/JNIBridge$a;->b:J

    const-wide/16 v4, 0x0

    cmp-long v0, v2, v4

    if-eqz v0, :cond_15

    .line 49
    iget-wide v2, p0, Lcom/JNIBridge$a;->b:J

    invoke-static {v2, v3}, Lcom/JNIBridge;->delete(J)V

    .line 51
    :cond_15
    monitor-exit v1

    .line 52
    return-void

    .line 51
    :catchall_17
    move-exception v0

    monitor-exit v1
    :try_end_19
    .catchall {:try_start_8 .. :try_end_19} :catchall_17

    throw v0
.end method

.method public final invoke(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
    .registers 12
    .param p1, "obj"    # Ljava/lang/Object;
    .param p2, "method"    # Ljava/lang/reflect/Method;
    .param p3, "objArr"    # [Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 56
    const-string v1, "JNIBridge$a.smali.invoke\u8c03\u7528"

    invoke-static {v1}, Lcom/smaliMess;->methodName(Ljava/lang/String;)V

    .line 57
    iget-object v2, p0, Lcom/JNIBridge$a;->a:Ljava/lang/Object;

    monitor-enter v2

    .line 58
    :try_start_8
    iget-wide v4, p0, Lcom/JNIBridge$a;->b:J

    const-wide/16 v6, 0x0

    cmp-long v1, v4, v6

    if-nez v1, :cond_13

    .line 59
    const/4 v1, 0x0

    monitor-exit v2
    :try_end_12
    .catchall {:try_start_8 .. :try_end_12} :catchall_1f

    .line 68
    :goto_12
    return-object v1

    .line 62
    :cond_13
    :try_start_13
    iget-wide v4, p0, Lcom/JNIBridge$a;->b:J

    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v1

    invoke-static {v4, v5, v1, p2, p3}, Lcom/JNIBridge;->invoke(JLjava/lang/Class;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_1c
    .catch Ljava/lang/NoSuchMethodError; {:try_start_13 .. :try_end_1c} :catch_22
    .catchall {:try_start_13 .. :try_end_1c} :catchall_1f

    move-result-object v1

    :try_start_1d
    monitor-exit v2

    goto :goto_12

    .line 73
    :catchall_1f
    move-exception v1

    monitor-exit v2
    :try_end_21
    .catchall {:try_start_1d .. :try_end_21} :catchall_1f

    throw v1

    .line 63
    :catch_22
    move-exception v0

    .line 64
    .local v0, "e":Ljava/lang/NoSuchMethodError;
    :try_start_23
    iget-object v1, p0, Lcom/JNIBridge$a;->f0c:Ljava/lang/reflect/Constructor;

    if-nez v1, :cond_2f

    .line 65
    sget-object v1, Ljava/lang/System;->err:Ljava/io/PrintStream;

    const-string v3, "JNIBridge error: Java interface default methods are only supported since Android Oreo"

    invoke-virtual {v1, v3}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    .line 66
    throw v0

    .line 67
    :cond_2f
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getModifiers()I

    move-result v1

    and-int/lit16 v1, v1, 0x400

    if-nez v1, :cond_3d

    .line 68
    invoke-direct {p0, p1, p2, p3}, Lcom/JNIBridge$a;->a(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    monitor-exit v2

    goto :goto_12

    .line 70
    :cond_3d
    throw v0
    :try_end_3e
    .catchall {:try_start_23 .. :try_end_3e} :catchall_1f
.end method
