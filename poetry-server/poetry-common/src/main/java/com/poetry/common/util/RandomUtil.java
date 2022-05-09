package com.poetry.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    public static List getRandomThreeInfoList(List list, int count) {
        List olist = new ArrayList<>();
        if (list.size() <= count) {
            return list;
        } else {
            Random random = new Random();
            for (int i = 0 ;i<count;i++){
                int intRandom = random.nextInt(list.size() - 1);
                olist.add(list.get(intRandom));
                list.remove(list.get(intRandom));
            }
            return olist;
        }
    }
}
