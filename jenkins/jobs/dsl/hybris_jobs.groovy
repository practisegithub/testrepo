// Folders
def workspaceFolderName = "${WORKSPACE_NAME}"
def projectFolderName = "${PROJECT_NAME}"


// Variables
#def hybrisEnvRepo = "hybris-env-project"
#def envFolderURL = "ssh://jenkins@gerrit:29418/${PROJECT_NAME}/" + hybrisEnvRepo

#def hybrisCodeRepo = "hybris-code-demo"
#def codeRepoURL = "ssh://jenkins@gerrit:29418/${PROJECT_NAME}/" + hybrisCodeRepo

#def hybrisDockerfile = "hybris-dockerfile"
#def dockerfileRepoURL = "ssh://jenkins@gerrit:29418/${PROJECT_NAME}/" + hybrisDockerfile

// Jobs
def deployJob = freeStyleJob(projectFolderName + "/Hybris-Deploy-Application")
#def buildJob = freeStyleJob(projectFolderName + "/Hybris-Build-Application")

// Views
def HybrisBuildPipelineView = buildPipelineView(projectFolderName + "/Hybris_Deployment")
HybrisBuildPipelineView.with{
    title('Hybris Deployment Pipeline')
    displayedBuilds(5)
    selectedJob(projectFolderName + "/Hybris-Build-Application")
    showPipelineParameters()
    showPipelineDefinitionHeader()
    refreshFrequency(5)
}

deployJob.with{
    description("This job deploys a sample Hybris application")
    logRotator {
    daysToKeep(30)
    numToKeep(10)
    }
    label("master")
    
    steps {
        environmentVariables {
            propertiesFile('''${ENV_NAME}.properties''')
        }
        shell('''#!/bin/bash

echo -e "\\n\\n******* This is a Test *******\\n" 
echo -e "\\n\\n******* This is a Test for Cartridge *******\\n" 

''')
    }
}