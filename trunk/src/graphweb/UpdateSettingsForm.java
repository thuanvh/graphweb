package graphweb;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 24 janv. 2010
 * Time: 20:42:16
 * To change this template use File | Settings | File Templates.
 */
public class UpdateSettingsForm extends ServletService {
    private void loadConfiguration() {
        configuration = GraphConfigurationManager.getGraphConfiguration(context.getRealPath(GraphConfigurationManager.DATA_FILE));
    }

    @Override
    public String execute() throws Exception {
        loadConfiguration();
        return "success";
    }

    public GraphConfiguration configuration;

    public GraphConfiguration getConfiguration() {
        return configuration;
//        configuration.getRationOfCoordination();
    }

    public void setConfiguration(GraphConfiguration configuration) {
        this.configuration = configuration;
    }
}
