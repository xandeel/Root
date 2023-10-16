package org.spigotmc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldServer;

public class TicksPerSecondCommand extends Command {

	public TicksPerSecondCommand(String name) {
		super(name);
		this.description = "Gets the current ticks per second for the server";
		this.usageMessage = "/tps";
		this.setPermission("bukkit.command.tps");
	}

	@Override
	public boolean execute(CommandSender sender, String currentAlias, String[] args) {
		if (!testPermission(sender)) {
			return true;
		}

		// PaperSpigot start - Further improve tick handling
		double[] tps = org.bukkit.Bukkit.spigot().getTPS();
		String[] tpsAvg = new String[tps.length];

		for (int i = 0; i < tps.length; i++) {
			tpsAvg[i] = format(tps[i]);
		}
		
		// WindSpigot - more detailed tps cmd

		StringBuilder sb = new StringBuilder();

		final Runtime runtime = Runtime.getRuntime();

		double usedMemory = runtime.totalMemory() - runtime.freeMemory();
		double maxMemory = runtime.maxMemory();
		double freeMemory = maxMemory - usedMemory;

		int totalPlayers = 0;
		int totalEntities = 0;
		int totalChunks = 0;
		int tileEntityCount = 0;

		for (WorldServer server : MinecraftServer.getServer().worlds) {
			totalPlayers += server.players.size();
			totalChunks += server.chunkProviderServer.chunks.size();
			totalEntities += server.entityList.size();
			tileEntityCount = tileEntityCount + server.tileEntityList.size();
		}


		sb.append(ChatColor.GOLD).append("TPS from last 5s, 1m, 5m, 15m").append(ChatColor.GRAY).append(": ").append(org.apache.commons.lang.StringUtils.join(tpsAvg, ", ")).append("\n");
		sb.append(ChatColor.GOLD).append("Full Tick").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN).append(Math.round(MinecraftServer.getServer().getLastMspt() * 100.0) / 100.0).append(" ms").append("\n");
		sb.append(ChatColor.GOLD).append("Online Players").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN).append(totalPlayers).append("\n")
				.append(ChatColor.GOLD).append("Total Entities").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN).append(totalEntities).append("\n")
				.append(ChatColor.GOLD).append("Total Tile Entities").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN).append(tileEntityCount).append("\n")
				.append(ChatColor.GOLD).append("Chunks").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN).append(totalChunks).append("\n");
		sb.append(ChatColor.GOLD).append("Active Threads").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN).append(Thread.activeCount()).append("\n")
				.append(ChatColor.GOLD).append("Daemon Threads").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN)
				.append(Thread.getAllStackTraces().keySet().stream().filter(Thread::isDaemon).count())
				.append("\n");
		sb.append(ChatColor.GOLD).append("Memory Usage").append(ChatColor.GRAY).append(": ").append(ChatColor.GREEN)
				.append(formatMem(usedMemory)).append("/").append(formatMem(maxMemory))
				.append(" MB ").append("(").append(formatMem(freeMemory))
				.append(" MB free)");

		sender.sendMessage(sb.toString());

		return true;
	}

	private static String format(double tps) // PaperSpigot - made static
	{
		return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
				+ ((tps > 21.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0); // Paper - only print * at 21, we commonly peak to 20.02 as the tick sleep is not accurate enough, stop the noise
	}
	private static String formatMem(double mem) {
		return ChatColor.GREEN.toString().toString() + Math.round(mem / 1024 / 1024);
	}
}
