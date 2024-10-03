package io.github.derechtepilz.updatableconfig;

public record CommentedConfigOption<T>(String[] comment, T option) {
}
