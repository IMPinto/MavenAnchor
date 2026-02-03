package com.github.mavenanchor;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service(Service.Level.PROJECT)
@State(
        name = "MavenAnchorSettings",
        storages = @Storage("MavenAnchor.xml")
)
public final class MavenAnchorSettings implements PersistentStateComponent<MavenAnchorSettings> {

    public String mavenHome = "";
    public String userSettings = "";

    public static MavenAnchorSettings getInstance(Project project) {
        return project.getService(MavenAnchorSettings.class);
    }

    @Nullable
    @Override
    public MavenAnchorSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull MavenAnchorSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}