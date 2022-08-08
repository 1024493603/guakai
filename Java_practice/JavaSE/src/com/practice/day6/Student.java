package com.practice.day6;

public class Student implements Comparable<Student> {
    private Integer id;
    private String name;
    private Integer age;
    public Student() {
        super();
    }
    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

    //自建类型时如果要使用HashSet，必须自行手动实现hashCode和equals

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        // 根据id值和name值age值是不是相等看是否已经存在
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    //TreeSet解决方法
    //按照年龄来排序，年龄相同按照名字排序
    @Override
    public int compareTo(Student o) {
        int num = this.age - o.age;                //年龄是比较的主要条件
        return num == 0 ? this.name.compareTo(o.name) : num;//姓名是比较的次要条件
    }

        //按照姓名长度排序：
//    public int compareTo(Student o) {
//            int length = this.name.length() - o.name.length();              //比较长度为主要条件
//            int num = length == 0 ? this.name.compareTo(o.name) : length;    //比较内容为次要条件
//            return num == 0 ? this.age - o.age : num;                        //比较年龄为次要条件
//    }
}
