package ua.nure.malytska.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest extends JFCTestCase {

    private MainFrame mainFrame;
    private static final String BROWSE_PANEL = "browsePanel";
    private static final String USER_TABLE = "userTable";
    private static final String ADD_BUTTON = "addButton";
    private static final String EDIT_BUTTON = "editButton";
    private static final String DELETE_BUTTON = "deleteButton";
    private static final String DETAILS_BUTTON = "detailsButton";

    public void setUp() throws Exception {
        super.setUp();
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public void tearDown() throws Exception {
        mainFrame.setVisible(false);
        getHelper();
        TestHelper.cleanUp(
                this);//очистит все невыполненные действия, чтобы они не повлияли на следующий тест
        super.tearDown();
    }

    public void testBrowseControls() { //тест ищет компоненты
        find(JPanel.class, BROWSE_PANEL);
        find(JTable.class, USER_TABLE);
        find(JButton.class, ADD_BUTTON);
        find(JButton.class, EDIT_BUTTON);
        find(JButton.class, DELETE_BUTTON);
        find(JButton.class, DETAILS_BUTTON);
    }

    protected Component find(Class<?> componentClass,
            String componentName) {//получем ссылку на класс и его имя
        NamedComponentFinder finder = new NamedComponentFinder(componentClass,
                componentName);
        finder.setWait(
                0);//количество милисекунд, которое должно пройти до появления каких-то компонентов
        Component component = finder.find(mainFrame,
                0);//будем искать от текцщего контейнера и во всех доерних подконтейнеров, вложенных в него
        assertNotNull("Could not find component '" + componentName + "'",
                component);
        return component;
    }
}