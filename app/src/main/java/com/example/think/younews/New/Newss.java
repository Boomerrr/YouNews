package com.example.think.younews.New;

import com.example.think.younews.Bean.MainNew;
import com.example.think.younews.Bean.News;

import java.util.ArrayList;

public class Newss {
    public static ArrayList<News> getData(){
        ArrayList<News> newsArrayList = new ArrayList<>();

        News news1 = new News();
        news1.setUserName("校学生会");
        news1.setTime("10.21");
        news1.setNews(" 校学生会招新了。。。。");
        newsArrayList.add(news1);

        News news2 = new News();
        news2.setUserName("校团委");
        news2.setTime("4.21");
        news2.setNews("重邮约跑活动开展。。。");
        newsArrayList.add(news2);

        News news3 = new News();
        news3.setUserName("团总支");
        news3.setTime("7.14");
        news3.setNews("院运会即将在下周举办");
        newsArrayList.add(news3);

        News news4 = new News();
        news4.setUserName("校党委");
        news4.setTime("10.21");
        news4.setNews("关于学风建设的重要指导");
        newsArrayList.add(news4);

        News news5 = new News();
        news5.setUserName("通信学院学生会");
        news5.setTime("6.21");
        news5.setNews("开展课堂无手机活动。。。。");
        newsArrayList.add(news5);

        News news6 = new News();
        news6.setUserName("宿管会");
        news6.setTime("9.17");
        news6.setNews("9月消防安全月，宿舍进行大规模排查安全隐患");
        newsArrayList.add(news6);

        News news7 = new News();
        news7.setUserName("重邮e站" );
        news7.setTime("12.21");
        news7.setNews("我爱古诗词活动开展，欢迎参与");
        newsArrayList.add(news7);

        News news8 = new News();
        news8.setUserName("青年志愿者协会");
        news8.setTime("8.01");
        news8.setNews("青年志愿周活动开展，欢迎各位同学积极参与");
        newsArrayList.add(news8);

        News news9 = new News();
        news9.setUserName("计算机学院团总支");
        news9.setTime("05.21");
        news9.setNews("图灵挑战赛开展，希望同学们踊跃参与");
        newsArrayList.add(news9);

        News news10 = new News();
        news10.setUserName("学生处");
        news10.setTime("21.41");
        news10.setNews("关于校园外卖进行整治");
        newsArrayList.add(news10);

       return newsArrayList;
    }
}
