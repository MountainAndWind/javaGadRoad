package dataStructure.Stack;

/**
 * @description:爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W]
 * 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 *
 * @author: slfang
 * @time: 2020/6/3 10:58
 */
public class Als21Game {

   /* public double new21Game(int N, int K, int W) {
        int singProbability = 1/W;//单个概率
        int count=0;
        if(N<=K){
            return 0;
        }else if(N>K){
            while(count<K){
                //HashMap<String,Object> hashMap =

            }
        }
        return null;
    }*/

    public static void main(String[] args) {
        Als21Game game = new Als21Game();
        //game.new21Game(6,1,10);
    }
}
