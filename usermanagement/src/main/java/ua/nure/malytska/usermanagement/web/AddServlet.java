package ua.nure.malytska.usermanagement.web;

import ua.nure.malytska.usermanagement.db.DAOFactory;
import ua.nure.malytska.usermanagement.db.DatabaseException;
import ua.nure.malytska.usermanagement.entity.SystemUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends EditServlet {

    @Override
    protected void processUser(SystemUser user) throws DatabaseException {
        DAOFactory.getInstance().getUserDao().create(user);
    }

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
}
