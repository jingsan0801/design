# 单例模式的六种实现

单例模式可以有八种实现, 其中最后两种是完美的, 但实际应用中根据不同场景, 并不一定要使用完美的实现.

[相关代码](https://github.com/jingsan0801/design)


## 饿汉式
最简单的是饿汉式:

* 把构造器置为`private`,保证不能在外部通过`new`的方式创建新实例
* 使用`static`保证只有在这个class加载的时候调用一次


```java
public class Single {
    private static final Single INSTANCE = new Single();

    private Single() {
    }

    public static Single getInstance() {
        return INSTANCE;
    }
}

```

### 缺点

这种实现`唯一的缺点`是, `new Single()` 是在类加载的时候创建的, 所以可能造成浪费, 因为创建的这个实例可能是用不到的.

大多数情况下推荐使用这种方式.

## 懒汉式

为了避免饿汉式可能导致的浪费, 改为在使用的时候再创建实例.

```java
public class Single02 {
    private static Single02 INSTANCE;

    private Single02() {

    }

	  // 因为判空和new是两个操作, 多线程的情况下很容易出现不一致的问题
    public static Single02 getInstance(){
		  // 多线程的情况下, 可能会被其他线程打断, 就会出现问题
        if(INSTANCE == null){
			  // 这里如果有其他的逻辑, 耗时会更长, 就更容易出现判空结果不一致的问题
            INSTANCE = new Single02();
        }
        return INSTANCE;
    }
}

```

### 缺点

多线程下会导致可能创建了多个实例的问题

![饿汉式线程安全问题](https://tva1.sinaimg.cn/large/007S8ZIlgy1gg02dgz67tj30u00ykqd9.jpg)

## 加锁的懒汉式

为了解决第二种实现方式可能导致的多线程问题, 使用`synchronize`加锁, 解决

```java
public class Single03 {
    private static Single03 INSTANCE;

    private Single03() {

    }

    // 使用synchronize加锁
    public static synchronized Single03 getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Single03();
        }
        return INSTANCE;
    }
}
```

已经解决了线程安全问题:
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gg02iiblwsj30u00y3tjg.jpg)

### 缺点

使用`synchronize`在方法上加锁, 会导致效率降低


### 加锁方式优化: 双重判断

```java
public class Single04 {
    private static volatile Single04 INSTANCE;

    private Single04() {

    }

    public static Single04 getInstance() {
        if (INSTANCE == null) {
            // 把 synchronize 放到方法里面, 只在部分代码上加锁
            synchronized (Single04.class) {
                // 双重判断, 否则还是没有把判空和new两个动作作为一个整体
                if (INSTANCE == null) {
                    INSTANCE = new Single04();
                }
            }

        }
        return INSTANCE;
    }
}
```


### 缺点

没有了线程安全问题, 但还是使用了`synchronize`, 而且逻辑比较复杂.

另外要注意的是要使用`volatile`, 避免在指令重排的情况下出现执行顺序变化导致的异常.

## 静态内部类实现

这种方式, 解决了第一种在外部类被加载时, 就初始化了实例的问题. 因为内部类在外部类被加载时是不会被加载的, 而是在被调用时才会被加载.

而且JVM加载本身只会加载一次static, 所以也就保证了单例.

这种方式是完美的.

```java
public class Single05 {

    private Single05() {

    }

    // 静态内部类在外部类被加载时, 是不会被加载的, 而是在被调用的时候才会被加载
    private static class Single05Holder{
        private final static Single05 INSTANCE = new Single05();
    }

    public static Single05 getInstance() {
        return Single05Holder.INSTANCE;
    }
}
```


## 枚举实现

```java
public enum  Single06 {

    INSTANCE;
}
```

这种实现是在<effective java>中推荐实现的方式.

因为枚举类型是线程安全的, 并且只会装载一次. 

### 完美的实现

枚举方式的实现是目前最完美的.

前面的几种方式都存在的问题, 枚举的方式不存在:

#### 通过反序列化突破单例

单例的class如果实现了 `Serializable`, 就可以通过反序列化的方式重新构建一个实例, 这样就突破了单例模式. 但`enum`没有这个问题.

```java
public class SerializeTest {

    private static void write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
//            Single01 single = Single01.getInstance();
            Single06 single = Single06.INSTANCE;
            oos.writeObject(single);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
//            Single01 single = (Single01) ois.readObject();
            Single06 single = (Single06) ois.readObject();
            System.out.println(single);

//            System.out.println(single == Single01.getInstance()); // return false
			  // enum返回的始终是 "INSTANCE"
            System.out.println(single == Single06.INSTANCE); //return true

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        write();
        read();
    }

}

```

解决的方法是在单例中重写`Object readResolve()`方法, 使之同样返回`INSTANCE`

```java
public class Single01 implements Serializable {
    // static 因为要在 static 的 getInstance()中使用
    // final 是为了避免被修改
    private static final Single01 INSTANCE = new Single01();

    private Single01(){

    }

    public static Single01 getInstance() {
        return INSTANCE;
    }


    private Object readResolve() throws ObjectStreamException{
        return INSTANCE;
    }
}
```

#### 通过反射方式突破单例

通过反射, 可以直接调用private的构造器, 再创建一个实例. 但因为`Enum`没有构造器, 所以无法通过这种方式不会有问题.

```java
public class ReflectTest {
    public static void main(String[] args)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
        InvocationTargetException {
        Single01 s1 = Single01.getInstance();
        final Class<?> singleClass = Class.forName("com.example.design.singleton.Single01");
        Constructor<?> declaredConstructor = singleClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        final Single01 s11 = (Single01)declaredConstructor.newInstance();
		  // 枚举实现的会报错, 因为enum没有构造器
		  // final Single06 s11 = (Single06)declaredConstructor.newInstance(); // 抛出 java.lang.NoSuchMethodException 异常
        System.out.println(s1 == s11);
    }
}

```

当然, 可以通过修改`private`构造器的方法来禁止被直接调用, 比如:

```java
private Single01(){
    if (INSTANCE !=null){
        throw new RuntimeException("error");
    }
}
```

