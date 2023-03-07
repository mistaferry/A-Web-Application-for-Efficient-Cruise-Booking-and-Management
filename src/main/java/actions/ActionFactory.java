package actions;

import actions.general.*;
import actions.customer.*;
import actions.admin.*;

import java.util.HashMap;
import java.util.Map;

public final class ActionFactory {
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        //admin
        ACTION_MAP.put("admin-get-user-cruises", new AdminGetUserCruisesAction());
        ACTION_MAP.put("change-payment-value", new ChangePaymentValueByAdminAction());
        ACTION_MAP.put("change-user-values-by-admin", new ChangeUserValuesByAdminAction());
        ACTION_MAP.put("get-user-info", new GetUserInfoAction());
        ACTION_MAP.put("view-all-users", new ViewAllUsersAction());
        ACTION_MAP.put("admin-view-all-cruises", new ViewCruises());
        ACTION_MAP.put("view-existing-data", new ViewExistingDataAction());

        //customer
        ACTION_MAP.put("get-order-form", new GetOrderForm());
        ACTION_MAP.put("place-order", new PlaceOrder());
        ACTION_MAP.put("view-user-cruises", new ViewUserCruisesAction());
        ACTION_MAP.put("view-cruises", new ViewCruiseCatalogAction());
        ACTION_MAP.put("edit-user-profile", new EditUserProfileAction());

        //general (common)
        ACTION_MAP.put("sign-in", new SignInAction());
        ACTION_MAP.put("register", new RegisterAction());
        ACTION_MAP.put("change-password", new ChangePasswordAction());

    }

    private ActionFactory(){ }

    public static ActionFactory getActionFactory(){
        return ACTION_FACTORY;
    }

    public static Action getAction(String action) {
        return ACTION_MAP.get(action);
    }
}