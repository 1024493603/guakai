package com.practice.day4;


public class Student extends Person implements IStudy {
        // 属性 field ：静态的描述这个事物特征
        // 属性私有,封装保护 private:访问范围是当前类
        private int age;

        //自己提供有参的构造函数时需要再提供一个无参的

        public Student() {
        }

        //仅提供id和name,age默认为50
        public Student(int id, String name) {
            this(id , name , 50);
        }

        public Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        /*
        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
        */
        //父类的内容也同样打印
        @Override  //表示覆盖父类的同名方法 （重写/覆盖）
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", tall=" + tall +
                    ", weight=" + weight +
                    ", age=" + age +
                    '}';
        }

        //覆盖接口并实现
        @Override
        public void Study() {
            System.out.println("Student.Study");
        }
    }