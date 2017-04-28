package com.example.huanghy.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 在活动中使用Menu菜单以及隐藏标题栏,隐士intent的用法
 */

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView openWebPage;//调用系统浏览器访问某个网页
    private TextView call;//拨打电话

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_main2);
        openWebPage = (TextView) findViewById(R.id.openWebPage);
        call = (TextView) findViewById(R.id.call);
        openWebPage.setOnClickListener(this);
        call.setOnClickListener(this);

    }

    @Override
    /**
     * android原生的:用于在活动中创建菜单,并显示菜单项
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        /*通过getMenuInflater()方法能够得到MenuInflater 对象，再调用它的inflate()方法就可以给
        当前活动创建菜单了。inflate()方法接收两个参数，第一个参数用于指定我们通过哪一个资源
        文件来创建菜单，这里当然传入R.menu.main，第二个参数用于指定我们的菜单项将添加到哪
        一个Menu 对象当中，这里直接使用onCreateOptionsMenu()方法中传入的menu 参数。然后给
        这个方法返回true，表示允许创建的菜单显示出来，如果返回了false，创建的菜单将无法显示。*/

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    /**
     * android原生的:定义菜单的响应事件，即菜单项的点击效果
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {//通过调用item.getItemId()来判断我们点击的是哪一个菜单项
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;//返回true表示处理完菜单项的事件，不需要将该事件继续传播下去了
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openWebPage:

                /*这里我们首先指定了Intent 的action 是Intent.ACTION_VIEW，这是一个Android 系统内
                置的动作，其常量值为android.intent.action.VIEW。然后通过Uri.parse()方法，将一个网址字
                符串解析成一个Uri 对象，再调用Intent 的setData()方法将这个Uri 对象传递进去。*/

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;

            /*与此对应，我们还可以在<intent-filter>标签中再配置一个<data>标签，用于更精确地指
            定当前活动能够响应什么类型的数据。<data>标签中主要可以配置以下内容。
            1. android:scheme
            用于指定数据的协议部分，如上例中的http 部分。
            2. android:host
            用于指定数据的主机名部分，如上例中的www.baidu.com 部分。
            3. android:port
            用于指定数据的端口部分，一般紧随在主机名之后。
            4. android:path
            用于指定主机名和端口之后的部分，如一段网址中跟在域名之后的内容。
            5. android:mimeType
            用于指定可以处理的数据类型，允许使用通配符的方式进行指定。
            只有<data>标签中指定的内容和Intent 中携带的Data 完全一致时，当前活动才能够响应
            该Intent。不过一般在<data>标签中都不会指定过多的内容，如上面浏览器示例中，其实只
            需要指定android:scheme 为http，就可以响应所有的http 协议的Intent 了。*/

            case R.id.call:

                /*首先指定了Intent 的action 是Intent.ACTION_DIAL，这又是一个Android 系统的内置动
                作。然后在data 部分指定了协议是tel，号码是10086。*/

                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:10086"));
                startActivity(intent1);
                break;
        }
    }
}
