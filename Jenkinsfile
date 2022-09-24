pipeline { 
    agent any
    tools {
        maven: 'maven-3.6.3'
    }
    stages { 
        stage('Build') { 
            steps { 
               echo 'Building using mvn'
               sh 'mvn clean package'
            }
        }
    }
}
