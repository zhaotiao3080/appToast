
# AppToast  一个 app中弹出toast的方便的小工具

使用步骤：

1.

2.在Application的onCreate()中初始化
AppToast.init(getContext());
即可在整个应用中随意使用

3.使用的地方  直接调用

a.AppToast.show("你好...");//参数可以是 string类型的内容

b.AppToast.show(R.string.tv_hello);//参数可以是 int的 strResId

c.AppToast.show(R.string.tv_hello,1000);//第一个参数是 int类型的 string的ResId,第二个参数是设置延迟多长时间显示toast（第二个参数即是  1000为1秒）

d.AppToast.show("你好...",1000);//第一个参数是 string类型的内容,第二个参数是设置延迟多长时间显示toast（1000为1秒）
