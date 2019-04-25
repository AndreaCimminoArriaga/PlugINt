package plugint.core.factories;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlugINt {

	private List<ClassFactory> factories; // jar file, & pluginloader
	
	private Logger log = Logger.getLogger(PlugINt.class.getName());

	public PlugINt() {
		factories = new ArrayList<>();
	}
	
	// -- Methods to load jar files
	
	public void loadJarFromDirectory(String directory) {
		try {
			File folder = new File(directory);
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	loadJarFromDirectory(fileEntry.getCanonicalPath());
		        } else {
		            if(fileEntry.isFile() && fileEntry.getName().endsWith(".jar")) {
			            	ClassFactory factory = new ClassFactory(fileEntry.getCanonicalPath());
			        		factories.add(factory);
		            }
		        }
		    }
		}catch(Exception e) {
			log.severe("Error loading plugins form directory: "+directory);
		}
	}
	
	public void loadJarFile(String jarFile) {
		ClassFactory factory = new ClassFactory(jarFile);
		factories.add(factory);
	}
	
	
	// -- Methods to create classes and objects
	
	
	public Class<?> createClassByFullName(String classFullName){
		Class<?> clazz = null;
		int factoriesSize = factories.size();
		for(int index =0; index<factoriesSize; index++){
			ClassFactory factory = factories.get(index);
			clazz = factory.getClassByFullName(classFullName);
			if(clazz!=null)
				break;
		}
		
		return clazz;
	}
	
	public Class<?> createClassByName(String className){
		Class<?> clazz = null;
		int factoriesSize = factories.size();
		for(int index =0; index<factoriesSize; index++){
			ClassFactory factory = factories.get(index);
			clazz = factory.getClassByName(className);
			if(clazz!=null)
				break;
		}
		return clazz;
	}
	
	public Object createObject(String classFullName, Class<?>[] agumentType, Object[] arguments){
		Object object = null;
		int factoriesSize = factories.size();
		for(int index =0; index<factoriesSize; index++){
			ClassFactory factory = factories.get(index);
			Class<?> clazz = factory.getClassByFullName(classFullName);
			if(clazz!=null) {
				try {
					object = clazz.getConstructor(agumentType).newInstance(arguments);
					break;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return object;
	}
	
	

}
