pipeline {

	environment {
		imagename = "ianhunterpersonal/bday-gifts"
		registryCredential = 'ianhunterpersonal_DockerHub'
		dockerImage = ''
	}

    agent any
    stages { 
        stage('Build') { 
            steps { 
               echo 'Building using mvn'
               sh 'mvn clean compile test'
            }
        }
        stage('Building image') {
		  steps{
			script {
				dockerImage = docker.build imagename
				echo "${dockerImage}"
			}
		  }
		}
    }
    
}
