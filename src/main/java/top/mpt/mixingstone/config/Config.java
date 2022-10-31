package top.mpt.mixingstone.config;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import top.mpt.mixingstone.MixingStone;
import top.mpt.mixingstone.utils.ConfigEntity;
import top.mpt.mixingstone.utils.PrizeEntity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private final List<PrizeEntity> prizeEntities;
    private final List<ConfigEntity> configEntities;
    private final Gson gson;
    File resourcePackDir = MinecraftClient.getInstance().runDirectory;
    File configDir = new File(resourcePackDir + "/MixingStone");
    File configFile = new File(configDir + "/config.json");
    File readmeFile = new File(configDir + "/readme.txt");
    public void readConfig() throws IOException{
        String configContent = FileUtils.readFileToString(configFile,"UTF-8");
        MixingStone.log.info(configContent);
        Type type = new TypeToken<List<ConfigEntity>>(){}.getType();
        List<ConfigEntity> config = gson.fromJson(configContent, type);
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
    @SuppressWarnings("all")
    public void writeConfig() throws IOException{
        boolean config = configFile.createNewFile();
        boolean readme = readmeFile.createNewFile();
        FileUtils.writeStringToFile(readmeFile,
                """
                使用说明：
                本mod使用权重比算法进行随机数生成，所以无论你在矿石后面填写多大的数。
                只要那个数最大，生成的概率也越高
                如果其他都是0，只有一个为其他数，那么将会只生成不是0的矿石,
                如果都为0,那么将只会生成原石。
                请勿填写负数(就算你填了，只要有一个是负数，我也只会生成原石)
                                                            ----mpt管理组
                ""","UTF-8");

        FileUtils.writeStringToFile(configFile,gson.toJson(configEntities),"UTF-8");
    }
    @SuppressWarnings("all")
    public void checkDir(){
        try {
            if (!configDir.exists()) {
                boolean mkdir = configDir.mkdir();
            }
            this.checkFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkFile() throws IOException{
        if(!configFile.exists() && !readmeFile.exists()){
            this.writeConfig();
            this.readConfig();
        }else {
            String content = FileUtils.readFileToString(configFile, "UTF-8");
            if(StringUtils.isBlank(content) || content.contains("null")) {
                this.writeConfig();
            }
            this.readConfig();
        }
    }
    public void init(){
        configEntities.add(new ConfigEntity("钻石矿",0.0));
        configEntities.add(new ConfigEntity("煤矿",0.0));
        configEntities.add(new ConfigEntity("金矿",0.0));
        configEntities.add(new ConfigEntity("铁矿",0.0));
        configEntities.add(new ConfigEntity("青金石矿",0.0));
        configEntities.add(new ConfigEntity("红石矿",0.0));
        configEntities.add(new ConfigEntity("绿宝石矿",0.0));
        configEntities.add(new ConfigEntity("原石",0.0));
        configEntities.add(new ConfigEntity("铜矿",0.0));
    }
    public Config() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.configEntities = new ArrayList<>();
        this.prizeEntities = new ArrayList<>();
        this.init();
        this.checkDir();

    }


    public List<PrizeEntity> getPrizeEntities() {
        return prizeEntities;
    }

}
