package com.github.mavenanchor;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MavenAnchorConfigurable implements Configurable {
    private final Project project;
    private final TextFieldWithBrowseButton mavenHomeField = new TextFieldWithBrowseButton();
    private final TextFieldWithBrowseButton userSettingsField = new TextFieldWithBrowseButton();

    public MavenAnchorConfigurable(Project project) {
        this.project = project;
    }

    @Override
    public String getDisplayName() {
        return "Maven Anchor Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        mavenHomeField.addBrowseFolderListener("Select Maven Home", null, project, null);
        userSettingsField.addBrowseFolderListener("Select Settings.xml", null, project, null);

        return FormBuilder.createFormBuilder()
                .addLabeledComponent("Maven home path:", mavenHomeField)
                .addLabeledComponent("User settings file:", userSettingsField)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    @Override
    public boolean isModified() {
        MavenAnchorSettings settings = MavenAnchorSettings.getInstance(project);
        return !mavenHomeField.getText().equals(settings.mavenHome) ||
                !userSettingsField.getText().equals(settings.userSettings);
    }

    @Override
    public void apply() {
        MavenAnchorSettings settings = MavenAnchorSettings.getInstance(project);
        settings.mavenHome = mavenHomeField.getText();
        settings.userSettings = userSettingsField.getText();
    }

    @Override
    public void reset() {
        MavenAnchorSettings settings = MavenAnchorSettings.getInstance(project);
        mavenHomeField.setText(settings.mavenHome);
        userSettingsField.setText(settings.userSettings);
    }
}