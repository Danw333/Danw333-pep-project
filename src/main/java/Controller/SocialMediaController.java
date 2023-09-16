package Controller;

import org.mockito.internal.matchers.Null;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint",  this::exampleHandler);
        app.post("/register", this::registerAccountHandler);
        app.post("/messages", this::createMessageHandler);
        return app;

        
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    private void registerAccountHandler(Context context) {
       
        Account account = parseAccountFromRequestBody(context);

        
        accountService.registerAccount(account);

        
        context.json(account);
    }

    private void createMessageHandler(Context context) {
      
        Message message = parseMessageFromRequestBody(context);

        
        messageService.createMessage(message);

       
        context.json(message);
    }

    

    private Account parseAccountFromRequestBody(Context context) {
      
        return context.bodyAsClass(Account.class);
    }

    private Message parseMessageFromRequestBody(Context context) {
        return null;
       
    }
}

    

