# Java8新特性

​	

# 介绍

		Java 8是Java自Java 5（发布于2004年）之后的最j具有革命性的版本。这个版本包含语言、编译器、库、工具和JVM等方面的十多个新特性。在本文中我们将学习这些新特性，并用实际的例子说明在什么场景下适合使用。
	
		Java 8 已经发布很久了，很多报道表明 Java 8 是一次重大的版本升级，虽然我们的 JDK 环境也升级到1.8，但是在日常的开发过程中，使用最多的编程风格还是停留在 JDK1.7。

## 有那些新特性

1. 速度更快
2. 代码更少(新增加了：**Lambda**表达式)
3. 强大的**Stream API**
4. 便于并行
5. 最大化减少空指针异常：(Optional)
6. **Nashorn引擎**，允许在JVM运行JS应用

    1. 运行语法

        ```sh
        jjs + [file.js]
        ```
    
2. 测试
   
        ```js
        function t()
        {
        	return 1;
        } 
        print(t()+1);
        ```
    
    3. 结果
    
        ![image](https://img2023.cnblogs.com/blog/2435432/202306/2435432-20230615013825544-138342431.png)​

# Lambda表达式

1. **什么是Lambda表达式？**

    <div>
    <span>&nbsp;&nbsp;&nbsp;&nbsp;Lambda是一个匿名函数，我们可以把 Lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递)。使用它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。</span>
    </div>
2. 初步使用

    1. `常规写法`

        1. `Runnable`​

            ```java
                @Test
                public void test01(){
                   //常规写法
                   Runnable run1 = new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("学习Java8");
                        }
                    };
                   run1.run();
                }
            ```
        2. `Comparatorinteger`​

            ```java
                @Test
                public void test02(){
                    //常规写法
                    Comparator<Integer> comparator1 = new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return Integer.compare(o1,o2);
                        }
                    };
                    int compare1 = comparator1.compare(123, 3123);
                    System.out.println(compare1);
                }
            ```
    2. `Lambda表达式写法`​

        1. `Runnable`​

            ```java
                @Test
                public void test01(){
                    //Lambda表达式写法
                    Runnable run2 = ()-> System.out.println("学习Java8 之Lambda表达式");
                    run2.run();
                }
            ```
        2. `Comparatorinteger`​

            ```java
                @Test
                public void test02(){
                    //Lambda表达式写法
                    Comparator<Integer> comparator2 = (o1,o2) -> Integer.compare(o1,o2);
                    int compare2 = comparator2.compare(123, 33);
                    System.out.println(compare2);
                }
            ```
    3. `方法引用`​

        1. `Comparatorinteger`​

            ```java
                @Test
                public void test02(){
                    //方法引用
                    Comparator<Integer> comparator3 = Integer::compare;
                    System.out.println(comparator3.compare(123, 31));
                }
            ```

## Lambda表达式的使用

1. 举例：(o1,o2) ->Integer.compare(o1,o2);

2. 格式：

   1. lambda操作符或者箭头操作符
   2. 左边：lambda形成列表（其实就是接口中的抽象方法的形参列表）
   3. 右边：lambda体（其实就是重写抽象方法的方法体）

3. Lambda表达式的使用：（分为9种情况介绍）

   1. 语法格式一：无参，无返回值

      ~~~Java
          @Test
          public void test01(){
              //Lambda表达式写法
              Runnable run2 = ()-> System.out.println("学习Java8 之Lambda表达式");
              run2.run();
          }
      ~~~

   2. 语法格式二：Lambda 需要一个参数，但是没有返回值

      ~~~java
      @Test
          public void test1(){
              //普通方式
              Consumer<String> consumer = new Consumer<String>() {
                  @Override
                  public void accept(String s) {
                      System.out.println(s);
                  }
              };
              consumer.accept("谎言和誓言的区别是什么？");
              System.out.println("=============================================");
              //Lambda表达式
              Consumer<String> consumer1 = (String s) ->{
                  System.out.println(s);
              };
              consumer1.accept("谎言和誓言的区别是什么？");
          }
      ~~~

   3. 语法格式三:数据类型可以省略，因为可由编译器推断得出，称为"类型推断"

      ~~~java
      @Test
          public void test1(){
              //Lambda表达式 类型推断
              Consumer<String> consumer1 = (s) ->{
                  System.out.println(s);
              };
              consumer1.accept("谎言和誓言的区别是什么？");
          }
      ~~~

   4. 语法格式四: Lambda若只需要一个参数时，参数的小括号可以省略

      ~~~Java
      @Test
          public void test1(){
              //Lambda表达式 类型推断
              Consumer<String> consumer1 = s ->{
                  System.out.println(s);
              };
              consumer1.accept("谎言和誓言的区别是什么？");
          }
      ~~~

   5. 语法格式五: Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值

      ~~~java
          @Test
          public void test02(){
              //Lambda表达式写法 
              Comparator<Integer> comparator2 = (o1, o2) -> {
                  System.out.println(o1);
                  System.out.println(o2);
                  return o1.compareTo(o2);
              };
              int compare2 = comparator2.compare(123, 33);
              System.out.println(compare2);
          }
      ~~~

   6. 语法格式六:当Lambda体只有一条语句时，return与大括号若有，都可以省略

      ~~~java
          @Test
          public void test02(){
              //Lambda表达式写法
              Comparator<Integer> comparator2 = (o1, o2) -> o1.compareTo(o2);
              System.out.println(comparator2.compare(4521,3125456));
          }
      ~~~

   7. <span style="color: red; font-weight: bold;">总结：</span>

      1. 左边lambda形参列表的参数类型可以省略(类型推断)；如果lambda形参列表只有一个参数，其一对()也可以省略
      2. 右边：Lambda体应该使用一对包裹;如果Lambda体只有一条执行语句(可能是return语句)，可以省略这一对{}和return关键字

4. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接。
5. Lambda表达式的本质：作为一个函数式接口的实例
# 函数式接口
## 什么是函数式接口
1. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接
	1. @FunctionalInterface：指定接口为函数式接口
2. 你可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常(即:非运行时异常)，那么该异常需要在目标接口的抽象方法上进行声明)。
3. 我们可以在一个接口上使用@FunctionalInterface注解，这样做可以检查它是否是一个函数式接口。同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。
4. 在java.util.function包下定义了Java8的丰富的函数式接口
## 如何理解函数式接口
- <div>
      <spam>&nbsp;&nbsp;&nbsp;&nbsp;Java从诞生日起就是一直倡导“一切皆对象”，在Java里面面向对象(OOP编程是一切。但是随着python、scala等语言的兴起和新技术的挑战，Java不得不做出调整以便支持更加广泛的技术要求，也即java不但可以支持OOP还可以支持OOF（面向函数编程)</spam>
  </div>

- <div>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;在函数式编程语言当中，函数被当做一等公民对待。在将函数作为一等公民的编程语言中，Lambda表达式的类型是函数。但是在Java8中，有所不同。在Java8中，Lambda表达式是对象，而不是函数，它们必须依附于一类特别的对象类型——函数式接口。</span>
  </div>

- <div>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;简单的说，在Java8中，Lambda表达式就是一个函数式接口的实例。这就是Lambda表达式和函数式接口的关系。也就是说，只要一个对象是函数式接口的实例，那么该对象就可以用Lambda表达式来表示。</span>
  </div>

- <div>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;所以以前使用匿名实现类表示的现在都可以用Lambda表达式来写</span>
  </div>

## Java内置四大核心函数式接口

| 函数式接口               | 类型参数 | 返回值  |                                                         用途 |
| ------------------------ | -------- | ------- | -----------------------------------------------------------: |
| Consumer<T>:消费性接口   | T        | void    |           对类型为T的对象应用操作，包含方法:void accept(T t) |
| Supplier<T>供给型接口    | 无       | T       |                        返回类型为干的对象，包含方法: T get() |
| Function<T, R>函数型接口 | T        | R       | 对类型为干的对象应用操作，并返回结果。结果是R类型的对象。包含方法:apply(T t) |
| Predicate<T>断定型接口   | T        | boolean | 确定类型为T的对象是否满足某约束，并返回boolean值。包含方法:boolean test(T t) |

# 方法引用

1. 当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用!

2. 方法引用可以看做是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambcla表达式的一个语法糖。

3. 要求:实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致!

4. 格式:使用操作符“∵”将类(或对象)与方法名分隔开来。

