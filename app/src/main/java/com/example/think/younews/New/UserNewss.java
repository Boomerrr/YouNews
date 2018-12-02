package com.example.think.younews.New;

import com.example.think.younews.Bean.News;
import com.example.think.younews.Bean.UserNews;

import java.util.ArrayList;

public class UserNewss {
    public static ArrayList<UserNews> getData(){
        ArrayList<UserNews> userNewsArrayList = new ArrayList<>();

        UserNews userNews1 = new UserNews();
        userNews1.setUserName("小明");
        userNews1.setTime("6.22");
        userNews1.setText("有人捡到我的运动手环吗");
        userNewsArrayList.add(userNews1);

        UserNews userNews2 = new UserNews();
        userNews2.setUserName("刘胤");
        userNews2.setTime("5.55");
        userNews2.setText("有人一起玩游戏吗");
        userNewsArrayList.add(userNews2);

        UserNews userNews3 = new UserNews();
        userNews3.setUserName("WSW");
        userNews3.setTime("5.28");
        userNews3.setText("信号与系统好难啊");
        userNewsArrayList.add(userNews3);

        UserNews userNews4 = new UserNews();
        userNews4.setUserName("吴杰");
        userNews4.setTime("10.44");
        userNews4.setText("哇塞，去吃火锅");
        userNewsArrayList.add(userNews4);

        UserNews userNews5 = new UserNews();
        userNews5.setUserName("陈松");
        userNews5.setTime("21.99");
        userNews5.setText("有人去唱歌吗。。。我贼溜");
        userNewsArrayList.add(userNews5);

        UserNews userNews6 = new UserNews();
        userNews6.setUserName("韩秋雨");
        userNews6.setTime("7.11");
        userNews6.setText("好困啊，不想上课啦");
        userNewsArrayList.add(userNews6);

        UserNews userNews7 = new UserNews();
        userNews7.setUserName("铁柱");
        userNews7.setTime("4.11");
        userNews7.setText("楼上是逗逼");
        userNewsArrayList.add(userNews7);

        UserNews userNews8 = new UserNews();
        userNews8.setUserName("王尼玛");
        userNews8.setTime("11.44");
        userNews8.setText("荆轲刺秦王");
        userNewsArrayList.add(userNews8);

        UserNews userNews9 = new UserNews();
        userNews9.setUserName("张劲松");
        userNews9.setTime("3.11");
        userNews9.setText("我下午去学习一天");
        userNewsArrayList.add(userNews9);

        UserNews userNews10 = new UserNews();
        userNews10.setUserName("WYL");
        userNews10.setTime("6.22");
        userNews10.setText("重庆方所真是个好地方，");
        userNewsArrayList.add(userNews10);

        return userNewsArrayList;
    }
}
