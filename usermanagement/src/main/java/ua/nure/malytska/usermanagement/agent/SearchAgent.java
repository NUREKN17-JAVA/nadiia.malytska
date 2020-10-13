package ua.nure.malytska.usermanagement.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ua.nure.malytska.usermanagement.db.DAOFactory;
import ua.nure.malytska.usermanagement.db.DatabaseException;

import java.util.Collection;

public class SearchAgent extends Agent {
    private AID[] aids;
    private SearchGUI gui = null;

    protected void setup() {
        super.setup();
        System.out.println(getAID().getName() + " started");

        gui = new SearchGUI(this);
        gui.setVisible(true);

        DFAgentDescription description = new DFAgentDescription();
        description.setName(getAID());
        ServiceDescription serviceDescr = new ServiceDescription();
        serviceDescr.setName("JADE-searching");
        serviceDescr.setType("searching");
        description.addServices(serviceDescr);
        try {
            DFService.register(this, description);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        addBehaviour(new TickerBehaviour(this, 60000) {

            protected void onTick() {
                DFAgentDescription agentDescription = new DFAgentDescription();
                ServiceDescription serviceDescription = new ServiceDescription();
                serviceDescription.setType("searching");
                agentDescription.addServices(serviceDescription);
                try {
                    DFAgentDescription[] descriptions = DFService
                            .search(myAgent, agentDescription);
                    aids = new AID[descriptions.length];
                    for (int i = 0; i < descriptions.length; i++) {
                        DFAgentDescription d = descriptions[i];
                        aids[i] = d.getName();
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        addBehaviour(new RequestServer());
    }

    protected void takeDown() {
        System.out.println(getAID().getName() + " terminated");
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        gui.setVisible(false);
        gui.dispose();
        super.takeDown();
    }

    public void search(String firstName, String lastName)
            throws SearchException {
        try {
            Collection users = DAOFactory.getInstance().getUserDao()
                    .find(firstName, lastName);
            if (users.size() > 0) {
                ShowUsers(users);
            } else {
                addBehaviour(
                        new SearchRequestBehaviour(aids, firstName, lastName));
            }
        } catch (DatabaseException e) {
            throw new SearchException(e);
        }
    }

    public void ShowUsers(Collection users) {
        gui.addUsers(users);
    }
}