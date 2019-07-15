package org.lhx.greedy;

import java.util.*;

/**
 * @author lhx
 * @date 2019/7/15 - 17:20
 */
public class Greedy {

    public static void main(String[] args) {
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        Set<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        Set<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        Set<String> hashSet4 = new HashSet<>();
        hashSet4.add("天津");
        hashSet4.add("上海");
        Set<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //存放所有的地区
        Set<String> allAres = new HashSet<>();
        allAres.add("北京");
        allAres.add("上海");
        allAres.add("天津");
        allAres.add("广州");
        allAres.add("深圳");
        allAres.add("成都");
        allAres.add("杭州");
        allAres.add("大连");

        //存放选择的电台集合
        List<String> selects = new ArrayList<>();

        //保存在遍历过程中存放遍历过程中电台覆盖的地区和当前没有覆盖地府的交集
        Set<String> tempSet = new HashSet<>();

        //定义maxKey,保存在一次遍历中能覆盖最大未覆盖地区对应的电台key,如果maxKey不为空,则会加入到selects中
        //如果allAres不为0,表示没有覆盖到所有地区
        String maxKey = null;
        while (allAres.size() != 0) {
            maxKey = null;
            //遍历broadcasts,取出对应的key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //当前key能覆盖的地区
                Set<String> strings = broadcasts.get(key);
                tempSet.addAll(strings);
                //求出tempSet和allAres集合的交集,交集会赋给tempSet
                tempSet.retainAll(allAres);
                //如果当前集合包含的未覆盖地区比maxKey指向的集合未覆盖地区多,就需要重置maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAres.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }

}
