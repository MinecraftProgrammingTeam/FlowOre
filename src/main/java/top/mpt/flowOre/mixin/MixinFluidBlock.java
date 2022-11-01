package top.mpt.flowOre.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.mpt.flowOre.FlowOre;
import top.mpt.flowOre.config.AbstractConfig;
import top.mpt.flowOre.config.configs.ConfigFile;
import top.mpt.flowOre.utils.PrizeUtil;

import java.util.List;
/**
 * @author : YouM
 */
@Mixin(FluidBlock.class)
public class MixinFluidBlock {

    /**
     *  通过 {@link Inject} 注入到 <br/>
     *  {@link FluidBlock#receiveNeighborFluids(World,BlockPos,BlockState)}方法中的 <br/>
     *  "world.setBlockState(pos, block.getDefaultState());"<br/>
     *  将其更改为{@link PrizeUtil#getBlockByPrize()}的返回值<br/>
     */
    @Inject(
            method = "receiveNeighborFluids",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"
            )
    )
    private void receiveNeighborFluids(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir){
        world.setBlockState(pos, world.getFluidState(pos).isStill() ? Blocks.OBSIDIAN.getDefaultState() : FlowOre.prizeUtil.getBlockByPrize().getDefaultState());
    }
}
