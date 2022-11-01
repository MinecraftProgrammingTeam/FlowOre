package top.mpt.flowOre.config.configs;

import org.apache.commons.io.FileUtils;
import top.mpt.flowOre.config.AbstractConfig;

import java.io.IOException;

/**
 * @author : YouM
 */
public class ReadMeFile extends AbstractConfig {

    public ReadMeFile() {
        super("readme.txt");
        this.init();
    }

    @Override
    public void readConfig() throws IOException {

    }

    @Override
    public void writeConfig() throws IOException {
        String content =
            """
                             FlowOre使用说明：
            本mod使用权重比算法进行随机数生成，所以无论你在矿石后面填写多大的数。
            只要那个数最大，生成的概率也越高
            如果其他都是0，只有一个为其他数，那么将会只生成不是0的矿石,
            如果都为0,那么将只会生成原石。
            请勿填写负数(就算你填了，只要有一个是负数，我也只会生成原石)
                                                ----mpt管理组
            """;
        FileUtils.writeStringToFile(this.file,content,"UTF-8");
    }

    @Override
    public void init() {
        super.init();
    }
}
