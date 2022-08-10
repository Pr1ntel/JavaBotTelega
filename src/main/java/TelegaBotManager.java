import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegaBotManager extends TelegramLongPollingBot {

    private Dbjokes dbjokes;

    public TelegaBotManager() {
        dbjokes = new Dbjokes();
    }

    @Override
    public String getBotUsername() {
        return "JavaTrueTest68bot";
    }

    @Override
    public String getBotToken() {
        return "5526029728:AAFxydKdJ9GDVm4rYdErIRlg9Wdmn7SISHU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {


            String chatId = update.getMessage().getChatId().toString();
            String recievdText =   update.getMessage().getText();

            String responseText = "";

            if(recievdText.equals("/joke")){
                responseText = dbjokes.getRandomJoke();
            }else {
                responseText = "Ошибка. Команда не распознана.ВВедите /joke для получения шутки";
            }

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(responseText);

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
