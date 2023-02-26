package org.sharpmc.ruins.api;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

public interface Registry<T> {

    void add(@NotNull String key, @NotNull T type);

    void remove(@NotNull String key);

    @NotNull Optional<T> get(@NotNull String key);

    @NotNull Collection<String> keys();

    @NotNull Collection<T> value();
}
