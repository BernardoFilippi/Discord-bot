package com.dcbot.DiscordBot.entities;

import java.util.ArrayList;
import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotMessage extends ListenerAdapter{

	OpenAiService oas = new OpenAiService(Tokens.apiKey);
	MessageChannel channel;
	Message eventMessage;
	
	public void onMessageReceived(MessageReceivedEvent event) {
		super.onMessageReceived(event);

		//Leitura do chat e criação da resposta
		System.out.println(event.getMessage().getContentDisplay());
		if(event.getMessage().getContentDisplay().toString().contains("@Migua-GPT")) {
			List<ChatMessage> messages = new ArrayList<>();
			ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), Personality.personality + event.getMessage().getContentDisplay().toString());
			messages.add(userMessage);
				ChatCompletionRequest request = ChatCompletionRequest
						.builder()
						.model("gpt-3.5-turbo")
						.messages(messages)
						.build();
				
				event.getChannel().sendMessage(oas.createChatCompletion(request).getChoices().get(0).getMessage().getContent().toString()).queue();
			} 
		}
	
	}

