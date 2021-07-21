package io.github.lucunji.darksword22.event;

import io.github.lucunji.darksword22.Main;
import io.github.lucunji.darksword22.entity.SwordEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RightClickEventListener {
    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickBlock interactEvent) {
        PlayerEntity playerEntity = interactEvent.getPlayer();
        BlockPos pos = interactEvent.getPos();
        if (!playerEntity.world.isRemote
                && BlockTags.CAMPFIRES.contains(playerEntity.world.getBlockState(pos).getBlock())
                && interactEvent.getItemStack().isItemEqual(Items.NETHERITE_SWORD.getDefaultInstance())) {
            interactEvent.getItemStack().shrink(1);
            playerEntity.world.addEntity(new SwordEntity(playerEntity.world, pos));
            interactEvent.setUseBlock(Event.Result.DENY);
            interactEvent.setUseItem(Event.Result.DENY);
        }
    }
}
