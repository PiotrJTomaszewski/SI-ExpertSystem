package drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import gui.GuiWindow;

public class DroolsRun {
	public static final void main(String[] args) {
        GuiWindow.readJson("C:\\Users\\Szymek\\Desktop\\123\\SI-ExpertSystem\\src\\main\\resources\\data\\data.json");
		try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
//            kSession.insert(message);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}