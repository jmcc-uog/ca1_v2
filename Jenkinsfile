pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your GitHub repository
                git credentialsId: 'your-git-credentials-id',
                    url: 'https://github.com/jmcc-uog/ca1_v2.git',
                    branch: 'main' // Specify the branch you want to build
            }
        }

        stage('Clean') {
            steps {
                // Clean the project
                sh 'mvn clean'
            }
        }

        stage('Build') {
            steps {
                // Build the project
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                // Run tests
                sh 'mvn test'
            }
        }
        stage('Package'){
            steps {
                //Run Package
                sh 'mvn package'
            }
        }
         stage('deploy') {
              def resourceGroup = 'college-jenkins_group'
              def webAppName = 'jmcc'
              // login Azure
              withCredentials([usernamePassword(credentialsId: '<service_princial>', passwordVariable: 'AZURE_CLIENT_SECRET', usernameVariable: 'AZURE_CLIENT_ID')]) {
               sh '''
                  az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID
                  az account set -s $AZURE_SUBSCRIPTION_ID
                '''
              }
              // get publish settings
              def pubProfilesJson = sh script: "az webapp deployment list-publishing-profiles -g $resourceGroup -n $webAppName", returnStdout: true
              def ftpProfile = getFtpPublishProfile pubProfilesJson
              // upload package
              sh "curl -T target/jmccabepetition-0.0.1-SNAPSHOT.war $ftpProfile.url/webapps/ROOT.war -u '$ftpProfile.username:$ftpProfile.password'"
              // log out
              sh 'az logout'
            }


    }


}
