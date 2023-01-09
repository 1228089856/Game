package fightingLandlord;

import java.util.*;

/**
 * 需求：
 * 在启动游戏房间的时候，应该提前准备好54张牌，完成洗牌、发牌、牌排序、逻辑
 * 分析：
 * 1、当系统启动的同时需要准备好数据的时候，就可以用静态代码块了
 * 2、洗牌就是打乱牌的顺序
 * 3、定义三个玩家、一次发出51张牌
 * 4、给玩家的牌进行排序（拓展）
 * 5、输出每个玩家的牌数据
 * 功能：
 * 1.做牌
 * 2.洗牌
 * 3.定义三个玩家
 * 4.发牌
 * 5.排序（拓展，了解）
 * 6.看牌
 */
public class GameDemo {
    /**
     * 1、定义一个静态的集合存储54张牌对象
     */
    public static List<Card> allCards = new ArrayList<>();

    /*
        2、做牌：定义静态代码块初始化数据
     */

    static {
        //3、定义点数：个数确定，类型确定，使用数组
        String[] sizes = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "k", "A", "2"};
        //4、定义花色：个数确定、类型确定，使用数组
        String[] colors = {"♥", "♦", "♠", "♣"};
        //5、组合点数和花色
        int index = 0;//记录牌的大小
        for (String size : sizes) {
            index++;
            for (String color : colors) {
                //6、封装成一个牌对象
                Card c = new Card(size, color,index);
                //7、存入到集合容器中去
                allCards.add(c);
            }
        }
        //8、大小王存入到集合对象中去
        Card c1 = new Card("","♔",++index);
        Card c2 = new Card("","♕",++index);
        Collections.addAll(allCards,c1,c2);
        System.out.println("新牌"+ allCards);
    }


    public static void main(String[] args) {
        //9、洗牌
        Collections.shuffle(allCards);
        System.out.println("洗牌后："+ allCards);

        //10、定义三个玩家，每个玩家的牌也是一个集合容器
        List<Card> linhuchong = new ArrayList<>();
        List<Card> jiumozhi = new ArrayList<>();
        List<Card> renyingying = new ArrayList<>();

        //11、开始发牌（从集合中发出51张牌给三个玩家，剩余三张牌作为底牌
        for (int i = 0; i < allCards.size()-3; i++) {
            //先拿到当前牌对象
            Card c = allCards.get(i);
            if (i % 3 == 0){
                //请阿冲接牌
                linhuchong.add(c);
            }else if (i % 3 == 1){
                jiumozhi.add(c);
            } else if (i % 3 == 2) {
                renyingying.add(c);
            }
        }

        //12、拿到最后三张底牌(把最后三张牌截取成一个子集合）
        List<Card> lastThreeCards = allCards.subList(allCards.size() - 3,allCards.size());

        //13、给玩家的牌排序（从大到小）
        sortCards(linhuchong);
        sortCards(jiumozhi);
        sortCards(renyingying);
        //14、输出玩家的牌
        System.out.println("阿冲：" + linhuchong);
        System.out.println("阿鸠：" + jiumozhi);
        System.out.println("盈盈：" + renyingying);
        System.out.println("底牌：" + lastThreeCards);

    }

    /**
     * 给牌排序
     * @param cards
     */
    private static void sortCards(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                //知道牌的大小，才可以制定规则
                return o2.getIndex()- o1.getIndex();
            }
        });
    }


}
