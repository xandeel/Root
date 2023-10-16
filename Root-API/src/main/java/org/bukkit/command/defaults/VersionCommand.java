package org.bukkit.command.defaults;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.google.common.collect.ImmutableList;

public class VersionCommand extends BukkitCommand {
	public VersionCommand(String name) {
		super(name);

		this.description = "Gets the version of this server including any plugins in use";
		this.usageMessage = "/version [plugin name]";
		this.setPermission("bukkit.command.version");
		this.setAliases(Arrays.asList("ver", "about"));
	}

	@Override
	public boolean execute(CommandSender sender, String currentAlias, String[] args) {
		if (!testPermission(sender))
			return true;

		String message = "&6This server is running &fWindSpigot &6version &f(MC: 1.8.8) &6(Implementing API version &f1.8.8-R2-SNAPSHOT)";
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
		Validate.notNull(sender, "Sender cannot be null");
		Validate.notNull(args, "Arguments cannot be null");
		Validate.notNull(alias, "Alias cannot be null");
		return ImmutableList.of();
	}
}
