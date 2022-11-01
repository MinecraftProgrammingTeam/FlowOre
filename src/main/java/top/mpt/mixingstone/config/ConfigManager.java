package top.mpt.mixingstone.config;

import top.mpt.mixingstone.config.configs.ConfigFile;
import top.mpt.mixingstone.config.configs.ReadMeFile;

import java.util.ArrayList;
import java.util.List;

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
