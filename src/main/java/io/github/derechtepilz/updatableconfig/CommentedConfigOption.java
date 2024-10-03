package io.github.derechtepilz.updatableconfig;

import org.jspecify.annotations.NullMarked;

/**
 * Defines a configuration option with an associated comment and a default value.
 *
 * @param comment The comment for this section. Each entry is treated as a single line.
 * @param option The default value for this config option
 * @param <T> The type of the config option value
 */
@NullMarked
public record CommentedConfigOption<T>(String[] comment, T option) {
}
