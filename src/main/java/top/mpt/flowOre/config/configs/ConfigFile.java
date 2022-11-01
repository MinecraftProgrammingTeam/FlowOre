package top.mpt.flowOre.config.configs;

import com.google.common.reflect.TypeToken;
import net.minecraft.block.Blocks;
import org.apache.commons.io.FileUtils;
import top.mpt.flowOre.FlowOre;
import top.mpt.flowOre.config.AbstractConfig;
import top.mpt.flowOre.entity.ConfigEntity;
import top.mpt.flowOre.entity.PrizeEntity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 * @author : YouM
 */
public class ConfigFile extends AbstractConfig {
    //存储 PrizeEntity 的 List
    private final List<ConfigEntity> configEntities;
    //存储 ConfigEntity 的 List
    private final List<PrizeEntity> prizeEntities;
    public ConfigFile() {
        super("config.json");
        configEntities = new ArrayList<>();
        prizeEntities = new ArrayList<>();
        init();
    }
    @Override
    public void init() {
        configEntities.add(new ConfigEntity("钻石矿",0.0));
        configEntities.add(new ConfigEntity("煤矿",0.0));
        configEntities.add(new ConfigEntity("金矿",0.0));
        configEntities.add(new ConfigEntity("铁矿",0.0));
        configEntities.add(new ConfigEntity("青金石矿",0.0));
        configEntities.add(new ConfigEntity("红石矿",0.0));
        configEntities.add(new ConfigEntity("绿宝石矿",0.0));
        configEntities.add(new ConfigEntity("原石",0.0));
        configEntities.add(new ConfigEntity("铜矿",0.0));
        super.init();
    }

    @Override
    public void readConfig() throws IOException {
        String configContent = FileUtils.readFileToString(this.file,"UTF-8");
        FlowOre.log.info(configContent);
        Type type = new TypeToken<List<ConfigEntity>>(){}.getType();//这里要获取泛型的内容，要不然Gson无法识别
        List<ConfigEntity> config = gson.fromJson(configContent, type);
        for(ConfigEntity entity : config){
            if(entity.getPoll() <= 0.0){//如果配置文件中有小于0.0的值，就只填加一个PrizeEntity并返回
                return;
            }
        }
        prizeEntities.add(new PrizeEntity(Blocks.DIAMOND_ORE,config.get(0).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.COAL_ORE,config.get(1).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.GOLD_ORE,config.get(2).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.IRON_ORE,config.get(3).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.LAPIS_ORE,config.get(4).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.REDSTONE_ORE,config.get(5).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.EMERALD_ORE,config.get(6).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.STONE,config.get(7).getPoll()));
        prizeEntities.add(new PrizeEntity(Blocks.COPPER_ORE ,config.get(8).getPoll()));
    }

    @Override
    public void writeConfig() throws IOException {
        FileUtils.writeStringToFile(this.file,gson.toJson(configEntities),"UTF-8");
    }
    public List<PrizeEntity> getPrizeEntities() {
        return prizeEntities;
    }
}
