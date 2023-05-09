package com;

import com.smaliMess;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;

public class JNIBridge {

    private static class a implements InvocationHandler {
        private Object a = new Object[0];
        private long b;

        /* renamed from: c  reason: collision with root package name */
        private Constructor f0c;

        public a(long j) {
            smaliMess.methodName("JNIBridge$a.smali.<init>调用");
            this.b = j;
            try {
                this.f0c = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                this.f0c.setAccessible(true);
            } catch (NoClassDefFoundError | NoSuchMethodException unused) {
                this.f0c = null;
            }
        }

        private Object a(Object obj, Method method, Object[] objArr) throws Throwable {
            smaliMess.methodName("JNIBridge$a.smali.a调用");
            if (objArr == null) {
                objArr = new Object[0];
            }
            Class<?> declaringClass = method.getDeclaringClass();
            return ((MethodHandles.Lookup) this.f0c.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
        }

        public final void a() {
            smaliMess.methodName("JNIBridge$a.smali.a调用");
            synchronized (this.a) {
                this.b = 0;
            }
        }

        @Override // java.lang.Object
        public final void finalize() {
            smaliMess.methodName("JNIBridge$a.smali.finalize调用");
            synchronized (this.a) {
                if (this.b != 0) {
                    JNIBridge.delete(this.b);
                }
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            smaliMess.methodName("JNIBridge$a.smali.invoke调用");
            synchronized (this.a) {
                if (this.b == 0) {
                    return null;
                }
                try {
                    return JNIBridge.invoke(this.b, method.getDeclaringClass(), method, objArr);
                } catch (NoSuchMethodError e) {
                    if (this.f0c == null) {
                        System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                        throw e;
                    } else if ((method.getModifiers() & 1024) == 0) {
                        return a(obj, method, objArr);
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    static native void delete(long j);

    static void disableInterfaceProxy(Object obj) {
        if (obj != null) {
            ((a) Proxy.getInvocationHandler(obj)).a();
        }
    }

    static native Object invoke(long j, Class cls, Method method, Object[] objArr);

    static Object newInterfaceProxy(long j, Class[] clsArr) {
        smaliMess.methodName("JNIBridge.smali.newInterfaceProxy调用");
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), clsArr, new a(j));
    }
}