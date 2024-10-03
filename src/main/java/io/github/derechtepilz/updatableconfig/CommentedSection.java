package io.github.derechtepilz.updatableconfig;

import org.jspecify.annotations.NullMarked;

/**
 * Defines a configuration section with an associated comment.
 *
 * @param comment The comment for this section. Each entry is treated as a single line.
 */
@NullMarked
public record CommentedSection(String[] comment) {
}
