package io.github.lucunji.darksword22.entity;

import io.github.lucunji.darksword22.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SwordEntity extends Entity {
    public SwordEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public SwordEntity(World world, BlockPos blockPos) {
        this(Main.SWORD_ENTITY.get(), world);
        this.setPosition(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5);
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
        if (!player.world.isRemote) {
            if (player.getHeldItem(hand).isEmpty()) {
                ItemStack swordStack = Items.NETHERITE_SWORD.getDefaultInstance();
                player.setHeldItem(hand, swordStack);
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType applyPlayerInteraction(PlayerEntity player, Vector3d vec, Hand hand) {
        return super.applyPlayerInteraction(player, vec, hand);
    }

    @Override
    public float getCollisionBorderSize() {
        return super.getCollisionBorderSize();
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return super.getSize(poseIn);
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return super.getBoundingBox();
    }

    @Override
    protected AxisAlignedBB getBoundingBox(Pose pose) {
        return super.getBoundingBox(pose);
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
