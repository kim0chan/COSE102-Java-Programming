# AnamWeatherBot
A simple chatbot on Telegram that reports on the weather in Anam.
Based on [**Rubenlagus's library for telegram chatbots**](https://github.com/rubenlagus/TelegramBots).

## Operation
It replies to the messages sent by the user. No matter the contents, it will report on the weather in Anam-dong. The weather information is based on [Naver Weather](https://weather.naver.com).

## Usage
### Creating A Bot
You can create a Telegram bot and get its token by **@BotFather**(more information on [here](https://core.telegram.org/bots)), sending a message, "/newbot".
```java
//MyAmazingBot.java
public String getBotUsername() {
		return "YourBotsName"; //Your bot's name
	}
	
	public String getBotToken() {
		return "123456789:BlahbLahBlAHblaHBlaah"; Your bot's token
	}
```
In this way, you can apply your bot.

### Functioning
You can modify the urls and the condition of the method 'execute(message)'. But since it uses web crawling method based on Jsoup library, you might have to modify the 'Document' part too. You can also change the 'generateSentence()' based on your experience.

### On Telegram
Add your bot in your friend list. While running the program, your bot will reply to your message.
