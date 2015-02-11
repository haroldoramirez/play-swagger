import com.wordnik.swagger.converter.ModelConverters;
import ignores.IgnoreConverter;
import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

    @Override
    public void beforeStart(Application app) {
        Logger.info("Registering custom converter");
        IgnoreConverter converter = new IgnoreConverter();
        ModelConverters.addConverter(new IgnoreConverter(), true);
    }

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}
