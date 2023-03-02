package tag;

import dto.OrderDTO;
import model.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.ArrayList;
import java.util.Iterator;

public class OrderDetails extends BodyTagSupport {
    private OrderDTO order;
    private ArrayList<OrderDTO> orders;
    Iterator<OrderDTO> iterator;

    public ArrayList<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderDTO> orders) {
        this.orders = orders;
        iterator = orders.iterator();
    }

    @Override
    public int doStartTag() throws JspException {
        if (iterator.hasNext()) {
            order = iterator.next();
            pageContext.setAttribute("shipName", order.getCruise().getShip().getName());
            pageContext.setAttribute("orderStart", order.getCruise().getStartDate());
            pageContext.setAttribute("duration", order.getCruise().getDuration());
            pageContext.setAttribute("price", order.getCruise().getPrice());
            pageContext.setAttribute("bookingDate", order.getDateOfRegistration());
            pageContext.setAttribute("paymentStatus", order.isPaid());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        if (iterator.hasNext()) {
            order = iterator.next();
            pageContext.setAttribute("shipName", order.getCruise().getShip().getName());
            pageContext.setAttribute("orderStart", order.getCruise().getStartDate());
            pageContext.setAttribute("duration", order.getCruise().getDuration());
            pageContext.setAttribute("price", order.getCruise().getPrice());
            pageContext.setAttribute("bookingDate", order.getDateOfRegistration());
            pageContext.setAttribute("paymentStatus", order.isPaid());
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }

    }
}
