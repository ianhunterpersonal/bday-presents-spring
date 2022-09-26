pipeline {

	environment {
		imagename = "ianhunterpersonal/bday-gifts"
		registryCredential = 'ianhunterpersonal_DockerHub'
		dockerImage = ''
	}

    agent any
    stages { 
        stage('Clean') { 
            steps { 
               sh 'echo "Cleaning..."; mvn clean'
            }
        }
        stage('Compile') { 
            steps { 
               sh 'echo "Compile..."; mvn compile'
            }
        }
        stage('Test') { 
            steps { 
               sh 'echo "Testing..."; mvn test'
            }
        }
        stage('Package') { 
            steps { 
               sh 'echo "Testing..."; mvn package'
            }
        }

        stage('Building image') {
			  steps {
			   sh 'echo "Building image...."'
				script {
					dockerImage = docker.build imagename
					echo "${dockerImage}"
				}
			 }
		  }
		  
	    stage('Deploy Image') {
	      steps{
	        script {
	          docker.withRegistry( '', registryCredential ) {
	            dockerImage.push("$BUILD_NUMBER")
	             dockerImage.push('latest')
	
	          }
	        }
	      }
	    }
	    
	    stage('Remove Unused docker image') {
	      steps{
	         sh "docker rmi $imagename:$BUILD_NUMBER"
	         sh "docker rmi $imagename:latest"
	      }
	    }        
    
}
}
