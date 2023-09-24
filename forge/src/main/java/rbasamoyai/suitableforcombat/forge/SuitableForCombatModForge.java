package rbasamoyai.suitableforcombat.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import rbasamoyai.suitableforcombat.index.forge.SFCBlocksImpl;
import rbasamoyai.suitableforcombat.index.forge.SFCItemsImpl;
import rbasamoyai.suitableforcombat.index.forge.SFCMenuTypesImpl;

@Mod(SuitableForCombatMod.MOD_ID)
public class SuitableForCombatModForge {

    public SuitableForCombatModForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		SFCBlocksImpl.BLOCKS.register(modBus);
        SFCItemsImpl.ITEMS.register(modBus);
		SFCMenuTypesImpl.MENU_TYPES.register(modBus);

        SuitableForCombatMod.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SFCModClientForge.clientInit(modBus, forgeBus));
    }

}
