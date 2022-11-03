package top.mpt.flowOre.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.mpt.flowOre.FlowOre;
import top.mpt.flowOre.utils.PrizeUtil;

/**
 * @author YouM
 */
@Mixin(FluidBlock.class)
public abstract class MixinFluidBlock extends Block {

    public MixinFluidBlock(Settings settings) {
        super(settings);
    }

    /**
     *  通过 {@link Redirect} 注入到 <br/>
     *  {@link FluidBlock#receiveNeighborFluids(World,BlockPos,BlockState)}方法中的 <br/>
     *  "   world.setBlockState(pos, block.getDefaultState());   "<br/>
     *  将其更改为{@link PrizeUtil#getBlockByPrize(boolean)}的返回值<br/>
     *  <br/>
     * 这里有一个弱智的问题, 原来使用{@link Inject}注解<br/>
     * 之后在注入的方法内调用{@link FluidState#isStill()}的返回值会一直为空<br/>
     * 使用注解 {@link Redirect} 才能解决<br/>
     * 原因可能是 调用的顺序导致的,具体的不太清楚<br/>
     */
    @Redirect(
            method = "receiveNeighborFluids",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z",
                    ordinal = 0
            )
    )
    private boolean receiveNeighborFluids(World world, BlockPos pos, BlockState state){
        return world.setBlockState(pos, world.getFluidState(pos).isStill() ? Blocks.OBSIDIAN.getDefaultState() : FlowOre.prizeUtil.getBlockByPrize(false).getDefaultState());
    }
}
