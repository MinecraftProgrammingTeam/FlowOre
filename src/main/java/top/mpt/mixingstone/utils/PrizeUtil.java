package top.mpt.mixingstone.utils;

import net.minecraft.block.Block;
import java.util.*;

//@SuppressWarnings("all")
public class PrizeUtil {
    private int total = 0;
    private int pos = 0;
    private List<Double> speed = new ArrayList<>(); //随机种子
    private Map<Double, Block> box = new HashMap<>();
    public void init(List<PrizeEntity> prizes) {
        //权重和
        for (PrizeEntity p : prizes){
            total += p.getPoll();
        }
        //添加total个数
        for (double i = 0.0; i < total; i++) speed.add(i);
    }
    public Block getBlockByPrize(List<PrizeEntity> prizes){
        //box盒子
        for (PrizeEntity prizeList : prizes)
        {
            for (int c = 0; c < prizeList.getPoll() ;c++) //权重越大所占的面积份数就越多
            {
                pos = PrizeEntity.Rand.nextInt(speed.size()); //取随机种子坐标
                Block s = box.get(speed.get(c));//乱序 礼品放入索引是speed[pos]的箱子里面
                for (Double key : box.keySet()){
                    box.put(key,s);
                    speed.remove(pos); //移除已抽取的箱子索引号
                }

            }
        }
        return box.get(PrizeEntity.Rand.nextDouble(total));
    }

    public PrizeUtil(List<PrizeEntity> prizes) {
        this.init(prizes);
    }
}
