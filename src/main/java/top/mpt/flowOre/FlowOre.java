package top.mpt.flowOre;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.mpt.flowOre.config.ConfigManager;
import top.mpt.flowOre.utils.PrizeUtil;

/**
 * @author : YouM
 */
public class FlowOre implements ModInitializer {
    public static final String name = "FlowOre";
    public static final String version = "0.1";
    public static final String author = "youm";
    public static ConfigManager config;
    public static Logger log = LoggerFactory.getLogger(FlowOre.class);
    public static PrizeUtil prizeUtil;
    @Override
    public void onInitialize() {
        config = new ConfigManager();
        config.load();
        prizeUtil = new PrizeUtil();
        log.info("{} loading... version: {} author: {} ",name,version,author);
    }
}
