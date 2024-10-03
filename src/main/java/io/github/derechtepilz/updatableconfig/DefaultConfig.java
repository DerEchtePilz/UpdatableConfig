package io.github.derechtepilz.updatableconfig;

import java.util.Map;

public interface DefaultConfig {

	Map<String, CommentedConfigOption<?>> getAllOptions();

	Map<String, CommentedSection> getAllSections();

}
