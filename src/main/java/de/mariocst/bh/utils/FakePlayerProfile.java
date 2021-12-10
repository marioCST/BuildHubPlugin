package de.mariocst.bh.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FakePlayerProfile implements PlayerProfile {
    private String name;

    public FakePlayerProfile(String name) {
        this.name = name;
    }

    @Override
    public @Nullable String getName() {
        return name;
    }

    @Override
    public @NotNull String setName(@Nullable String name) {
        this.name = name;
        assert name != null;
        return name;
    }

    @Override
    public @Nullable UUID getId() {
        return null;
    }

    @Override
    public @Nullable UUID setId(@Nullable UUID uuid) {
        return null;
    }

    @Override
    public @NotNull Set<ProfileProperty> getProperties() {
        Set<ProfileProperty> set = new HashSet<>();
        set.add(new ProfileProperty("null", "null"));
        return set;
    }

    @Override
    public boolean hasProperty(@Nullable String property) {
        return false;
    }

    @Override
    public void setProperty(@NotNull ProfileProperty property) {

    }

    @Override
    public void setProperties(@NotNull Collection<ProfileProperty> properties) {

    }

    @Override
    public boolean removeProperty(@Nullable String property) {
        return false;
    }

    @Override
    public void clearProperties() {

    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean completeFromCache() {
        return false;
    }

    @Override
    public boolean completeFromCache(boolean onlineMode) {
        return false;
    }

    @Override
    public boolean completeFromCache(boolean lookupUUID, boolean onlineMode) {
        return false;
    }

    @Override
    public boolean complete(boolean textures) {
        return false;
    }

    @Override
    public boolean complete(boolean textures, boolean onlineMode) {
        return false;
    }
}
