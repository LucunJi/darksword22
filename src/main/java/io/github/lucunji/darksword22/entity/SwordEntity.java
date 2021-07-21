package io.github.lucunji.darksword22.entity;

import io.github.lucunji.darksword22.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SwordEntity extends Entity {
    private static final DataParameter<Integer> AGE = EntityDataManager.createKey(SwordEntity.class, DataSerializers.VARINT);

    public SwordEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public SwordEntity(World world, BlockPos blockPos) {
        this(Main.SWORD_ENTITY.get(), world);
        this.setPosition(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
        if (!player.world.isRemote && this.isAlive()) {
            if (player.getHeldItem(hand).isEmpty()) {
                ItemStack swordStack = Items.NETHERITE_SWORD.getDefaultInstance();
                player.setHeldItem(hand, swordStack);
                this.remove();
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void tick() {
        super.tick();
        this.dataManager.set(AGE, this.dataManager.get(AGE) + 1);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(AGE, 0);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        this.dataManager.set(AGE, compound.getInt("Age"));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putInt("Age", this.dataManager.get(AGE));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
