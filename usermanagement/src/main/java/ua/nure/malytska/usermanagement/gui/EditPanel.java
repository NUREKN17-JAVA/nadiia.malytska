package ua.nure.malytska.usermanagement.gui;

import ua.nure.malytska.usermanagement.db.DatabaseException;
import ua.nure.malytska.usermanagement.entity.SystemUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class EditPanel extends AddPanel {
    private static final long serialVersionUID = -3719802058751424289L;

    private SystemUser user;

    public EditPanel(MainFrame parent) {
        super(parent);
        setName("editPanel");
    }

    protected void doAction(ActionEvent e) throws ParseException {
        System.out.println(user);
        if ("ok".equalsIgnoreCase(e.getActionCommand())) {
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
            try {
                Date date = format.parse(getDateOfBirthField().getText());
                user.setDateOfBirth(date);
            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                throw e1;
            }
            try {
                parent.getUserDao().update(user);
            } catch (DatabaseException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setUser(SystemUser user) {
        DateFormat format = DateFormat.getDateInstance();
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getLastNameField().setText(user.getLastName());
        getDateOfBirthField().setText(format.format(user.getDateOfBirth()));
    }
}
