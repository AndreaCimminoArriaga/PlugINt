package plugint.core.factories;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String jarFile = "./plugins/plugin-0.0.1-SNAPSHOT.jar";
		String pluginName = "LocalFileConnectorPlugin";
		String interfaceName = "helio.framework.Connector";
		PlugINt plugint = new PlugINt();
		
		plugint.loadJarFromDirectory("./plugins/");
		Class<?>[] argumentType = {List.class};
		List<String> argumentsRaw = new ArrayList<>();
		argumentsRaw.add("./mappings/data/tabla-parametros-1.csv");
		Object[] arguments = {argumentsRaw};
		String name = plugint.createObjectFromName(pluginName, argumentType, arguments).getClass().getName();
		System.out.println(name);
		
	}

}
