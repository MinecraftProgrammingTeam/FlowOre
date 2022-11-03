package top.mpt.flowOre.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import top.mpt.flowOre.FlowOre;

import java.io.File;
import java.io.IOException;
/**
 * @author YouM
 *
 * 准确的说并不能算是AbstractConfig
 * 而是AbstractFile
 * 因为同时也借助此类创建了解释的文件
 * 原因: json的规范尽量不要写注释,我也可以不按照他的规范走
 *      但是既然用了别人的东西，还是要遵循以下别人的规则
 */
public abstract class AbstractConfig {
    protected String filename;
    protected File file;
    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    //获取 .minecraft 路径
    protected File runDir= MinecraftClient.getInstance().runDirectory;
    //在 runDir 目录下 创建子目录
    protected File configDir = new File(runDir + "/" + FlowOre.name);

    public AbstractConfig(String filename) {
        this.filename = filename;
    }
    /**
     * 检查configDir是否被创建
     */
    @SuppressWarnings("all")
    private void checkDir(){
        try {
            if (!configDir.exists()) {
                boolean mkdir = configDir.mkdir();
            }
            this.checkFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 检查文件是否被创建
     */
    private void checkFile() throws IOException{
        this.file = new File(configDir + "/" + filename);
        if(!file.exists()){ //file是否被创建
            boolean config = file.createNewFile();
            writeConfig();
            readConfig();
        }else {
            String content = FileUtils.readFileToString(file, "UTF-8");
            if(StringUtils.isBlank(content) || content.contains("null")) {
                writeConfig();
            }
            readConfig();
        }
    }
    /**
     * 初始化方法
     */
    public void init(){
        checkDir();
    }

    /**
     * 读取配置文件
     */
    public abstract void readConfig() throws IOException;

    /**
     *写入配置文件
     */
    public abstract void writeConfig() throws IOException;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}

