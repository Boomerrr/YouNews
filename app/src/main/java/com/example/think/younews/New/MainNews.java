package com.example.think.younews.New;

import com.example.think.younews.Bean.MainNew;
import com.example.think.younews.R;

import java.util.ArrayList;

public class MainNews {
    public static ArrayList<MainNew> getData(){
        ArrayList<MainNew> mainNewArrayList = new ArrayList<>();

        MainNew mainNew1 = new MainNew();
        mainNew1.setText("  开学注意事项");
        mainNew1.setPicture(R.drawable.main1);
        mainNewArrayList.add(mainNew1);

        MainNew mainNew2 = new MainNew();
        mainNew2.setText("千喜鹤食堂重新装潢啦~");
        mainNew2.setPicture(R.drawable.main2);
        mainNewArrayList.add(mainNew2);

        MainNew mainNew3 = new MainNew();
        mainNew3.setText(" 大艺团招新资讯");
        mainNew3.setPicture(R.drawable.main3);
        mainNewArrayList.add(mainNew3);

        MainNew mainNew4 = new MainNew();
        mainNew4.setText("九月摄影大赛");
        mainNew4.setPicture(R.drawable.main4);
        mainNewArrayList.add(mainNew4);

        MainNew mainNew5 = new MainNew();
        mainNew5.setText("通信之星足球四强赛");
        mainNew5.setPicture(R.drawable.main5);
        mainNewArrayList.add(mainNew5);

        MainNew mainNew6 = new MainNew();
        mainNew6.setText("信科杯辩论赛开始啦");
        mainNew6.setPicture(R.drawable.main6);
        mainNewArrayList.add(mainNew6);

        MainNew mainNew7 = new MainNew();
        mainNew7.setText("红高粱食堂装修了");
        mainNew7.setPicture(R.drawable.main1);
        mainNewArrayList.add(mainNew7);

        MainNew mainNew8 = new MainNew();
        mainNew8.setText("911消防安全周");
        mainNew8.setPicture(R.drawable.main2);
        mainNewArrayList.add(mainNew8);

        MainNew mainNew9 = new MainNew();
        mainNew9.setText("学生会换届选举");
        mainNew9.setPicture(R.drawable.main3);
        mainNewArrayList.add(mainNew9);

        MainNew mainNew10 = new MainNew();
        mainNew10.setText("又是一年新生季");
        mainNew10.setPicture(R.drawable.main4);
        mainNewArrayList.add(mainNew10);

        return mainNewArrayList;
    }
}
