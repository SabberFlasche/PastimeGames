package me.plasmabase.pastimegames.manager.Games;

import me.plasmabase.pastimegames.PastimeGames;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Items {

    private static ItemStack _void;
    private static ItemStack turnTrue;
    private static ItemStack turnFalse;
    private static ItemStack chipTrue;
    private static ItemStack chipFalse;

    public static void initItems() {
        //init placeholder-item
        _void = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta _void_meta = _void.getItemMeta();
        _void_meta.setDisplayName(PastimeGames.settingsManager().placeholderItemDisplayName());
        //ArrayList<String> _void_lore = new ArrayList<>();
        //_void_meta.setLore(_void_lore);
        _void.setItemMeta(_void_meta);

        //init turn-items
        turnTrue = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta turnTrue_meta = turnTrue.getItemMeta();
        turnTrue_meta.setDisplayName(PastimeGames.settingsManager().yourTurnItemDisplayName());
        turnTrue.setItemMeta(turnTrue_meta);

        turnFalse = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta turnFalse_meta = turnFalse.getItemMeta();
        turnFalse_meta.setDisplayName(PastimeGames.settingsManager().opponentsTurnItemDisplayName());
        turnFalse.setItemMeta(turnFalse_meta);

        //init chip-items
        chipTrue = new ItemStack(Material.ENDER_PEARL);
        ItemMeta chipTrue_meta = chipTrue.getItemMeta();
        chipTrue_meta.setDisplayName(PastimeGames.settingsManager().yourChipItemDisplayName());
        chipTrue.setItemMeta(chipTrue_meta);

        chipFalse = new ItemStack(Material.SNOWBALL);
        ItemMeta chipFalse_meta = chipFalse.getItemMeta();
        chipFalse_meta.setDisplayName(PastimeGames.settingsManager().opponentsChipItemDisplayName());
        chipFalse.setItemMeta(chipFalse_meta);
    }

    protected static ItemStack _void() {
        return _void;
    }

    protected static ItemStack turnTrue() {
        return turnTrue;
    }

    protected static ItemStack turnFalse() {
        return turnFalse;
    }

    protected static ItemStack chipTrue() {
        return chipTrue;
    }

    protected static ItemStack chipFalse() {
        return chipFalse;
    }

    protected static ItemStack playerHead(Player player) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHead_meta = (SkullMeta) playerHead.getItemMeta();
        playerHead_meta.setDisplayName(player.getName());
        playerHead_meta.setOwningPlayer(player);
        playerHead.setItemMeta(playerHead_meta);
        return playerHead;
    }
}
