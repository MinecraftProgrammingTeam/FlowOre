package top.mpt.mixingstone.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.mpt.mixingstone.MixingStone;
import top.mpt.mixingstone.utils.PrizeUtil;

@Mixin(LavaFluid.class)
public class MinecraftLavaFluid {


    @Inject(
            method = "flow",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/fluid/FlowableFluid;flow(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;Lnet/minecraft/fluid/FluidState;)V"
            )
    )
    private void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci){
        world.setBlockState(pos, MixingStone.prizeUtil.getBlockByPrize(MixingStone.config.getPrizeEntities()).getDefaultState(), Block.NOTIFY_ALL);
    }
}
