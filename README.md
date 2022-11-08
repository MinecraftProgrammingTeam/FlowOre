
# FlowOre 
## 一个关于岩浆和水生成随机矿石的我的世界模组 
## 模组想法提供者: 咸鱼插歪 
### 作者:MPT管理组
![github license](https://img.shields.io/badge/license-MIT-blue.svg)
![github star](https://badgen.net/github/stars/YOM667/FlowOre)

### 0.1 version
1. [X] 矿石生成
2. [X] 配置文件的读写
3. [X] 权重随机数算法

##### 问题: 
1. 权重比随机算法的时间复杂度为 O(n^3) 生成不同矿石速度不一样 (已解决)
2. 加载读写配置文件的代码和主类代码有点shit
3. 如果岩浆从水的上方下落与水混合，将会生成石头而不是随机矿物，会改的！(已解决)
