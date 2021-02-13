package mod.wittywhiscash.damageoverhaul.common.item;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

public class GuidebookItem extends Item {

    public GuidebookItem() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverUser = (ServerPlayerEntity) user;
            PatchouliAPI.get().openBookGUI(serverUser, new Identifier(DamageOverhaul.MOD_ID, "guidebook"));
        }
        return super.use(world, user, hand);
    }
}
