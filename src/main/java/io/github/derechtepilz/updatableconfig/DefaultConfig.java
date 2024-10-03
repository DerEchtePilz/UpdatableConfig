package io.github.derechtepilz.updatableconfig;

import org.jspecify.annotations.NullMarked;

import java.util.Map;

/**
 * The {@code DefaultConfig} interface defines a contract for retrieving configuration options and sections.
 * It provides methods to access all configuration options and sections in the form of maps.
 * <p>
 * Each configuration option is stored as a {@link CommentedConfigOption} and each section as a {@link CommentedSection}.
 * These can be retrieved using the respective methods {@link #getAllOptions()} and {@link #getAllSections()}.
 * <p>
 * The {@code DefaultConfig} interface is commonly used in settings where configuration data is divided into
 * sections and individual options, possibly with comments attached for documentation or readability.
 */
@NullMarked
public interface DefaultConfig {

	/**
	 * Retrieves all configuration options.
	 * <p>
	 * The returned map contains configuration options, where the key is the option name (represented as a {@code String}),
	 * and the value is a {@link CommentedConfigOption} object, which may also include associated comments.
	 *
	 * @return a {@code Map<String, CommentedConfigOption<?>>} containing all configuration options.
	 */
	Map<String, CommentedConfigOption<?>> getAllOptions();

	/**
	 * Retrieves all configuration sections.
	 * <p>
	 * The returned map contains configuration sections, where the key is the section name (represented as a {@code String}),
	 * and the value is a {@link CommentedSection} object, which may include comments and options related to that section.
	 *
	 * @return a {@code Map<String, CommentedSection>} containing all configuration sections.
	 */
	Map<String, CommentedSection> getAllSections();

}
