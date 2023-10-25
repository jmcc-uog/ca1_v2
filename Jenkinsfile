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
                sh 'mvn package'
            }
        }



    }


}
