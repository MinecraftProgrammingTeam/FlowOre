package top.mpt.flowOre.utils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import top.mpt.flowOre.FlowOre;
import top.mpt.flowOre.entity.PrizeEntity;

import java.util.*;

/**
 * @author : YouM
 */
public class PrizeUtil {

    private List<Integer> speed = new ArrayList<>(); //随机种子
    private Map<Integer, Block> box = new HashMap<>(); // 存储Block的盒子，Integer用来通过随机数随机获取Block
    private int total = 0;//权重总共的数量
    private int pos; //获取随机数后的存储在这里
    private final List<PrizeEntity> list;
    public PrizeUtil(List<PrizeEntity> list){
        this.list = list;
    }
    /**
     * 权重随机数算法 ,时间复杂度O(n^3) ，十分弱智的算法，会优化的，但是对矿石的产生只有平均300ms的延迟
     * list 从config获取的List对象内部存储Block对象和权重
     * @return 返回 Block 对象 可能是矿石中的任意一个
     */
    public Block getBlockByPrize(){
        //最后返回
        return box.get(PrizeEntity.Rand.nextInt(total));
    }
    public void init(){
        //获取所有权重并加到total中
        for (PrizeEntity p : list){
            total += p.getPoll();
        }
        FlowOre.log.info(String.valueOf(total));
        //添加total个数 用来对总共的数进行数量分布
        for (int i = 0; i < total; i++) speed.add(i);

        for (PrizeEntity prizeList : list)
        {
            for (int c = 0; c < prizeList.getPoll(); c++) //权重越大所占的面积份数就越多
            {
                pos = PrizeEntity.Rand.nextInt(speed.size());
                //这块要判断随机数获取的索引有是否为null，没有再加，有了就不能加了
                while (true){
                    if(box.get(speed.get(pos)) == null){
                        box.computeIfAbsent(speed.get(pos), k -> prizeList.getKey());
                        break;
                    }else {
                        pos = PrizeEntity.Rand.nextInt(speed.size());
                    }
                }
                speed.remove(pos); //移除已抽取的箱子索引号
            }
        }
    }
}
