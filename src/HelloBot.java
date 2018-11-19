import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class HelloBot extends AbilityBot {
  public static final String BOT_TOKEN = "734999045:AAG4XUexxr7ImZMwIU7wS7KTM45SGtnrKCc";
  public static final String BOT_USERNAME = "JansoriBot";

  public HelloBot() {
    super(BOT_TOKEN, BOT_USERNAME);
  }

  @Override
  public int creatorId() {
    return 655163129;
  }

  public Ability sayHelloWorld() {
    return Ability
        .builder()
        .name("hello")
        .info("says hello world!")
        .locality(ALL)
        .privacy(PUBLIC)
        .action(ctx -> silent.send("Hello world!", ctx.chatId()))
        .build();
    //test
  }
}