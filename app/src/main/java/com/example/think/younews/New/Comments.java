package com.example.think.younews.New;

import com.example.think.younews.Bean.Comment;

import java.util.ArrayList;

public class Comments {
    public static ArrayList<Comment> getData(){
        ArrayList<Comment> comments = new ArrayList<>();

        Comment comment1 = new Comment();
        comment1.setUserName("小张");
        comment1.setTime("01:01");
        comment1.setComment("哇塞。。。。。");

        Comment comment2 = new Comment();
        comment1.setUserName("吴杰");
        comment1.setTime("02:02");
        comment1.setComment("我也这样觉得");
        comments.add(comment2);

        Comment comment3 = new Comment();
        comment1.setUserName("田宇");
        comment1.setTime("03:03");
        comment1.setComment("我觉得你好棒啊");
        comments.add(comment3);

        Comment comment4 = new Comment();
        comment1.setUserName("赵四");
        comment1.setTime("04:04");
        comment1.setComment("尼古拉斯赵赞同你");
        comments.add(comment4);

        Comment comment5 = new Comment();
        comment1.setUserName("周杰伦");
        comment1.setTime("05:05");
        comment1.setComment("哎哟，不错哟。。。。。");
        comments.add(comment5);

        Comment comment6 = new Comment();
        comment1.setUserName("Mike");
        comment1.setTime("06:06");
        comment1.setComment("I got it.");
        comments.add(comment6);

        Comment comment7 = new Comment();
        comment1.setUserName("Bob");
        comment1.setTime("07:07");
        comment1.setComment("It is a little cool");
        comments.add(comment7);

        Comment comment8 = new Comment();
        comment1.setUserName("赵丽");
        comment1.setTime("08:08");
        comment1.setComment("你要加油啊。。");
        comments.add(comment8);

        Comment comment9 = new Comment();
        comment1.setUserName("李勇");
        comment1.setTime("09:09");
        comment1.setComment("别灰心，可以的");
        comments.add(comment9);

        Comment comment10 = new Comment();
        comment1.setUserName("胖哥");
        comment1.setTime("10:10");
        comment1.setComment("走啊，吃小面了");
        comments.add(comment10);

        Comment comment11 = new Comment();
        comment1.setUserName("李家兴");
        comment1.setTime("11:01");
        comment1.setComment("少年你需要学习。。。。。");
        comments.add(comment11);

        return comments;
    }
}
