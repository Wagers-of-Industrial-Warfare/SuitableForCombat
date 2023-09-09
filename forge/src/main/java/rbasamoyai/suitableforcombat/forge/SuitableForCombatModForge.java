package rbasamoyai.suitableforcombat.forge;

import rbasamoyai.suitableforcombat.SuitableForCombatMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rbasamoyai.suitableforcombat.index.forge.SFCItemsImpl;

@Mod(SuitableForCombatMod.MOD_ID)
public class SuitableForCombatModForge {

    public SuitableForCombatModForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        SFCItemsImpl.ITEMS.register(modBus);

        SuitableForCombatMod.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SFCModClientForge.clientInit(modBus, forgeBus));
    }

}
