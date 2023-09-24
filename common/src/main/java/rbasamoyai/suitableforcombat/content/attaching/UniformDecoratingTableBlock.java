package rbasamoyai.suitableforcombat.content.attaching;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import rbasamoyai.suitableforcombat.index.SFCMenuTypes;

public class UniformDecoratingTableBlock extends Block {

	public UniformDecoratingTableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide && player instanceof ServerPlayer splayer) {
			SFCMenuTypes.openMenu(splayer, this.getName(), (i, inv, p) -> new UniformDecoratingMenu(i, inv, ContainerLevelAccess.create(level, pos)), buf -> {});
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

}
