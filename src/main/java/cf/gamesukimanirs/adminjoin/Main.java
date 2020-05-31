package cf.gamesukimanirs.adminjoin;

//java
import java.io.File;
import java.util.LinkedHashMap;
//event
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.EventHandler;
//command
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.Command;
//config
import cn.nukkit.utils.Config;
//base
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener{

	private String PluginName = "AdminJoin for Nukkit";
	private String version = "1.0.0";
	private Config cfg;

	@SuppressWarnings({ "deprecation", "serial" })
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info(PluginName + "-v" + version + "を読み込みました。作者:gamesukimanIRS");
    	getLogger().warning("製作者偽りと二次配布、友人用を除いた他人用の改造、改造配布、プラグインの横流し、悪用等はおやめ下さい。");
    	getLogger().warning("また、このプラグインを使用して発生した如何なる問題に対しての責任は負いかねます。");
    	getLogger().info("このプラグインを使用する際はどこかにプラグイン名「" + PluginName + "」と作者名「gamesukimanIRS」を記載して頂けると光栄です。");
    	getDataFolder().mkdir();
		cfg = new Config(new File(getDataFolder(), "messages.yml"), Config.YAML,
				new LinkedHashMap<String,Object>() {{
			put("#OPがログインした時のメッセージ。{name}はOPの名前に置き換えられます。","");
			put("opjoinmessage","§a{name}(OP)§e が世界にやってきました");
			put("#OPがログアウトする時のメッセージ。{name}はOPの名前に置換。","");
			put("opquitmessage","§a{name}(OP)§e が世界を去りました");
		}});
		if(!(cfg.get("opjoinmessage") instanceof String)) {cfg.set("opjoinmessage", "§a{name}(OP)§e が世界にやってきました");cfg.save();}
		if(!(cfg.get("opquitmessage") instanceof String)) {cfg.set("opquitmessage", "§a{name}(OP)§e が世界を去りました");cfg.save();}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(e.getPlayer().isOp()) {
			String msg = (String)cfg.get("opjoinmessage");
			msg = msg.replaceAll("\\{name\\}", e.getPlayer().getName());
			e.setJoinMessage(msg);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if(e.getPlayer().isOp()) {
			String msg = (String)cfg.get("opquitmessage");
			msg = msg.replaceAll("\\{name\\}", e.getPlayer().getName());
			e.setQuitMessage(msg);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.isOp()) {
			sender.sendMessage("§a[AdminJoin]§cエラー:001 OP権限がありません");
			return true;
		}
		try {
			if(!(args[0] instanceof String)) {
				sender.sendMessage("§a[AdminJoin]§cエラー:002 メッセージ内容を入力してください。");
				return false;
			}
			switch (cmd.getName()) {
				case "setopjoin" :
					String msgj = String.join(" ",args);
					cfg.set("opjoinmessage",msgj);
					cfg.save();
					getLogger().info(sender.getName()+"によってOPログイン時のメッセージが「"+msgj+"」に変更されました");
					sender.sendMessage("§a[AdminJoin]§bOPログイン時のメッセージを設定しました。");
					return true;
				case "setopquit" :
					String msgq = String.join(" ",args);
					cfg.set("opquitmessage",msgq);
					cfg.save();
					getLogger().info(sender.getName()+"によってOPログアウト時のメッセージが「"+msgq+"」に変更されました");
					sender.sendMessage("§a[AdminJoin]§bOPログアウト時のメッセージを設定しました。");
					return true;
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			sender.sendMessage("§a[AdminJoin]§cエラー:002 メッセージ内容を入力してください。");
			return false;
		}
		return true;
	}
}