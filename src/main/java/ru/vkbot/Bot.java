package ru.vkbot;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;


public class Bot {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, IOException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Keyboard keyboard = new Keyboard();
        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("ОБНОВИТЬ").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        //line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Кто я?").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        allKey.add(line1);
        keyboard.setButtons(allKey);
        GroupActor actor = new GroupActor(, "");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        while (true){
            MessagesGetLongPollHistoryQuery historyQuery =  vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            Update.timer();
            if (!messages.isEmpty()){
                messages.forEach(message -> {
                    System.out.println(message.toString());
                    try {
                        if (message.getText().equals("Начать")){
                            vk.messages().send(actor).message("Напиши свою группу.").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().equals("Неделя")){
                            vk.messages().send(actor).message("Идет "+TimeTest.SemesterWeek()+" учебная неделя.").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if (message.getText().toUpperCase().matches("^[А-Я]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$")) {
                            Data data = new Data();
                            System.out.println(new Date().toString());
                            vk.messages().send(actor).message(data.find(message.getText().toUpperCase())+" Последние обновление в "+Update.timeUp).userId(message.getFromId()).randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                            System.out.println(new Date().toString());
                        }
                        else if (message.getText().toUpperCase().equals("ОБНОВИТЬ")){
                            Update.parsHtml();
                            vk.messages().send(actor).message("Обновлено в "+Update.timeUp).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else {
                            vk.messages().send(actor).message("Неверный формат группы.").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                    }
                    catch (ApiException | ClientException | IOException e) {e.printStackTrace();}
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }
}
