package actions;
import actions.general.SignInAction;
import actions.general.ViewCatalogAction;

import java.util.*;

public final class ActionFactory {
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        ACTION_MAP.put("sign-in", new SignInAction());
        ACTION_MAP.put("catalog", new ViewCatalogAction());
    }

    private ActionFactory(){ }

    public static ActionFactory getActionFactory(){
        return ACTION_FACTORY;
    }

    public static Action getAction(String action) {
        return ACTION_MAP.get(action);
    }
}