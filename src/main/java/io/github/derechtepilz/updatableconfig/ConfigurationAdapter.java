package io.github.derechtepilz.updatableconfig;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.io.File;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Defines a generic config system wrapper for use in the {@link io.github.derechtepilz.updatableconfig.ConfigGenerator}.
 *
 * @param <Configuration> The type of the underlying configuration system used.
 */
@NullMarked
public interface ConfigurationAdapter<Configuration> {

	/**
	 * Sets a config value
	 *
	 * @param key The config option path
	 * @param value The value associated with the path
	 */
	void setValue(String key, Object value);

	/**
	 * Sets a comment for a config option
	 *
	 * @param key The config option path
	 * @param comment The comment associated with the path
	 */
	void setComment(String key, String[] comment);

	/**
	 * Gets the config value for a given path
	 *
	 * @param key The config option path associated with the value
	 * @return The value at the given path
	 */
	Object getValue(String key);

	/**
	 * Gets the comment for a given path
	 *
	 * @param key The config option path associated with the comment
	 * @return The comment at the given path
	 */
	String[] getComment(String key);

	/**
	 * Returns a set containing all keys present in the underlying config
	 *
	 * @return a set containing all keys present in the underlying config
	 */
	Set<String> getKeys();

	/**
	 * Checks if a config option path is present in the underlying config
	 *
	 * @param key The config option path to check
	 * @return {@code true} if the underlying configuration contains the given key, {@code false} otherwise
	 */
	boolean contains(String key);

	/**
	 * Tries to create one or multiple configuration section(s) if the config option path contains a period (.)
	 * while also setting comments for the sections as specified in a {@link io.github.derechtepilz.updatableconfig.DefaultConfig} object
	 * in the {@link DefaultConfig#getAllSections()} method if a comment is available.
	 *
	 * @param key The config option path that might or might not contain a period (.) that indicates that sections need to be created
	 * @param defaultConfiguration A {@link io.github.derechtepilz.updatableconfig.DefaultConfig} object to set section comments if available
	 */
	void tryCreateSection(String key, DefaultConfig defaultConfiguration);

	/**
	 * Performs additional final operations on the finished generated configuration if necessary.
	 *
	 * @return this {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter} object
	 */
	ConfigurationAdapter<Configuration> complete();

	/**
	 * Returns the underlying configuration object wrapped by this {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter}
	 *
	 * @return the underlying configuration object wrapped by this {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter}
	 */
	Configuration config();

	/**
	 * Creates a new {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter} using default, non-modified values
	 *
	 * @return a new {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter} using default, non-modified values
	 */
	ConfigurationAdapter<Configuration> createNew();

	/**
	 * Saves the generated config wrapped by this {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter}.
	 *
	 * @param directory The directory in which the config will be stored
	 * @param configFile The file to which the configuration will be written
	 * @param logger The {@link java.util.logging.Logger} to use when logging errors. May be {@code null} of logging is
	 *               not necessary or unwanted.
	 */
	void saveDefaultConfig(File directory, File configFile, @Nullable Logger logger);

}
