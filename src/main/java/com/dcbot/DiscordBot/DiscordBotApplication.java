package com.dcbot.DiscordBot;

import javax.security.auth.login.LoginException;

import com.dcbot.DiscordBot.entities.Bot;
import com.dcbot.DiscordBot.entities.BotMessage;
import com.dcbot.DiscordBot.entities.Tokens;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class DiscordBotApplication {

	public static void main(String[] args) throws LoginException{
	
		JDABuilder jdaBuilder = JDABuilder.createDefault(Tokens.dctoken);
		
		jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING);
		jdaBuilder.addEventListeners(new Bot(), new BotMessage()).build();

	}

}