5. 如下三种主要使用情况:

   1. 对象::实例方法名

      ~~~java
      // 情况一 对象：：实例方法（非静态方法，所以需要类去调用）
      
              // Consumer中的 void accept(T t)
              // PrintStream中的 void println(T t)
              Consumer<String> con1 = str -> System.out.println(str);
              con1.accept("beijing");
              System.out.println("*************************");
              Consumer<String> con2 = System.out::println;
              con2.accept("北京");
      
              // Employee中的 String getName()
              // Supplier中的 T get()
              Employee emp = new Employee("jack", 5000, 2012, 10, 20);
              Supplier<String> sup1 = () -> emp.getName();
              System.out.println(sup1.get());
              System.out.println("*************************");
              Supplier<String> sup2 = emp::getName;
              System.out.println(sup2.get());
      ~~~
   2. 类::静态方法名
   		~~~java
   		// 情况二 类：：静态方法

        // Comparator中的 int compare(T t1, T t2)
        // Integer中的 int compare(T t1, T t2)
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(12,24));
        System.out.println("*************************");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12,24));

        // Function中的 R apply(T t)
        // Math中的 Long round(Double d)
        Function<Double, Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double d) {
                return Math.round(d);
            }
        };
        Function<Double, Long> func1 = d -> Math.round(d);
        System.out.println(func1.apply(24.0));
        System.out.println("*************************");
        Function<Double, Long> func2 = Math::round;
        System.out.println(func1.apply(24.0));
       ~~~

   3. 类::实例方法名
   		~~~java
   		// 情况三 类：：实例方法

        // Comparator中的 int compare(T t1, T t2)
        // String中的 int t1.compareTo(t2)
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc", "bcd"));
        System.out.println("*************************");
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abc", "bcd"));

        // BiPredicate中的 boolean test(T t1, T t2)
        // String中的 boolean t1.equals(t2)
        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        BiPredicate<String, String> pre2 = String::equals;

        // Function中的 R apply(T t)
        // Employee中的 String getName()
        Employee employee = new Employee("jack", 5000, 2012, 10, 20);
        Function<Employee, String> func1 = e -> e.getName();
        System.out.println(func1.apply(employee));
        System.out.println("*************************");
        Function<Employee, String> func2 = Employee::getName;
        System.out.println(func2.apply(employee));
       ~~~
       

## 构造器引用和数组引用

1. **构造器引用**

   1. 和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致；

   2. 抽象方法的返回值类型即为构造器所属的类的类型；

   3. 构造器引用代码实例

      ~~~java
      // 构造器引用
      
              // Supplier中的 T get()
              // Employee的无参构造器：Employee()
              Supplier<Employee> sup = new Supplier<Employee>() {
                  @Override
                  public Employee get() {
                      return new Employee();
                  }
              };
              Supplier<Employee> sup1 = () -> new Employee();
              System.out.println("*************************");
              Supplier<Employee> sup2 = Employee::new;
      
              // Function中的 R apply(T t)
              Function<Integer, Employee> func1 = id -> new Employee(id);
              Employee employee = func1.apply(1001);
              System.out.println("*************************");
              Function<Integer, Employee> func1 = Employee::new;
      
              // Function中的 R apply(T t, U u)
              Function<Integer, String, Employee> func2 = (id,name) -> new Employee(id, name);
              Employee employee1 = func1.apply(1001, "ACX");
              System.out.println("*************************");
              Function<Integer, String, Employee> func3 = Employee::new;
      ~~~

2. **数组引用**：可以把数组看作一个特殊的类，写法与构造器引用一致；

# StreamAPI

1. Stream关注的是对数据的运算，与CPU打交道；集合关注的是数据的存储，与内存打交道；
2. 注意点：
	1. Stream 自己不会存储元素。
	2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream
	3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
3. Stream 执行流程
	stream实例化 ---->  一系列的中间操作（过滤、映射、...） ---->  终止操作
4. 说明
	1. 一个中间操作链，对数据源的数据进行处理
	2. 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用

## Stream 实例化
1. 创建

   1. 方式一：通过集合
      - 方式一：通过集合
      - default Stream<E> parallelStream() : 返回一个并行流（类似多线程随机取）
   2. 方式二：通过数组（Java8 中的 Arrays 的静态方法 stream() 可以获取数组流）
      - static <T> Stream<T> stream(T[] array): 返回一个流
   3. 方式三：通过Stream 的of()
      - public static<T> Stream<T> of(T... values) : 返回一个流
   4. 方式四：创建无限流
      - 迭代 public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
      - 生成 public static<T> Stream<T> generate(Supplier<T> s)

2. 中间操作

   1. 筛选与切片

      ![image-20230624202259194](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202259194.png)

      ~~~java
      List<Employee> empList = EmployeeData.getEmployees();
              // filter(Predicate p)——接收 Lambda ， 从流中排除某些元素
              Stream<Employee> stream = empList.stream();
                  // 练习1 查询员工表中薪资大于7000的员工信息
                  stream.filter(e->e.getSalary() > 7000).forEach(System.out::println);
              // limit(n)——截断流，使其元素不超过给定数量
              empList.stream().limit(3).forEach(System.out::println);
              // skip(n)——跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
              empList.stream().skip(3).forEach(System.out::println);
              // distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
              empList.stream().distinct().forEach(System.out::println);
      ~~~

   2. 映射

      ![image-20230624202333137](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202333137.png)

   3. 排序

      ![image-20230624202349625](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202349625.png)

   4. **终止操作**

      1. 匹配与查找

         ![image-20230624202418408](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202418408.png)

         ![image-20230624202428924](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202428924.png)

   5. 归约

      ![image-20230624202444436](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202444436.png)

   6. 收集

      ![image-20230624202456673](Java8%E6%96%B0%E7%89%B9%E6%80%A7.assets/image-20230624202456673.png)

   7. Optional类：解决空指针问题；

