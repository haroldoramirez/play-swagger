import com.wordnik.swagger.converter.ModelConverters;
import ignores.CustomSwaggerModelConverter;
import ignores.IgnoreConverterScala;
import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

    @Override
    public void beforeStart(Application app) {
        Logger.info("Registering custom converter");
        ModelConverters.addConverter(new IgnoreConverterScala(), true);
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
