package com.example.huanghy.myapp.util.storage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by huanghy on 2017/4/9.
 *      文件存储以及读取(storage  [ˈstɔ:rɪdʒ]-->>"存储"的意思)
 *
 *      存储位置：可以在DDMS下的files目录下查看，是fileName文件.
 *
 *      文件存储的方式并不适合用于保存一些较为复杂的文本数据.
 *
 *      文件存储是Android 中最基本的一种数据存储方式，它不对存储的内容进行任何的格式
 *      化处理，所有数据都是原封不动地保存到文件当中的，因而它比较适合用于存储一些简单的
 *      文本数据或二进制数据。如果你想使用文件存储的方式来保存一些较为复杂的文本数据，就
 *      需要定义一套自己的格式规范，这样方便于之后将数据从文件中重新解析出来。
 *
 *      其实所用到的核心技术就是Context类中提供的openFileInput()和openFileOutput()方法，
 *      之后就是利用Java 的各种流来进行读写操作就可以了。
 *
 *      Android 系统中主要提供了三种方式用于简单地实现数据持久化功能，
 *      即文件存储、SharedPreference 存储以及数据库存储。当然，除了这三种方式之外，你还可
 *      以将数据保存在手机的SD 卡中，不过使用文件、SharedPreference 或数据库来保存数据会相
 *      对更简单一些，而且比起将数据保存在SD 卡中会更加的安全。
 *
 */

public class FileStorageUtil {

    /**
     * 保存数据到文件中,需要外部指定创建的文件名称fileName
     * @param context
     * @param fileName  存储的文件名称，如果没有该文件则会自动创建
     * @param data  需要存储的数据内容（多次向同一个文件操作时候，会覆盖原有的数据内容）
     */
    public static void save(Context context,String fileName,String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {

            /*Context 类中提供了一个openFileOutput ()方法，可以用于将数据存储到指定的文件中。
            这个方法接收两个参数，第一个参数是文件名，在文件创建的时候使用的就是这个名称，注
            意这里指定的文件名不可以包含路径，因为所有的文件都是默认存储到/data/data/<package
            name>/files/ 目录下的。第二个参数是文件的操作模式， 主要有两种模式可选，
            MODE_PRIVATE 和MODE_APPEND。其中MODE_PRIVATE 是默认的操作模式，表示当指
            定同样文件名的时候，所写入的内容将会覆盖原文件中的内容，而MODE_APPEND 则表示
            如果该文件已存在就往文件里面追加内容，不存在就创建新文件。其实文件的操作模式本来
            还有另外两种，MODE_WORLD_READABLE 和MODE_WORLD_WRITEABLE，这两种模
            式表示允许其他的应用程序对我们程序中的文件进行读写操作，不过由于这两种模式过于危
            险，很容易引起应用的安全性漏洞，现已在Android 4.2 版本中被废弃。*/

            out = context.openFileOutput(fileName, MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从文件中读取数据，需要外部指定从哪个文件fileName中读取数据
     * @param context
     * @param fileName  文件名
     * @return
     */
    public static String restore(Context context,String fileName) {

        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {

            /*类似于将数据存储到文件中，Context 类中还提供了一个openFileInput()方法，用于从文
            件中读取数据。这个方法要比openFileOutput()简单一些，它只接收一个参数，即要读取的文
            件名，然后系统会自动到/data/data/<package name>/files/目录下去加载这个文件，并返回一个
            FileInputStream 对象，得到了这个对象之后再通过Java 流的方式就可以将数据读取出来了。*/

            in = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }




    /*****************************使用data作为默认文件实现文件内容存取***********************/

    /**
     * 保存数据到data文件中，指定默认文件的名称为data，即不需要考虑文件名称问题，都是存储在data文件中
     * @param context
     * @param data
     */
    public static void save(Context context,String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput("data", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从data文件中读取数据
     * @param context
     * @return
     */
    public static String restore(Context context) {

        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = context.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


}
