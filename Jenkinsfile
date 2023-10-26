pipeline {
    agent any

    environment {
            DB_URL = credentials('DB_URL')
            DB_USERNAME = credentials('DB_USERNAME')
            DB_PASSWORD = credentials('DB_PASSWORD')
            SOURCE_CODE_PATH = pwd()
        }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your GitHub repository
                git credentialsId: 'your-git-credentials-id',
                    url: 'https://github.com/jmcc-uog/ca1_v2.git',
                    branch: 'main' // Specify the branch you want to build
            }
        }
        stage('Prepare') {
            steps {

                            // Update the application.properties file with the right test info parameter
                            sh '''
                                sed -i 's|spring.datasource.username=.*|spring.datasource.username='"$DB_USERNAME"'|g' ${SOURCE_CODE_PATH}/src/main/resources/application.properties
                                sed -i 's|spring.datasource.url=.*|spring.datasource.url='"$DB_URL"'|g' ${SOURCE_CODE_PATH}/src/main/resources/application.properties
                                sed -i 's|spring.datasource.password=.*|spring.datasource.password='"${DB_PASSWORD}"'|g' ${SOURCE_CODE_PATH}/src/main/resources/application.properties
                            '''

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
                sh '''
                       mvn clean package
                       mv target/*.war ROOT.war
                   '''
            }
        }

        stage('Approval') {

        	            steps {

        	      		// Create an Approval Button with a timeout of 15minutes.
        	                timeout(time: 60, unit: "MINUTES") {
        	                    input message: 'Do you want to approve the deployment?', ok: 'Yes'


        	                }

        	                echo "Initiating deployment"

        	            }


        }
        stage('Deploy') {
            steps {
                    azureWebAppPublish azureCredentialsId: '56dff281-d3ba-4338-b034-2aa23346aa9c',
                                       resourceGroup: 'college-jenkins_group', appName: 'jmcc',
                                       filePath: '*.war', sourceDirectory: 'target', targetDirectory: 'webapps'
             }
          }



    }


}
