package com.github.mavenanchor;



import com.intellij.openapi.project.Project;

import com.intellij.openapi.startup.ProjectActivity;

import kotlin.Unit;

import kotlin.coroutines.Continuation;

import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

import org.jetbrains.idea.maven.project.MavenProjectsManager;

import org.jetbrains.idea.maven.project.MavenGeneralSettings;



import java.io.File;



public class MavenSetupActivity implements ProjectActivity {



    @Nullable

    @Override

    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {

// Change these strings to your actual local paths

        String myCustomMavenHome = "C:/Program Files/Maven/apache-maven-3.9.6";

        String myCustomSettingsXml = "C:/Users/IsraelPinto/.m2/custom-settings.xml";



        MavenProjectsManager manager = MavenProjectsManager.getInstance(project);



        if (manager != null) {

            MavenGeneralSettings settings = manager.getGeneralSettings();



// Set the Maven Home path

            settings.setMavenHome(myCustomMavenHome);



// Set the User Settings File path

            settings.setUserSettingsFile(myCustomSettingsXml);



            System.out.println("MavenAnchor: Successfully applied paths to " + project.getName());

        }



        return Unit.INSTANCE;

    }

}