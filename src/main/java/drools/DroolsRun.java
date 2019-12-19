package drools;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import gui.BookInfoWindow;
import gui.QuestionAndAnswerWindow;

public class DroolsRun {
	public static final void main(String[] args) {
		// Load JSON files
		URL resQA = DroolsRun.class.getResource("/data/QandA.json");
		File jsonFileQA;
		try {
			jsonFileQA = Paths.get(resQA.toURI()).toFile();
	        QuestionAndAnswerWindow.readJson(jsonFileQA.getAbsolutePath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		URL resBooks = DroolsRun.class.getResource("/data/BooksNames.json");
		File jsonFileBooks;
		try {
			jsonFileBooks = Paths.get(resBooks.toURI()).toFile();
	        BookInfoWindow.readJson(jsonFileBooks.getAbsolutePath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
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

