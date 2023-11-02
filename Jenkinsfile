pipeline {
    agent any

    environment {
            DB_URL = credentials('DB_URL')
            DB_USERNAME = credentials('DB_USERNAME')
            DB_PASSWORD = credentials('DB_PASSWORD')
            SOURCE_CODE_PATH = pwd()
            USER_CREDENTIALS = credentials('college.azurecr.io')
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
                       docker build -t college.azurecr.io/ca:latest .

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


                    sh '''
                       echo ${USER_CREDENTIALS_PSW} | docker login -u ${USER_CREDENTIALS_USR} --password-stdin college.azurecr.io
                       docker push college.azurecr.io/ca:latest
                       scp -i /var/lib/jenkins/workspace/college-jenkins_key.pem ROOT.war  azureuser@10.1.0.5:/tmp/ROOT.war
                       ssh -i /var/lib/jenkins/workspace/college-jenkins_key.pem azureuser@10.1.0.5 'mv /tmp/ROOT.war /opt/tomcat/webapps/'

                       ssh -i /var/lib/jenkins/workspace/college-jenkins_key.pem azureuser@10.1.0.5 "echo ${USER_CREDENTIALS_PSW} | docker login -u ${USER_CREDENTIALS_USR} --password-stdin college.azurecr.io"
                       ssh -i /var/lib/jenkins/workspace/college-jenkins_key.pem azureuser@10.1.0.5 '(docker ps -a --format {{.Names}} | grep app -w) && (docker stop app && docker rm app) || docker pull college.azurecr.io/ca '
                       sleep 15
                       ssh -i /var/lib/jenkins/workspace/college-jenkins_key.pem azureuser@10.1.0.5 'docker run -itd --add-host host.docker.internal:host-gateway -p 8081:8080 --name app college.azurecr.io/ca'

                    '''

            }
          }



    }


}
