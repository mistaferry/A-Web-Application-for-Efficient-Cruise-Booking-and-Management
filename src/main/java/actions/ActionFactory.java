package actions;
import actions.general.*;

import java.util.*;

public final class ActionFactory {
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        ACTION_MAP.put("sign-in", new SignInAction());
//        ACTION_MAP.put("catalog", new ViewCatalogAction());
//        ACTION_MAP.put("view-cruises", new ViewCruises());
        ACTION_MAP.put("view-cruises", new ViewCruiseCatalogAction());
        ACTION_MAP.put("edit-user-profile", new EditUserProfileAction());
        ACTION_MAP.put("change-password", new ChangePasswordAction());
        ACTION_MAP.put("create-order", new CreateOrderAction());
        ACTION_MAP.put("register", new RegisterAction());
    }

    private ActionFactory(){ }

    public static ActionFactory getActionFactory(){
        return ACTION_FACTORY;
    }

    public static Action getAction(String action) {
        return ACTION_MAP.get(action);
    }
}