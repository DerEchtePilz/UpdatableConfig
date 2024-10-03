package io.github.derechtepilz.updatableconfig;

import java.io.File;
import java.util.Set;
import java.util.logging.Logger;

public interface ConfigurationAdapter<Configuration> {

	void setValue(String key, Object value);

	void setComment(String key, String[] comment);

	Object getValue(String key);

	String[] getComment(String key);

	Set<String> getKeys();

	boolean contains(String key);

	void tryCreateSection(String key, DefaultConfig defaultConfiguration);

	ConfigurationAdapter<Configuration> complete();

	Configuration config();

	ConfigurationAdapter<Configuration> createNew();

	void saveDefaultConfig(File directory, File configFile, Logger logger);

}
