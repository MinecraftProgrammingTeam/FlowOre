package top.mpt.flowOre.config.configs;

import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.util.registry.Registry;
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
        for(Block b : Registry.BLOCK){
            if(b instanceof OreBlock oreBlock){
                configEntities.add(new ConfigEntity(Registry.BLOCK.getId(b).toString(),0.0));
            }
        }
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.DIAMOND_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.COAL_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.GOLD_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.IRON_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.LAPIS_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.REDSTONE_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.EMERALD_ORE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.COBBLESTONE).toString(),0.0));
//        configEntities.add(new ConfigEntity(Registry.BLOCK.getId(Blocks.COPPER_ORE).toString(),0.0));
        super.init();
    }

    @Override
    public void readConfig() throws IOException {
        String configContent = FileUtils.readFileToString(this.file,"UTF-8");
        FlowOre.log.info(configContent);
        Type type = new TypeToken<List<ConfigEntity>>(){}.getType();//这里要获取泛型的内容，要不然Gson无法识别
        List<ConfigEntity> config = gson.fromJson(configContent, type);
        for(ConfigEntity entity : config){
            if(entity.getPoll() < 0.0){//如果配置文件中有小于0.0的值，就直接返回,不存任何东西
                return;
            }
        }
        for (ConfigEntity configEntity : config){
            prizeEntities.add(new PrizeEntity(configEntity.getKey(),configEntity.getPoll()));
        }

    }

    @Override
    public void writeConfig() throws IOException {
        FileUtils.writeStringToFile(this.file,gson.toJson(configEntities),"UTF-8");
    }
    public List<PrizeEntity> getPrizeEntities() {
        return prizeEntities;
    }
}
