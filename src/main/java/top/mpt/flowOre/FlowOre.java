package top.mpt.flowOre;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.mpt.flowOre.config.AbstractConfig;
import top.mpt.flowOre.config.ConfigManager;
import top.mpt.flowOre.config.configs.ConfigFile;
import top.mpt.flowOre.utils.PrizeUtil;

/**
 * <h1>FlowOre</h1>
 * <h2>开发团队:MPT</h2>
 * @author YouM
 */
public class FlowOre implements ModInitializer {
    public static final String name = "FlowOre";
    public static final String version = "0.1";
    public static final String author = "YouM";
    public static ConfigManager config;
    public static Logger log = LoggerFactory.getLogger(FlowOre.class);
    public static PrizeUtil prizeUtil;
    @Override
    public void onInitialize() {
        config = new ConfigManager();
        config.load();
        AbstractConfig config = FlowOre.config.getFileByClass(ConfigFile.class);
        if(config instanceof ConfigFile c){
            prizeUtil = new PrizeUtil(c.getPrizeEntities());
            prizeUtil.init();
        }
        log.info("{} loading... version: {} author: {} ",name,version,author);
    }
}
