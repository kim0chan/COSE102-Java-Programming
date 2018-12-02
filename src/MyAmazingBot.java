import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyAmazingBot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage() && update.getMessage().hasText()) {
			SendMessage message = new SendMessage()
					.setChatId(update.getMessage().getChatId())
					.setText(update.getMessage().getText());
			try {
				execute(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String getBotUsername() {
		return "JansoriBot";
	}
	
	public String getBotToken() {
		return "734999045:AAG4XUexxr7ImZMwIU7wS7KTM45SGtnrKCc";
	}
	
	/*public getWeatherInformation() {
		
	}*/
 }