package top.mpt.mixingstone;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.mpt.mixingstone.config.ConfigManager;
import top.mpt.mixingstone.utils.PrizeUtil;

public class MixingStone implements ModInitializer {
    public static ConfigManager config;
    public static Logger log = LoggerFactory.getLogger(MixingStone.class);
    public static PrizeUtil prizeUtil;
    @Override
    public void onInitialize() {
        config = new ConfigManager();
        config.load();
        prizeUtil = new PrizeUtil();
        log.info("MixingStone loading...");
    }
}
