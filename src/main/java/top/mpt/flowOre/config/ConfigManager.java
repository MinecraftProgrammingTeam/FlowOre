package top.mpt.flowOre.config;

import top.mpt.flowOre.config.configs.ConfigFile;
import top.mpt.flowOre.config.configs.ReadMeFile;

import java.util.ArrayList;
import java.util.List;
/**
 * @author YouM
 */
public class ConfigManager {
    private List<AbstractConfig> configs;
    public void load(){
        configs = new ArrayList<>();
        configs.add(new ConfigFile());
        configs.add(new ReadMeFile());
    }
    public AbstractConfig getFileByClass(Class<? extends AbstractConfig> clazz){
        for (AbstractConfig config : configs) {
            if (config.getClass() != clazz) continue;
            return config;
        }
        return null;
    }
}
