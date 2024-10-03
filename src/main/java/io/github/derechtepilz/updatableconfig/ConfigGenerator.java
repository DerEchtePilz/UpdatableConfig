package io.github.derechtepilz.updatableconfig;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NullUnmarked;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * The {@code ConfigGenerator} class defines a set of methods used to create and update configuration files.
 */
@NullMarked
public class ConfigGenerator {

	private final DefaultConfig defaultConfig;

	private ConfigGenerator(DefaultConfig defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

	/**
	 * Creates a new {@link io.github.derechtepilz.updatableconfig.ConfigGenerator} object.
	 *
	 * @param defaultConfig A {@link io.github.derechtepilz.updatableconfig.DefaultConfig} object that defines a set of config options for the config to generate.
	 * @return the newly created {@link io.github.derechtepilz.updatableconfig.ConfigGenerator} object with the given {@link io.github.derechtepilz.updatableconfig.DefaultConfig} object.
	 */
	public static ConfigGenerator createNew(DefaultConfig defaultConfig) {
		return new ConfigGenerator(defaultConfig);
	}

	/**
	 * Populates an empty configuration with the default configuration values that were provided when creating
	 * this {@link io.github.derechtepilz.updatableconfig.ConfigGenerator} object.
	 *
	 * @param adapter The {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter} object that holds the actual configuration
	 * @param <T> The type of the underlying configuration wrapped by the {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter}
	 */
	public <T> void populateDefaultConfig(ConfigurationAdapter<T> adapter) {
		for (Map.Entry<String, CommentedConfigOption<?>> commentedConfigOption : defaultConfig.getAllOptions().entrySet()) {
			adapter.tryCreateSection(commentedConfigOption.getKey(), defaultConfig);
			adapter.setValue(commentedConfigOption.getKey(), commentedConfigOption.getValue().option());
			adapter.setComment(commentedConfigOption.getKey(), commentedConfigOption.getValue().comment());
		}
		adapter.complete();
	}

	/**
	 * Updates an existing configuration.
	 * <p>
	 * Things that this method is able to update include:
	 * <ul>
	 *     <li>Adding new config options if they don't exist already</li>
	 *     <li>Removing existing config options if they are not listed in the {@link io.github.derechtepilz.updatableconfig.DefaultConfig} object passed into this class</li>
	 *     <li>Adding, removing and updating config option comments</li>
	 * </ul>
	 * <p>
	 * Existing config options are not touched by this method.
	 *
	 * @param existingConfig The existing configuration that is used as a basis to create a new, updated configuration if necessary
	 * @param <T> The type of the underlying configuration wrapped by the {@link io.github.derechtepilz.updatableconfig.ConfigurationAdapter}
	 * @return The updated configuration or {@code null}, if the config doesn't need to be updated
	 */
	public <T> @Nullable ConfigurationAdapter<T> generateWithNewValues(ConfigurationAdapter<T> existingConfig) {
		ConfigurationAdapter<T> updatedConfig = existingConfig.createNew();

		boolean shouldRemoveValues = shouldRemoveOptions(existingConfig);

		boolean wasConfigUpdated = false;
		for (Map.Entry<String, CommentedConfigOption<?>> commentedConfigOption : defaultConfig.getAllOptions().entrySet()) {
			String path = commentedConfigOption.getKey();

			// Update config option
			if (existingConfig.contains(path)) {
				updatedConfig.tryCreateSection(path, defaultConfig);
				updatedConfig.setValue(path, existingConfig.getValue(path));
			} else {
				wasConfigUpdated = true;
				updatedConfig.tryCreateSection(path, defaultConfig);
				updatedConfig.setValue(path, commentedConfigOption.getValue().option());
			}

			// Update config option comment
			String[] defaultComment = commentedConfigOption.getValue().comment();
			String[] configComment = existingConfig.getComment(path);

			if (!Arrays.equals(defaultComment, configComment)) {
				wasConfigUpdated = true;
			}

			updatedConfig.setComment(path, commentedConfigOption.getValue().comment());
		}
		for (Map.Entry<String, CommentedSection> commentedSection : defaultConfig.getAllSections().entrySet()) {
			String[] defaultComment = commentedSection.getValue().comment();
			String[] configComment = existingConfig.getComment(commentedSection.getKey());

			if (!Arrays.equals(defaultComment, configComment)) {
				wasConfigUpdated = true;
			}

			updatedConfig.setComment(commentedSection.getKey(), commentedSection.getValue().comment());
		}
		if (shouldRemoveValues) {
			wasConfigUpdated = true;
		}
		return (wasConfigUpdated) ? updatedConfig.complete() : null;
	}

	private <T> boolean shouldRemoveOptions(ConfigurationAdapter<T> config) {
		Set<String> configOptions = config.getKeys();
		Set<String> defaultConfigOptions = defaultConfig.getAllOptions().keySet();

		return !defaultConfigOptions.containsAll(configOptions);
	}

}
