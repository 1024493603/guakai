package com.practice.day18;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class IODemo {
    @Test
    public void testFileReader(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("io.txt");
            int ch = fileReader.read(); //字符流，每次读取一个字符
            System.out.println((char) ch);  //W
            int ch1 = fileReader.read();
            System.out.println((char)ch1);  //e
            int ch2 = fileReader.read();
            System.out.println((char) ch2); // l
            int ch3 = fileReader.read();
            System.out.println(ch3);        // -1
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReader2(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("io.txt");
            int ch = -1;
            while ((ch = fileReader.read()) != -1) {
                System.out.println((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReader3(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("io.txt");
            char[] buffer = new char[20];
            int ch = -1;
            while ((ch = fileReader.read(buffer)) != -1) {
                System.out.println(ch);
                System.out.println(Arrays.toString(buffer));
//                20
//                [q, w, e, r, t, y, u, i, o, p, a, s, d, f, g, h, j, k, l, z]
//                6
//                [x, c, v, b, n, m, u, i, o, p, a, s, d, f, g, h, j, k, l, z]
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileReaderWriter(){
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("io.txt");
            fileWriter = new FileWriter("io_back.txt");
            char[] buffer = new char[20];
            int length = -1;
            while ((length = fileReader.read(buffer)) != -1) {
                System.out.println(length);
                System.out.println(Arrays.toString(buffer));
                fileWriter.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //文本文件使用字节或字符流均可，但读取某部分内容用字符流
    //图片、视频等复制只能用字节流

    @Test
    public void testInputOutputStream(){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("a.png");
            outputStream = new FileOutputStream("a_back.png");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(buffer)) != -1) {
                //System.out.println(Arrays.toString(buffer));
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testObjectOutputStream(){
        Student student = new Student(2, "张三", 23);

        FileOutputStream fileOutputStream = null;
        ObjectOutput objectOutput = null;
        try {
            fileOutputStream = new FileOutputStream("student");
            objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objectOutput != null) {
                try {
                    objectOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testObjectInputStream() {
        FileInputStream inputStream = null;
        ObjectInputStream objectInput = null;
        try {
            inputStream = new FileInputStream("student");
            objectInput = new ObjectInputStream(inputStream);
            Student student = (Student) objectInput.readObject();
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInput != null) {
                try {
                    objectInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInput != null) {
                try {
                    objectInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
