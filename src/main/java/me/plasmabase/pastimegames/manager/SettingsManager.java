package me.plasmabase.pastimegames.manager;

import me.plasmabase.pastimegames.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class SettingsManager {

    private final FileConfiguration config;
    private final String version;

    private final String path_reloadMessage = "reload-message";
    private final String path_commands_noPermission = "commands.noPermission";
    private final String path_commands_wrongSyntax = "commands.wrongSyntax";
    private final String path_connect4_invTitle = "connect4.invTitle";
    private final String path_connect4_items_placeholder_displayName = "connect4.items.placeholder.displayName";
    private final String path_connect4_items_yourTurn_displayName = "connect4.items.yourTurn.displayName";
    private final String path_connect4_items_opponentsTurn_displayName = "connect4.items.opponentsTurn.displayName";
    private final String path_connect4_items_yourChipItem_displayName = "connect4.items.yourChipItem.displayName";
    private final String path_connect4_items_opponentsChipItem_displayName =
            "connect4.items.opponentsChipItem.displayName";

    private String reloadMessage;
    private String commands_noPermission;
    private String commands_wrongSyntax;
    private String connect4_invTitle;
    private String connect4_items_placeholder_displayName;
    private String connect4_items_yourTurn_displayName;
    private String connect4_items_opponentsTurn_displayName;
    private String connect4_items_yourChipItem_displayName;
    private String connect4_items_opponentsChipItem_displayName;

    public SettingsManager() {
        this.config = Main.plugin().getConfig();
        this.version = Main.plugin().getDescription().getVersion();
        this.loadConfig();
        this.checkVersion();
    }

    private void loadConfig() {
        this.reloadMessage = this.config.getString(path_reloadMessage);
        this.commands_noPermission = this.config.getString(path_commands_noPermission);
        this.commands_wrongSyntax = this.config.getString(path_commands_wrongSyntax);
        this.connect4_invTitle = this.config.getString(path_connect4_invTitle);
        this.connect4_items_placeholder_displayName = this.config.getString(path_connect4_items_placeholder_displayName);
        this.connect4_items_yourTurn_displayName = this.config.getString(path_connect4_items_yourTurn_displayName);
        this.connect4_items_opponentsTurn_displayName = this.config.getString(path_connect4_items_opponentsTurn_displayName);
        this.connect4_items_yourChipItem_displayName = this.config.getString(path_connect4_items_yourChipItem_displayName);
        this.connect4_items_opponentsChipItem_displayName = this.config.getString(path_connect4_items_opponentsChipItem_displayName);
    }

    /**
     * Reloads the config
     */
    public void reload() {
        Main.plugin().reloadConfig();
        this.loadConfig();
        this.checkVersion();
        Main.plugin().getLogger().info("config.yml reloaded");
    }

    private void checkVersion() {
        try {
            if (!(config.getString("version").equalsIgnoreCase(version))) {
                fixConfig();
            }
        }catch (NullPointerException e) {
            fixConfig();
        }
    }

    private void fixConfig() {
        config.set("version", version);
        config.set("reload-message", reloadMessage);
        config.set("commands.noPermission", commands_noPermission);
        config.set("commands.wrongSyntax", commands_wrongSyntax);
        config.set("connect4.invTitle", connect4_invTitle);
        config.set("connect4.items.placeholder.displayName", connect4_items_placeholder_displayName);
        config.set("connect4.items.yourTurn.displayName", connect4_items_yourTurn_displayName);
        config.set("connect4.items.opponentsTurn.displayName", connect4_items_opponentsTurn_displayName);
        config.set("connect4.items.yourChipItem.displayName", connect4_items_yourChipItem_displayName);
        config.set("connect4.items.opponentsChipItem.displayName", connect4_items_opponentsChipItem_displayName);
        this.save();
    }

    /**
     * Saves the config
     */
    public void save() {
        Main.plugin().saveConfig();
    }

    /**
     * @return commands_noPermission
     */
    public @NotNull String noPermissionMessage() {
        return ChatColor.translateAlternateColorCodes('&', commands_noPermission);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param commands_noPermission parameter to set
     */
    public void noPermissionMessage(@NotNull String commands_noPermission) {
        this.commands_noPermission = commands_noPermission;
        this.config.set(path_commands_noPermission, commands_noPermission);
        this.save();
    }

    /**
     * @return commands_wrongSyntax
     */
    public @NotNull String wrongSyntaxMessage() {
        return ChatColor.translateAlternateColorCodes('&', commands_wrongSyntax);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param commands_wrongSyntax parameter to set
     */
    public void wrongSyntaxMessage(@NotNull String commands_wrongSyntax) {
        this.commands_wrongSyntax = commands_wrongSyntax;
        this.config.set(path_commands_wrongSyntax, commands_wrongSyntax);
        this.save();
    }

    /**
     * @return reloadMessage
     */
    public @NotNull String reloadMessage() {
        return ChatColor.translateAlternateColorCodes('&', reloadMessage);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param reloadMessage parameter to set
     */
    public void reloadMessage(@NotNull String reloadMessage) {
        this.reloadMessage = reloadMessage;
        this.config.set(path_reloadMessage, reloadMessage);
        this.save();
    }

    /**
     * @return connect4_invTitle
     */
    public @NotNull String connect4InventoryTitle() {;
        return ChatColor.translateAlternateColorCodes('&', connect4_invTitle);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param connect4_invTitle parameter to set
     */
    public void connect4InventoryTitle(@NotNull String connect4_invTitle) {
        this.connect4_invTitle = connect4_invTitle;
        this.config.set(path_connect4_invTitle, connect4_invTitle);
        this.save();
    }

    /**
     * @return connect4_items_placeholder_displayName
     */
    public @NotNull String connect4PlaceholderItemDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', connect4_items_placeholder_displayName);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param connect4_items_placeholder_displayName parameter to set
     */
    public void connect4PlaceholderItemDisplayName(@NotNull String connect4_items_placeholder_displayName) {
        this.connect4_items_placeholder_displayName = connect4_items_placeholder_displayName;
        this.config.set(path_connect4_items_placeholder_displayName, connect4_items_placeholder_displayName);
        this.save();
    }

    /**
     * @return connect4_items_yourTurn_displayName
     */
    public @NotNull String connect4YourTurnItemDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', connect4_items_yourTurn_displayName);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param connect4_items_yourTurn_displayName parameter to set
     */
    public void connect4YourTurnItemDisplayName(@NotNull String connect4_items_yourTurn_displayName) {
        this.connect4_items_yourTurn_displayName = connect4_items_yourTurn_displayName;
        this.config.set(path_connect4_items_yourTurn_displayName , connect4_items_yourTurn_displayName);
        this.save();
    }

    /**
     * @return connect4_items_opponentsTurn_displayName
     */
    public @NotNull String connect4OpponentsTurnItemDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', connect4_items_opponentsTurn_displayName);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param connect4_items_opponentsTurn_displayName parameter to set
     */
    public void connect4OpponentsTurnItemDisplayName(@NotNull String connect4_items_opponentsTurn_displayName) {
        this.connect4_items_opponentsTurn_displayName = connect4_items_opponentsTurn_displayName;
        this.config.set(path_connect4_items_opponentsTurn_displayName , connect4_items_opponentsTurn_displayName);
        this.save();
    }

    /**
     * @return connect4_items_yourChipItem_displayName
     */
    public @NotNull String connect4YourChipItemDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', connect4_items_yourChipItem_displayName);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param connect4_items_yourChipItem_displayName parameter to set
     */
    public void connect4YourChipItemDisplayName(@NotNull String connect4_items_yourChipItem_displayName) {
        this.connect4_items_yourChipItem_displayName = connect4_items_yourChipItem_displayName;
        this.config.set(path_connect4_items_yourChipItem_displayName , connect4_items_yourChipItem_displayName);
        this.save();
    }

    /**
     * @return connect4_items_opponentsChipItem_displayName
     */
    public @NotNull String connect4OpponentsChipItemDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', connect4_items_opponentsChipItem_displayName);
    }

    /**
     * Sets parameter and then saves it to the config.
     * ColorChat: '&'
     * @param connect4_items_opponentsChipItem_displayName parameter to set
     */
    public void connect4OpponentsChipItemDisplayName(@NotNull String connect4_items_opponentsChipItem_displayName) {
        this.connect4_items_opponentsChipItem_displayName = connect4_items_opponentsChipItem_displayName;
        this.config.set(path_connect4_items_opponentsChipItem_displayName , connect4_items_opponentsChipItem_displayName);
        this.save();
    }
}
